
var leng1 = 0;
var leng2 = 0;
$(window).resize(function(){
	maskResize();
});
function maskResize(){
    $(".mask").css("height",$(document).height());
    $(".mask").css("width",$(window).width());
}
$("textarea:eq(0)").keyup(function(){
	$(".bottom span:eq(0)").text("");
	leng1 = $("textarea:eq(0)").val().length;
	if(leng1 <= 2000){
		$(".bottom span:eq(1)").text("字数："+leng1+"/2000").css("color","#000");
	}else{
		$(".bottom span:eq(1)").text("字数："+leng1+"/2000").css("color","#f00");
	}
});
function editNotice(nid){
	//字数限制
	leng1 = $("textarea:eq(0)").val().length;
	leng2 = $("input[name='ntitle']").val().length;
	if(leng2 > 32){
		$(".bottom span:eq(0)").text("标题字数超出32字").css("color","#FF0000");
		return;
	}else if(leng1 > 2000){
		$(".bottom span:eq(0)").text("内容字数超出2000字").css("color","#FF0000");
		return;
	}else if(!leng1||!leng2){
		$(".bottom span:eq(0)").text("请输入内容及标题").css("color","#FF0000");
		return;
	}
	//换行及空格处理
	var title = $("input[name='ntitle']").val();
	var text = $("textarea:eq(0)").val().replace(/\n/g, '_@').replace(/\r/g, '_#');
	text = text.replace(/_@/g, '<br/>');
	text = text.replace(/\s/g, '&nbsp;');
	
	//序列化要传输的数据
    $.ajax({
        url: "/StudentUnion/NoticeServlet",
        type: "POST",
        data: {"opt":"editNotice","nid":nid,"text":text,"title":title},
        success: function (data) {
   			if(data.length > 0){
   				closeWindow();
 				$(".finish").text("编辑成功").css("background-color","#00C900").slideDown().delay(1000).slideUp();
 				setTimeout(function(){
 					window.location.reload();
 				},2000);
   			}else{
   				$(".finish").text("编辑失败").css("background-color","#FF0000").slideDown().delay(1000).slideUp();
   			}
        }  
     });  
}
function edit(nid){
	$(".editWindow a:last").prop("href","javascript:editNotice("+nid+")");
	$("input[name='ntitle']").val($(".n"+nid).parent().prev().find("legend").text());
	//换行及空格还原
	var content = $(".n"+nid).parent().prev().find(".content").html();
	content = content.replace(/<br>/g, '\n');
	content = content.replace(/\&nbsp;/g, ' ');
	
	$("textarea:eq(0)").val(content);
	maskResize();
	$(".editWindow").fadeIn(300);
    $(".mask").show(300);
    $("textarea:eq(0)").keyup();
}
function closeWindow(){
	$("input[name='ntitle']").val("");
	$("textarea:eq(0)").val("");
	$(".editWindow,.delWindow").fadeOut(300);
    $(".mask").hide(300);
}
function del(nid){
	$(".delWindow a:last").prop("href","javascript:delNotice("+nid+")");
	maskResize();
	$(".delWindow").fadeIn(300);
    $(".mask").show(300);
}
function delNotice(nid){
    $.ajax({
        url: "/StudentUnion/NoticeServlet",
        type: "POST",
        data: {"opt":"delNotice","nid":nid},
        success: function (data) {
   			if(data.length > 0){
   				closeWindow();
 				$(".finish").text("删除成功").css("background-color","#00C900").slideDown().delay(1000).slideUp();
 				setTimeout(function(){
 					window.location.reload();
 				},2000);
   			}else{
   				$(".finish").text("删除失败").css("background-color","#FF0000").slideDown().delay(1000).slideUp();
   			}
        }
     });
}