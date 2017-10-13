package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StuDao;
import com.entity.AdmCla;
import com.entity.AdmStuInfo;
import com.entity.AssistantInfo;
import com.entity.BookInfo;
import com.entity.BookStateInfo;
import com.google.gson.Gson;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyServlet() {
        super();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String opt = request.getParameter("opt");
    	switch (opt) {
		case "selBook":
			doSelBook(request, response);  //查询书籍
			break;
		case "loanBook":
			loanBook(request, response);  //借书
			break;
		case "returnBook":
			returnBook(request, response);  //还书
			break;
		case "viewLog":
			viewLog(request, response);  //借还记录
			break;
		case "addBook":
			addBook(request, response);  //添加书籍
			break;
		case "selAss":
			selAss(request, response);  //查询助教
			break;
		case "insAss":
			insAss(request, response);  //添加助教
			break;
		case "selcla":
			selcla(request, response);  //查询班级
			break;
		case "delAss":
			delAss(request, response);  //删除助教
			break;
		case "updAss":
			updAss(request, response);  //修改助教
			break;
		case "admClaSel":
			admClaSel(request, response);
			break;
		case "admClaIns":
			admClaIns(request, response);
			break;
		case "admClaUpd":
			admClaUpd(request, response);
			break;
		case "admStu":
			admStu(request, response);
			break;
		case "admStuCla":
			admStuCla(request, response);
			break;
		case "admStuDep":
			admStuDep(request, response);
			break;
		case "admStuAdd":
			admStuAdd(request, response);
			break;
		case "admStuUpd":
			admStuUpd(request, response);
			break;
			
		default:
			break;
		}
    }

	/**
	 * 查询书籍
	 */
	protected void doSelBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String page = request.getParameter("page");
		List<BookInfo> list = new StuDao().selBook(name, page);
		PrintWriter out = response.getWriter();
		if(list.size() > 0 && list != null){
			out.println(new Gson().toJson(list));
		} else{
			out.println(0);
		}
		out.close();
	}
	
	/**
	 * 借书
	 */
	protected void loanBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookno = request.getParameter("no");
		String stuno = request.getParameter("sname");
		String outtime = request.getParameter("outtime");
		PrintWriter out = response.getWriter();
		if(new StuDao().loanBook(new BookStateInfo(bookno, null, stuno, null, outtime, null, null,null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}
	
	/*
	 * 还书
	 */
	protected void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ret = request.getParameter("ret");
		PrintWriter out = response.getWriter();
		if(new StuDao().returned(ret)){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//查看日志
	protected void viewLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String log = request.getParameter("log");
		String lpage = request.getParameter("lpage");
		List<BookStateInfo> list = new StuDao().viewLog(log,lpage);
		PrintWriter out = response.getWriter();
		if(list != null && list.size() > 0){
			out.println(new Gson().toJson(list));
		} else{
			out.println(0);
		}
		out.close();
	}

	//添加书籍
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		String bname = request.getParameter("bname");
		String bauthor = request.getParameter("bauthor");
		PrintWriter out = response.getWriter();
		if(new StuDao().addBook(new BookInfo(bno, bname, bauthor, null, null, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//查询助教信息
	protected void selAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selAss = request.getParameter("val");
		String page = request.getParameter("page");
		List<AssistantInfo> list = new StuDao().selAss(selAss, page);
		PrintWriter out = response.getWriter();
		if(list != null && list.size() > 0){
			out.println(new Gson().toJson(list));
		} else{
			out.println(0);
		}
		out.close();
	}

	//新增助教信息
	protected void insAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String cla = request.getParameter("class");
		PrintWriter out = response.getWriter();
		if(new StuDao().insAss(new AssistantInfo(null, no, null, null,cla, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}
	
	//查询可选班级,新增助教信息时
	protected void selcla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(new StuDao().selCla()));
		out.close();
	}

	//删除助教信息
	protected void delAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		PrintWriter out = response.getWriter();
		if(new StuDao().delAss(aid)){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//修改助教信息
	protected void updAss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String asno = request.getParameter("asno");
		String aclass = request.getParameter("aclass");
		PrintWriter out = response.getWriter();
		if(new StuDao().updAss(new AssistantInfo(aid, asno, null, null, aclass, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//管理员管理班级  查询
	protected void admClaSel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		List<AdmCla> list = new StuDao().admClaSel(page);
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(list));
		out.close();
	}

	//管理员管理班级  新增
	protected void admClaIns(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("name");
		PrintWriter out = response.getWriter();
		if(new StuDao().admClaIns(cname)){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//管理员管理班级  修改
	protected void admClaUpd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("name");
		PrintWriter out = response.getWriter();
		if(new StuDao().admClaUpd(new AdmCla(cid, cname, null, null))){
			out.println(1);
		} else{
			out.println(0);	
		}
		out.close();
	}

	//管理员管理学员获取班级
	protected void admStuCla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(new StuDao().admStuCla()));
		out.close();
	}

	//管理员管理学员获取部门
	protected void admStuDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(new StuDao().admStuDep()));
		out.close();
	}

	//管理员管理学员查询学生信息
	protected void admStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sname = request.getParameter("name");
		String sclass = request.getParameter("class");
		String sdepartment = request.getParameter("department");
		String spage = request.getParameter("page");
		PrintWriter out = response.getWriter();
		out.println(new Gson().toJson(new StuDao().admStu(new AdmStuInfo(null, sname, null, sclass, sdepartment, spage))));
		out.close();
	}

	//管理员管理学员       添加学生
	protected void admStuAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");
		String spwd = request.getParameter("spwd");
		String scname = request.getParameter("scname");
		String sdname = request.getParameter("sdname");
		PrintWriter out = response.getWriter();
		if(new StuDao().admStuAdd(new AdmStuInfo(sno, sname, spwd, scname, sdname, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

	//管理员管理学员       修改学生
	protected void admStuUpd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");
		String spwd = request.getParameter("spwd");
		String scname = request.getParameter("scname");
		String sdname = request.getParameter("sdname");
		PrintWriter out = response.getWriter();
		if(new StuDao().admStuUpd(new AdmStuInfo(sno, sname, spwd, scname, sdname, null))){
			out.println(1);
		} else{
			out.println(0);
		}
		out.close();
	}

}
