package com.entity;

public class NoticeEntity {
	private String nid;  //公告id
	private String npublisher;  //发布者
	private String ndate;  //发布日期
	private String nweek;  //星期
	private String ntitle;  //标题
	private String ncontent;  //内容
	private String ndid;  //所属部门的id
	private String ndname;  //所属部门的名称
	private String nregion;  //发布区域（dept或public）
	public NoticeEntity() {
		super();
	}
	public NoticeEntity(String nid, String npublisher, String ndate,
			String nweek, String ntitle, String ncontent, String ndid,
			String ndname, String nregion) {
		super();
		this.nid = nid;
		this.npublisher = npublisher;
		this.ndate = ndate;
		this.nweek = nweek;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.ndid = ndid;
		this.ndname = ndname;
		this.nregion = nregion;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getNpublisher() {
		return npublisher;
	}
	public void setNpublisher(String npublisher) {
		this.npublisher = npublisher;
	}
	public String getNregion() {
		return nregion;
	}
	public void setNregion(String nregion) {
		this.nregion = nregion;
	}
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	public String getNweek() {
		return nweek;
	}
	public void setNweek(String nweek) {
		switch(nweek){
			case "1" : this.nweek = "星期日";break;
			case "2" : this.nweek = "星期一";break;
			case "3" : this.nweek = "星期二";break;
			case "4" : this.nweek = "星期三";break;
			case "5" : this.nweek = "星期四";break;
			case "6" : this.nweek = "星期五";break;
			case "7" : this.nweek = "星期六";break;
			default : this.nweek = "出错";break;
		}
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNdid() {
		return ndid;
	}
	public void setNdid(String ndid) {
		this.ndid = ndid;
	}
	public String getNdname() {
		return ndname;
	}
	public void setNdname(String ndname) {
		this.ndname = ndname;
	}
}
