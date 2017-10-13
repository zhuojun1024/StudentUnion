package com.entity;

public class BookInfo {
	
	private String no;//书籍编号
	private String name;//书籍名称
	private String author;//书籍作者
	private String state;//书籍状态
	private String page;//当前页码
	private String pnum;//总页码
	public BookInfo() {
		super();
	}
	public BookInfo(String no, String name, String author, String state, String page, String pnum) {
		super();
		this.no = no;
		this.name = name;
		this.author = author;
		this.state = state;
		this.page = page;
		this.pnum = pnum;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	
}
