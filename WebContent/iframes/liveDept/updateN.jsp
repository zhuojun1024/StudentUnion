<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告管理</title>
<script src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/StudentUnion/css/liveDept/updateN.css" />
</head>
<body>
	<p class="notice">公告管理</p>
	<c:forEach var="n" items="${notice }">
		<fieldset class="${n.nregion }">
			<legend>${n.ntitle }</legend>
			<p class="content">${n.ncontent }</p>
			<p>${n.npublisher } [${n.ndate.substring(5,16) } ${n.nweek }]</p>
		</fieldset>
		<p class="edit">
			<a class="n${n.nid}" href="javascript:edit(${n.nid})">编辑</a>
			<a class="n${n.nid}" href="javascript:del(${n.nid})">删除</a>
		</p>
	</c:forEach>
	<p class="load">
		<label>■ 部门公告</label>
		<label>■ 公共频道</label>
		<a href="NoticeServlet?opt=setNotice&page=${page>1?page-1:1}">上一页</a>
		<span>第${allpage==0?0:page }/${allpage }页</span>
		<a href="NoticeServlet?opt=setNotice&page=${page+1>allpage?page:page+1}">下一页</a>
	</p>
	<div class="mask">
		<div class="editWindow">
			<p class="editNotice">编辑公告</p>
			<input name="ntitle" spellcheck="false"/>
			<textarea autofocus='autofocus' spellcheck="false" ></textarea>
			<p class="bottom">
				<span></span>
				<span>字数：0/2000</span>
				<a href="javascript:closeWindow()">取消</a>
				<a href="javascript:editNotice()">完成</a>
			</p>
		</div>
		<div class="delWindow">
			<p>确定要删除本条公告吗？</p>
			<p class="delBottom">
				<a href="javascript:closeWindow()">取消</a>
				<a href="javascript:delNotice()">确定</a>
			</p>
		</div>
	</div>
	<div class="finish">
		编辑成功
	</div>
</body>
<script src="/StudentUnion/js/liveDept/updateN.js"></script>
</html>