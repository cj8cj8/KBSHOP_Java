package uuu.vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.vgb.entity.Customer;
import uuu.vgb.exception.VGBDataInvalidException;
import uuu.vgb.exception.VGBException;
import uuu.vgb.service.CustomerService;

public class TestCustomerServiceUpdate {
	public static void main(String[] args) {
		
		CustomerService service=new CustomerService();
		try {
			Customer c=service.login("A123456789","test123" );
			System.out.println(c);
			
			c.setAddress("高雄市");
			c.setPhone("0912345678");
			c.setName("小明");
			service.update(c);
			
		} catch (VGBDataInvalidException e) {
			Logger.getLogger("TestCustomerServiceUpdate").log(Level.SEVERE,e.getMessage(),e);
		} catch (VGBException e) {
			Logger.getLogger("TestCustomerServiceUpdate").log(Level.SEVERE,e.getMessage(),e);
		}catch (Exception e) {
			Logger.getLogger("TestCustomerServiceUpdate").log(Level.SEVERE,"非預期錯誤",e);
		}
	}

}
