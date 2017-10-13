package com.entity;
/**
 * 寻物实体类
 * @author Administrator
 *
 */
public class Notice {
	private int nid;  //编号id
	private String nname;  //用户名
	private String ncontent;  //内容
	private String ntime;  //发布当前时间
	private int ntid;  //发布当前时间
	
	
	
	public Notice() {
		super();
	}

	public Notice(int nid, String nname, String ncontent, String ntime, int ntid) {
		super();
		this.nid = nid;
		this.nname = nname;
		this.ncontent = ncontent;
		this.ntime = ntime;
		this.ntid = ntid;
	}

	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNtime() {
		return ntime;
	}
	public void setNtime(String ntime) {
		this.ntime = ntime;
	}
	public int getNtid() {
		return ntid;
	}
	public void setNtid(int ntid) {
		this.ntid = ntid;
	}
	
	
	
}
