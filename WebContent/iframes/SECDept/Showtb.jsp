<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<title>Insert title here</title>
<style>
	*{
		margin: 0px;
		padding: 0px;
		font-family: '幼圆';
	}
	body{
		min-width: 820px;
	}
	.title{
		text-align: center;
		font-size: 24px;
		margin: 20px 0px;
	}
	table{
		width: 96%;
		margin: auto;
		border-collapse:collapse;
	}
	table tr td{
		height: 28px;
		border: 1px solid #1499F7;
		text-align: center;
		box-sizing: border-box;
	}
	
	table tr td:nth-child(1){
		width: 5%;
	}
	table tr td:nth-child(2){
		width: 15%;
	}
	table tr td:nth-child(3){
		width: 15%;
	}
	table tr td:nth-child(4){
		width: 20%;
	}
	table tr td:nth-child(5){
		width: 20%;
	}
	table tr td:nth-child(6){
		width: 25%;
	}
	
	p:not(:first-child){
		width: 96%;
		text-align: right;
		margin: 20px auto;
	}
	p:not(:first-child) a{
		border-radius: 3px;
		cursor: pointer;
		padding: 8px 16px;
		background-color: #1499F7;
		color: #FFF;
		text-decoration: none;
	}
	a:hover{
		opacity: 0.7;
	}
</style>
</head>
<body>
	<p class="title">采购查询</p>
<table border="1">
	<tr><td>编号</td><td>预购买物品</td><td>数量</td><td>单价/元</td><td>总价/元</td><td>购买时间</td></tr>
	<c:forEach items="${pageBean.pageList}" var="sl" varStatus="num">
		<tr>
			<td>${num.count}</td>
			<td>${sl.goods}</td>
			<td>${sl.num}</td>
			<td>${sl.price}</td>
			<td>${sl.tprice}</td>
			<td>${sl.buytime.substring(0,16)}</td>
		</tr>
	</c:forEach>
</table>
<p>
	<a href="/StudentUnion/ShowServlet?opt=Showtb&cpage=${pageBean.cpage-1==0?1:pageBean.cpage-1 }">上一页</a>
	<span>第${pageBean.cpage }/${pageBean.allPage }页</span>
	<a href="/StudentUnion/ShowServlet?opt=Showtb&cpage=${pageBean.cpage==pageBean.allPage?pageBean.allPage:pageBean.cpage+1 }">下一页</a>
</p>
</body>
</html>
<script>
	$(function(){
		$("table tr:even").css("background-color","#D5EAF3");
	});
</script>