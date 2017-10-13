package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.NoticeEntity;

public class NoticeDao {
	
	//分页查询公告
	public List getNotice(String role,int page,String npublisher){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if("0".equals(role)){
			sql = "select n.*,d.dname from su_dept d left join (select row_number() over(order by ndate desc) rn,* from su_notice )n on n.rn>("+5+"*"+(page-1)+") and n.rn<=("+5+"*"+page+") where n.ndid = d.did";
		}else{
			if(npublisher != null && npublisher.length() != 0){
				npublisher = " and n.npublisher='"+npublisher+"'";
			}else{
				npublisher = "";
			}
			//select n.*,d.dname from su_dept d left join (select row_number() over(order by ndate desc) rn,* from su_notice where ndid=4 or ndid=0 and npublisher='系统') n on n.rn>0 and n.rn<=3 where n.ndid = d.did
			sql = "select n.*,d.dname from su_dept d left join (select row_number() over(order by ndate desc) rn,* from su_notice where ndid="+role+" or nregion='public')n on n.rn>("+5+"*"+(page-1)+") and n.rn<=("+5+"*"+page+") where n.ndid = d.did"+npublisher;
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List list = new ArrayList();
			while(rs.next()){
				NoticeEntity n = new NoticeEntity();
				n.setNid(rs.getString("nid"));
				n.setNpublisher(rs.getString("npublisher"));
				n.setNdate(rs.getString("ndate"));
				n.setNweek(rs.getString("nweek"));
				n.setNtitle(rs.getString("ntitle"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNdid(rs.getString("ndid"));
				n.setNdname(rs.getString("dname"));
				n.setNregion(rs.getString("nregion"));
				list.add(n);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
	
	//查询公告条数
	public int getCount(String role,String npublisher){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if("0".equals(role)) {
			sql = "select count(*) from su_notice where 1=1";
		}else if(npublisher != null) {
			sql = "select count(*) from su_notice where npublisher='"+npublisher+"'";
		}else {
			sql = "select count(*) from su_notice where nregion='public' or ndid="+role;
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int allpage = 0;
			if(rs.next()){
				allpage = (int) Math.ceil(rs.getFloat(1)/5);
			}
			return allpage;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return 0;
	}
	
	//新增公告
	public boolean addNotice(NoticeEntity n){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into su_notice values(?,"+n.getNdid()+",default,default,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, n.getNpublisher());
			ps.setString(2, n.getNtitle());
			ps.setString(3, n.getNcontent());
			ps.setString(4, n.getNregion());
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return false;
	}
	
	//编辑公告
	public boolean editNotice(String text, String title, String nid){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		String sql = "update su_notice set ntitle='"+title+"',ncontent='"+text+"',ndate=default,nweek=default where nid="+nid;
		try {
			ps = conn.prepareStatement(sql);
			if(ps.executeUpdate()>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			BaseDao.closeDB(conn, ps, null);
		}
		return false;
	}
	
	//删除公告
	public boolean delNotice(String nid){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		String sql = "delete from su_notice where nid="+nid;
		try {
			ps = conn.prepareStatement(sql);
			if(ps.executeUpdate()>0){
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
