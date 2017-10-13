$(function(){
	sel();
});
$(window).resize(function(){
	maskResize();
});
function maskResize(){
 $(".mask").css("height",$(document).height());
 $(".mask").css("width",$(window).width());
}
function delshow(aid){
	$(".delWindow a:last").prop("href","javascript:delAss("+aid+")");
	maskResize();
	$(".delWindow").fadeIn(300);
	$(".mask").show(300);
}
function updateshow(obj,aid){
	$(".updateWindow a:last").prop("href","javascript:save("+aid+")");
	var $this = $(obj).parent().prevAll();
	$(".asno").text($this.eq(3).text())
	$(".bname").text($this.eq(2).text());
	$(".bauthor").text($this.eq(1).text());
	$(".sclass").text($this.eq(0).text());
	maskResize();
	$(".updateWindow").fadeIn(300);
	$(".mask").show(300);
}
function closeWindow(){
	$(".sname").val("");
	$(".delWindow,.updateWindow").fadeOut(300);
	$(".mask").hide(300);
}
var page = 1;
var pnum = 10;
//查询的助教信息
function sel(){
	var val = $("input[name='sel_info']").val();
	$.post("/StudentUnion/MyServlet?opt=selAss&data="+Math.random(),"val="+val+"&page="+page,function(data){
		$(".ass_info tr:not(:first)").remove();
		var str = "";
		$.each(data,function(i,v){
			str += "<tr>";
			str += "<td>"+((page-1)*10+i+1)+"</td>";
			str += "<td>"+v.asno+"</td>";
			str += "<td>"+v.asname+"</td>";
			str += "<td>"+v.asclass+"</td>";
			str += "<td>"+v.aclass+"</td>";
			str += "<td><a href='javascript:;' onclick='updateshow(this,"+v.aid+")'>变更</a></td>";
			str += "<td><a href='javascript:;' onclick='delshow("+v.aid+")'>删除</a></td>";
			str += "</tr>";	
		});
		if(data == 0){
			str +="<tr><td colspan='7'>暂无记录</td></tr>";
			$(".ass_info").append(str);
			$("table tr:even").css("background-color","#D5EAF3");
			return;
		}else{
			pnum = data[0].apnum
		}
		$(".ass_info").append(str);
		str = "<a href='javascript:;' onclick='anone(\"上一页\")'>上一页</a>第" +
				page + "/" + pnum +
				"页<a href='javascript:;' onclick='anone(\"下一页\")'>下一页</a>";
		$(".botton").empty().append(str);
		$("table tr:even").css("background-color","#D5EAF3");
	},"json");
}

//删除助教信息
function delAss(str){
	$.post("/StudentUnion/MyServlet?opt=delAss&data="+Math.random(),"aid="+str+"",function(data){
		if(data == 1){
			$(".finish").text("删除成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
		} else{
			$(".finish").text("删除失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
		}
		closeWindow();
		sel();
	});
}

//变更信息按钮
function updAss(str){
	$("#upd_inf").css("display","block");
	var $this = $(str).parent().prevAll();
	for(var i = 0; i < $this.length; i++){
		$("form").children("input, select").eq(i).val($this.eq($this.length-1-i).text());
	}
	$("form span").text($this.eq(2).text());
}

//修改信息
function save(aid){
		var param = "aid="+aid;
			param += "&asno="+$(".asno").text();
			param += "&bname="+$(".bname").text();
			param += "&bauthor="+$(".bauthor").text();
			param += "&aclass="+$(".aclass").val();
		$.post("/StudentUnion/MyServlet?opt=updAss&data="+Math.random(),param,function(data){
			if(data == 1){
				$(".finish").text("变更成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
			} else{
				$(".finish").text("变更失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
			}
			closeWindow();
			sel();
		});
}

//隐藏变更信息div
function none(){
	$("#upd_inf").css("display","none");
}

//上一页下一页
function anone(str){
	switch(str){
	case "上一页":
		if(page > 1){
			page--;
		}
		break;
	case "下一页":
		if(page < pnum){
			page++;
		}
		break;
	}
	sel();
}

//查询可选班级
$(document).ready(function(){
	$.post("/StudentUnion/MyServlet?opt=selcla",null,function(data){
//		for(var i = 0; i < $("select").length; i++){
			$.each(data, function(i,v){
				$("select").append("<option value='"+v+"'>"+v+"</option>");
			});
//		}
	},"json");
});

