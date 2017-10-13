package com.entity;

//封装社团部的社团信息
public class Tb_Club {

	private int attid;
	private int clubid;
	private String clubname;
	private int peoplenum;
	private int peoplecomenum;
	private int peoplenocome;
	private String thetime;
	private int aclubid;
	
	public Tb_Club() {
		super();
	}
	

	public Tb_Club(int attid, int clubid, String clubname, int peoplenum,
			int peoplecomenum, int peoplenocome, String thetime, int aclubid) {
		super();
		this.attid = attid;
		this.clubid = clubid;
		this.clubname = clubname;
		this.peoplenum = peoplenum;
		this.peoplecomenum = peoplecomenum;
		this.peoplenocome = peoplenocome;
		this.thetime = thetime;
		this.aclubid = aclubid;
	}




	public int getAttid() {
		return attid;
	}




	public void setAttid(int attid) {
		this.attid = attid;
	}




	public int getPeoplenocome() {
		return (peoplenum-peoplecomenum);
	}
	public void setPeoplenocome(int peoplenocome) {
		this.peoplenocome = peoplenocome;
	}
	public int getClubid() {
		return clubid;
	}
	public void setClubid(int clubid) {
		this.clubid = clubid;
	}
	public String getClubname() {
		return clubname;
	}
	public void setClubname(String clubname) {
		this.clubname = clubname;
	}
	public int getPeoplenum() {
		return peoplenum;
	}
	public void setPeoplenum(int peoplenum) {
		this.peoplenum = peoplenum;
	}
	public int getPeoplecomenum() {
		return peoplecomenum;
	}
	public void setPeoplecomenum(int peoplecomenum) {
		this.peoplecomenum = peoplecomenum;
	}
	
	public String getThetime() {
		return thetime;
	}
	public void setThetime(String thetime) {
		this.thetime = thetime;
	}
	public int getAclubid() {
		return aclubid;
	}
	public void setAclubid(int aclubid) {
		this.aclubid = aclubid;
	}
	
}
