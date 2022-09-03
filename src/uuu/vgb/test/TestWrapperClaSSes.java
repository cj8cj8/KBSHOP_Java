package uuu.vgb.test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestWrapperClaSSes {

	public static void main(String[] args) {
		String quantity="14";
		int	qty=Integer.parseInt(quantity);
		System.out.println(qty);
		
		String priceStr="123.45";
		double price= Double.parseDouble(priceStr);
		System.out.println(price);
		
		Map<String, Integer>cartMap=new TreeMap<>();
		cartMap.put("酒精",3);//int->Integer物件:boxing
		cartMap.put("口罩",5);//int->Integer物件:boxing
		//Wrapper物件的boxing,unboxing
//		Integer five= new Integer(5);//boxing
//		double price1=100;
//		double price2=1050;
//		
//		double amount=price*five.intValue();
//		
//		Double totalAmount=new Double(amount+price2*3);//boxing
//		double totalAmountFee=totalAmount.doubleValue()+100;//Double物件->double:unboxing
//		
		
		//Wrapper物件的Auto-boxing,unboxing
		Integer five= 5;//boxing
		double price1=100;
		double price2=1050;
		
		double amount=price1*five;//five後的.intValue()不用寫 five->Integer物件:Auto-boxing
		
		Double totalAmount=amount+price2*3;//Auto-boxing
		double totalAmountFee=totalAmount+100;//Double物件->double:Auto-boxing
		 System.out.println("Map: " +cartMap.entrySet() );
	}

}
