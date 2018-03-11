// JavaScript Document


function manageCheckLogin(obj) {
	var EUPwdtt = document.getElementById("password");
	var EUIdtt = document.getElementById("userName");
	var EUPwd = EUPwdtt.value;
	var EUId = EUIdtt.value;
	$.ajax({
		// 为什么这里还需要返回上一级
		url : 'ManageUserServlet',
		type : 'post',
		data : {
			param : "manageLogin",
			EUId : EUId,
			EUPwd : EUPwd
		},
		dataType : 'json',
		success : function(data) {
			if (data.isExist == false) {
				// 实现思路是把本来隐藏的显示出来
				$("#loginSpan").show();
			} else {
				window.location.href = 'manageIndex.jsp';
			}
		}
	})
}