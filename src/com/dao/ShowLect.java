package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Lecture;
import com.entity.PageBean;


public class ShowLect {
	/**
	 * 查寻所有物资的方法
	 * @return
	 */
	public PageBean Showtblist(PageBean bean){
		Connection conn = BaseDao.getConnection();//连接数据库
		PreparedStatement ps = null;//创建预编译对象
		ResultSet rs = null;//创建结果集对象
		try {
			String sql="select * from(select  row_number() over(order by lid desc) rn,* from Lecture)r where r.rn>(("+bean.getCpage()+"-1)*"+bean.getShowNum()+") and r.rn<=("+bean.getCpage()*bean.getShowNum()+")";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();//执行语句
			List list = new ArrayList(); 
			while(rs.next()){
				Lecture p = new Lecture();
				p.setLid(rs.getInt(2));
				p.setPname(rs.getString(3));
				p.setLname(rs.getString(4));
				p.setLdatatime(rs.getString(5));
				list.add(p);
			}
			bean.setPageList(list);
			sql="select count(*) from Lecture";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				//将总数据条数封装到PageBean
				bean.setAllNum(rs.getInt(1));
			}
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
	/**
	 * 删除信息的方法
	 * @param property
	 * @return
	 */
	
	 public boolean delete(int id){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("delete Lecture where lid="+id);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return false;
	}
	/**
	 * 添加物品的方法
	 * @param property
	 * @return
	 */
	public boolean addLect(Lecture lecture){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into Lecture values(?,?,?)");
			ps.setString(1,lecture.getPname());
			ps.setString(2,lecture.getLname());
			ps.setString(3,lecture.getLdatatime());
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return false;
	} 
}
