var d = new Date();
var sort = null;
var page = 1;
//网页加载完毕时，将表格偶数行颜色改变
$(function(){
	$("table tr:even").css("background-color","#D5EAF3");
	setDate();
});
$(".year").change(function(){
	page = 1;
	getMonth();
	saveDate();
});
$(".month").change(function(){
	page = 1;
	saveDate();
});
$(".dorm").change(function(){
	page = 1;
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
	var selday = "01";
	var number = $(".dorm option:selected").val();
	createTable(selyear+"-"+selmonth+"-"+selday,number);
}
//设置日期
function setDate(){
	//生成年
	$(".year").empty().append("<option value='"+d.getFullYear()+"' selected='selected'>"+d.getFullYear()+"年</option>")
					  .append("<option value='"+(d.getFullYear()-1)+"'>"+(d.getFullYear()-1)+"年</option>");
	getMonth();
	$(".month option").removeProp("selected");
	$(".month option[value='"+(d.getMonth()+1)+"']").prop("selected",true);
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
function createTable(date,number){
	if(page <= 0){
		page = 1;
		return;
	}else if(page > allpage && allpage != 0){
		page = allpage;
	}
	$.ajax({
        url: "/StudentUnion/StudentServlet",
        type: "POST",
        dataType: "json",
        data: {"opt":"selDorm","date":date,"building":number.substr(0,1),"number":number.substr(1,4),"ajax":"true","sort":sort,"page":page},
        success: function (data) {
        	$(".table table tr:not(:first)").remove();
        	if(data[0].length == 0){
        		var tr = "<tr><td colspan='7'>暂无数据</td></tr>";
       			$(".table table").append(tr);
        	}
         	$.each(data[0], function(i, n){
       			var tr  = "<tr>";
       				tr += "<td>"+(i+1+(page-1)*12)+"</td>";
					tr += "<td>"+n.date.substring(5,10)+"</td>";
   					tr += "<td>"+n.standarda+"</td>";
   					tr += "<td>"+n.standardb+"</td>";
   					tr += "<td>"+n.standardc+"</td>";
   					tr += "<td>"+n.standardd+"</td>";
   					tr += "<td>"+n.mark+"</td>";
       				tr += "</tr>";
       			$(".table table").append(tr);
        	});
         	allpage = data[1];
         	$(".load span").text("第"+page+"/"+allpage+"页");
        	$("table tr:even").css("background-color","#D5EAF3");
        }
	});
}