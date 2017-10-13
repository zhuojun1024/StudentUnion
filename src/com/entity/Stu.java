package com.entity;
	//学生会主体类
public class Stu {
	private int id;
	private String stitle; //标题
	private String sncontent; //内容
	private String stime; //时间
	private String sleader; //负责�?
	private String shelp; //协助部门
	private String scontact; //联系方式
	private String sremarks; //备注
	
	public Stu() {
		super();
	}

	public Stu(int id, String stitle, String sncontent, String stime,
			String sleader, String shelp, String scontact, String sremarks) {
		super();
		this.id = id;
		this.stitle = stitle;
		this.sncontent = sncontent;
		this.stime = stime;
		this.sleader = sleader;
		this.shelp = shelp;
		this.scontact = scontact;
		this.sremarks = sremarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getSncontent() {
		return sncontent;
	}

	public void setSncontent(String sncontent) {
		this.sncontent = sncontent;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getSleader() {
		return sleader;
	}

	public void setSleader(String sleader) {
		this.sleader = sleader;
	}

	public String getShelp() {
		return shelp;
	}

	public void setShelp(String shelp) {
		this.shelp = shelp;
	}

	public String getScontact() {
		return scontact;
	}

	public void setScontact(String scontact) {
		this.scontact = scontact;
	}

	public String getSremarks() {
		return sremarks;
	}

	public void setSremarks(String sremarks) {
		this.sremarks = sremarks;
	}

	
	
}
