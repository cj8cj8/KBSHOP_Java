package uuu.vgb.test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import uuu.vgb.entity.VIP;

public class TestVIP {

	public static void main(String[] args) {
	VIP vip=new VIP();
	
	vip.setName("大明");
	vip.setId("a123456770");
	vip.setPassword("tes3621616");
	vip.setEmail("test05@gmail.com.tw");
	vip.setBirthday(1995,10,18);
	vip.setGender('M');
	vip.setDiscount(10);	
//	System.out.println("顧客姓名:"+vip.getName());
//	System.out.println("顧客身份證:"+vip.getId());	
//	System.out.println("顧客密碼:"+vip.getPassword());
//	System.out.println("顧客信箱:"+vip.getEmail());
//	System.out.println("顧客生日:"+vip.getBirthday());
//	System.out.println("顧客生日:"+vip.getBirthday().getDayOfWeek().getDisplayName(TextStyle.SHORT,Locale.getDefault()));
//	System.out.println("顧客性別:"+vip.getGender());
//	System.out.println("VIP折扣:-"+vip.getDiscount()+"%off");
//	System.out.println("VIP折扣:"+vip.getDiscountString());
	System.out.println(vip);
	

	}

}
