package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ShowDao;
import com.entity.PageBean;
import com.entity.Property;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opt = request.getParameter("opt");
		if("Showtb".equals(opt)){
			doShowtb(request,response);
		}else if("addGoods".equals(opt)){
			doaddGoods(request,response);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*从数据库获取所有列表*/
	protected void doShowtb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cpage=Integer.parseInt(request.getParameter("cpage"));
		//构建一个分页的PageBean
		PageBean bean=new PageBean();
		bean.setCpage(cpage);//设置当前第几页
		
		//调用后台方法进行分页查询数据
		bean=new ShowDao().Showtblist(bean);
		request.setAttribute("pageBean", bean);
		request.getRequestDispatcher("iframes/SECDept/Showtb.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*添加物品信息到数据库中*/
	protected void doaddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//获取所有数据
		String Goods = request.getParameter("Goods");
		int Num = Integer.parseInt(request.getParameter("Num"));
		float Price = Float.parseFloat(request.getParameter("Price"));
		float TPrice = Float.parseFloat(request.getParameter("TPrice"));
		String BuyTime = request.getParameter("BuyTime");
		//将所有值封装到Property实体对象中
		Property pro = new Property(0, Goods, Num, Price, TPrice, BuyTime);
		if(new ShowDao().addGoods(pro)){
			out.print(1);
		}else{
			out.print(0);
		}
	}

}
