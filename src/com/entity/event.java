package com.entity;
public class event{
  private int eid;
  private String sevent;
  private String ethings;
  private String ename;
  private String datatime;
  
public event() {
	super();
	// TODO Auto-generated constructor stub
}
public event(int eid, String sevent, String ethings, String ename,
		String datatime) {
	super();
	this.eid = eid;
	this.sevent = sevent;
	this.ethings = ethings;
	this.ename = ename;
	this.datatime = datatime;
}
public int getEid() {
	return eid;
}
public void setEid(int eid) {
	this.eid = eid;
}
public String getSevent() {
	return sevent;
}
public void setSevent(String sevent) {
	this.sevent = sevent;
}
public String getEthings() {
	return ethings;
}
public void setEthings(String ethings) {
	this.ethings = ethings;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public String getDatatime() {
	return datatime;
}
public void setDatatime(String datatime) {
	this.datatime = datatime;
}
  
}
