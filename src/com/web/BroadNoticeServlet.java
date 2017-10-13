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

import com.dao.BroadNoticeDao;
import com.dao.NoticeDao;
import com.entity.Notice;
import com.entity.PageBean;
import com.google.gson.Gson;

/**
 * 寻物启事和失物查询
 */
@WebServlet("/notice")
public class BroadNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BroadNoticeServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* 重写service，由用户自定义发配请求 */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String opt = request.getParameter("opt");
		if ("selectData".equals(opt)) {
			doSelectData(request, response);
		} else if ("addData".equals(opt)) {
			doAddData(request, response);
		} else if ("removeData".equals(opt)) {
			doRemoveData(request, response);
		} else if ("getData".equals(opt)) {
			doGetData(request, response);
		} else if ("upData".equals(opt)) {
			doUpDate(request, response);
		} else {
			response.sendRedirect("error.html");
		}
	}
	/**
	 * 模糊查询
	 */
	protected void doSelectData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//当前页数
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		//当前类型
		int ctype = Integer.parseInt(request.getParameter("ctype"));
		//模糊查询
		String res=request.getParameter("selectData");
		
		PageBean page = new PageBean();
		page.setShowNum(3);//每页显示的数据数据
		page.setCpage(cpage);// 设置当前第几页
		// 3:调用后台方法进行分页查询数据
		page = new BroadNoticeDao().selectData(page,res,ctype);
		// :将数据保存在会话中
		request.setAttribute("pageBean", page);
		request.setAttribute("selectData", res);
		request.setAttribute("ctype", ctype);
		request.getRequestDispatcher("iframes/broadDept/showNotice.jsp").forward(request,response);
	}

	/**
	 * 添加新的数据
	 */
	protected void doAddData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收用户参数
		String ncontent = request.getParameter("ncontent");
		//接收当前添加类型
		int ctype = Integer.parseInt(request.getParameter("ctype"));
		// 创建当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		// 封装数据到实体类中
		Notice n = new Notice(0, "XXX", ncontent,df.format(new Date()),ctype);

		// 调用Dao方法执行添加操作
		PrintWriter out = response.getWriter();
		if (new BroadNoticeDao().addData(n)) {
			out.println(1);
		} else {
			out.println(0);
		}
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
		if (new BroadNoticeDao().removeData(id)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}

	/**
	 * 查询指定id
	 */
	protected void doGetData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收用户参数
		int id = Integer.parseInt(request.getParameter("id"));

		// 去到dao层找对应的用户方法
		Notice n = new BroadNoticeDao().getUserById(id);
		// 将数据回应在网页
		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(n));
		out.close();

	}

	/**
	 * 修改指定id的值
	 */
	protected void doUpDate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收用户参数
		int id = Integer.parseInt(request.getParameter("id"));
		String ncontent = request.getParameter("ncontent");
		int ctype = Integer.parseInt(request.getParameter("ctype"));
		
		// 封装数据到实体类
		Notice n = new Notice(id, null, ncontent, null,ctype);

		// 调用Dao方法执行添加操作
		PrintWriter out = response.getWriter();
		if (new BroadNoticeDao().upData(n)) {
			out.println(1);
		} else {
			out.println(0);
		}
		out.close();
	}
	
}
