package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.PageBean;
import com.entity.Property;


public class ShowDao {
	/**
	 * 查寻所有物资的方法
	 * @return
	 */
	public PageBean Showtblist(PageBean bean){
		Connection conn = BaseDao.getConnection();//连接数据库
		PreparedStatement ps = null;//创建预编译对象
		ResultSet rs = null;//创建结果集对象
		try {
			String sql="select * from(select  row_number() over(order by BuyTime desc) rn,* from Property)r where r.rn>(("+bean.getCpage()+"-1)*"+bean.getShowNum()+") and r.rn<=("+bean.getCpage()*bean.getShowNum()+")";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();//执行语句
			List list = new ArrayList(); 
			while(rs.next()){
				Property p = new Property();
				p.setId(rs.getInt(2));
				p.setgoods(rs.getString(3));
				p.setnum(rs.getInt(4));
				p.setprice(rs.getFloat(5));
				p.settprice(rs.getFloat(6));
				p.setbuytime(rs.getString(7));
				list.add(p);
			}
			bean.setPageList(list);
			sql="select count(*) from Property";
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
	 * 添加物品的方法
	 * @param property
	 * @return
	 */
	public boolean addGoods(Property property){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into Property values(?,?,?,?,?)");
			ps.setString(1, property.getgoods());
			ps.setInt(2, property.getnum());
			ps.setFloat(3, property.getprice());
			ps.setFloat(4, property.gettprice());
			ps.setString(5, property.getbuytime());
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
