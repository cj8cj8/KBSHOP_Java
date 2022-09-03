package uuu.vgb.test;

import java.time.LocalDate;

import javax.management.StringValueExp;

import uuu.vgb.entity.Outlet;
import uuu.vgb.entity.Product;

public class TestOutlet {

	public static void main(String[] args) {
		Outlet outlet=new Outlet();

		outlet.setId(1);
		outlet.setName("桶裝酒精");
		outlet.setStock(0);
		outlet.setUnitPrice(650);
		outlet.setDescription("無腐蝕性、不漂白，可直接用於手部清潔，添加苦味，防止誤食");
		outlet.setPhotoUrl("https://cs-d.ecimg.tw/items/DABC8YA900BUNB3/000001_1634204286.jpg");
		String dateStr=null;
		outlet.setLunchDate(dateStr);
		outlet.setDiscount(10);
//		System.out.println("id:"+outlet.getId());
//		System.out.println("Name:"+outlet.getName());
//		System.out.println("Stock:"+outlet.getStock());
//		System.out.println("定價:"+outlet.getListPrice());
//		System.out.println("折扣:"+outlet.getDiscountString());
//		System.out.println("售價:"+outlet.getUnitPrice());
//		System.out.println("Description:"+outlet.getDescription());
//		System.out.println("PhotoUrl:"+outlet.getPhotoUrl());
//		System.out.println("LunchDate:"+outlet.getLunchDate());
//		System.out.println("折扣:"+outlet.getDiscountString());
		
		//System.err.println("****************************************");
		//System.out.println(outlet.toString());
		System.out.println(outlet);
		//System.out.println(String.valueOf(outlet));
	
	}

}
