<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/clubDept/club_likeselect_html.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/StudentUnion/css/clubDept/defaultStyle.css" />
</head>
<body>
	<p class="title">查询结果</p>
	<p class="search"><input id='club_name'><a href="javascript:name_toselect()">查询</a></p>
<table id="myTab" border=1>
		<tr>
			<td>编号</td>
			<td>社团名称</td>
			<td>社团人数</td>
			<td>实到人数</td>
			<td>缺勤人数</td>
			<td>日期</td>
			<td>管理</td>
		</tr>
		
		<!-- 如果当前页的数据为空自动跳到上一页 -->
		<c:if test="${empty(bean.pageList) }">
			<tr>
				<td colspan='7'>没有数据</td>
			</tr>
			<c:if test="${bean.cpage>1}">
			<jsp:forward page="/ClubDeptServlet?opt=clubname_to_select&clubname=${clubname}&cpage=${bean.cpage-1 }"></jsp:forward>
			</c:if>
		</c:if>
		
		<!-- 循环添加行，显示数据 -->
	<c:forEach var="cl" items="${bean.pageList }" varStatus="ids">
		<tr>
			<!-- 编号 -->
			<td>${ids.count+(bean.cpage-1)*bean.showNum}</td>
			<!-- 社团名 -->
			<td>${cl.clubname }</td>
			<!-- 社团总人数 -->
			<td>${cl.peoplenum }</td>
			<!-- 实到人数 -->
			<td>${cl.peoplecomenum }</td>
			<!-- 缺勤人数 -->
			<td>${cl.peoplenocome }</td>
			<!-- 登记时间 -->
			<td>${cl.thetime }</td>
			<td>
				<a href="javascript:del(${cl.attid},${bean.cpage},'${clubname}')">删除</a>
			</td>
		</tr>
	</c:forEach>
	</table>
	<p class="botton">
		<a href="ClubDeptServlet?opt=clubtoselect&cpage=1">返回全部</a>
		<a href="ClubDeptServlet?opt=clubname_to_select&clubname=${clubname}&cpage=${bean.cpage-1<=0?1:bean.cpage-1}">上一页</a>
		<span>第${bean.cpage }/${bean.allPage }页</span>
		<a href="ClubDeptServlet?opt=clubname_to_select&clubname=${clubname}&cpage=${bean.cpage+1>bean.allPage?bean.allPage:bean.cpage+1 }">下一页</a>
	</p>
	<div class="mask">
		<div class="delWindow">
			<p>确定要删除本条记录吗？</p>
			<p class="delBottom">
				<a href="javascript:closeWindow()">取消</a>
				<a href="javascript:toremove()">确定</a>
			</p>
		</div>
	</div>
	<div class="finish">
		提示消息
	</div>
</body>
</html>