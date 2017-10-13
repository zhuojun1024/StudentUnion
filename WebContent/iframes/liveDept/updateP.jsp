<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/StudentUnion/css/liveDept/updateP.css" />
<title>修改密码</title>
<style>
</style>
</head>
<body>
	<p class="updatep">修改密码</p>
	<div>
		<form action="LoginServlet" method="post">
			<input name="pwd" type="password" placeholder="原密码" /><br />
			<input name="npwd" type="password" placeholder="新密码" /><br />
			<input name="repwd" type="password" placeholder="确认新密码" /><br />
			<p><span>密码支持8~16位字母与数字组合</span><br /><a href="javascript:return false;" class="disabled">修改</a></p>
		</form>
	</div>
	<div class="finish">
		修改成功
	</div>
</body>
<script src="/StudentUnion/js/liveDept/updateP.js"></script>
</html>