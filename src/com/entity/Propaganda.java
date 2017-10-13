package com.entity;

public class Propaganda {
	private int pid;
	private String pname;
	private String content;
	private String pdatatime;
	public Propaganda() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Propaganda(int pid, String pname, String content, String pdatatime) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.content = content;
		this.pdatatime = pdatatime;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPdatatime() {
		return pdatatime;
	}
	public void setPdatatime(String pdatatime) {
		this.pdatatime = pdatatime;
	}
	
}
