package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.entity.StudentEntity;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写service方法，根据opt参数调用对应的方法
		String opt = request.getParameter("opt");
		switch(opt){
			case "updatePwd"  : updatePwd(request,response); break;
			case "logout"     : logout(request,response); break;
		}
	}

	//修改密码
	protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取出学号、原密码、新密码
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String sno = s.getSno();
		String pwd = request.getParameter("pwd");
		String npwd = request.getParameter("npwd");
		
		//执行密码修改操作
		int rs = new UserDao().updatePwd(sno, pwd, npwd);
		
		
		//如果密码修改成功，则更新会话中的密码
		if(rs == 1){
			s.setSpwd(npwd);
			request.getSession().setAttribute("info",s);
		}
		
		
		//返回修改结果给客户端
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.close();
	}

	//注销登录
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//清空session数据
		request.getSession().setAttribute("info",null);
		
		//重定向到登陆界面
		PrintWriter out = response.getWriter();
		out.print("<script>top.location.href='/StudentUnion'</script>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
