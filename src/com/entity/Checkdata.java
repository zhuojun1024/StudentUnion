package com.entity;

public class Checkdata {
	private int cid;
	private String dname;
	private int allnum;
	private int num;
	private int lacknum;
	private String ntime;
	
	public Checkdata() {
		super();
	}
	public Checkdata(int cid, String dname, int allnum, int num, int lacknum,
			String ntime) {
		super();
		this.cid = cid;
		this.dname = dname;
		this.allnum = allnum;
		this.num = num;
		this.lacknum = lacknum;
		this.ntime = ntime;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getAllnum() {
		return allnum;
	}
	public void setAllnum(int allnum) {
		this.allnum = allnum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getLacknum() {
		return lacknum;
	}
	public void setLacknum(int lacknum) {
		this.lacknum = lacknum;
	}
	public String getNtime() {
		return ntime;
	}
	public void setNtime(String ntime) {
		this.ntime = ntime;
	}
	
	
}
