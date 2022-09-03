package uuu.vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.vgb.entity.Customer;
import uuu.vgb.exception.VGBDataInvalidException;
import uuu.vgb.exception.VGBException;
import uuu.vgb.service.CustomerService;

public class TestCustomerServiceRegister {

	public static void main(String[] args) {
		Customer c=new Customer();
		try {
			c.setId("B106211992");
			c.setName("測試生日");
			c.setPassword("test123");
			
			c.setGender('m');
			c.setEmail("Fg5456@uuu.com");	
			c.setPhone("0904222400");
			c.setBirthday("1995-06-07");		
			c.setAddress("南京復興");
			c.setSubscribed(false);
		
		
		CustomerService service=new CustomerService();
		
			service.register(c);
		}catch (VGBException e) {
			Logger.getLogger("TestCustomerServiceRegister").log(
					Level.SEVERE, e.getMessage(),e);//for admin ,tester,developer
		}catch (VGBDataInvalidException e) {
				System.err.println(e.getMessage());//for user
		} catch (Exception e) {
			Logger.getLogger("TestCustomerServiceRegister").log(
					Level.SEVERE, "發生非預期錯誤",e);//for admin ,tester,developer
		}
	}

	
}
