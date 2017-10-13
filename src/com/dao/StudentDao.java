package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.DormEntity;

public class StudentDao {
	//按班级和日期获取寝室内务信息
	public List getForCla(String date, String cid,String sort){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sort = "1".equals(sort)?"d.dmark desc,":"";
		date = date == null?"getdate()":"'"+date+"'";
		String sql = "select d.*,c.cname from su_dormregister d left join su_class c on d.dcid=c.cid where datediff(dd,d.ddate,"+date+")=0 and d.dcid="+cid+" order by "+sort+"dbuilding,dnumber";
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
	
	
	//查询所有班级
	public List getCla(){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from su_class";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List list = new ArrayList();
			//rs.next();//排除第一个特殊班级”暂无“
			while(rs.next()){
				String[] s = new String[2];
				s[0] = rs.getString(1);
				s[1] = rs.getString(2);
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
	
	//查询寝室一个月的内务登记信息
	public List getForDorm(String date,String building, String number, String cid, String sort, int page){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sort = "1".equals(sort)?"dmark desc,":"";
		date = date==null?"getdate()":"'"+date+"'";
		//select d.*,c.cname from su_class c left join (select row_number() over(order by ddate) rn,* from su_dormregister where datediff(mm,ddate,'2017-06-01')=0 and dbuilding='a' and dnumber='101' and dcid='1') d on d.rn>0 and d.rn<=12  where dcid=c.cid
		String sql = "select d.*,c.cname from su_class c left join (select row_number() over(order by "+sort+"ddate) rn,* from su_dormregister where datediff(mm,ddate,"+date+")=0 and dbuilding='"+building+"' and dnumber='"+number+"' and dcid='"+cid+"') d on d.rn>("+12+"*"+(page-1)+") and d.rn<=("+12+"*"+page+") where dcid=c.cid";
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
	
	//查询总条数
	public int getCount(String date, String building, String number, String cid){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		date = date==null?"getdate()":"'"+date+"'";
		//select COUNT(*) from su_dormregister where datediff(mm,ddate,'2017-06-01')=0 and dbuilding='a' and dnumber='101' and dcid='1'
		String sql = "select COUNT(*) from su_dormregister where datediff(mm,ddate,"+date+")=0 and dbuilding='"+building+"' and dnumber='"+number+"' and dcid="+cid;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int allpage = 0;
			if(rs.next()){
				allpage = (int) Math.ceil(rs.getFloat(1)/12);
			}
			return allpage;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//查询本班级的所有寝室信息
	public List getDorm(String cid){
		Connection conn = BaseDao.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from su_dorminfo where dcid="+cid;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List list = new ArrayList();
			while(rs.next()){
				String[] s = new String[2];
				s[0] = rs.getString(2);
				s[1] = rs.getString(3);
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
}
