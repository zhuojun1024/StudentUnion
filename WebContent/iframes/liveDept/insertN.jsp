<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布公告</title>
<script src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/StudentUnion/css/liveDept/insertN.css" />
</head>
<body>
	<p class="addNotice">发布公告</p>
	<input name="ntitle" placeholder="键入标题" autofocus='autofocus' spellcheck="false"/>
	<textarea spellcheck="false" ></textarea>
	<label>Tips：部门公告仅本部门可见，公共频道所有人可见</label>
	<p class="bottom">
		<span>字数：0/2000&nbsp;&nbsp;发布到:</span>
		<a href="javascript:addNotice('dept')">部门公告</a>
		<a href="javascript:addNotice('public')">公共频道</a>
	</p>
	<div class="finish">
		发布成功
	</div>
</body>
<script src="/StudentUnion/js/liveDept/insertN.js"></script>
</html>