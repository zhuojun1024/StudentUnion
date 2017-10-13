package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.InspectionDao;
import com.google.gson.Gson;

@WebServlet("/InspectionServlet")
public class InspectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InspectionServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opt = request.getParameter("opt");
		switch(opt){
			case "getIns" : getIns(request,response);break;
		}
	}

	protected void getIns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		List list = new InspectionDao().getIns(date);
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(list));
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
