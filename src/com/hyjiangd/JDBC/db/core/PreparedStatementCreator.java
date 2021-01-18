package com.hyjiangd.JDBC.db.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementCreator {
	// JDBC模板設計模式
	// 創建預編譯語句物件
	PreparedStatement createPreparedStatement(Connection conn) throws SQLException;

}
