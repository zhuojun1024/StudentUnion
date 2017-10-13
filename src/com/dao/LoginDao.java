package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.entity.StudentEntity;

public class LoginDao {
	
	//校对账号密码，如果存在则返回学生信息
	public StudentEntity doLogin(String sno, String spwd){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select s.*,c.cname,d.dname from su_class c,su_dept d left join su_student s on sno='"+sno+"' and spwd='"+spwd+"' where s.scid = c.cid and s.sdid = d.did";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				StudentEntity s = new StudentEntity();
				s.setSid(rs.getString("sid"));
				s.setSno(rs.getString("sno"));
				s.setSname(rs.getString("sname"));
				s.setSpwd(rs.getString("spwd"));
				s.setScid(rs.getString("scid"));
				s.setScname(rs.getString("cname"));
				s.setSdid(rs.getString("sdid"));
				s.setSdname(rs.getString("dname"));
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
}
