package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Notice;
import com.entity.PageBean;

/**
 * 寻物启事基础类
 * @author Administrator
 *
 */
public class BroadNoticeDao {
	 /**
	  * 查询和模糊
	  */
	 public PageBean selectData(PageBean page,String data,int type){
		//连接数据库
		Connection conn=BaseDao.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="";
		try {
			if(data==null){
			sql="select * from"
						+ "(select ROW_NUMBER() over(order by ntime DESC ) nd,* from br_notice where ntid="+type
						+ ")l where l.nd>(("+page.getCpage()+"-1)*"+page.getShowNum()+") and l.nd<=("+page.getCpage()*page.getShowNum()+")";
			}else{
			sql="select * from"
					+ "(select ROW_NUMBER() over(order by ntime DESC ) nd,* from br_notice where ntid="+type+" and ncontent like '%"+data+"%'"
					+ ")l where l.nd>(("+page.getCpage()+"-1)*"+page.getShowNum()+") and l.nd<=("+page.getCpage()*page.getShowNum()+")";
			}
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();//执行查询
			List list=new ArrayList();//创建list数组
			while(rs.next()){
				Notice n=new Notice();
				n.setNid(rs.getInt(2));
				n.setNname(rs.getString(3));
				n.setNcontent(rs.getString(4));
				n.setNtime(rs.getString(5));
				n.setNtid(rs.getInt(6));
				list.add(n);
			}
			//将查询当前页要显示的数据封装到PageBean中
			page.setPageList(list);
			//然后获取总的数据数量
			if(data==null){
				sql="select count(*) from br_notice where ntid="+type;
			}else{
				sql="select count(*) from br_notice  where ntid="+type+" and ncontent like '%"+data+"%'";
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
	
	
	/**
	 * 添加数据
	 */
	public boolean addData(Notice n){
		String sql="insert into br_notice values(?,?,?,?)";
		Object[] obj={n.getNname(),n.getNcontent(),n.getNtime(),n.getNtid()};
		return BaseDao.updateDb(sql, obj);
	 }
	 /**
	  * 删除数据方法
	  * @param id 数据id
	  */
	 public boolean removeData(int id){
		 String sql="delete from br_notice where nid="+id;
		 return BaseDao.updateDb(sql, null);
	 }
	 /**
	  * 根据指定id查找信息
	  */
	 public Notice getUserById(int id){
		 //连接数据库
		 Connection conn=BaseDao.getConnection();
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		 try {
			String sql="select * from br_notice where nid="+id;
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				Notice n=new Notice(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				return n;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeDB(conn, ps, rs);
		}
		 return null;
	 }
	 
	 /**
	  * 修改数据
	  */
	 public boolean upData(Notice n){
		String sql="update br_notice set ncontent=? where ntid="+n.getNtid()+" and nid="+n.getNid();
		Object[] obj={n.getNcontent()};
		return BaseDao.updateDb(sql, obj);
	 }
}
