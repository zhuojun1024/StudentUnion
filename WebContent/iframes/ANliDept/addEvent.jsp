<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<!-- 搭建JQuery UI环境 -->
<link type="text/css" href="/StudentUnion/css/ui-lightness/jquery-ui-1.8.20.custom.css" rel="stylesheet" />
<script type="text/javascript" src="/StudentUnion/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/StudentUnion/js/jquery-ui-1.8.20.custom.min.js"></script>
<script type="text/javascript" src="/StudentUnion/js/jquery.ui.datepicker-zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="/StudentUnion/css/ANliDept/addPageStyle.css" />
</head>
<body>
<p class="title">添加活动记录</p>
	<form id="Myform">
	    <table>
			<tr>
				<td>活动名称</td>
				<td><input name="Sevent"></td>
			</tr>
			<tr>
				<td>协助服务</td>
				<td><input name="Ethings"></td>
			</tr>
			<tr>
				<td>负责人</td>
				<td><input name="Ename"></td>
			</tr>
			<tr>
				<td>日期</td>
				<td><input name="Datatime"></td>
			</tr>
			<tr>
			</tr>
		</table>
	</form>
	<p class="botton"><a href="javascript:inputEmpty()">清空</a><a href="javascript:save()">保存</a></p>
	<div class="finish">
		提示消息
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("input").prop("spellcheck",false);
	$("table tr:even").css("background-color","#D5EAF3");
});

function save(){
	var param = $("#Myform").serialize();//获取表单所有参数
	//alert(param);
	$.get("/StudentUnion/EventServlet?opt=addevent",param,function(data){
		if(data==1){
			$(".finish").text("数据保存成功").css("background-color","#10C910").slideDown().delay(1500).slideUp();
		}else{
			$(".finish").text("出错，数据未能保存").css("background-color","#F00").slideDown().delay(1500).slideUp();
		}
	});
	$("#Myform")[0].reset();
}
//日期格式
$("input[name='Datatime']").datepicker({
	minDate: "-20",
	maxDate: "getDate",
	dateFormat:"yy-mm-dd"
});
//点击清空按钮时清空表格的所有数据
function inputEmpty(){
	$("input").val("");
}
</script>
</html>