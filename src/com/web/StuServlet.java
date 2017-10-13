package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StyleStuDao;
import com.entity.Stu;
import com.google.gson.Gson;

/**
 * 处理学生的所有增，删，改，查
 */
@WebServlet("/StuServlet")
public class StuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public StuServlet() {
        super();
    }
    
    //重写service自定义转发规�?
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	String opt=request.getParameter("opt");
    	if("Stushow".equals(opt)){
    		doStuShow(request,response);
    	}else if("addStu".equals(opt)){
    		doAddStu(request,response);
    	}else if("remove".equals(opt)){
    		doRemove(request,response);
    	}else if("stuById".equals(opt)){
    		doStuById(request,response);
    	}else if("updateStu".equals(opt)){
    		doUpdateStu(request,response);
    	}else if("stuById2".equals(opt)){
    		doStuById2(request,response);
    	}else if("Stushow2".equals(opt)){
    		doStuShow(request,response);
    	}
	}
    
    //查询�?��策划方案信息show
	protected void doStuShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用方法查询出所有信�?
			List stuList = new StyleStuDao().getStuList();
			//将查询结果以JSON字符串形式响应到客户�?
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(stuList));
			out.close();
				
	}
	
	//添加申请
	protected void doAddStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取�?
		String stitle = request.getParameter("stitle");
		String sncontent = request.getParameter("sncontent");
		String stimes = request.getParameter("stime");
		String sleader = request.getParameter("sLeader");
		String shelp = request.getParameter("shelp");
		String scontact = request.getParameter("scontact");
		String sremarks = request.getParameter("sremarks");
		
		//封装到对象中
		Stu stu = new Stu(0,stitle,sncontent,stimes,sleader,shelp,scontact,sremarks);
		
		//调用Dao方法执行添加操作
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if(new StyleStuDao().addStu(stu)){
					out.println("true");
				}else{
					out.println("false");
				}
			out.close();
	}
	
	//根据标题删除申请
	protected void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id=Integer.parseInt(request.getParameter("id"));
		//将结果以JSON格式响应到客户端
		PrintWriter out = response.getWriter();
		if(new StyleStuDao().deleteStuById(id)){
			out.print(1);
		}else{
			out.print(0);
		}
			
	}
		
	//修改申请
	protected void doUpdateStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取请求过来的�?
		int id=Integer.parseInt(request.getParameter("id"));
		String stitle = request.getParameter("stitle");
		String sncontent = request.getParameter("sncontent");
		String stime = request.getParameter("stime");
		String sleader = request.getParameter("sleader");
		String shelp = request.getParameter("shelp");
		String scontact = request.getParameter("scontact");
		String sremarks = request.getParameter("sremarks");
		
		//封装到Stu数组�?
		Stu stu = new Stu(id,stitle,sncontent,stime,sleader,shelp,scontact,sremarks);
		
		//调用StuDao方法执行修改操作
		PrintWriter out = response.getWriter();
		if(new StyleStuDao().updateStu(stu)){
			out.println(1);
		}else{
			out.println(0);
		}
		out.close();
	}
	
	
	//根据ID查询�?��
	protected void doStuById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		//调用Dao方法，查询出对象
		Stu stu = new StyleStuDao().getStuById(id);
		//将结果以JSON格式响应到客户端
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(stu));
		out.close();
				
	}
	
//	显示要审核信�?
	protected void doStuShow2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用方法查询出所有信�?
			List stuList = new StyleStuDao().getStuList2();
			//将查询结果以JSON字符串形式响应到客户�?
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(stuList));
			out.close();
				
	}
//	根据id通过审核申请信息
	protected void doStuById2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用�?
		int id=Integer.parseInt(request.getParameter("id"));
		//将结果以JSON格式响应到客户端
		PrintWriter out = response.getWriter();
		if(new StyleStuDao().upStuById2(id)){
			out.print(1);
		}else{
			out.print(0);
		}
				
	}
	
}
