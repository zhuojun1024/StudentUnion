package com.entity;
public class sponsor{
	private int sid;
	private String sevent;
	private String things;
	private String datatime;
	private String sname;
	private String result;
	
	public sponsor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public sponsor(int sid, String sevent, String things, String datatime,
			String sname, String result) {
		super();
		this.sid = sid;
		this.sevent = sevent;
		this.things = things;
		this.datatime = datatime;
		this.sname = sname;
		this.result = result;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSevent() {
		return sevent;
	}
	public void setSevent(String sevent) {
		this.sevent = sevent;
	}
	public String getThings() {
		return things;
	}
	public void setThings(String things) {
		this.things = things;
	}
	public String getDatatime() {
		return datatime;
	}
	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
