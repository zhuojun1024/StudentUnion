<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/StudentUnion/css/SECDept/addGoods.css">
<script type="text/javascript" src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<p class="title">采购登记</p>
	
	<form id="Myform">
	<table border="1">
		<tr>
			<td>物品名称</td>
			<td><input name="Goods"></td>
		</tr>
		<tr>
			<td>数量</td>
			<td><input name="Num"></td>
		</tr>
		<tr>
			<td>单价(元)</td>
			<td><input name="Price"></td>
		</tr>
		<tr>
			<td>总价(元)</td>
			<td><input name="TPrice"></td>
		</tr>
		<tr>
			<td>购买时间</td>
			<td><input name="BuyTime" placeholder="格式：2017-6-27 8:00"></td>
		</tr>
	</table>
	<p><a href="javascript:saveData()">保存</a></p>
	<div class="INS">
		<font>* 协会资金主要来自会费，外联赞助方面做得不够，需要加强外联经费的收入，以方便协会今后的持续发展。
及时将财务收支信息向协会公布，同时还可以向全体会员进行公开。</font>
	</div>
</form>
<div class="finish">
	提示消息
</div>
</body>
</html>
<script type="text/javascript">
$(function(){
	$("input").prop("spellcheck",false);
	$("table tr:even").css("background-color","#D5EAF3");
});
//获取所有input 的值，判断
	function saveData(){
		var req = /^20\d{2}-\d{1,2}-\d{1,2} (([1-9]{1})|([0-1][0-9])|([1-2][0-3])):([0-5][0-9])$/;
		if(!req.test($("input[name='BuyTime']").val())){
			$(".finish").text("请输入正确的日期格式").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
			$("input[name='BuyTime']").val("").focus();
			return;
		}
		var allinput = $(":input").size()-1;
		for(var i=0;i<allinput;i++){
			var val = $("input:eq("+i+")").val();
			if(val.length>0 && val != null){
			}else{
				$(".finish").text("请输入数据").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
				return;
			}
		}
		var param = $("#Myform").serialize();//获取表单所有参数
		$.get("/StudentUnion/ShowServlet?opt=addGoods",param,function(data){
			if(data==1){
				$(".finish").text("保存成功").css("background-color","#10C910").slideDown().delay(1500).slideUp();
			}
			if(data==0){
				$(".finish").text("出错，数据未能保存").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
			}
		});
		//数据保存后清空表单
		$("#Myform")[0].reset();
	
}
</script>