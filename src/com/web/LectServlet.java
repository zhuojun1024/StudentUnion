package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ShowLect;
import com.entity.Lecture;
import com.entity.PageBean;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/LectServlet")
public class LectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opt = request.getParameter("opt");
		if("Showlect".equals(opt)){
			doShowLect(request,response);
		}else if("addLect".equals(opt)){
			doaddLect(request,response);
		}else if("delete".equals(opt)){
			delete(request,response);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*从数据库获取所有列表*/
	protected void doShowLect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cpage=Integer.parseInt(request.getParameter("cpage"));
		//构建一个分页的PageBean
		PageBean bean=new PageBean();
		bean.setCpage(cpage);//设置当前第几页
		
		//调用后台方法进行分页查询数据
		bean=new ShowLect().Showtblist(bean);
		request.setAttribute("pageBean", bean);
		request.getRequestDispatcher("iframes/ANliDept/Lecture.jsp").forward(request, response);
	}
	/*从数据库删除对应ID的数据*/
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		int param=Integer.parseInt(request.getParameter("param"));
	
		if(new ShowLect().delete(param)){
			out.print(1);
		}else{
			out.print(0);
		}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*添加物品信息到数据库中*/
	protected void doaddLect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//获取所有数据
		String Pname = request.getParameter("Pname");
		String Lname=request.getParameter("Lname");
		String Ldatatime = request.getParameter("Ldatatime");
		//将所有值封装到Property实体对象中
		Lecture pro = new Lecture(0, Pname, Lname ,Ldatatime);
		if(new ShowLect().addLect(pro)){
			out.print(1);
		}else{
			out.print(0);
		}
	}

}
