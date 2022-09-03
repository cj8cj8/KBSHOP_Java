package uuu.vgb.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.vgb.entity.Customer;
/**
 * 可以避免SQL Injection的攻擊的技巧
 *  避免關鍵->商業邏輯: WHERE id=? AND password=?
 * @author Admin
 *
 */
public class TestAllCustomersLogin2 {
	private final static String driver = System.getProperty("driver", "com.mysql.cj.jdbc.Driver");
	private final static String url = System.getProperty("url", "jdbc:mysql://localhost:3306/vgb");
	private final static String userid = "root";
	private final static String pwed = "Lyh0207@";

	private	final static String SELECT_CUSTOMERS = "SELECT id, password, name, gender, email, birthday, "
			+ "address, phone, subscribed, discount"+" FROM customers WHERE id=? OR email=?";

	public static void main(String[] args) {
		//1.輸入;
		String id="";
		String password="";
		Scanner scanner=new Scanner(System.in);
		System.out.println("請輸入身分證");
		id=scanner.next();
		System.out.println("請輸入密碼");
		password=scanner.next();
		//執行商業邏輯(JDBC查詢)
		 String sql=SELECT_CUSTOMERS+" WHERE id=? AND password=?";
		 Customer c=null;
		 try {
			//載入driver
			Class.forName(driver);		
			try (
					//建立連線
					Connection connection = DriverManager.getConnection(url, userid, pwed);			
					//3.準備指令
					PreparedStatement pstmt= connection.prepareStatement(SELECT_CUSTOMERS);					
					){
				//3.1傳入?的值
				pstmt.setString(1, id);
				pstmt.setString(2, id);				
				try(ResultSet rs=pstmt.executeQuery();){				
				//4.執行指令
				
				System.out.println("查詢結果");
				//5.處理rs
				while(rs.next()) {
						c=new Customer();
						c.setId(rs.getString("id"));
						c.setPassword(rs.getString("password"));
						c.setName(rs.getString("name"));
						c.setGender(rs.getString("gender").charAt(0));
						c.setBirthday(rs.getString("birthday"));
						c.setAddress(rs.getString("address"));
						c.setPhone(rs.getString("phone"));
						c.setSubscribed(rs.getBoolean("subscribed"));
						
						System.out.println("折扣:"+rs.getInt("discount"));						
						System.out.println("\n");
						System.out.println("****************");
					}	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Logger.getLogger("讀取所有顧客資料").log(Level.SEVERE, "資料庫連線失敗", e);
			}
			if(c!=null) {
				if(!c.getPassword().equals(password)) {
					c=null;
				}
			}
			//3.1輸出正確結果
			System.out.println(c!=null?c.getName()+"登入成功":"登入失敗");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//3.2 log錯誤訊息
			Logger.getLogger("讀取所有顧客資料").log(Level.SEVERE, "載入driver失敗", e);
		}
	}
}

	


