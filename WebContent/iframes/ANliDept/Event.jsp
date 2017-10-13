<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/StudentUnion/css/ANliDept/showPageStyle.css" />
<title>活动信息</title>
</head>
<style>
	table tr td:nth-child(1){
		width: 10%;
	}
	table tr td:nth-child(2){
		width: 15%;
	}
	table tr td:nth-child(3){
		width: 25%;
	}
	table tr td:nth-child(4){
		width: 20%;
	}
	table tr td:nth-child(5){
		width: 15%;
	}
	table tr td:nth-child(6){
		width: 15%;
	}
</style>
<body>
<p class="title">活动记录查询</p>
<table>
	<tr>   
	 <td>编号</td>	
	 <td>活动名</td>
	 <td>协助事件</td>
	 <td>负责人</td>
	 <td>日期</td>
	 <td>操作</td>	 
	 </tr>
	<c:if test="${empty(pageBean.pageList)}">
		<tr>
			<td colspan='6'>暂无数据</td>
		</tr>
	</c:if>
    <c:forEach items="${pageBean.pageList}" var="sl" varStatus="num">
		<tr>
			<td>${num.count}</td>
			<td>${sl.sevent}</td>
			<td>${sl.ethings}</td>
			<td>${sl.ename}</td>
			<td>${sl.datatime.substring(0,11)}</td>
			<td><a href="javascript:del(${sl.eid})">删除</a></td>
		</tr>
	</c:forEach>
</table>
<p class="botton">
	<a href="/StudentUnion/EventServlet?opt=Showevent&cpage=${pageBean.cpage-1==0?1:pageBean.cpage-1}">上一页</a>
	<span>第${pageBean.cpage}/${pageBean.allPage }页</span>
	<a href="/StudentUnion/EventServlet?opt=Showevent&cpage=${pageBean.cpage==pageBean.allPage?pageBean.allPage:pageBean.cpage+1}">下一页</a>
</p>
<div class="mask">
	<div class="delWindow">
		<p>确定要删除本条记录吗？</p>
		<p class="delBottom">
			<a href="javascript:closeWindow()">取消</a>
			<a href="javascript:deletes()">确定</a>
		</p>
	</div>
</div>
<div class="finish">
	提示消息
</div>
</body>
<script type="text/javascript">
	$(function(){
		$("input").prop("spellcheck",false);
		$("table tr:even").css("background-color","#D5EAF3");
	});
	$(window).resize(function(){
		maskResize();
	});
	function del(eid){
		$(".delWindow a:last").prop("href","javascript:deletes("+eid+")");
		maskResize();
		$(".delWindow").fadeIn(300);
	    $(".mask").show(300);
	}
	function closeWindow(){
		$(".delWindow").fadeOut(300);
	    $(".mask").hide(300);
	}
	function maskResize(){
	    $(".mask").css("height",$(document).height());
	    $(".mask").css("width",$(window).width());
	}
	function deletes(opt){
    	$.get("/StudentUnion/EventServlet?opt=delete","param="+opt,function(data){
    		if(data==1){
    			$(".finish").text("删除成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
    			setTimeout(function(){
        			location.reload();
    			},2000);
    		}else{
    			$(".finish").text("删除失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
    		}
    		closeWindow();
    	});
	}
</script>
</html>