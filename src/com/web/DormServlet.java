package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DormDao;
import com.entity.DormEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/DormServlet")
public class DormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DormServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写service方法，根据opt参数调用对应的方法
		String opt = request.getParameter("opt");
		switch(opt){
			case "getDormInfo" : getDormInfo(request,response);break;
			case "addDormInfo" : addDormInfo(request,response);break;
		}
	}

	//获取寝室登记信息
	protected void getDormInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取网页数据
		String building = request.getParameter("building");  //栋别
		String floor = request.getParameter("floor");  //楼层
		String ajax   = request.getParameter("ajax");  //判断是否为ajax调用
		String date = request.getParameter("date");
		
		//调用获取寝室登记信息的方法
		List dorminfo = new DormDao().getDormInfo(building, floor, date);
		
		//如果寝室登记信息不存在，则获取寝室班级信息
		List dormclass = null;
		if(dorminfo.size() == 0){
			dormclass = new DormDao().getDormClass(building, floor);
		}
		
		//如果此方法为ajax方法调用的，则将数组对象转换为字符串，并响应ajax的请求并跳出方法
		if("true".equals(ajax)){
			PrintWriter out = response.getWriter();
			if(dorminfo.size() == 0){
				out.print(new Gson().toJson(dormclass));
			}else{
				out.print(new Gson().toJson(dorminfo));
			}
			return;
		}
		
		//保存寝室班级信息、寝室登记信息、寝室楼层，并转发到网页
		request.setAttribute("dormclass", dormclass);
		request.setAttribute("dorminfo", dorminfo);
		request.setAttribute("building", building);
		request.getRequestDispatcher("iframes/liveDept/insertDormInfo.jsp").forward(request, response);
	}

	//添加寝室登记信息
	protected void addDormInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取json字符串
		String jsonstr = request.getParameter("jsonstr");
		//将json转换为对象
		JsonObject obj = new JsonParser().parse(jsonstr).getAsJsonObject();
		//获取栋别、楼层、负责人
		String building = obj.get("building").getAsString();
		int floor = obj.get("floor").getAsInt();
		String liable = obj.get("liable").getAsString();
		String date = obj.get("date").getAsString();
		//创建完成标记
		int finish = 0;
		
		//遍历json对象，并存储到list当中
		List list = new ArrayList();
		for(int i = 1; i <= 15; i++){
			DormEntity d = new DormEntity();
			JsonArray j = obj.get("num"+i).getAsJsonArray();
			d.setBuilding(building);
			d.setNumber(""+(floor*100+i));
			d.setCid(j.get(0).getAsString());
			d.setCname(j.get(1).getAsString());
			d.setStandarda(j.get(2).getAsString());
			d.setStandardb(j.get(3).getAsString());
			d.setStandardc(j.get(4).getAsString());
			d.setStandardd(j.get(5).getAsString());
			d.setMark(j.get(6).getAsString());
			d.setLiable(liable);
			list.add(d);
		}
		
		//遍历List数组，并调用添加或者更新方法（取决于数据是否存在）
		if(new DormDao().getDormInfo(building,""+floor,date).size() == 0){
			for(int i = 0; i < 15; i++){
				if(new DormDao().addDormInfo((DormEntity)list.get(i),date)){
					finish++;
				}
			}
		}else{
			for(int i = 0; i < 15; i++){
				if(new DormDao().updateDormInfo((DormEntity)list.get(i),date)){
					finish++;
				}
			}
		}
		
		//将标记成功的次数返回给网页
		PrintWriter out = response.getWriter();
		out.println(finish);
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
