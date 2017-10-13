package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.DormEntity;

public class DormDao {
	
	//获取寝室登记信息
	public List getDormInfo(String building, String floor, String date){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		date = date==null?"getdate()":"'"+date+"'";
		String sql = "select d.*,c.cname from su_dormregister d left join su_class c on d.dcid=c.cid where d.dnumber like '"+floor+"%' and datediff(dd,d.ddate,"+date+")=0 and d.dbuilding='"+building+"'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List list = new ArrayList();
			while(rs.next()){
				DormEntity d = new DormEntity();
				d.setId(rs.getString("did"));
				d.setBuilding(rs.getString("dbuilding"));
				d.setNumber(rs.getString("dnumber"));
				d.setCid(rs.getString("dcid"));
				d.setStandarda(rs.getString("dstandarda"));
				d.setStandardb(rs.getString("dstandardb"));
				d.setStandardc(rs.getString("dstandardc"));
				d.setStandardd(rs.getString("dstandardd"));
				d.setMark(rs.getString("dmark"));
				d.setLiable(rs.getString("dliable"));
				d.setDate(rs.getString("ddate"));
				d.setCname(rs.getString("cname"));
				list.add(d);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
	
	//获取寝室所属班级
	public List getDormClass(String building, String floor){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select d.*,c.cname from su_dorminfo d left join su_class c on d.dcid=c.cid where dbuilding='"+building+"' and dnumber like '"+floor+"%'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List list = new ArrayList();
			while(rs.next()){
				String[] s = new String[2];
				s[0] = rs.getString("dcid");
				s[1] = rs.getString("cname");
				list.add(s);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
	
	//添加寝室登记信息
	public boolean addDormInfo(DormEntity d,String date){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		date = date == null ? "default" : "'" + date + "'";
		String sql = "insert into su_dormregister values(?,?,?,?,?,?,?,?,?,"+date+")";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, d.getBuilding());
			ps.setString(2, d.getNumber());
			ps.setString(3, d.getCid());
			ps.setString(4, d.getStandarda());
			ps.setString(5, d.getStandardb());
			ps.setString(6, d.getStandardc());
			ps.setString(7, d.getStandardd());
			ps.setString(8, d.getMark());
			ps.setString(9, d.getLiable());
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
	
	//更新寝室登记信息
	public boolean updateDormInfo(DormEntity d,String date){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		date = date==null?"getdate()":"'"+date+"'";
		String sql = "update su_dormregister set dcid=?,dstandarda=?,dstandardb=?,dstandardc=?,dstandardd=?,dmark=?,dliable=? where datediff(dd,ddate,"+date+")=0 and dnumber=? and dbuilding=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, d.getCid());
			ps.setString(2, d.getStandarda());
			ps.setString(3, d.getStandardb());
			ps.setString(4, d.getStandardc());
			ps.setString(5, d.getStandardd());
			ps.setString(6, d.getMark());
			ps.setString(7, d.getLiable());
			ps.setString(8, d.getNumber());
			ps.setString(9, d.getBuilding());
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
