//注册手机号验证
function userRegistered() {
	var phone = $.trim($("#phone").val());
	var rtn = false;
	if (null==phone || phone=="") {
		showError("phone", "手机号码不能为空");
		return false;
	} else if (!/^1[1-9]\d{9}$/.test(phone)) {	
		showError("phone", "手机号码不正确");
		return false;
	} else {
		$.ajax({
			 type:"POST",
			 url:"loan/phoneCheck",
		     dataType: "text",
		     async: false,
		     data:"phone="+phone,
			 success: function(msg) {
		  		 if (msg==0) {
		  			showSuccess('phone');
		  			rtn = true;
		  		 } else {
		  			showError('phone','此手机号码已被注册');
		  			rtn = false;
		  		 }
			 },
			 error:function() {
				 showError('phone', '请求错误');
				 rtn = false;
			 }
		});
	}
	if(!rtn){
		return false;
	}
	return true;
}
//密码验证
function psCheck() {
	var pwd = $.trim($("#loginPassword").val());
	var replaypassword = $.trim($("#replayLoginPassword").val());
	if (pwd=="") {
		   showError('loginPassword','请输入登录密码');
		   return false;
	}
	if (!(/^[0-9a-zA-Z]+$/.test(pwd))) {
		showError('loginPassword','密码字符只可使用数字和大小写英文字母');
		return false;
	} else if (!(/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*$/.test(pwd))) {
		showError('loginPassword','密码应同时包含英文或数字');
	    return false;
	} else if(pwd.length<6||pwd.length>16) {
		showError('loginPassword','密码应为6-16位');
		return false;
	} else {
		showSuccess('loginPassword');
	}
	if (pwd!=null && replaypassword!=null&& pwd== replaypassword) {
		showSuccess('replayLoginPassword');
	}
	return true;
}
//两次密码是否相等验证
function pwdequ() {
	var userpassword=$.trim($("#loginPassword").val());//密码
	var replaypassword=$.trim($("#replayLoginPassword").val());//确认密码
	if (!psCheck()) {
		hideError('replayLoginPassword');
		return false;
	}
	if (userpassword=="") {
		showError('loginPassword','请输入登录密码！');
		return false;
	} else if(replaypassword=="") {
		showError('replayLoginPassword','请再次输入登录密码');
		return false;
	} else if(userpassword!=replaypassword) {
		showError('replayLoginPassword','两次输入登录密码不一致');
		return false;
	} else {
		showSuccess('replayLoginPassword');
		return true;
	}
	return true;
}
//验证码验证
function checkCaptcha() {
	var rtn = false;
	var captcha = $.trim($("#captcha").val());
	if (captcha == "") {
		showError('captcha','请输入图形验证码');
		return false;
	} else {
		$.ajax({
			type:"POST",
			url:"loan/verifyCaptcha",
			dataType: "text",
			async: false,
			data:"captcha="+captcha,
			success: function(msg) {
				var obj = jQuery.parseJSON(msg);
			    if (obj.errorMessage=="ok") {
			    	showSuccess('captcha');
			    	rtn = true;
			    } else {
			    	showError('captcha', obj.errorMessage);
			    	rtn = false;
			    }
			},
		    error:function() {
				showError('captcha','网络错误');
				rtn = false;
			}
		});
	}
	if (!rtn) {
		return false;
	}
	return true;
} 
//错误提示
function showError(id,msg) {
	$("#"+id+"Ok").hide();
	$("#"+id+"Err").html("<i></i><p>"+msg+"</p>");
	$("#"+id+"Err").show();
	$("#"+id).addClass("input-red");
}
//错误隐藏
function hideError(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id).removeClass("input-red");
}
//显示成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}

//提交注册
function register () {
	var loginPassword = $.trim($("#loginPassword").val());
	var replayLoginPassword = $.trim($("#replayLoginPassword").val());//确认密码
	if (userRegistered() && pwdequ() && checkCaptcha()) {
		$("#loginPassword").val($.md5(loginPassword));//jquery MD5加密
		$("#replayLoginPassword").val($.md5(replayLoginPassword));
		var phone = $.trim($("#phone").val());
		loginPassword = $.trim($("#loginPassword").val());
		replayLoginPassword = $.trim($("#replayLoginPassword").val());//确认密码
		var captcha = $.trim($("#captcha").val());
		$.ajax({
			type:"POST",
			url:"loan/register",
			dataType: "text",
			async: false,
			data:"phone="+phone+"&loginPassword="+loginPassword+"&replayLoginPassword="+replayLoginPassword+"&captcha="+captcha,
			success: function(msg) {
				var obj = jQuery.parseJSON(msg);
				if (obj.errorMessage == "ok") {
					window.location.href = "realName.jsp";
				} else {
					showError('captcha', obj.errorMessage);
				}
			},
		    error:function() {
				 showError('captcha','网络错误');
				 rtn = false;
			}
		});
	}
}
//注册协议确认
$(function() {
	$("#agree").click(function(){
		var ischeck = document.getElementById("agree").checked;
		if (ischeck) {
			$("#btnRegist").attr("disabled", false);
			$("#btnRegist").removeClass("fail");
		} else {
			$("#btnRegist").attr("disabled","disabled");
			$("#btnRegist").addClass("fail");
		}
	});
});

//打开注册协议弹层
function alertBox(maskid,bosid){
	$("#"+maskid).show();
	$("#"+bosid).show();
}
//关闭注册协议弹层
function closeBox(maskid,bosid){
	$("#"+maskid).hide();
	$("#"+bosid).hide();
}