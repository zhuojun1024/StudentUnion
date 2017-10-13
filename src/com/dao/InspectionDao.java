package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.InspectionEntity;

public class InspectionDao {
	//查询指定日期考勤数据
	public List getIns(String date){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(date == null){
			date = "GETDATE()";
		}
		String sql = "select i.*,c.cname from su_inspection i left join su_class c on i.icid=c.cid where datediff(dd,i.idate,"+date+")=0";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List list = new ArrayList();
			while(rs.next()){
				InspectionEntity i = new InspectionEntity();
				i.setIid(rs.getString("iid"));
				i.setIcid(rs.getString("icid"));
				i.setIall(rs.getString("iall"));
				i.setIone(rs.getString("ione"));
				i.setItwo(rs.getString("itwo"));
				i.setIstandarda(rs.getString("istandarda"));
				i.setIstandardb(rs.getString("istandardb"));
				i.setIstandardc(rs.getString("istandardc"));
				i.setIstandardd(rs.getString("istandardd"));
				i.setIstandarde(rs.getString("istandarde"));
				i.setImark(rs.getString("imark"));
				i.setIclaperson(rs.getString("iclaperson"));
				i.setIperson(rs.getString("iperson"));
				i.setIdate(rs.getString("idate"));
				list.add(i);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		
		return null;
	}
	
	//添加考勤数据
	public boolean addIns(InspectionEntity i){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into su_inspection values(?,?,?,?,?,?,?,?,?,?,?,?,default)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, i.getIcid());
			ps.setString(2, i.getIall());
			ps.setString(3, i.getIone());
			ps.setString(4, i.getItwo());
			ps.setString(5, i.getIstandarda());
			ps.setString(6, i.getIstandardb());
			ps.setString(7, i.getIstandardc());
			ps.setString(8, i.getIstandardd());
			ps.setString(9, i.getIstandarde());
			ps.setString(10, i.getImark());
			ps.setString(11, i.getIclaperson());
			ps.setString(12, i.getIperson());
			if(ps.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return false;
	}
	
	//更新考勤数据
	public boolean updIns(InspectionEntity i){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update su_inspection set";
			   sql += " icid="+i.getIcid();
			   sql += " iall="+i.getIall();
			   sql += " ione="+i.getIone();
			   sql += " itwo="+i.getItwo();
			   sql += " istandarda="+i.getIstandarda();
			   sql += " istandardb="+i.getIstandardb();
			   sql += " istandardc="+i.getIstandardc();
			   sql += " istandardd="+i.getIstandardd();
			   sql += " istandarde="+i.getIstandarde();
			   sql += " imark="+i.getImark();
			   sql += " iclaperson='"+i.getIclaperson()+"'";
			   sql += " iperson='"+i.getIperson()+"'";
		try {
			ps = conn.prepareStatement(sql);
			if(ps.executeUpdate() > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return false;
	}
}
