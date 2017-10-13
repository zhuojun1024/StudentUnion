package com.entity;

import java.util.List;

/**
 * 封装所有分页信息的工具类
 * @author Administrator
 *
 */
public class PageBean {
	
	private int cpage=1;//当前页
	private int showNum=12;//默认每页显示10行
	private int allNum=0;//共有多少行数据
	private int allPage=0;//共有多少页
	
	private List pageList;//封装每页要显示的数据

	public int getCpage() {
		return cpage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
		//当设置allNum时自动计算总页数
		if(allNum%showNum==0)
			this.allPage=allNum/showNum;
		else
			this.allPage=allNum/showNum+1;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public List getPageList() {
		return pageList;
	}

	public void setPageList(List pageList) {
		this.pageList = pageList;
	}

	
	
}






