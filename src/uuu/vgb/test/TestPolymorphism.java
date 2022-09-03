package uuu.vgb.test;

import uuu.vgb.entity.Customer;

public class TestPolymorphism {

	public static void main(String[] args) {
		Object o =new Object();// normal referance type declaration
		String s ="Hello";//// normal referance type declaration
		Object o1="Hello";//// polymorphic referance type declaration
		
		o1=new Customer("A123456789","password","大明");
		if(o1 instanceof String) {//確認 o1的型態
			System.out.println(((String) o1).length());
			System.out.println("測試:"+((String)o1).length());
		}else if(o1 instanceof Customer) {
			System.out.println("身份證:"+((Customer)o1).getId());
		}
		System.out.println(o1.toString());
	}

}
