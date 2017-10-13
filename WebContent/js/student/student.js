//定义侧边栏链接数组
var links = [];
//定义侧边栏折叠状态
var folds = false;

//页面加载时顶部条文字行高居中
$(function(){
	var date = new Date().getFullYear()+"年"+(new Date().getMonth()+1)+"月"+new Date().getDate()+"日";
	$(".datetime").text(date);
	$(".top").css("line-height",$(".top").height()+"px");
	links = [
	         	"../StudentServlet?opt=selCla",
	         	"../StudentServlet?opt=selDorm&page=1",
	         	"../NoticeServlet?opt=getNotice&page=1",
	         	"../iframes/liveDept/updateP.jsp",
	         	"../UserServlet?opt=logout"
         	];
});

//侧边栏收起按钮显隐
$(".table").mouseover(function(){
	$(".table a:first").css("display","inline");
});
$(".table").mouseout(function(){
	$(".table a:first").css("display","none");
});

//窗口大小改变时顶部条文字行高居中
$(window).resize(function() {
	$(".top").css("line-height",$(".top").height()+"px");
});

//折叠侧边栏
function fold(){
	if(folds){
		folds = false;
		$(".menu").animate({'width':'16%'},310);
		$(".table a").animate({'left':'16%'},310);
		$(".top").animate({'height':'11%'},310);
		$(".table").animate({'width':'84%','height':'100%'},300);
		$(".menu").animate({'height':'100%'},300);
		$(".table a").html("&#xf104;");
		$(".table iframe").height("100%").width("100%");
		setTimeout(function(){
			$(".top").css("line-height",$(".top").height()+"px");
		},310);
	}else{
		folds = true;
		$(".menu").animate({'width':'0%'},300);
		$(".table a").animate({'left':'5px'},300);
		$(".top").animate({'height':'0%'},300);
		$(".table").animate({'width':'100%','height':'100%'},310);
		$(".menu").animate({'height':'100%'},310);
		$(".table a").html("&#xf105;");
		$(".table iframe").height("112%").width("100%");
	}
}


//侧边栏展开关闭动画
$(".menu div").click(function(){
	$(this).parent().find("div").css("border-bottom","1px solid #99D9EA");
	$(this).parent().find("div span").html("&#xf105;");

	if($(this).prop("class") == "close"){
		$(this).prop("class","open");
		$(this).find("span").html("&#xf105;");
	}else{
		$(this).prop("class","close");
		$(this).find("span").html("&#xf107;");
		$(this).css("border","none");
	}

	$(this).parent().find("ul").not($(this).next()).slideUp(200);
	$(this).parent().find("div").not($(this)).prop("class","open");
	
	$(this).next().slideToggle(200);
});

//点击侧边栏让iframe链接到对应页面
function linkTo(index,obj){
	$(".menu div").css("font-weight","normal");
	$(".menu ul li").removeClass("sel");
	$(obj).parent().prev().css("font-weight","bolder");
	$(obj).addClass("sel");
	$(".table iframe").prop("src",links[index]);
}