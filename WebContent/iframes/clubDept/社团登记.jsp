<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/StudentUnion/js/jquery-1.9.1.min.js"></script>
<title>Insert title here</title>
<style>
	*{
		margin: 0px;
		padding: 0px;
		font-family: '幼圆';
	}
	.title{
		text-align: center;
		font-size: 24px;
		margin: 20px 0px;
	}
	table{
		width: 96%;
		margin: auto;
		border-collapse:collapse;
	}
	table tr td{
		height: 40px;
		border: 1px solid #1499F7;
		text-align: center;
		box-sizing: border-box;
	}
	input{
		width: 100%;
		height: 100%;
		border: none;
		text-align: center;
		outline: none;
		font-size: 15px;
		background-color: rgba(0,0,0,0);
	}
	.botton{
		width: 96%;
		text-align: right;
		margin: 20px auto;
	}
	
	a{
		margin: auto;
		border-radius: 3px;
		cursor: pointer;
		padding: 8px 16px;
		background-color: #1499F7;
		color: #FFF;
		text-decoration: none;
	}
	a:first-child{
		background-color: #F00;
		margin-right: 12px;
	}
	a:hover{
		opacity: 0.7;
	}
	
	table tr td:nth-child(1){
		width: 10%;
	}
	table tr td:nth-child(2){
		width: 15%;
	}
	table tr td:nth-child(4){
		width: 18%;
	}
	table tr td:nth-child(5){
		width: 18%;
	}
	table tr td:nth-child(6){
		width: 18%;
	}
	table tr td:nth-child(7){
		width: 21%;
	}
</style>
</head>
<body>
	<p class="title">出勤登记</p>
	<table id="myTab" border=1>
		<tr>
			<td>编号</td>
			<td>社团名称</td>
			<td>社团人数</td>
			<td>实到人数</td>
			<td>缺勤人数</td>
			<td>状态</td>
		</tr>
	</table>
	<p class="botton"><a href="javascript:inputEmpty()">清空</a><a href="javascript:tosubmit()">登记</a></p>
</body>
</html>
<script>
window.load=loadpage();
function loadpage(){
	$.get("/StudentUnion/ClubDeptServlet?opt=clubselect",function(data){
		if(data==0){
			alert("页面错误");
		}else{
			$.each(data,function(i,v){
				//动态生成表格，每一行具有每行数据的提交功能
				var tr="<tr>";
				tr+="<td>"+(i+1)+"</td>";
				tr+="<td><input name='clubname' readonly='readonly' value='"+v.clubname+"' /></td>";
				tr+="<td style='display:none;'><input name='clubid' value='"+v.clubid+"'></td>";
				tr+="<td><input name='peoplenum'></td>";
				tr+="<td><input name='peoplecomenum' onblur='sum_nocome(this)'/></td>";
				tr+="<td><input name='peoplenocome' readonly='readonly' value='0'></td><td class='warn_text'></td>";
				tr+="</tr>";
				$("#myTab").append(tr);
			});
			$("table tr:even").css("background-color","#D5EAF3");
		}
	},"json");
}
//点击按钮取出所有数据并提交到后台
	function tosubmit(){
	//获取有多少行
	var trlength = $("#myTab tr:not(:first)").length;
	var param="";
	var the_for_warn;//登记状态
	for(var i=1;i<=trlength;i++){
		toregist(i,param);
	}
}

//异步添加的方法
	function toregist(i,param){
		//点击登记按钮时，自动为为空的全部人数添加默认值（0）
		var default_zero_allnum=$("input[name=peoplenum]:eq("+(i-1)+")").val();
		if(default_zero_allnum==""){
			$("input[name=peoplenum]:eq("+(i-1)+")").val("0");
		}
		//点击登记按钮时，自动为为空的实到人数添加默认值（0）
		var default_zero_comenum=$("input[name=peoplecomenum]:eq("+(i-1)+")").val();
		if(default_zero_comenum==""){
			$("input[name=peoplecomenum]:eq("+(i-1)+")").val("0");
		}
		
		var tr=$("#myTab tr:eq("+i+")");//获取每一行
		for(var j=0;j<7;j++){//每一行有三列，循环4次，取出每列
			var firsttd=$("#myTab tr:eq("+i+") td:eq(0)").text();
			var td=$("#myTab tr:eq("+i+") td:eq("+j+") :input").val();
			//把每一行的值拼接成一个参数形式
			if(j==0){//第一行：社团名称
				param+="thistr="+firsttd;
			}
			if(j==1){//第一行：社团名称
				param+="&clubname="+td;
			}
			if(j==2){//第二行：社团id(隐藏域)
				param+="&clubid="+td;
			}
			if(j==3){//第三行：社团人数
				param+="&peoplenum="+td;
			}
			if(j==4){//第四行：实到人数
				param+="&peoplecomenum="+td;
			}
			if(j==5){//第五行：缺勤人数
				param+="&peoplenocome="+td;
			}
		}
		$.get("/StudentUnion/ClubDeptServlet?opt=shetuandengji",param,function(data){//异步添加到数据库
			if(data && data.success){//登记成功
				 $("#myTab tr:eq("+data.row+") .warn_text").text("成功").css({"color":"#10C910","font-weight":"bold"});
				var trlength = $("#myTab tr:not(:first)").length;
				for(var i=1;i<=trlength;i++){
					$("#myTab tr:eq("+i+") td:eq(4) input").val("");
					$("#myTab tr:eq("+i+") td:eq(5) input").val("");
				}
			}else{//登记失败
				 $("#myTab tr:eq("+data.row+") .warn_text").text("失败").css("color","red");
				var trlength = $("#myTab tr:not(:first)").length;
				for(var i=1;i<=trlength;i++){
					$("#myTab tr:eq("+i+") td:eq(4) input").val("");
					$("#myTab tr:eq("+i+") td:eq(5) input").val("");
				}
			}
		},"json");
	} 

//输入实到人数时自动计算缺勤人数
	function sum_nocome(opt){
		//计算缺勤人数
		var allpeople=$(opt).parent().prev().children().val();
		var comepeople=$(opt).val();
		var nocome_num=allpeople-comepeople;
		//赋值
		if(nocome_num>=0){//如果实到人数小于或等于总人数（正确）
			$(opt).parent().next().children().val(nocome_num);
		    //获取当前是第几行，然后给第几行的状态列赋值
		    var thetr=$(opt).parent().parent().children(":first").text();
		    $("#myTab tr:eq("+thetr+") .warn_text").text("");
		}
		if(nocome_num<0){
		    //获取当前是第几行，然后给第几行的状态列赋值
		    var thetr=$(opt).parent().parent().children(":first").text();
		    $("#myTab tr:eq("+thetr+") .warn_text").text("实到人数大于总人数");
		}
	}

	//点击清空按钮时清空表格的所有数据
	function inputEmpty(){
		$("input:not([name='clubname']):not([name='peoplenocome'])").each(function(i){
			$(this).val("");
			$(".warn_text").text("");
		});
		$("input[name='peoplenocome']").val("0");
	}

</script>