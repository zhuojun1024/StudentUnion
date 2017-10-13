package com.entity;

public class AdmCla {

	private String cid;
	private String cname;//班级名称
	private String csum;//班级人数
	private String cpage;
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public AdmCla() {
		super();
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCsum() {
		return csum;
	}
	public void setCsum(String csum) {
		this.csum = csum;
	}
	public AdmCla(String cid, String cname, String csum, String cpage) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.csum = csum;
		this.cpage = cpage;
	}
	
}
