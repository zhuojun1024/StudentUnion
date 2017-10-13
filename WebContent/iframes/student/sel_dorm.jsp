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
	<p class="title">寝室内务记录查询</p>
	<div class="top">
		<div class="left">
			<select class="year">
				<option value="2017">----年</option>
			</select>
			<select class="month">
				<option value="1">--月</option>
			</select>
			<select class="dorm">
				<c:forEach var="d" items="${dorm }">
						<option value="${d[0] }${d[1] }">${fn:toUpperCase(d[0]) }-${d[1] }</option>
				</c:forEach>
			</select>
		</div>
		<div class="right">
			<span>排序方式：</span>
			<a href="javascript:;" onclick="sortTo(this,null)">日期</a>
			<a href="javascript:;" onclick="sortTo(this,1)">得分</a>
		</div>
	</div>
	<div class="table">
		<form action="" method="post">
			<table>
				<tr>
					<td>编号</td>
					<td>日期</td>
					<td>地面墙面</td>
					<td>物品</td>
					<td>安全</td>
					<td>床铺</td>
					<td>得分</td>
				</tr>
				<c:forEach var="d" items="${dorminfo }" varStatus="ids">
					<tr>
						<td>${ids.count+(page-1)*12}</td>
						<td>${d.date.substring(5,10) }</td>
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
	<p class="load">
		<a href="javascript:;" onclick="page--;saveDate()">上一页</a>
		<span>第${page }/${allpage }页</span>
		<a href="javascript:;" onclick="page++;saveDate()">下一页</a>
	</p>
</body>
<script>
	var allpage = ${allpage==null?0:allpage};
</script>
<script src="/StudentUnion/js/student/sel_dorm.js"></script>
</html>