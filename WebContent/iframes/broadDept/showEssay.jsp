<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/StudentUnion/css/broadDept/Essay.css">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
</head>
<body>
	<p class="title">文稿朗诵</p>
<!-- 	这是发布消息和模糊查询的  -->
	<div class="updata">
		<a href="javascript:addshow()">新增</a>
		<form class="seek" action="essay?opt=showData&cpage=1" method="post" >
			<input name="selectData" value="${param.selectData }">
			<a href="javascript:$('.seek').submit()">查询</a>
		</form>
	</div>
	<!-- 	这是消息显示 -->
		
		<table>
			<tr>
				<td>日期</td>
				<td>标题</td>
				<td>操作</td>
			</tr>
		<c:forEach var="e" items="${pageBean.pageList}" varStatus="ids" >
			<tr>
				<td>${e.etime.substring(5,10) }</td>
				<td><a href="javascript:show(${e.eid })">${e.etitle }</a></td>
				<td><a href="javascript:delshow(${e.eid })">删除</a></td>
			</tr>
		</c:forEach>
		<c:if test="${empty pageBean.pageList}">
			<tr><td colspan="3">暂无数据</td></tr>
		</c:if>
		</table>
<!-- 切换页 -->
	<p class="botton">
		<a href="essay?opt=showData&cpage=${pageBean.cpage-1<=0?1:pageBean.cpage-1}&selectData=${param.selectData }">上一页</a>
		<span>第${pageBean.cpage }/${pageBean.allPage }页</span>
		<a href="essay?opt=showData&cpage=${pageBean.cpage+1>pageBean.allPage?pageBean.allPage:pageBean.cpage+1 }&selectData=${param.selectData }">下一页</a>
	</p>
	<div class="mask">
		<div class="addWindow">
			<p class="title">添加文章</p>
			<input autofocus='autofocus' name="etitle" spellcheck="false"/>
			<textarea spellcheck="false" ></textarea>
			<p class="bottom">
				<span></span>
				<span>字数：0/2000</span>
				<a href="javascript:closeWindow()" class="close">取消</a>
				<a href="javascript:addData()">完成</a>
			</p>
		</div>
		<div class="showWindow">
			<p class="title"></p>
			<textarea spellcheck="false" readonly="readonly"></textarea>
			<p class="bottom">
				<a href="javascript:closeWindow()">关闭</a>
			</p>
		</div>
		<div class="delWindow">
			<p>确定要删除本条数据吗？</p>
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
<script>
var leng1 = 0;
var leng2 = 0;
// 打开添加框
$(function(){
	$("input").prop("spellcheck",false);
	$("table tr:even").css("background-color","#D5EAF3");
});
$(window).resize(function(){
	maskResize();
});
function maskResize(){
    $(".mask").css("height",$(document).height());
    $(".mask").css("width",$(window).width());
}
function show(eid){
	//异步取出对应id数值
	$.post("essay?opt=getData","id="+eid,function(data){
		$(".showWindow .title").text(data.etitle);
		$(".showWindow textarea").text(data.econtent);
	},"json");
	maskResize();
	$(".showWindow").fadeIn(300);
    $(".mask").show(300);
}
function addshow(){
	maskResize();
	$(".addWindow").fadeIn(300);
    $(".mask").show(300);
}
function delshow(eid){
	$(".delWindow a:last").prop("href","javascript:removerDate("+eid+")");
	maskResize();
	$(".delWindow").fadeIn(300);
    $(".mask").show(300);
}
function closeWindow(){
	$(".showWindow .title").val("");
	$("textarea:eq(0)").val("");
	$(".showWindow,.delWindow,.addWindow").fadeOut(300);
    $(".mask").hide(300);
}
$(".addWindow textarea").keyup(function(){
	$(".addWindow .bottom span:eq(0)").text("");
	leng1 = $(".addWindow textarea").val().length;
	if(leng1 <= 2000){
		$(".addWindow .bottom span:eq(1)").text("字数："+leng1+"/2000").css("color","#000");
	}else{
		$(".addWindow .bottom span:eq(1)").text("字数："+leng1+"/2000").css("color","#f00");
	}
});

// 添加新的数据
function addData(){
	var etitle = $(".addWindow input[name='etitle']").val();
	var econtent = $(".addWindow textarea").val();
	var param = "etitle="+etitle+"&econtent="+econtent;
	//进行异步操作
	$.post("essay?opt=addData",param,function(data){
		if(data==1){
			$(".finish").text("添加成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
			closeWindow();
			setTimeout(function(){
				location=location;
			},2000);
		}else{
			$(".finish").text("添加失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
		}
	});
}
//获取指定id
function getData(id){
	show();
	//异步取出对应id数值
	$.post("essay?opt=getData","id="+id,function(data){
		$("#showDiv h2").text(data.etitle);
		$("#showDiv p").text(data.econtent);
	},"json");
}
// 删除
function removerDate(id){
	$.post("essay?opt=removeData","id="+id,function(data){
		if(data==1){
			$(".finish").text("删除成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
			closeWindow();
			setTimeout(function(){
				location=location;
			},2000);
		}else{
			$(".finish").text("删除失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
		}
	});
}

</script>
</html>