//定义楼层标记
var floor = 0;
var d = new Date();
//网页加载完毕时，关闭所有输入框的拼写检查，并将表格偶数行颜色改变
$(function(){
	$("input").prop("spellcheck",false);
	$(".table tr:even").css("background-color","#D5EAF3");
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
function saveDate(){
	var selyear = $(".year option:selected").val();
	var selmonth = $(".month option:selected").val();
	var selday = $(".day option:selected").val();
	createTable(selyear+"-"+selmonth+"-"+selday);
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
//加载数据库数据并创建表格
function createTable(date){
    $.ajax({
        url: "/StudentUnion/DormServlet",
        type: "POST",
        dataType: "json",
        data: {"opt":"getDormInfo","building":building,"floor":$(".sel").text().substr(0,1),"ajax":"true","date":date},
        success: function (data) {
        	$(".table table tr:not(:first)").remove();
			$("input[name='liable']").val("");
			//如果当天没有登记记录，则查询寝室的班级信息
        	if(data[0].id == null){
        		var num = parseInt($(".sel").text().substr(0,1))*100+1;
        		$(".liable span:first").text("未登记").prop("class","colorblue");
        		$(".right a:last").text("保存");
             	$.each(data, function(i, n){
           			var tr  = "<tr>";
       					tr += "<td><input name='number' readonly='readonly' value="+building.toUpperCase()+"-"+(num+i)+" /></td>";
       					tr += "<td class='cid"+n[0]+"'><input name='cname' readonly='readonly' value="+n[1]+" /></td>";
       					tr += "<td><input name='standarda' /></td>";
       					tr += "<td><input name='standardb' /></td>";
       					tr += "<td><input name='standardc' /></td>";
       					tr += "<td><input name='standardd' /></td>";
       					tr += "<td><input name='mark' value='100'/></td>";
           				tr += "</tr>";
           			$(".table table").append(tr);
            	});
             
             //如果当天已经登记过，则直接取出数据
        	}else{
    			$("input[name='liable']").val(data[0].liable);
        		$(".liable span:first").text("已登记").prop("class","colorgreen");
        		$(".right a:last").text("更新");
             	$.each(data, function(i, n){
           			var tr  = "<tr>";
       					tr += "<td><input name='number' readonly='readonly' value="+building.toUpperCase()+"-"+n.number+" /></td>";
       					tr += "<td class='cid"+n.cid+"'><input name='cname' readonly='readonly' value="+n.cname+" /></td>";
       					tr += "<td><input name='standarda' value='"+n.standarda+"' /></td>";
       					tr += "<td><input name='standardb' value='"+n.standardb+"' /></td>";
       					tr += "<td><input name='standardc' value='"+n.standardc+"' /></td>";
       					tr += "<td><input name='standardd' value='"+n.standardd+"' /></td>";
       					tr += "<td><input name='mark' value='"+n.mark+"' /></td>";
           				tr += "</tr>";
           			$(".table table").append(tr);
            	});
        	}
        	
        	//关闭所有输入框的拼写检查，并将表格偶数行颜色改变
    		$("input").prop("spellcheck",false);
    		$(".table tr:even").css("background-color","#D5EAF3");
        }
     });
}

//点击楼层时改变当前楼层的显示样式，调用创建表格方法
function toF(f){
	$(".center a").removeClass("sel");
	$(".center a:eq("+(f-1)+")").addClass("sel");
	saveDate();
}

//点击清空按钮时清空表格的所有数据
function inputEmpty(){
	$("input:not([name='number']):not([name='cname'])").each(function(i){
		$(this).val("");
	});
	$("tr").find("input:last").val("100");
}

//点击保存按钮时保存数据
function saveTable(autosave){
	//判断是否填有数据
	var allvalue = 0;
	$("input").each(function(i){
		if($(this).val().length > 0) allvalue++;
	});
	if($("input[name='liable']").val().length == 0){
		$(".finish").text("请填写负责人").css("background-color","#FF0000").slideDown().delay(1000).slideUp();
		return;
	}else if(allvalue < 46){
		$(".finish").text("请至少填写得分列").css("background-color","#FF0000").slideDown().delay(1000).slideUp();
		return;
	}
	//创建JSON字符串，并保存表格数据
	var selyear = $(".year option:selected").val();
	var selmonth = $(".month option:selected").val();
	var selday = $(".day option:selected").val();
	var date = selyear+"-"+selmonth+"-"+selday;
	var jsonstr = "{";
	for(var i = 1; i <= 15; i++){
		var $row = $("form table tr:eq("+i+")");
		var liable = $("input[name='liable']").val().replace(/\"/g,"“");
		var cid = $row.find("td:eq(1)").prop("class").substr(3,2);
		var cname = $row.find("input[name='cname']").val().replace(/\"/g,"“");
		var standarda = $row.find("input[name='standarda']").val().replace(/\"/g,"“");
		var standardb = $row.find("input[name='standardb']").val().replace(/\"/g,"“");
		var standardc = $row.find("input[name='standardc']").val().replace(/\"/g,"“");
		var standardd = $row.find("input[name='standardd']").val().replace(/\"/g,"“");
		var mark = $row.find("input[name='mark']").val().replace(/\"/g,"“");
		jsonstr += "\"num"+i+"\":[\""+cid+"\",\""+cname+"\",\""+standarda+"\",\""+standardb+"\",\""+standardc+"\",\""+standardd+"\",\""+mark+"\"],";
	}
	jsonstr += "\"building\":\""+building+"\",\"floor\":\""+$(".sel").text().substr(0,1)+"\",\"liable\":\""+liable+"\",\"date\":\""+date+"\"}";
	//alert(jsonstr);
	
	//将 数据发送到服务器
    $.ajax({
        url: "/StudentUnion/DormServlet",
        type: "POST",
        data: {"jsonstr":jsonstr,"opt":"addDormInfo"},
        success: function (data) {
        	if(data == 15){
        		$(".finish").text("成功").css("background-color","#00C900").slideDown().delay(1000).slideUp();
        		saveDate();
        	}else{
        		$(".finish").text("出错，数据未能保存").css("background-color","#FF0000").slideDown().delay(1000).slideUp();
        	}
        }
     });
}