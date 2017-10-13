$(function(){
	$("input").prop("spellcheck",false);
	$("table tr:even").css("background-color","#D5EAF3");
});
//提交表单
function save(){
	var param = $("form:first").serialize();
	if(!$("input:eq(0)").val()){
		$(".finish").text("请填入学号").css("background-color","#F00").slideDown().delay(1000).slideUp();
		return;
	}else if($("select option:eq(0):selected").length==1){
		$(".finish").text("请选择班级").css("background-color","#F00").slideDown().delay(1000).slideUp();
		return;
	}
	$.post("/StudentUnion/MyServlet?opt=insAss&data="+Math.random(),param,function(data){
		if(data == 1){
			$(".finish").text("添加成功").css("background-color","#10C910").slideDown().delay(1000).slideUp();
		}else{
			$(".finish").text("添加失败").css("background-color","#F00").slideDown().delay(1000).slideUp();
		}
		$("input:eq(0)").val("");
		$("select option:eq(0)").prop("selected",true);
	});
}

//查询可选班级
$(document).ready(function(){
	$.post("/StudentUnion/MyServlet?opt=selcla",null,function(data){
			$.each(data, function(i,v){
				$("option:last").after("<option value='"+v+"'>"+v+"</option>");
			});
	},"json");
});

