package uuu.vgb.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;


import uuu.vgb.entity.Order;
import uuu.vgb.entity.PaymentType;
import uuu.vgb.entity.Customer;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.ShippingType;
import uuu.vgb.entity.ShoppingCart;
import uuu.vgb.exception.StockShortageException;
import uuu.vgb.exception.VGBDataInvalidException;
import uuu.vgb.exception.VGBException;
import uuu.vgb.service.CustomerService;
import uuu.vgb.service.OrderService;
import uuu.vgb.service.ProductService;


public class TestShoppingCart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerService service= new CustomerService();
		ProductService pservice=new ProductService();
		
		try {
		Customer member=service.login("A123456789","test123");	
		Product p1=pservice.getProductById("1");		
		Product p7=pservice.getProductById("7");
				
		ShoppingCart cart =new ShoppingCart();
		cart.setMember(member);
		
		cart.add(p1, null,1, 4);
		
		//cart.add(p7, 1,null, 1);
		
		//System.out.println(cart);
		
		Order order=new Order();
		order.add(cart);
		order.setCreatedDate(LocalDate.now());
		order.setCreatedTime(LocalTime.now());
		order.setPaymentType(PaymentType.valueOf("HOME"));
		order.setPaymentFee(order.getPaymentType().getFee());
		order.setShippingType(ShippingType.valueOf("HOME"));
		order.setShippingFee(order.getShippingType().getFee());
		
		order.setRecipientName(member.getName());
		order.setRecipientEmail(member.getEmail());
		order.setRecipientPhone(member.getPhone());
		order.setShippingAddres(member.getAddress());
		
		//System.out.println(order);
		
		OrderService oService=new OrderService();
		oService.createOrder(order);
		}catch (StockShortageException e) {			
			Logger.getLogger("測試庫存").log(Level.SEVERE,e.getMessage(), e);
		}catch(VGBException|VGBDataInvalidException e) {
			Logger.getLogger("測試購物車").log(Level.SEVERE,e.getMessage(),e);
			}catch(Exception e) {
				Logger.getLogger("測試購物車").log(Level.SEVERE,"發生非預期錯誤",e);
			}
		}
		

}
