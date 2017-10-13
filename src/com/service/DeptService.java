package com.service;

import java.util.List;

import com.dao.CheckDao;
import com.entity.Checkdata;

public class DeptService {
	/**
	 * 保存所有部门
	 * @return
	 */
	public List getDeptlist(){
		return new CheckDao().getDep();
	}
	/**
	 * 添加部门出勤数据
	 * @return
	 */
	public boolean getaddCheckdata(Checkdata cd){
		return new CheckDao().addCheckdata(cd);
	}
	/**
	 * 带时间参数查询
	 */
	public List getCheckservlet(String mytime){
		return new CheckDao().getCheckservelt(mytime);
	}
}
