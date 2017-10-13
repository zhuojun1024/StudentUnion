$(function(){
	$("input").prop("spellcheck",false);
	$("table tr:even").css("background-color","#D5EAF3");
});

//提交表单数据（添加书籍）
function save(){
	var param = $("form").serialize();
	if(!$("input:eq(0)").val() || !$("input:eq(1)").val() || !$("input:eq(2)").val()){
		$(".finish").text("请填入数据").css("background-color","#F00").slideDown().delay(1000).slideUp();
		return;
	}
	$.post("/StudentUnion/MyServlet?opt=addBook&data="+Math.random(),param,function(data){
		if(data==1){
			$(".finish").text("添加成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
		}else{
			$(".finish").text("添加失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
		}
	});
}


