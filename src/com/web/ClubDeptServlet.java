package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ClubDeptDao;
import com.entity.PageBean;
import com.google.gson.Gson;

@WebServlet("/ClubDeptServlet")
public class ClubDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ClubDeptServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String opt=request.getParameter("opt");
    	switch(opt){
    	case "clubselect"://这个是登记页面的查询方法
    		doSelectClub(request, response);break;
    	case "shetuandengji"://登记的方法
    		doinsertclub(request, response);break;
    	case "clubtoselect"://查询页面的查询方法
    		doSelect(request, response);break;
        case "toremove"://删除记录的方法
        	doRemove(request, response);break;
        case "clubname_to_select"://通过社团名查询的方法
        	doname_select(request, response);break;
    	}
    }
    
	protected void doSelectClub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//从数据库中取出社团相关数据
		List list=new ClubDeptDao().getClubInfo();
		PrintWriter out=response.getWriter();
		if(list == null){
			out.print(0);
		}else{
		//将数据转为json数组形式返回页面
			out.print(new Gson().toJson(list));
		}
	}
	
	protected void doinsertclub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义变量存储传过来的参数
		//当前判断登记的第几行
		int thistr=Integer.parseInt(request.getParameter("thistr"));
		PrintWriter out=response.getWriter();
		//登记的社团id
		int clubid=Integer.parseInt(request.getParameter("clubid"));
		//登记的总人数
		int peoplenum=Integer.parseInt(request.getParameter("peoplenum"));
		//登记的实到人数
		int peoplecomenum=Integer.parseInt(request.getParameter("peoplecomenum"));
		//登记的缺勤人数
		int peoplenocome=0;
		try{
		peoplenocome=Integer.parseInt(request.getParameter("peoplenocome"));
		}catch(Exception ex){
			
		}
		
		//判断，当所有数据正常范围内才添加数据
		if(peoplecomenum <= peoplenum && peoplecomenum >= 0 && (peoplenum-peoplecomenum) == peoplenocome && peoplenocome >= 0 && peoplenum != 0){
			//获取当前时间
			Date thetime = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datetime = sdf.format(thetime);
			
			if(new ClubDeptDao().insertClub_dengji(peoplenum, peoplecomenum,peoplenocome, datetime, clubid)){
				out.print("{\"success\":true,\"row\":\""+thistr+"\"}");
			}
		}else{//如果传过来的数据不规范，直接给出响应
			out.print("{\"success\":false,\"row\":\""+thistr+"\"}");
		}
		
	}

	protected void doSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取出要查询第几页的数据
			int cpage=Integer.parseInt(request.getParameter("cpage"));
		//构建一个分页的PageBean
			PageBean bean=new PageBean();
			bean.setCpage(cpage);//设置当前第几页
			bean=new ClubDeptDao().getsomeClub(bean);
		//把bean保存到请求中
			request.setAttribute("bean", bean);
		//转向到页面
			request.getRequestDispatcher("iframes/clubDept/社团查询.jsp").forward(request, response);
			
	}
		//查询页删除记录的代码
	protected void doRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out=response.getWriter();
		//取出要删除的行
			int attid=Integer.parseInt(request.getParameter("attid"));
		//调用方法删除记录
			if(new ClubDeptDao().removeRow(attid)){
				out.print(1);
			}else{
				out.print(0);
			}		
	}
	
	//根据传过来的社团名字进行模糊查询
	protected void doname_select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
	//取出要查询的社团名
		String clubname=request.getParameter("clubname");
		int cpage=Integer.parseInt(request.getParameter("cpage"));
		PageBean bean=new PageBean();
		bean.setCpage(cpage);//设置当前第几页
		bean=new ClubDeptDao().getClubtoname(bean,clubname);
	//把bean保存到请求中
		request.setAttribute("clubname", clubname);
		request.setAttribute("bean", bean);
		//转向到页面
		request.getRequestDispatcher("iframes/clubDept/社团模糊查询.jsp").forward(request, response);
		
}
}
