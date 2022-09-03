package uuu.vgb.test;

import java.time.LocalDate;
import java.time.Month;

import uuu.vgb.entity.Customer;

public class TestCustomer {

	public static void main(String[] args) {
		Customer customer=new Customer();
		customer.setId("A123456789");//必要 ROC ID
		customer.setPassword("test123");//必要,2~20個字元
		customer.setName(" 小明 ");//必要,2~20個字元
		customer.setGender('M');//必要,'M':男,'F':女
		customer.setEmail("123456@uuu.com");//必要
		//customer.birthday=LocalDate.of(1988,06,07);//
		//customer.birthday=LocalDate.parse("1998-06-07");
		customer.setBirthday(1998,06,07);//int
		customer.setBirthday("2013-06-07");//string
		//需要符合(年-日-月)iso8601的格式
		 //public Date birthday; import java.time.Date;
		customer.setAddress("南京復興");//非必要,not null,0~100個字元 
		customer.setPhone("00000000");//非必要,not null,0~30個字元
		//customer.subscribed=false;
	
//		//System.out.println("password:"+customer.password);
//		System.out.println("password:"+customer.getPassword()); //asdf1234
//		
//		
//		//System.out.println(customer.getAge()+"歲");
//		//System.out.println("age:"+customer.getAge());
//		System.out.println("birthday:"+customer.getBirthday());//getBirthday();
//		
//		//System.out.println("id:"+customer.id); //A123456789		
//		System.out.println("身分證字號:"+customer.getId());
//		System.err.println(customer.checkROCId(customer.getId()));
//		
//		System.out.println("customer.name:"+customer.getName()); //狄會貴
//		
//		
//		System.out.println("gender:"+customer.getGender()); //M
//		System.out.println("email:"+customer.getEmail()); //test@gmail.com
//		 //2010-02-28		
//		System.out.println("address:"+customer.getAddress());//
//		System.out.println("phone:"+customer.getPhone());//
//		System.out.println("subscribed:"+customer.isSubscribed());//false
		
		
		System.out.println(customer);
		
	}

}
