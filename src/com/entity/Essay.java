package com.entity;
/**
 * 同学来稿实体类
 *
 */
public class Essay {
	private int eid;
	private String ename;
	private String etitle;
	private String econtent;
	private String etime;
	
	
	public Essay() {
		super();
	}
	public Essay(int eid, String ename, String etitle, String econtent,
			String etime) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.etitle = etitle;
		this.econtent = econtent;
		this.etime = etime;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEtitle() {
		return etitle;
	}
	public void setEtitle(String etitle) {
		this.etitle = etitle;
	}
	public String getEcontent() {
		return econtent;
	}
	public void setEcontent(String econtent) {
		this.econtent = econtent;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	
}
