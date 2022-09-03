package uuu.vgb.service;

import java.util.List;

import uuu.vgb.entity.Product;
import uuu.vgb.exception.VGBException;

public class ProductService {
	private ProductDAO dao=new ProductDAO();
	
	public List<Product> getALLProduct()throws VGBException{
		List<Product>list=dao.getALLProduct();
		
		return list;
	}
	public List<Product> getIndexRandomProduct()throws VGBException{
		List<Product>list=dao.selectIndexRandomProduct();
		
		return list;
	}
	public List<Product> getProductByName(String keyword)throws VGBException{
		if(keyword==null||keyword.length()==0) {
			throw new IllegalArgumentException("關鍵字查詢不得為空白");
		}
		
		List<Product>list=dao.selectProductByName(keyword);
		
		return list;
	}
	public List<Product> getProductByBrand(String keyword)throws VGBException{
		if(keyword==null||keyword.length()==0) {
			throw new IllegalArgumentException("關鍵字查詢不得為空白");
		}
		
		List<Product>list=dao.selectProductByBrand(keyword);
		
		return list;
	}
	public List<Product> getProductByNameStorng(String opt,String keyword1 )throws VGBException{
		if(opt!=null||opt.length()>0) {
		if(keyword1==null||keyword1.length()==0) {
			throw new IllegalArgumentException("關鍵字查詢不得為空白");
			}
		}
		List<Product>list=dao.selectProductByNameStorng(opt,keyword1);
		
		return list;
	}
	public List<Product> getProductByCategory(String Category)throws VGBException{
		if(Category==null||Category.length()==0) {
			throw new IllegalArgumentException("關鍵字查詢不得為空白");
		}
		
		List<Product>list=dao.selectProductByCategory(Category);
		
		return list;
	}
	public List<Product> getProductByDate(String date)throws VGBException{
		
		
		List<Product>list=dao.selectProductByDate(date);
		
		return list;
	}
	public Product getProductById(String productId)throws VGBException{
		if(productId==null||productId.length()==0) {
			throw new IllegalArgumentException("用產品代碼查詢產品時必須有代碼資料");
		}
		
		return dao.selectProductById(productId);
	}

	
	
	
}
