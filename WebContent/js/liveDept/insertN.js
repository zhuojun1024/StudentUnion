
var leng1 = 0;
var leng2 = 0;
$("textarea:eq(0)").keydown(function(){
	leng1 = $("textarea:eq(0)").val().length;
	if(leng1 <= 2000){
		$(".bottom span:eq(0)").html("字数："+leng1+"/2000&nbsp;&nbsp;发布到:").css("color","#000");
	}else{
		$(".bottom span:eq(0)").html("字数："+leng1+"/2000&nbsp;&nbsp;发布到:").css("color","#f00");
	}
});
function addNotice(region){
	//停止动画
	$(".finish").stop(true,true);
	//字数限制
	leng1 = $("textarea:eq(0)").val().length;
	leng2 = $("input[name='ntitle']").val().length;
	if(leng2 > 32){
		$(".finish").text("标题字数超出32字").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
		return;
	}else if(leng1 > 2000){
		$(".finish").text("内容字数超出2000字").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
		return;
	}else if(!leng1||!leng2){
		$(".finish").text("请输入内容及标题").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
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
        data: {"opt":"addNotice","text":text,"title":title,"region":region},
        success: function (data) {
   			if(data.length > 0){
 				$(".finish").text("发布成功").css("background-color","#00C900").slideDown().delay(1000).slideUp();
 				$("input[name='ntitle']").val("");
 				$("textarea:eq(0)").val("");
   			}else{
   				$(".finish").text("发布失败").css("background-color","#FF0000").slideDown().delay(1500).slideUp();
   			}
        }  
     });  
}