package com.entity;

public class Property {
	private int id;
	private String goods;
	private int num;
	private float price;
	private float tprice;
	private String buytime;
	
	
	public Property() {
		super();
	}
	public Property(int id, String goods, int num, float price, float tprice,
			String buytime) {
		super();
		this.id = id;
		this.goods = goods;
		this.num = num;
		this.price = price;
		this.tprice = tprice;
		this.buytime = buytime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getgoods() {
		return goods;
	}
	public void setgoods(String goods) {
		this.goods = goods;
	}
	public int getnum() {
		return num;
	}
	public void setnum(int num) {
		this.num = num;
	}
	public float getprice() {
		return price;
	}
	public void setprice(float price) {
		this.price = price;
	}
	public float gettprice() {
		return tprice;
	}
	public void settprice(float tprice) {
		tprice = price*num;
		this.tprice = tprice;
	}
	public String getbuytime() {
		return buytime;
	}
	public void setbuytime(String buytime) {
		this.buytime = buytime;
	}
	
	
}