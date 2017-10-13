$(function(){
	getIns();
});

function getIns(){
	$.ajax({
		url: "/StudentUnion/InspectionServlet",
		type: "POST",
		dataType: "JSON",
		data: {"opt":"getIns"},
		success: function(date){
			if(date.length == 0){
				alert("没有数据");
				return;
			}
			$(".table table tr:not(:first)").remove();
			$.each(date,function(i,v){
				var tr = "<tr>";
					tr += "<td class='c"+v.icid+"'>"+v.cname+"</td>";
					tr += "<td>"+v.iall+"</td>";
					tr += "<td><input name='ione' value='"+v.ione+"' /></td>";
					tr += "<td><input name='itwo' value='"+v.itwo+"' /></td>";
					tr += "<td><input name='istandarada' value='"+v.istandarada+"' /></td>";
					tr += "<td><input name='istandaradb' value='"+v.istandaradb+"' /></td>";
					tr += "<td><input name='istandaradc' value='"+v.istandaradc+"' /></td>";
					tr += "<td><input name='istandaradd' value='"+v.istandaradd+"' /></td>";
					tr += "<td><input name='istandarade' value='"+v.istandarade+"' /></td>";
					tr += "<td><input name='imark' value='"+v.imark+"' /></td>";
					tr += "<td><input name='iclaperson' value='"+v.iclaperson+"' /></td>";
					tr += "</tr>";
				$(".table table").append(tr);
			});
		}
	});
}