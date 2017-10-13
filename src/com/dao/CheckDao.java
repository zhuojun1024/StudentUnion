package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Checkdata;
import com.entity.Department;

public class CheckDao {
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
			
			//排除前三个特殊角色（管理员、教员、学员）
			for(int i = 0; i < 3; i++){
				rs.next();
			}
			
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
	public boolean addCheckdata(Checkdata cd){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into Department_check values(?,?,?,?,?)");
			ps.setString(1, cd.getDname());
			ps.setInt(2, cd.getAllnum());
			ps.setInt(3, cd.getNum());
			ps.setInt(4, cd.getLacknum());
			ps.setString(5, cd.getNtime());
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
		mytime = mytime==null?"getdate()":"'"+mytime+"'";
		try {
			ps = conn.prepareStatement("select * from Department_check c where datediff(dd,c.ntime,"+mytime+")=0");
			rs = ps.executeQuery();
			List list = new ArrayList();
			while(rs.next()){
				Checkdata cd = new Checkdata();
				cd.setCid(rs.getInt(1));
				cd.setDname(rs.getString(2));
				cd.setAllnum(rs.getInt(3));
				cd.setNum(rs.getInt(4));
				cd.setLacknum(rs.getInt(5));
				cd.setNtime(rs.getString(6));
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
	
	public boolean dodeletedata(int id){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("delete Department_check where cid="+id);
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
