package uuu.vgb.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import uuu.vgb.entity.Order;
import uuu.vgb.entity.PaymentType;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.ShippingType;
import uuu.vgb.entity.ShoppingCart;

import uuu.vgb.exception.VGBDataInvalidException;
import uuu.vgb.exception.VGBException;
import uuu.vgb.service.CustomerService;
import uuu.vgb.service.OrderService;
import uuu.vgb.service.ProductService;


public class TestGetOrderByCustomerID2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerService service= new CustomerService();
		ProductService pservice=new ProductService();
		
		try {
		Customer member=service.login("A123456789","test123");	
	
		
	
	
		
		
		OrderService oService=new OrderService();
		
		
		List<Order> order=oService.getOrderHistory(member.getId());
		
		
		Order orders=oService.getOrderDetail(member.getId(), "11");
		System.out.println(orders);
		}catch(VGBException|VGBDataInvalidException e) {
			Logger.getLogger("測試購物車").log(Level.SEVERE,e.getMessage());
			}catch(Exception e) {
				Logger.getLogger("測試購物車").log(Level.SEVERE,"發生非預期錯誤",e);
			}
		}
		

}
