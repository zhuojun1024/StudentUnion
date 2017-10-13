package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Department;
import com.entity.sponsor;

public class ANliCheckDao {
	/**
	 * 查询所有部门
	 * @return
	 */
	public List getDep(){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps  = conn.prepareStatement("select * from su_dept");
			rs = ps.executeQuery();
			List list = new ArrayList();
			while(rs.next()){
				Department dt = new Department();
				dt.setDid(rs.getInt(1));
				dt.setDname(rs.getString(2));
				list.add(dt);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	} 
	
	/**
	 * 添加部门数据方法
	 * @param cd实体类的值
	 * @return
	 */
	public boolean addCheckdata(sponsor cd){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into sponsor values(?,?,?,?,?)");
			ps.setString(1, cd.getSevent());
			ps.setString(2, cd.getThings());
			ps.setString(3, cd.getDatatime());
			ps.setString(4, cd.getSname());
			ps.setString(5, cd.getResult());
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
	 * 带条件（时间）查询
	 */
	public List getCheckservelt(String mytime){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select * from sponsor where datatime='"+mytime+"'");
			rs = ps.executeQuery();
			List list = new ArrayList();
			while(rs.next()){
				sponsor cd = new sponsor();
				cd.setSevent(rs.getString(2));
				cd.setThings(rs.getString(3));
				cd.setDatatime(rs.getString(4));
				cd.setSname(rs.getString(5));
				cd.setResult(rs.getString(6));
				list.add(cd);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
}	
