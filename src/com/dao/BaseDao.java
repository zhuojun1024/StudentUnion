package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	
	//加载数据库驱动
	static{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//连接数据库
	public static Connection getConnection(){
		String sqlUrl = "jdbc:sqlserver://localhost:1433;databaseName=StudentUnion";
		try {
			return DriverManager.getConnection(sqlUrl,"sa","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//关闭数据库
	public static void closeDB(Connection conn, PreparedStatement ps, ResultSet rs){
		
			try {
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			try {
				if(ps != null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			try {
				if(rs != null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 公共的执行对数据库添加，删除，修改的方法
	 * @param sql 要执行的SQL语句，支持占位符
	 * @param obj 如果SQL有占位符，通过obj数组传递占位符对应的数据
	 * @return
	 */
	public static boolean updateDb(String sql,Object[] obj){
		Connection conn=getConnection();//获取数据库链接
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);//构建一个预编译语句对象
			//如果传递了占位符对应的值，则说明使用了占位符
			if(obj!=null&&obj.length>0){
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i+1, obj[i]);
				}
			}
			if(ps.executeUpdate()>0)//执行修改
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeDB(conn, ps, null);
		}
		return false;
	}
	
	/**
	 * 所有查询都可以调用这个方法
	 */
	public static Object[] AllSelect(String sql){
		Connection conn=getConnection();//获取数据库链接
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			return new Object[]{conn,ps,rs};
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改
	 */
	public boolean update(String sql, Object... obj){
		PreparedStatement ps = null;
		Connection conn = BaseDao.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < obj.length; i++){
				ps.setObject(i+1, obj[i]);
			}
			if(ps.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeDB(conn, ps, null );
		}
		return false;
	}
	
	/**
	 * 查询
	 */
	public Object[] sel(String sql){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = BaseDao.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] obj = new Object[]{rs, ps, conn};
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
