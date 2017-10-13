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
<script src="/StudentUnion/js/jquery.color.js"></script>
<link rel="stylesheet" type="text/css" href="css/liveDept/insertDormInfo.css" />
</head>
<body>
	<div class="liable">
		<span class="${dorminfo.size()==0?'colorblue':'colorgreen' }">${dorminfo.size()==0?"未登记":"已登记" }</span>
		<span>负责人：</span><input autofocus="autofocus" name="liable" value="${dorminfo[0].liable }"/>
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
			<a href="javascript:saveTable()">${dorminfo.size()==0?"保存":"更新" }</a>
		</div>
		<div class="center">
			<a href="javascript:toF(1)" class="sel">1F</a>
			<a href="javascript:toF(2)">2F</a>
			<a href="javascript:toF(3)">3F</a>
			<a href="javascript:toF(4)">4F</a>
			<a href="javascript:toF(5)">5F</a>
		</div>
	</div>
	<div class="table">
		<form action="" method="post">
			<table>
				<tr>
					<td>宿舍号</td>
					<td>班级</td>
					<td>地面墙面</td>
					<td>物品</td>
					<td>安全</td>
					<td>床铺</td>
					<td>得分</td>
				</tr>
				<c:forEach var="d" items="${dorminfo }">
					<tr>
						<td><input name="number" readonly="readonly" value="${fn:toUpperCase(building) }-${d.number }" /></td>
						<td class="cid${d.cid }"><input name="cname" readonly="readonly" value="${d.cname }" /></td>
						<td><input name="standarda" value="${d.standarda }" /></td>
						<td><input name="standardb" value="${d.standardb }" /></td>
						<td><input name="standardc" value="${d.standardc }" /></td>
						<td><input name="standardd" value="${d.standardd }" /></td>
						<td><input name="mark" value="${d.mark }" /></td>
					</tr>
				</c:forEach>
				<c:forEach var="dc" items="${dormclass }" varStatus="status">
					<tr>
						<td><input name="number" readonly="readonly" value="${fn:toUpperCase(building) }-${100+status.count }" /></td>
						<td class="cid${dc[0] }"><input name="cname" readonly="readonly" value="${dc[1] }" /></td>
						<td><input name="standarda" /></td>
						<td><input name="standardb" /></td>
						<td><input name="standardc" /></td>
						<td><input name="standardd" /></td>
						<td><input name="mark" value="100"/></td>
					</tr>
				</c:forEach>
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
	</div>
</body>
<script>
	//存储栋别
	var building = "${building==null?'a':building }";
</script>
<script src="/StudentUnion/js/liveDept/insertDormInfo.js"></script>
</html>