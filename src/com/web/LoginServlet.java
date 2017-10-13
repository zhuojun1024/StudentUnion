package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LoginDao;
import com.entity.StudentEntity;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/StudentUnion");
	}
	//用户登录
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码处理
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//获取账号密码
		String sno = request.getParameter("sno");
		String spwd = request.getParameter("spwd");
		
		//查询账号密码
		StudentEntity s = new LoginDao().doLogin(sno,spwd);
		
		//如果不存在，则返回空值到登录页面，提示用户账号或密码错误
		if(s == null){
			PrintWriter out = response.getWriter();
			out.print("");
			out.close();
		//如果存在，则保存用户信息，并重定向到对应部门的主页
		}else{
			request.getSession().setAttribute("info", s);
			request.setAttribute("info", s);
			String url = null;
			switch(s.getSdid()){
				case "0" : url = "dept/admin.jsp";break;			//管理员
				case "1" : url = "dept/student.jsp";break;   //学员
				case "2" : url = "dept/liveDept.jsp";break;  //生活部
				case "3" : url = "dept/inspectionDept.jsp";break;	//纪检部
				case "4" : url = "dept/SECDept.jsp";break;   //秘书部
				case "5" : url = "dept/learnDept.jsp";break; //学习部
				case "6" : url = "dept/publicityDept.jsp";break;    //宣传部
				case "7" : url = "dept/styleDept.jsp";break; //文体部
				case "8" : url = "dept/broadDept.jsp";break; //播音部
				case "9" : url = "dept/clubDept.jsp";break;  //社团部
				case "10" : url = "dept/ANliDept.jsp";break; //外联部
				
				default: url = "404.jsp";break;
			}
			response.sendRedirect(url);
		}
	}

}
