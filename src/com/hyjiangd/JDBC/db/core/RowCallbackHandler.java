package com.hyjiangd.JDBC.db.core;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowCallbackHandler {
	
	// 處理結果集物件
	void processRow(ResultSet rs) throws SQLException;
}
