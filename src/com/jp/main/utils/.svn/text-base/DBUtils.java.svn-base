package com.jp.main.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jp.main.entity.CarTakeVo;

/**
 * JDBC数据库交互类
 * @author zh.h
 *
 */
public class DBUtils{
	
	private static final String QUERY="SELECT * FROM ";
	private static final String DELETE="DELETE FROM ";
	
	public static List<CarTakeVo> queryAndBatchDelete(Connection conn ,String tableName){
		List<CarTakeVo> list= new ArrayList<CarTakeVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn.setAutoCommit(false);
			pstmt=(PreparedStatement)conn.prepareStatement(QUERY+tableName);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CarTakeVo vo=new CarTakeVo();
				//from 1 to end!
				list.add(vo);
			}
			pstmt=(PreparedStatement)conn.prepareStatement(DELETE+tableName);
			conn.commit();
			conn.setAutoCommit(true);
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				pstmt.close();
	            conn.close();
	            conn=null;
		    }catch(SQLException sqle){
		      	sqle.printStackTrace();
		    }
		}
		return list;
	}
}
