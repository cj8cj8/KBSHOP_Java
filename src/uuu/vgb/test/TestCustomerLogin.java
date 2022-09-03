package uuu.vgb.test;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.vgb.entity.Customer;
import uuu.vgb.exception.VGBException;
import uuu.vgb.service.CustomerService;

public class TestCustomerLogin {
	public static void main(String[] args) {
		//1.輸入
		String id="A123456789";
		String password="test123";
//		Scanner scanner=new Scanner(System.in);
//		System.out.println("帳號");
//		id=scanner.next();
//		System.out.println("請輸入密碼");
//		password=scanner.next();
		
		//2.c呼叫商業邏輯
		CustomerService service=new CustomerService();
		
		try {
		Customer c=service.login("A123456789","test123");
		//3.1 輸出
		System.out.println(c);
		}catch(VGBException e){
		//3.2.輸出失敗	
		Logger.getLogger("TestCustomerLogin").log(Level.SEVERE,e.getMessage(),e);
			
		}catch (Exception e) {
			Logger.getLogger("TestCustomerLogin").log(Level.SEVERE,"發生非預期錯誤",e);
		}
		
		
		
		System.out.println("the end");
}

}
