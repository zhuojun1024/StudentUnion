<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"	prefix="c" %>
<%@ page errorPage="error.html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/StudentUnion/css/broadDept/notice.css">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
</head>
<body>
	<h3 style="text-align: center;">${param.ctype==1?'寻物启事':'失物招领' }</h3>
<!-- 	这是发布消息和模糊查询的  -->
	<div class="updata">
		<input type="button" value="发布消息" onclick="showDiv()">
		<form class="seek" action="notice?opt=selectData&cpage=1&ctype=${param.ctype}" method="post" >
			<input name="selectData" value="${param.selectData }">
			<input type="submit" value="查询">
		</form>
	</div>
	
	<div style="height:400px;">
<!-- 	这是消息显示 -->
		<c:if test="${empty pageBean.pageList}">
		<fieldset>
			<h2 style="color:red;text-align: center;">没有数据</h2>
		</fieldset>
		</c:if>
		<c:forEach var="n" items="${pageBean.pageList}" varStatus="ids" >
		<fieldset>
			<legend>发布者:${n.nname }</legend>
			<p>${n.ncontent}</p>
			<span style="color:#46a1ef;">${n.ntime.substring(0,16) }</span>
			<input type="button" value="删除" onclick="removeData(${n.nid})" style="float:right;">
			<input type="button" value="编辑" onclick="getDate(${n.nid})" style="float:right;">
		</fieldset>
		</c:forEach>
		</div>
		<div style="text-align: center;">
		<a href="notice?opt=selectData&cpage=1&selectData=${param.selectData }&ctype=${param.ctype}">首页</a>
		&nbsp;
		<a href="notice?opt=selectData&cpage=${pageBean.cpage-1<=0?1:pageBean.cpage-1}&selectData=${param.selectData }&ctype=${param.ctype}">上一页</a>
			&nbsp;${pageBean.cpage }/${pageBean.allPage }&nbsp;
		<a href="notice?opt=selectData&cpage=${pageBean.cpage+1>pageBean.allPage?pageBean.allPage:pageBean.cpage+1 }&selectData=${param.selectData }&ctype=${param.ctype}">下一页</a>
		&nbsp;
		<a href="notice?opt=selectData&cpage=${pageBean.allPage }&selectData=${param.selectData }&ctype=${param.ctype}">尾页</a>
		</div>
		
	<!-- 	 添加框 -->
	<div id="upDiv">
		<form action="" id="addFrm">
			<input name="id" type="hidden">
			<input name="ctype" type="hidden" value=${param.ctype }>
			内容：<textarea name="ncontent" rows="10" cols="20"></textarea><br/>
					<input type="button" value="添加" onclick="addData()" >
					<input type="button" value="修改" onclick="upData()" style="display:none">					
					<input type="button" value="关闭" onclick="colseDiv()" >
		</form>
	</div>
<!-- 	提示框 -->
	<div id="hintDiv">
		<h2>你已取消操作</h2>
	</div>
</body>
<script type="text/javascript">
//打开添加框
function showDiv(){
	$(":button[value=添加]").css("display","inline-block");
	$(":button[value=修改]").css("display","none");
	$("#upDiv").slideDown(800);//显示添加框
	$("#addFrm")[0].reset();//清空数据
}
//关闭添加框
function colseDiv(){
	$("#upDiv").slideUp(600);
	$("#addFrm")[0].reset();//清空数据
	$("#hintDiv").slideDown(600);//显示添加框提示框
	setTimeout("hintDiv()",2000);
}
//提示框
function hintDiv(){
	$("#hintDiv").slideUp(600,function(){
		location=location;
	});
}
//添加事件 
function addData(){
	//序列化参数
	var param=$("#addFrm").serialize();//表单序列化
	//进行异步操作
	$.post("notice?opt=addData",param,function(data){
		if(data==1){
			$("#hintDiv").html("<h2>添加成功</h2>");
			colseDiv();
		}else{
			$("#hintDiv").html("<h2>添加失败</h2>");
			history.back();
			colseDiv();
		}
	});
}
//删除寻物启事的方法
function removeData(id){
	$.post("notice?opt=removeData","id="+id,function(data){
		if(data==1){
			colseDiv();
			$("#hintDiv").html("<h2>删除成功</h2>");
		}else{
			colseDiv();
			$("#hintDiv").html("<h2>删除失败</h2>");
			history.back();
			colseDiv();
		}
	});
}

//修改界面的值
function getDate(id){
	showDiv();//显示界面
	
	$(":button[value=修改]").css("display","inline-block");
	$(":button[value=添加]").css("display","none");

	$("#addFrm")[0].reset();//清空数据
	//异步取出对应id数值
	$.post("notice?opt=getData","id="+id,function(data){
		$("input[name='id']").val(data.nid);
		$("textarea").val(data.ncontent);
		$("input[name='number']").val(data.ncontact);
	},"json");
}

//将修改信息保存在数据库
function upData(){
	//取出表单的值
	var param=$("#addFrm").serialize();//表单序列化
	//异步取出对应id数值
	$.post("notice?opt=upData",param,function(data){
		if(data==1){
			$("#hintDiv").html("<h2>修改成功</h2>");
			colseDiv();//关闭界面
		}else{
			$("#hintDiv").html("<h2>修改失败</h2>");
			colseDiv();
		}
	},"json");
	
}
</script>
</html>