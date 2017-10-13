<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宿舍楼A栋</title>
<script src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="/StudentUnion/css/inspectionDept/insertInfo.css" /> -->
</head>
<style>
	h2{
		text-align: center;
		font-family: '幼圆';
		font-size: 32px;
	}
</style>
<body>
<h2>... 项目维护中 ...</h2>
<!-- 	<div class="liable">
		<span class="colorblue">未登记</span>
		<span>班级考勤表</span>
	</div>
	<div class="top">
		<div class="left">
			<select class="year">
				<option value="2017">----年</option>
			</select>
			<select class="month">
				<option value="1">--月</option>
			</select>
			<select class="day">
				<option value="1">--日</option>
			</select> 
		</div>
		<div class="right">
			<a href="javascript:inputEmpty()">清空</a>
			<a href="javascript:saveTable()">保存</a>
		</div>
		<div class="center">
			<span>负责人：</span><input autofocus="autofocus" name="liable" value=""/>
		</div>
	</div>
	<div class="table">
		<form action="" method="post">
			<table>
				<tr>
					<td>班级</td>
					<td>应到人数</td>
					<td>一次实到</td>
					<td>二次实到</td>
					<td>早退</td>
					<td>请假</td>
					<td>缺勤</td>
					<td>违纪</td>
					<td>教室卫生</td>
					<td>总扣分</td>
					<td>负责人</td>
				</tr>
			</table>
		</form>
	</div>
	<div class="finish">
		发布成功
	</div>
	<div class="mask">
		<div class="tof">
			<p>确定要清空表格数据吗？</p>
			<p class="delBottom">
				<a href="javascript:">取消</a>
				<a href="javascript:">清空</a>
			</p>
		</div>
	</div> -->
</body>
<script>
	$(function(){
		setInterval(function(){
			var r = Math.floor(Math.random()*255);
			var g = Math.floor(Math.random()*255);
			var b = Math.floor(Math.random()*255);
			$("h2").css("color","rgba("+r+","+g+","+b+",1)");
		},500);
	});
</script>
<!-- <script src="/StudentUnion/js/inspectionDept/insertInfo.js"></script> -->
</html>