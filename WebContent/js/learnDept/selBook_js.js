$(function(){
	selBook();
	$("input").prop("spellcheck",false);
});
$(window).resize(function(){
	maskResize();
});
function maskResize(){
 $(".mask").css("height",$(document).height());
 $(".mask").css("width",$(window).width());
}
function returnshow(obj){
	$(".returnWindow a:last").prop("href","javascript:returned('"+$(obj.target).parent().prevAll().eq(3).text()+"')");
	maskResize();
	$(".returnWindow").fadeIn(300);
	$(".mask").show(300);
}
function borrowshow(it){
	var $it = $(it);
	$(".bno").text($it.parent().prevAll().eq(3).text());
	$(".bname").text($it.parent().prevAll().eq(2).text());
	$(".bauthor").text($it.parent().prevAll().eq(1).text());
	$(".borrowWindow a:last").prop("href","javascript:sure_loan()");
	maskResize();
	$(".borrowWindow").fadeIn(300);
	$(".mask").show(300);
}
function recordshow(){
	maskResize();
	$(".recordWindow").fadeIn(300);
	$(".mask").show(300);
}
function closeWindow(){
	lpage = 1;
	lpnum = 1;
	$(".sname").val("");
	$(".borrowWindow,.returnWindow,.recordWindow").fadeOut(300);
	$(".mask").hide(300);
}
var page = 1;//当前页码
var pnum = 1;//总页码
var lpage = 1;//日志当前页码
var lpnum = 1;//日志总页码
//查询书籍
function selBook(){
	var name = $("input[name=name]").val();
	if(name.length >0) page=1;
	$.post("/StudentUnion/MyServlet?opt=selBook&data="+Math.random(),"name="+name+"&page="+page,function(data){
		$(".maintable tr:not(:first)").remove();
		pnum = data[0].pnum;
		var str = "";
		$.each(data,function(i,v){
			str += "<tr><td>"+(i+1)+"</td>";
			str += 	"<td>"+v.no+"</td>";
			str += "<td>"+v.name+"</td>";
			str += "<td>"+v.author+"</td>" ;
			str += 	"<td>"+v.state+"</td>";
			switch(v.state){
				case "待借":str += "<td><a href='javascript:;' onclick='borrowshow(this)'>借出</a></td>";break;
				case "借出":str += "<td><a href='javascript:;' onclick='returnshow(event)'>归还</a></td>";break;
				case "损坏":str += "<td><a href='javascript:;' class='disabled'>损坏</a></td>";break;
				case "丢失":str += "<td><a href='javascript:;' class='disabled'>丢失</a></td>";break;
			}
			str += "<td><a href='javascript:;' onclick='viewLog(event.target.parentNode.parentNode.cells[1].innerText)'>借还记录</a></td>";
			str += "</tr>";
		});
		if(data == 0) str +="<tr><td colspan='6'>暂无记录</td></tr>";
		$(".maintable tr:last").after(str);
		str = "<a href='javascript:anone(\"上一页\")'>上一页</a>第" +
				page + "/" + pnum +
				"页<a href='javascript:anone(\"下一页\")'>下一页</a>";
		$(".botton").empty().append(str);
		$("table tr:even").css("background-color","#D5EAF3");
	},"json");
}

//借书div显示
function loan(it){
}

//关闭弹出的div
function close_ale(){
	$("#ale").css("display","none");
}

//借出书籍
function sure_loan(){
	var param = {
		no:$(".bno").text(),
		sname:$(".sname").val(),
		outtime:""
	};
	if(param.sname.length == 0){
		$(".finish").text("请输入学号").css("background-color","#F00").slideDown().delay(1000).slideUp();
		return;
	}
	param = $.param(param);
	$.post("/StudentUnion/MyServlet?opt=loanBook&data="+Math.random(),param,function(data){
		if(data==1){
			$(".finish").text("借书成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
		} else{
			$(".finish").text("借书失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
		}
		closeWindow();
		selBook();
	});
}

//归还书籍
function returned(e){
	$.post("/StudentUnion/MyServlet?opt=returnBook&data="+Math.random(),"ret="+e,function(data){
		if(data==1){
			$(".sname").val("");
			$(".finish").text("归还成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
		} else{
			$(".finish").text("归还失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
		}
		closeWindow();
		selBook();
	});
}

//查看记录
function viewLog(txt){
	recordshow();
	$(".record").empty().css("display","block");
	$.post("/StudentUnion/MyServlet?opt=viewLog&data="+Math.random(),"log="+txt+"&lpage="+lpage,function(data){
		var strs = "<table border=1>";
		strs += "<tr><td>序号</td><td>借书人学号</td><td>借书人姓名</td><td>借书时间</td><td>还书时间</td></tr>";
		$.each(data,function(i,v){
			lpnum = data[0].pnum;
			if(v.intime==undefined){
				v.intime = "暂未归还";
			}
			strs += "<tr>";
			strs += "<td>"+((lpage-1)*10+i+1)+"</td>";
			strs += "<td>"+v.stuno+"</td>";
			strs += "<td>"+v.stuname+"</td>";
			strs += "<td>"+v.outtime.substring(0,16)+"</td>";
			strs += "<td>"+v.intime.substring(0,16)+"</td>";
			strs += "</tr>";
		});
		if(data == 0) strs +="<tr><td colspan='5'>暂无记录</td></tr>";
		strs += "</table>";
		$(".record").append(strs);
		$(".recordWindow .title").text("借书记录");
		$(".recordWindow .bottom").empty().append("<a href='javascript:;' onclick='logPage(text,\""+txt+"\");'>上一页</a>第" +
			lpage + "/" + lpnum + 
			"页<a href='javascript:;' onclick='logPage(text,\""+txt+"\");'>下一页</a>" +
			"<a href='javascript:closeWindow()' class='close'>关闭</a>");
		$("table tr:even").css("background-color","#D5EAF3");
	},"json");
}

//弹出的借书界面，在输入框内按回车则提交数据
$(document).keyup(function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if(e && e.keyCode==13){//按下回车
		if($("#ale_loan input[type=text]").is(":focus")){//账号输入框或密码输入框是否拥有焦点
			sure_loan();//提交表单
		}
	}
});

//分页 ，上一页和下一页的范围
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
		default:page=1;break;
	}
	selBook();
}

function logPage(str,txt){
	switch(str){
	case "上一页":
		if(lpage > 1){
			lpage--;
		}
		break;
	case "下一页":
		if(lpage < lpnum){
			lpage++;
		}
		break;
	}
	viewLog(txt);
}
