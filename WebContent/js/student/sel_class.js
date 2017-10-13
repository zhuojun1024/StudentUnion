var d = new Date();
var sort = null; 
//网页加载完毕时，将表格偶数行颜色改变
$(function(){
	$("table tr:even").css("background-color","#D5EAF3");
	setDate();
});
$(".year").change(function(){
	getMonth();
	saveDate();
});
$(".month").change(function(){
	getDay();
	if($(".month option[value='"+(d.getMonth()+1)+"']:selected").length == 1){
		$(".day option[value='"+d.getDate()+"']").prop("selected",true);
	}
	saveDate();
});
$(".day").change(function(){
	saveDate();
});
$(".cla").change(function(){
	saveDate();
});
function sortTo(obj,s){
	$(".right a").css("background-color","#1499F7");
	$(obj).css("background-color","#10C910");
	sort = s;
	saveDate();
}
function saveDate(){
	var selyear = $(".year option:selected").val();
	var selmonth = $(".month option:selected").val();
	var selday = $(".day option:selected").val();
	var cid = $(".cla option:selected").val();
	createTable(selyear+"-"+selmonth+"-"+selday,cid);
}
//设置日期
function setDate(){
	//生成年
	$(".year").empty().append("<option value='"+d.getFullYear()+"' selected='selected'>"+d.getFullYear()+"年</option>")
					  .append("<option value='"+(d.getFullYear()-1)+"'>"+(d.getFullYear()-1)+"年</option>");
	getMonth();
	$(".month option").removeProp("selected");
	$(".month option[value='"+(d.getMonth()+1)+"']").prop("selected",true);
	getDay();
	$(".day option").removeProp("selected");
	$(".day option[value='"+d.getDate()+"']").prop("selected",true);
}
//生成月
function getMonth(){
	//获取当前月份并设置为选中
	var selyear = $(".year option:selected").val();
	var fullmonth = 11;
	if(selyear == d.getFullYear()){
		fullmonth = new Date(selyear,d.getMonth()+1,0).getMonth();
	}else{
		fullmonth = 11;
	}
	$(".month").empty();
	for(var i = 0; i <= fullmonth; i++){
		$(".month").append("<option value='"+(i+1)+"'>"+(i<9?'0'+(i+1):(i+1))+"月</option>");
	}
}
//生成日
function getDay(){
	var selyear = $(".year option:selected").val();
	var selmonth = $(".month option:selected").val();
	var fullday = new Date(selyear,selmonth,0).getDate();
	if(selmonth == (d.getMonth()+1)){
		fullday = new Date(selyear,selmonth,d.getDate()).getDate();
	}
	$(".day").empty();
	for(var i = 1; i <= fullday; i++){
		$(".day").append("<option value='"+i+"'>"+(i<10?'0'+i:i)+"日</option>");
	}
}
function createTable(date,cid){
	$.ajax({
        url: "/StudentUnion/StudentServlet",
        type: "POST",
        dataType: "json",
        data: {"opt":"selCla","date":date,"cid":cid,"ajax":"true","sort":sort},
        success: function (data) {
        	$(".table table tr:not(:first)").remove();
        	if(data.length == 0){
        		var tr = "<tr><td colspan='7'>暂无数据</td></tr>";
       			$(".table table").append(tr);
       			return;
        	}
         	$.each(data, function(i, n){
       			var tr  = "<tr>";
					tr += "<td>"+(i+1)+"</td>";
					tr += "<td>"+n.building.toUpperCase()+"-"+n.number+"</td>";
   					tr += "<td>"+n.standarda+"</td>";
   					tr += "<td>"+n.standardb+"</td>";
   					tr += "<td>"+n.standardc+"</td>";
   					tr += "<td>"+n.standardd+"</td>";
   					tr += "<td>"+n.mark+"</td>";
       				tr += "</tr>";
       			$(".table table").append(tr);
        	});
        	$("table tr:even").css("background-color","#D5EAF3");
        }
	});
}