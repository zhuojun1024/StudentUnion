package com.entity;
public class Lecture{
  private int lid;
  private String pname;
  private String lname;
  private String ldatatime;
  
public Lecture() {
	super();
	// TODO Auto-generated constructor stub
}
public Lecture(int lid, String pname, String lname, String ldatatime) {
	super();
	this.lid = lid;
	this.pname = pname;
	this.lname = lname;
	this.ldatatime = ldatatime;
}
public int getLid() {
	return lid;
}
public void setLid(int lid) {
	this.lid = lid;
}
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getLdatatime() {
	return ldatatime;
}
public void setLdatatime(String ldatatime) {
	this.ldatatime = ldatatime;
}
  
  

  
}
