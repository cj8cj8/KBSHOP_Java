package uuu.vgb.test;

import java.security.Provider.Service;

import uuu.vgb.entity.Product;


public class TestProduct_PassByValue {
	public static void main(String[]args) {
		Product p3=new Product(3,"平面醫用口罩",250,30);
		
		
		//double price=p3.getUnitPrice()+200;
		//p3.setUnitPrice(price);
		
		ProductServiceI service=new ProductServiceI();
		service.addPrice(p3.getUnitPrice());
		System.out.println("UnitPriceTEST"+p3.getUnitPrice());
		
		service.addPrice(p3);
		System.out.println("UnitPrice"+p3.getUnitPrice());
	}
}
class ProductServiceI{
	public void addPrice(double price) {
		price=price+200;
	}
	public void addPrice(Product p) {
		p.setUnitPrice(p.getUnitPrice()+200);
	}
}