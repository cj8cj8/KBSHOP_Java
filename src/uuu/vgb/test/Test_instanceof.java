package uuu.vgb.test;

import uuu.vgb.entity.Customer;
import uuu.vgb.entity.VIP;

public class Test_instanceof {
	public static void main(String[]agrs) {
		Object o=new Object();
		Customer c= new Customer();
		VIP v=	new VIP();
		String s="";
		int i;
		System.out.println(o instanceof Object);
		System.out.println(c instanceof Object);
		System.out.println(v instanceof Object);
		System.out.println("***************************");
		System.out.println(o instanceof Customer);
		System.out.println(c instanceof Customer);
		System.out.println(v instanceof Customer);
		//System.out.println(s instanceof Customer);// compile-time error
		System.out.println("***************************");
		System.out.println(o instanceof VIP);
		System.out.println(c instanceof VIP);
		System.out.println(v instanceof VIP);
		//System.out.println(s instanceof VIP);// compile-time error
	}

}
