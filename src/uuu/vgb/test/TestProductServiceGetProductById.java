package uuu.vgb.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.vgb.entity.Product;
import uuu.vgb.exception.VGBException;
import uuu.vgb.service.ProductService;

public class TestProductServiceGetProductById {
	public static void main(String[] args) {
		ProductService service = new ProductService();
		try {
			Product p = service.getProductById("1");
			System.out.println(p);
		} catch (VGBException e) {
			Logger.getLogger("TestProductServiceGetProductById").log(Level.SEVERE, e.getMessage(), e);
		} catch (Exception e) {
			Logger.getLogger("TestProductServiceGetProductById").log(Level.SEVERE, "發生非預期錯誤", e);
		}
	}
}
