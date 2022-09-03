package uuu.vgb.test;

import java.time.LocalDate;

import uuu.vgb.entity.Product;

public class TestProduct {

	public static void main(String[] args) {
		Product p=new Product();

		p.setId(1);
		p.setName("桶裝酒精");
		p.setStock(0);
		p.setUnitPrice(650);
		p.setDescription("無腐蝕性、不漂白，可直接用於手部清潔，添加苦味，防止誤食");
		p.setPhotoUrl("https://cs-d.ecimg.tw/items/DABC8YA900BUNB3/000001_1634204286.jpg");
		String dateStr=null;
		p.setLunchDate(dateStr);
		System.out.println(p);
		
//		System.out.println("id:"+p.getId());
//		System.out.println("Name:"+p.getName());
//		System.out.println("Stock:"+p.getStock());
//		System.out.println("UnitPrice:"+p.getUnitPrice());
//		System.out.println("Description:"+p.getDescription());
//		System.out.println("PhotoUrl:"+p.getPhotoUrl());
//		System.out.println("LunchDate:"+p.getLunchDate());
		System.err.println("\n****************************************");
		Product p2=new Product(2,"桶裝食用級酒精濕巾",698);
		System.out.println(p2);
		
		
		System.err.println("\n****************************************");
		
		Product p3=new Product(3,"平面醫用口罩",250,30);
		System.out.println(p3);
		
		
		
		
	
	
	}

}
