package com.hyjiangd.JDBC.db.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelp {
	
	// 數據庫配置項目最好放在數據庫持有層中，與web顯示層分離
	// 因此config.properties與servelt放一起，而非放到web-inf下的web.xml旁邊
	static Properties info = new Properties();
	static {
		// 獲得屬性文件輸入流
		InputStream in = DBHelp.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			// 從文件讀取到變量info
			info.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	private static String url = "jdbc:mysql://localhost/testdb?serverTimezone=UTC";
//	private static String user = "root";
//	private static String password = "Ab27215936";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		
		Class.forName(info.getProperty("driver"));
		// 這些硬編碼應該要改放到配置文件中以降低維護難度
		
		Connection connection = DriverManager.getConnection(info.getProperty("url"), info);
		// 這邊為getConnection的overload方法，直接讀取info中的帳號密碼
		return connection;
	}
}
