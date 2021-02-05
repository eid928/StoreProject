package com.hyjiangd.JDBC.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemp {
	
	/**
	 * 執行查詢方法
	 * @param pscreator 創建語句物件
	 * @param callbackhandler 創建結果集遍歷物件
	 * @throws DataAccessException 
	 */
	public void query(PreparedStatementCreator pscreator,
			          RowCallbackHandler callbackhandler) throws DataAccessException {
		
		Connection connection = null;
		PreparedStatement pstatement = null;
		ResultSet resultset = null;
		
		// 這裡使用預編譯statement。
		try {connection = DBHelp.getConnection();
			 pstatement = pscreator.createPreparedStatement(connection);
			 resultset = pstatement.executeQuery();
			
			System.out.println("數據庫連接成功" + connection);
			
			while (resultset.next()) {
				callbackhandler.processRow(resultset);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DataAccessException("JdbcTemp中的SQLException: ", e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DataAccessException("JdbcTemp中的SQLException: ", e);
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DataAccessException("JdbcTemp中不能關閉數據庫連接: ", e);
				}
			}
			if(pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DataAccessException("JdbcTemp中不能關閉語句物件: ", e);
				}
			}
			if(resultset != null) {
				try {
					resultset.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DataAccessException("JdbcTemp中不能關閉結果集: ", e);
				}
			}
		}
		
	}
	
	/**
	 * 數據修改方法
	 * @param pscreator 創建語句物件
	 * @throws DataAccessException
	 */
	public void update(PreparedStatementCreator pscreator) throws DataAccessException {
		
		Connection connection = null;
		PreparedStatement pstatement = null;
		
		// 這裡使用預編譯statement。
		try {connection = DBHelp.getConnection();
			 pstatement = pscreator.createPreparedStatement(connection);
			
			 pstatement.executeUpdate();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DataAccessException("JdbcTemp中的SQLException: ", e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new DataAccessException("JdbcTemp中的SQLException: ", e);
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DataAccessException("JdbcTemp中不能關閉數據庫連接: ", e);
				}
			}
			if(pstatement != null) {
				try {
					pstatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DataAccessException("JdbcTemp中不能關閉語句物件: ", e);
				}
			}
		}
	}

}
