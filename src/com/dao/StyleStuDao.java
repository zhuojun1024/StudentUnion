package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Stu;






public class StyleStuDao {
	/*
	 * 查询所有学生会的信息并将每一条信息分别封装到一个Stu对象中，最后将所有对象存入一个List集合中并返回。
	 */
	public List getStuList(){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps= null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from Stu_Sports");
			rs = ps.executeQuery();	//执行查询操作
			List list = new ArrayList();
			while(rs.next()){
				Stu s = new Stu();
				s.setId(rs.getInt(1));
				s.setStitle(rs.getString(2));
				s.setSncontent(rs.getString(3));
				s.setStime(rs.getString(4));
				s.setSleader(rs.getString(5));
				s.setShelp(rs.getString(6));
				s.setScontact(rs.getString(7));
				s.setSremarks(rs.getString(8));
				list.add(s);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
	
	
	/*
	 * 编写一个添方法，添加成功返回true，反之返回false
	 */
	public boolean addStu(Stu stu){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;

		try {
			String sql = "insert into Stu_Sports values(?,?,?,?,?,?,?,1)";
			ps = conn.prepareStatement(sql);	//预编译语句
			ps.setString(1,stu.getStitle());
			ps.setString(2,stu.getSncontent());
			ps.setString(3,stu.getStime());
			ps.setString(4,stu.getSleader());
			ps.setString(5,stu.getShelp());
			ps.setString(6,stu.getScontact());
			ps.setString(7,stu.getSremarks());
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
	
	//根据ID查询出信息
	public Stu getStuById(int id){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from Stu_Sports where id="+id;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(); //执行查询
			if(rs.next()){
				Stu stu = new Stu();
				stu.setId(rs.getInt(1));
				stu.setStitle(rs.getString(2));
				stu.setSncontent(rs.getString(3));
				stu.setStime(rs.getString(4));
				stu.setSleader(rs.getString(5));
				stu.setShelp(rs.getString(6));
				stu.setScontact(rs.getString(7));
				stu.setSremarks(rs.getString(8));
				return stu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
	
	/*
	 * 修改学生
	 * stu:封装了所有信息的对象
	 */
		public boolean updateStu(Stu stu){
			String sql = "update Stu_Sports set stitle=?,sncontent=?,stime=?,sleader=?,shelp=?,scontact=?,sremarks=? where id=?";
			Object[] obj = {stu.getStitle(),stu.getSncontent(),stu.getStime(),stu.getSleader(),stu.getShelp(),stu.getScontact(),stu.getSremarks(),stu.getId()};
			return BaseDao.updateDb(sql, obj);
		}
		
		/*
		 * 根据ID删除的方法
		 */
		public boolean deleteStuById(int id){
			String sql = "delete Stu_Sports where id="+id;
			return BaseDao.updateDb(sql, null);
		}
		
		
		//根据ID查询
			public boolean upStuById2(int id){
				String sql = "update Stu_Sports set stest=? where id="+id;
				Object[] obj = {2};
				return BaseDao.updateDb(sql, obj);
			}
			
			
			
			public List getStuList2(){
				Connection conn = BaseDao.getConnection();
				PreparedStatement ps= null;
				ResultSet rs = null;
				
				try {
					ps = conn.prepareStatement("select * from Stu_Sports where stest=1");
					rs = ps.executeQuery();	//执行查询操作
					List list = new ArrayList();
					while(rs.next()){
						Stu s = new Stu();
						s.setId(rs.getInt(1));
						s.setStitle(rs.getString(2));
						s.setSncontent(rs.getString(3));
						s.setStime(rs.getString(4));
						s.setSleader(rs.getString(5));
						s.setShelp(rs.getString(6));
						s.setScontact(rs.getString(7));
						s.setSremarks(rs.getString(8));
						list.add(s);
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
