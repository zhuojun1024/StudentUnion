package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EssayDao;
import com.entity.Essay;
import com.entity.PageBean;
import com.google.gson.Gson;

/**
 * 同学来稿服务类
 */
@WebServlet("/essay")
public class EssayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EssayServlet() {
        super();
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
//    	重写service方法
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	String opt=request.getParameter("opt");
    	if("showData".equals(opt)){
    		doShowData(request, response);
    	}else if("addData".equals(opt)){
    		doAddData(request, response);
    	}else if("getData".equals(opt)){
    		doGetData(request, response);
    	}else if("removeData".equals(opt)){
    		doRemoveData(request, response);
    	}    	
    	else{
    		response.sendRedirect("error.html");
    	}
    }
    /*查询所有数据*/
	protected void doShowData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//当前页数
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		//模糊查询
		String res=request.getParameter("selectData");
		PageBean page = new PageBean();
		page.setShowNum(8);//每页显示的数据数据
		page.setCpage(cpage);// 设置当前第几页
		// 3:调用后台方法进行分页查询数据
		page = new EssayDao().selectData(page,res);
		// :将数据保存在会话中
		request.setAttribute("pageBean", page);
		request.setAttribute("selectData", res);
		request.getRequestDispatcher("iframes/broadDept/showEssay.jsp").forward(request,response);
	}
	/* 添加数据*/
	protected void doAddData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收用户参数
		String etitle = request.getParameter("etitle");
		String econtent = request.getParameter("econtent");

		// 创建当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		// 封装数据到实体类中
		Essay e = new Essay(0, "XXX",etitle,econtent,df.format(new Date()));

		// 调用Dao方法执行添加操作
		PrintWriter out = response.getWriter();
		if (new EssayDao().addData(e)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}
  /*查询指定id数据*/
	protected void doGetData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//要查询的id
		int id=Integer.parseInt(request.getParameter("id"));
		Essay e=new EssayDao().getData(id);
		// 将数据回应在网页
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(e));
		out.close();
	}
	
	
	/**
	 * 删除的功能
	 * */
	protected void doRemoveData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收用户参数
		int id = Integer.parseInt(request.getParameter("id"));
		// 调用Dao方法执行添加操作
		PrintWriter out = response.getWriter();
		if (new EssayDao().removeData(id)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}

}
