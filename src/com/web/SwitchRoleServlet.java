package com.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.StudentEntity;

/**
 * Servlet implementation class SwitchRoleServlet
 */
@WebServlet("/SwitchRoleServlet")
public class SwitchRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SwitchRoleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		s.setSdid("1");
		s.setSdname("ѧԱ");
		request.getSession().setAttribute("info", s);
		request.setAttribute("info", s);
		response.sendRedirect("dept/student.jsp");
	}

}
