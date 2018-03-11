$(function() {

	// 加载顶部常用菜单栏 只是服务于header.jsp
	document.getElementById("childNav").innerHTML = '<div class="wrap">'
			+ '<ul class="clearfix" id="topNav"> </ul></div>'
})
//当ajax菜鸟哥后台读入数据时，封装的方法显示商品信息
function productViewAppend(whichTag, data) {
	for (var i = 0; i < data.length; i++) {
		var productViewCons = '<li>'
				+ '<dl>'
				+ '<dt><a href="ProductServlet?param=productView&EPId='
				+ data[i].EPId
				+ '" target="_blank"><img src='
				+ data[i].EPFile
				+ ' /></a></dt>'
				+ '<dd class="title"><a href="ProductServlet?param=productView&EPId='
				+ data[i].EPId + '"target="_blank">' + data[i].EPName
				+ '</a></dd>' + '<dd class="price">' + data[i].EPPrice
				+ '</dd>' + '</dl></li>';
		$(whichTag).append(productViewCons);
	}
}
// ajax代码
$(function() {
	// 定义常量：

	// 读取特价商品
	$.ajax({
		url : 'ProductServlet',
		type : 'post',
		data : {
			param : 'specialProduct',
		},
		dataType : 'json',
		success : function(data) {
			productViewAppend("#special_price", data)
		}

	})
	// 加载热卖商品
	$.ajax({
		url : 'ProductServlet',
		type : 'post',
		data : {
			param : 'hotProduct',
		},
		dataType : 'json',
		success : function(data) {
			// 从前端传来一个json里面是商品的所有信息。然后使用jquery读出来

			// 这里data应该是一个list集合
			productViewAppend("#hotProduct", data)

		}

	})
	// 失去焦点的时候利用ajax判断用户名是否存在【注意】网络太慢应该会导致延迟
	// 得到用户名

	// 获取新闻题目列表信息
	$.ajax({
		url : 'ShowNewsServlet',
		type : 'post',
		data : {
			param : 'newsListPage',
		},
		dataType : 'json',
		success : function(data) {

			// 加载新闻标题列表
			for (var i = 0; i < data.length; i++) {
				var href = 'ShowNewsServlet?param=newsDetailPage&newsTitle='
						+ data[i].ENTitle;
				$("#showNews").append(
						'<li><a href=' + href + '>' + data[i].ENTitle
								+ '</a></li>');
			}
		}
	})

	// 加载分类上栏和下栏

	$.ajax({
		url : 'ProductServlet',
		type : 'post',
		data : {
			param : 'productCateg',
		},
		dataType : 'json',
		success : function(data) {

			// 加载分类：
			for (var i = 0; i < data.length; i++) {
				// 加载顶部的菜单分类
				if (data[i].EPCIsOften == 1) {
					$("#topNav").append(
							'<li><a href=ProductServlet?param=categ&EPCId='
									+ data[i].EPCId + '>  ' + data[i].EPCName
									+ '</a></li>');
				}

				// 加载左侧分类信息：
				// 对于有有父类和子类的分类表其实就是根据pid来加载的 但是这个又不像是城市地区那样的，所以还是有点不一样
				// 最本质的就是要知道什么时候分多少级，如果有一个功能不知道分多少级如何根据一个程序一劳永逸
				// data[i].EPCId==1表示图书音像分类，
				if (data[i].EPCId == 1) {

					$("#leftNav").append('<dt>' + data[i].EPCName + '</dt>');
					// 加载所有子类 注意优化代码减少不必要的循环
					for (var j = 0; j < data.length; j++) {
						if (data[j].EPCParentId == 1) {
							$("#leftNav").append(
									'<li><a href=ProductServlet?param=categ&EPCId='
											+ data[j].EPCId + '>  '
											+ data[j].EPCName + '</a></li>');
						}

					}

				}
				// data[i].EPCId==2表示百货分类
				if (data[i].EPCId == 2) {
					$("#leftNav").append('<dt>' + data[i].EPCName + '</dt>');

					for (var j = 0; j < data.length; j++) {
						if (data[j].EPCParentId == 2) {
							$("#leftNav").append(
									'<li><a href=ProductServlet?param=categ&EPCId='
											+ data[j].EPCId + '>  '
											+ data[j].EPCName + '</a></li>');
						}

					}

				}
			}
		}
	})

})

// JavaScript Document
window.onload = function() {
	showChater();
	scrollChater();
}
window.onscroll = scrollChater;
window.onresize = scrollChater;

// 注册信息：
function checkLogin(obj) {

	var EUPwdtt = document.getElementById("password");
	var EUIdtt = document.getElementById("userName");
	var EUPwd = EUPwdtt.value;
	var EUId = EUIdtt.value;
	$.ajax({
		url : 'UserServlet',
		type : 'post',
		data : {
			param : "login",
			EUId : EUId,
			EUPwd : EUPwd
		},
		dataType : 'json',
		success : function(data) {
			if (data.isExist == false) {
				//实现思路是把本来隐藏的显示出来
				$("#loginSpan").show();
			} else {
				window.location.href = 'login-result.html';
			}
		}
	})

}


function focusItem(obj) {
	obj.parentNode.parentNode.className = "current";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML = "";
	msgBox.className = "";
}

function checkItem(obj) {
	obj.parentNode.parentNode.className = "";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	var EUId = obj.value;
	switch (obj.name) {
	case "EUId":
		$.ajax({
			url : 'CheckEUIdServlet',
			type : 'post',
			data : {
				EUId : EUId
			},
			dataType : 'json',
			success : function(data) {
				var isExist = data.isExist;

				if (EUId == '') {
					$(msgBox).html('用户名不可以为空');
					$(msgBox).addClass('error');
				} else if (isExist) {
					$(msgBox).html('该用户名已经存在');
					$(msgBox).addClass('error');
				}

				else {
					$(msgBox).html('该用户名可以使用');
					$(msgBox).addClass('ok');
				}

				// $("#regInfo").html(regInfo);

			}
		})
		// if (obj.value == "") {
		// msgBox.innerHTML = "用户名不能为空";
		// msgBox.className = "error";
		// return false;
		// }
		break;
	case "EUPwd":
		if (obj.value == "") {
			msgBox.innerHTML = "密码不能为空";
			msgBox.className = "error";
			return false;
		} else {
			msgBox.innerHTML = "可以使用";
			msgBox.className = "ok";
		}
		break;
	case "rePassWord":
		if (obj.value == "") {
			msgBox.innerHTML = "确认密码不能为空";
			msgBox.className = "error";
			return false;
		} else if (obj.value != document.getElementById("passWord").value) {
			msgBox.innerHTML = "两次输入的密码不相同";
			msgBox.className = "error";
			return false;
		} else {
			msgBox.innerHTML = "可以使用";
			msgBox.className = "ok";
		}
		break;
	case "veryCode":
		if (obj.value == "") {
			msgBox.innerHTML = "验证码不能为空";
			msgBox.className = "error";
			return false;
		}
		break;
	}
	return true;
}
// 这段代码是干什么的了？？我都用ajax实现了还有检查form表单的作用了吗
// function checkForm(frm) {
// var els = frm.getElementsByTagName("input");
// for (var i = 0; i < els.length; i++) {
// if (typeof (els[i].getAttribute("onblur")) == "function") {
// if (!CheckItem(els[i]))
// return false;
// }
// }
// return true;
// }

function showChater() {
	var _chater = document.createElement("div");
	_chater.setAttribute("id", "chater");
	var _dl = document.createElement("dl");
	var _dt = document.createElement("dt");
	var _dd = document.createElement("dd");
	var _a = document.createElement("a");
	_a.setAttribute("href", "#");
	_a.onclick = openRoom;
	_a.appendChild(document.createTextNode("在线聊天"));
	_dd.appendChild(_a);
	_dl.appendChild(_dt);
	_dl.appendChild(_dd);
	_chater.appendChild(_dl);
	document.body.appendChild(_chater);
}

function openRoom() {
	window.open("chat-room.html", "chater",
			"status=0,scrollbars=0,menubar=0,location=0,width=600,height=400");
}

function scrollChater() {
	var chater = document.getElementById("chater");
	var scrollTop = document.documentElement.scrollTop;
	var scrollLeft = document.documentElement.scrollLeft;
	chater.style.left = scrollLeft + document.documentElement.clientWidth - 92
			+ "px";
	chater.style.top = scrollTop + document.documentElement.clientHeight - 25
			+ "px";
}

function inArray(array, str) {
	for (a in array) {
		if (array[a] == str)
			return true;
	}
	return false;
}

function setCookie(name, value) {
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}

function getCookie(name) {
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null)
		return unescape(arr[2]);
	return null;
}

function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function goBuy(id, price) {
	var newCookie = "";
	var oldCookie = getCookie("product");
	if (oldCookie) {
		if (inArray(oldCookie.split(","), id)) {
			newCookie = oldCookie;
		} else {
			newCookie = id + "," + oldCookie;
		}
	} else {
		newCookie = id;
	}
	setCookie("product", newCookie);
	location.href = "shopping.html";
}

function delShopping(id) {
	var tr = document.getElementById("product_id_" + id);
	var oldCookie = getCookie("product");
	if (oldCookie) {
		var oldCookieArr = oldCookie.split(",");
		var newCookieArr = new Array();
		for (c in oldCookieArr) {
			var cookie = parseInt(oldCookieArr[c]);
			if (cookie != id)
				newCookieArr.push(cookie);
		}
		var newCookie = newCookieArr.join(",");
		setCookie("product", newCookie);
	}
	if (tr)
		tr.parentNode.removeChild(tr);
}

function reloadPrice(id, status) {
	var price = document.getElementById("price_id_" + id).getElementsByTagName(
			"input")[0].value;
	var priceBox = document.getElementById("price_id_" + id)
			.getElementsByTagName("span")[0];
	var number = document.getElementById("number_id_" + id);
	if (status) {
		number.value++;
	} else {
		if (number.value == 1) {
			return false;
		} else {
			number.value--;
		}
	}
	priceBox.innerHTML = "￥" + price * number.value;
}

// ajax代码
