<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/StudentUnion/css/student/sel_class.css" />
<title></title>
</head>
<body>
	<p class="title">班级寝室内务查询</p>
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
			<select class="cla">
				<c:forEach var="c" items="${cla }">
					<c:choose>
						<c:when test="${c[0]==cid }">
							<option value="${c[0] }" selected="selected">${c[1] }</option>
						</c:when>
						<c:otherwise>
							<option value="${c[0] }">${c[1] }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		<div class="right">
			<span>排序方式：</span>
			<a href="javascript:;" onclick="sortTo(this,null)">寝室</a>
			<a href="javascript:;" onclick="sortTo(this,1)">得分</a>
		</div>
	</div>
	<div class="table">
		<form action="" method="post">
			<table>
				<tr>
					<td>编号</td>
					<td>宿舍号</td>
					<td>地面墙面</td>
					<td>物品</td>
					<td>安全</td>
					<td>床铺</td>
					<td>得分</td>
				</tr>
				<c:forEach var="d" items="${dorminfo }" varStatus="ids">
					<tr>
						<td>${ids.count}</td>
						<td>${fn:toUpperCase(d.building) }-${d.number }</td>
						<td>${d.standarda }</td>
						<td>${d.standardb }</td>
						<td>${d.standardc }</td>
						<td>${d.standardd }</td>
						<td>${d.mark }</td>
					</tr>
				</c:forEach>
				<c:if test="${empty dorminfo }">
					<tr>
						<td colspan="7">暂无数据</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
<script src="/StudentUnion/js/student/sel_class.js"></script>
</html>