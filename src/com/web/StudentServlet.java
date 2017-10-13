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

import com.dao.StudentDao;
import com.entity.StudentEntity;
import com.google.gson.Gson;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opt = request.getParameter("opt");
		switch(opt){
			case "selCla" : selCla(request,response); break;
			case "selDorm": selDorm(request,response);break;
		}
	}

	//班级寝室内务查询
	protected void selCla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String cid  = request.getParameter("cid");
		String ajax = request.getParameter("ajax");
		String sort = request.getParameter("sort");
		if(cid==null){
			StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
			cid = s.getScid();
			List cla = new StudentDao().getCla();
			request.setAttribute("cla", cla);
		}
		List dorminfo = new StudentDao().getForCla(date, cid, sort);
		
		//如果此方法为ajax方法调用的，则将数组对象转换为字符串，并响应ajax的请求并跳出方法
		if("true".equals(ajax)){
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(dorminfo));
			return;
		}
		request.setAttribute("dorminfo", dorminfo);
		request.setAttribute("cid", cid);
		request.getRequestDispatcher("iframes/student/sel_class.jsp").forward(request, response);
	}

	protected void selDorm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String building = request.getParameter("building");
		String number = request.getParameter("number");
		String ajax = request.getParameter("ajax");
		String sort = request.getParameter("sort");
		int page = Integer.parseInt(request.getParameter("page"));  //当前页数
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String cid = s.getScid();
		
		if(building == null || number == null){
			List list = new StudentDao().getDorm(cid);
			String[] dorm = (String[]) list.get(0);
			building = dorm[0];
			number = dorm[1];
			request.setAttribute("dorm", list);
		}
		
		List dorminfo = new StudentDao().getForDorm(date, building, number, cid, sort, page);
		int allpage = new StudentDao().getCount(date, building, number, cid);
		//如果此方法为ajax方法调用的，则将数组对象转换为字符串，并响应ajax的请求并跳出方法
		if("true".equals(ajax)){
			List ass = new ArrayList();
			ass.add(dorminfo);
			ass.add(allpage);
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(ass));
			return;
		}
		request.setAttribute("dorminfo", dorminfo);
		request.setAttribute("allpage", allpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("iframes/student/sel_dorm.jsp").forward(request, response);
	}

}
