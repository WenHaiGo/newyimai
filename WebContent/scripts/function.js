$(function() {

})
// 当ajax从后台读入数据时，封装的方法显示商品信息
function loadProduct(whichTag, data) {
	for (var i = 0; i < data.length; i++) {
		var productViewCons = '<li>'
				+ '<dl>'
				+ '<dt><a href="product-view.html?EPId='
				+ data[i].EPId
				+ '" target="_blank"><img src='
				+ data[i].EPFile
				+ ' /></a></dt>'
				+ '<dd class="title"><a href="product-view.html?EPId='
				+ data[i].EPId + '"target="_blank">' + data[i].EPName
				+ '</a></dd>' + '<dd class="price">' + data[i].EPPrice
				+ '</dd>' + '</dl></li>';
		$(whichTag).append(productViewCons);
	}
}
// 加载左侧分类
function loadCateg() {
	$.ajax({
		url : 'CategServlet',
		type : 'post',
		data : {
			param : 'allCateg',
		},
		dataType : 'json',
		success : function(data) {
			// 加载大类
			for (var i = 0; i < data.length; i++) {
				var pstr = '<dt>' + data[i].name + '</dt>';
				$("#classlist").append(pstr);

				// 加载子类
				var temp = data[i].list;
				for (var j = 0; j < temp.length; j++) {
					var cstr = '<dd>';
					cstr += '<a href="product-list.html?cid=' + temp[j].EPCId
							+ '">' + temp[j].EPCName + '</a>';
					cstr += '</dd>';
					$("#classlist").append(cstr);
				}
			}
		}
	})
}
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
				// 实现思路是把本来隐藏的显示出来
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
