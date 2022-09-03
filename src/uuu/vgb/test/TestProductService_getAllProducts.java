package uuu.vgb.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.vgb.entity.Product;
import uuu.vgb.exception.VGBException;
import uuu.vgb.service.ProductService;

public class TestProductService_getAllProducts {

	public static void main(String[] args) {
		ProductService serivce=new ProductService();
		try {
//			List<Product>list =serivce.getALLProduct();
//			System.out.println(list);
//		System.out.println(list.size());
		
			
//			List<Product>list1 =serivce.getProductByName("z");
//			System.out.println(list1);
//			System.out.println(list1.size());
		
		List<Product>list1 =serivce.getProductByBrand("z");
		System.out.println(list1);
		System.out.println(list1.size());
//			List<Product>list =serivce.getProductByDate("");
//			System.out.println(list);
//			System.out.println(list.size());
//			
//			List<Product>list1 =serivce.getProductByCategory("手托");
//			System.out.println(list1);
//			System.out.println(list1.size());
		}catch (VGBException e) {
			Logger.getLogger("TestProductService_getAllProducts").log(
					Level.SEVERE, e.getMessage(),e);
		}

	}

}
