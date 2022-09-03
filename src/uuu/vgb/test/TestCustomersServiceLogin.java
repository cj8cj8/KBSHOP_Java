package uuu.vgb.test;

import java.util.Scanner;

import uuu.vgb.entity.Customer;
import uuu.vgb.service.CustomerService;
/**
 * 可以避免SQL Injection的攻擊的技巧
 *  避免關鍵->商業邏輯: WHERE id=? AND password=?
 * @author Admin
 *
 */
public class TestCustomersServiceLogin {
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
		System.out.println("請輸入帳號");
		id=scanner.next();
		System.out.println("請輸入密碼");
		password=scanner.next();
		//執行商業邏輯(JDBC查詢)
		CustomerService service=new CustomerService();
	//	Customer c=service.login(id, password);
		//System.out.println(c!=null?c.getName()+"登入成功":"登入失敗");
	}
}

	


