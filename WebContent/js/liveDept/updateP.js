
var req = /^[a-zA-Z0-9]{8,16}$/;
	//格式验证
    $("input").keyup(function(){
    var name = $(this).prop("name");
	var pwd = $("input[name='pwd']").val();
	var npwd = $("input[name='npwd']").val();
	var repwd = $("input[name='repwd']").val();
	if(name == "pwd"){
		if(pwd.length < 8||pwd.length > 16){
		$("input:eq(0)").css("border","1px solid #F00");
		$("form p span:eq(0)").text("请输入正确的原密码").css("color","#F00");
		disabled(0);
		}else{
			$(this).css("border","1px solid #10C910");
			$("form p span:eq(0)").text("原密码格式正确").css("color","#10C910");
		}
	}else if(name == "npwd"){
		if(!req.test(npwd)){
			$("input:eq(1)").css("border","1px solid #F00");
			$("form p span:eq(0)").text("新密码支持8~16位字母与数字组合").css("color","#F00");
			disabled(0);
		}else{
			$(this).css("border","1px solid #10C910");
			$("form p span:eq(0)").text("新密码格式正确").css("color","#10C910");
			if(repwd.length != 0) $("input:eq(2)").keyup();
		}
	}else if(name == "repwd"){
		if(npwd.length == 0){
			$("input:eq(1)").keyup();
		}else if(npwd != repwd){
			$("input:eq(2)").css("border","1px solid #F00");
			$("form p span:eq(0)").text("两次密码输入不一致").css("color","#F00");
			disabled(0);
		}else{
			$(this).css("border","1px solid #10C910");
			$("form p span:eq(0)").text("两次密码输入一致").css("color","#10C910");
		}
	}
	if((pwd.length >= 8 && pwd.length <= 16) && (req.test(npwd)) && (repwd.length != 0) && (npwd == repwd)){
		$("input").css("border","1px solid #10C910");
		$("form p span:eq(0)").text("格式验证通过").css("color","#10C910");
		disabled(1);
	}
});
//禁用按钮与解除禁用
function disabled(opt){
	if(opt == 1){
		$("form p a:eq(0)").css("opacity","1");
		$("form p a:eq(0)").removeProp("class");
		$("form p a:eq(0)").prop("href","javascript:updatePwd()");
	}else{
		$("form p a:eq(0)").css("opacity","0.5");
		$("form p a:eq(0)").prop("class","disabled");
		$("form p a:eq(0)").prop("href","javascript:return false;");
	}
}
function updatePwd(){
	var param = $("form:eq(0)").serialize();
	//序列化要传输的数据
    $.ajax({
        url: "/StudentUnion/UserServlet",
        type: "POST",
        data: param+"&opt=updatePwd",
        success: function (data) {
			$("form:eq(0)")[0].reset();
			$("input").css("border","1px solid #1499F7");
			$("form p span:eq(0)").text("密码支持8~16位字母与数字组合").css("color","#1499F7");
			disabled(0);
			if(data == "1"){
 				$(".finish").text("修改成功").css("background-color","#00C900").slideDown().delay(1500).slideUp();
   			}else if(data == "-1"){
   				$(".finish").text("原密码错误").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
   			}else{
   				$(".finish").text("修改失败").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
   			}
        }  
     });  
}