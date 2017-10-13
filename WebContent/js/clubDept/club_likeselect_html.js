$(function(){
	$("table tr:even").css("background-color","#D5EAF3");
});
$(window).resize(function(){
	maskResize();
});
function del(attid,cpage,cname){
	$(".delWindow a:last").prop("href","javascript:toremove("+attid+","+cpage+",'"+cname+"')");
	maskResize();
	$(".delWindow").fadeIn(300);
    $(".mask").show(300);
}
function closeWindow(){
	$(".editWindow,.delWindow").fadeOut(300);
    $(".mask").hide(300);
}
function maskResize(){
    $(".mask").css("height",$(document).height());
    $(".mask").css("width",$(window).width());
}
//根据id删除数据
function toremove(attid,cpage,cname){
	$.get("ClubDeptServlet?opt=toremove","attid="+attid,function(data){
		if(data==1){
			$(".finish").text("删除成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
			setTimeout(function(){
				location.href="ClubDeptServlet?opt=clubname_to_select&clubname="+cname+"&cpage="+cpage;
			},2000);
		}else{
			$(".finish").text("删除失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
		}
		closeWindow();
	});
}
//根据社团名字查询出对应的记录
function name_toselect(){
	var clubname=$("#club_name").val();
	location.href="ClubDeptServlet?opt=clubname_to_select&clubname="+clubname+"&cpage=1";
}
