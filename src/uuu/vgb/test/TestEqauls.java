package uuu.vgb.test;

import java.util.Scanner;

import uuu.vgb.entity.Customer;
import uuu.vgb.entity.Outlet;
import uuu.vgb.entity.Product;

public class TestEqauls {

	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
		String s1="Hello";
		String s2=new String("Hello");
		System.out.println("請輸入Hello");
		String s3=scanner.next();
		
		System.out.println("s1==s2:"+(s1==s2));//false;
		System.out.println("s2==s3:"+(s2==s3));//false;
		System.out.println("s1==s3:"+(s1==s3));//false;
		
		System.out.println("s1.equals(s2):"+s1.equals(s2));//true;
		System.out.println("s1.equals(s3):"+s1.equals(s3));//true;
		s2=s1;
		System.out.println("s1==s2:"+(s1==s2));//true
		System.out.println("s1.equals(s2):"+s1.equals(s2));//true;
		
		Customer c1=new Customer("A123456789","Password","忠明");
		Customer c2=new Customer("A123456789","Pssword","忠明");
		
		System.out.println("c1==c2:"+(c1==c2));//false
		System.out.println("c1.equals(c2)"+c1.equals(c2));
		//還沒寫Customer類別eqials方法時:false，已經給了:true
		
		c1=c2;
		System.out.println("c1==c2:"+(c1==c2));//true
		System.out.println("c1.equals(c2)"+c1.equals(c2));//true
		
		Product p1 =new Product(1,"酒精",250);
		Product p2 =new Product(1,"酒精",250);
		Outlet outlet=new Outlet();
		outlet.setId(1);
		outlet.setName("酒精");
		outlet.setUnitPrice(250);
		outlet.setDiscount(1);
	
	
	
	}

}
