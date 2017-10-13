<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告</title>
<script src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/StudentUnion/css/liveDept/notice.css" />
<style>
</style>
</head>
<body>
	<p class="notice">公告</p>
	<c:forEach var="n" items="${notice }">
		<fieldset class="${n.nregion }">
			<legend>${n.ntitle }</legend>
			<p class="content">${n.ncontent }</p>
			<p>${n.npublisher } [${n.ndname }] [${n.ndate.substring(5,16) } ${n.nweek }]</p>
		</fieldset>
	</c:forEach>
	<p class="load">
		<label>■ 部门公告</label>
		<label>■ 公共频道</label>
		<a href="NoticeServlet?opt=getNotice&page=${page-1>1?page-1:1 }">上一页</a>
		<span>第${page }/${allpage }页</span>
		<a href="NoticeServlet?opt=getNotice&page=${page+1>allpage?page:page+1 }">下一页</a>
	</p>
</body>
</html>