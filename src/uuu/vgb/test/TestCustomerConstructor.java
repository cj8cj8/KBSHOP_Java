package uuu.vgb.test;

import uuu.vgb.entity.Customer;

public class TestCustomerConstructor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c=new Customer("a123456789","asd456987","大明");
//		c.setId("a123456789");
//		c.setName("大明");
//		c.setPassword("asd456987");
//		c.setBirthday(1995, 10, 10);
//		c.setEmail("asdqqq456@gmail.com");
		System.out.println(c.toString());
	}

}
