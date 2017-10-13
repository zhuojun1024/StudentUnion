package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.NoticeDao;
import com.entity.NoticeEntity;
import com.entity.StudentEntity;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   public NoticeServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//根据opt参数判断需要调用的方法
		String opt = request.getParameter("opt");
		switch(opt){
			case "getNotice"  : getNotice(request,response); break;
			case "addNotice"  : addNotice(request,response); break;
			case "setNotice"  : setNotice(request,response); break;
			case "editNotice" : editNotice(request,response); break;
			case "delNotice" : delNotice(request,response); break;
		}
	}

	protected void getNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取出当前用户角色、当前页数。
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String role = s.getSdid();  //部门编号
		int page = Integer.parseInt(request.getParameter("page"));  //当前页数
		
		//根据角色和当前页数取出对应页数的公告
		List list = new NoticeDao().getNotice(role,page,null);
		
		//取出公告总页数
		int allpage = new NoticeDao().getCount(role,null);
		
		//保存取出的公告、总页数、当前页数，转向到公告页面
		request.setAttribute("notice", list);
		request.setAttribute("allpage", allpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("iframes/liveDept/notice.jsp").forward(request, response);
	}

	protected void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取用户信息
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String role = s.getSdid();  //部门编号
		String name = s.getSname();  //姓名
		
		String text = request.getParameter("text");  //公告内容
		String title = request.getParameter("title");  //公告标题
		String region = request.getParameter("region"); //发布区域
		
		//保存到实体类中
		NoticeEntity n = new NoticeEntity(null,name,null,null,title,text,role,null,region);
		PrintWriter out = response.getWriter();
		
		//判断是否发布成功
		if(new NoticeDao().addNotice(n)){
			out.print("finish");
		}else{
			out.print("");
		}
		out.close();
	}

	//获取当前用户发布的公告
	protected void setNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取用户信息
		StudentEntity s = (StudentEntity) request.getSession().getAttribute("info");
		String role = s.getSdid();  //部门编号
		String npublisher = s.getSname();  //发布者
		
		int page = Integer.parseInt(request.getParameter("page"));  //当前页数

		//根据角色和当前页数取出对应页数的公告
		List list = new NoticeDao().getNotice(role,page,npublisher);
		
		//取出公告总页数
		int allpage = new NoticeDao().getCount(role,npublisher);
		
		//保存取出的公告、总页数、当前页数，转向到公告页面
		request.setAttribute("notice", list);
		request.setAttribute("allpage", allpage);
		request.setAttribute("page", page);
		request.getRequestDispatcher("iframes/liveDept/updateN.jsp").forward(request, response);
	}

	//编辑公告
	protected void editNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");  //公告内容
		String title = request.getParameter("title");  //公告标题
		String nid = request.getParameter("nid");  //公告id
		
		//判断是否更新成功
		PrintWriter out = response.getWriter();
		if(new NoticeDao().editNotice(text, title, nid)){
			out.print("finish");
		}else{
			out.print("");
		}
		out.close();
	}

	//删除公告
	protected void delNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nid = request.getParameter("nid");  //公告id
		
		//判断是否删除成功
		PrintWriter out = response.getWriter();
		if(new NoticeDao().delNotice(nid)){
			out.print("finish");
		}else{
			out.print("");
		}
		out.close();
	}

	protected void bak(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
