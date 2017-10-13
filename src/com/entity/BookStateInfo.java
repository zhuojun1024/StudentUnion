package com.entity;

public class BookStateInfo {

	private String bno;
	private String bname;
	private String stuno;
	private String stuname;
	private String outtime;
	private String intime;
	private String state;
	private String pnum;
	public BookStateInfo() {
		super();
	}
	public BookStateInfo(String bno, String bname, String stuno, String stuname, String outtime, String intime,
			String state,String pnum) {
		super();
		this.bno = bno;
		this.bname = bname;
		this.stuno = stuno;
		this.stuname = stuname;
		this.outtime = outtime;
		this.intime = intime;
		this.state = state;
		this.pnum = pnum;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public String getOuttime() {
		return outtime;
	}
	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(String intime) {
		this.intime = intime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
