package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	
	//ÐÞ¸ÄÃÜÂë
	public int updatePwd(String sno, String pwd, String npwd){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from su_student where sno='"+sno+"' and spwd='"+pwd+"'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				sql = "update su_student set spwd='"+npwd+"' where sno='"+sno+"'";
				BaseDao.closeDB(null, ps, rs);
				ps = null;
				ps = conn.prepareStatement(sql);
				if(ps.executeUpdate() > 0){
					return 1;
				}else{
					return 0;
				}
			}else{
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return 0;
	}
}
