package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Essay;
import com.entity.PageBean;

public class EssayDao {
	/**
	  * 查询和模糊
	  */
	 public PageBean selectData(PageBean page,String data){
		//连接数据库
		Connection conn=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="";
		try {
			if(data==null){
			sql="select * from"
						+ "(select ROW_NUMBER() over(order by etime DESC ) nd,* from br_StuEssay "
						+ ")l where l.nd>(("+page.getCpage()+"-1)*"+page.getShowNum()+") and l.nd<=("+page.getCpage()*page.getShowNum()+")";
			}else{
			sql="select * from"
					+ "(select ROW_NUMBER() over(order by etime DESC ) nd,* from br_StuEssay where etitle like '%"+data+"%'"
					+ ")l where l.nd>(("+page.getCpage()+"-1)*"+page.getShowNum()+") and l.nd<=("+page.getCpage()*page.getShowNum()+")";
			}
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();//执行查询
			List list=new ArrayList();//创建list数组
			while(rs.next()){
				Essay e=new Essay(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				list.add(e);
			}
			//将查询当前页要显示的数据封装到PageBean中
			page.setPageList(list);
			//然后获取总的数据数量
			if(data==null){
				sql="select count(*) from br_StuEssay";
			}else{
				sql="select count(*) from br_StuEssay  where etitle like '%"+data+"%'";
			}
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				//将总数据条数封装到PageBean
				page.setAllNum(rs.getInt(1));
			}
			//返回page方法
			return page;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		return null;
	}
	 public boolean addData(Essay e){
		String sql="insert into br_StuEssay values(?,?,?,?)";
		Object[] obj={e.getEname(),e.getEtitle(),e.getEcontent(),e.getEtime()};
		return BaseDao.updateDb(sql, obj);
	 }
	 //查询id数据
	 public Essay getData(int id){
		 //连接数据库
		 Connection conn=BaseDao.getConnection();
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 try {
			String sql="select * from br_StuEssay where eid="+id;
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				Essay e=new Essay(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				return e;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		 return null;
	 }
	 /**
	  * 删除数据方法
	  * @param id 数据id
	  */
	 public boolean removeData(int id){
		 String sql="delete from br_StuEssay where eid="+id;
		 return BaseDao.updateDb(sql, null);
	 }
}
