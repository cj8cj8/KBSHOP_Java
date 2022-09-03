package uuu.vgb.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import uuu.vgb.exception.VGBException;



class MySQLConnection {
	
	private final static String driver = System.getProperty("driver", "com.mysql.cj.jdbc.Driver");
	private final static String url = System.getProperty("url", "jdbc:mysql://localhost:3306/vgb");
	private final static String userid = "root";
	private final static String pwd = "Lyh0207@";
	
	static Connection getConnection()throws VGBException{
		
		 try {
			Class.forName(driver);//1.Driver
		
			try {
				 Connection connection = DriverManager.getConnection(url,userid,pwd);//2.建立連線		
				
				 return connection;
			
			} catch (SQLException e) {
				//Logger.getLogger("MySQL_Connection").log(Level.SEVERE,"建立連線失敗",e);
				throw new VGBException("建立連線失敗",e);
			}	 
			
					 
		 } catch (ClassNotFoundException e) {		
			//Logger.getLogger("MySQL_Connection").log(Level.SEVERE,"載入Driver失敗",e);
			throw new VGBException("載入JDBC Driver失敗",e);
		 }
		
	}
	
	
}
