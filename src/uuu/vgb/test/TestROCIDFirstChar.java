package uuu.vgb.test;

import java.util.Scanner;

import uuu.vgb.entity.Customer;

public class TestROCIDFirstChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("請輸入身分證");
		Scanner scanner=new Scanner(System.in);
		String id=scanner.next();
		Customer customer=new Customer();
		customer.setId(id);
		System.out.println(customer.getId());
		

	}

}
