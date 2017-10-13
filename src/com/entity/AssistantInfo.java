package com.entity;

public class AssistantInfo {
	private String aid;
	private String asno;//助教学号
	private String asname;//助教姓名
	private String asclass;//所在班级
	private String aclass;//所教班级
	private String apnum;//总页码
	public AssistantInfo() {
		super();
	}
	public AssistantInfo(String aid, String asno, String asname, String asclass, String aclass, String apnum) {
		super();
		this.aid = aid;
		this.asno = asno;
		this.asname = asname;
		this.asclass = asclass;
		this.aclass = aclass;
		this.apnum = apnum;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAsno() {
		return asno;
	}
	public void setAsno(String asno) {
		this.asno = asno;
	}
	public String getAsname() {
		return asname;
	}
	public void setAsname(String asname) {
		this.asname = asname;
	}
	public String getAsclass() {
		return asclass;
	}
	public void setAsclass(String asclass) {
		this.asclass = asclass;
	}
	public String getAclass() {
		return aclass;
	}
	public void setAclass(String aclass) {
		this.aclass = aclass;
	}
	public String getApnum() {
		return apnum;
	}
	public void setApnum(String apnum) {
		this.apnum = apnum;
	}
	
}
