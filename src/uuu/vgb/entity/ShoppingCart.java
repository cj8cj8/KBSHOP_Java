package uuu.vgb.entity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import uuu.vgb.exception.VGBDataInvalidException;

public class ShoppingCart {
	
	public static final NumberFormat PRICE_FORMAT=new DecimalFormat("###,###0");
	public static final NumberFormat TOTAL_PRICE_FORMAT=new DecimalFormat("########0");
	private Customer member;
	private Map<CartItem, Integer> cartMap = new HashMap<>();

	public Customer getMember() {
		return member;
	}

	public void setMember(Customer member) {
		this.member = member;
	}

//cart's muators:add,update,remove
	public void add(Product product, Integer typeName, Integer sizeName, int quantity) {
		if (product == null) {
			throw new IllegalArgumentException("加入購物車時Product物件不得為null");
		}
		ProductType type = null;
		if (product.istype() && typeName >0) {
			type = product.findType(typeName);
			if (type == null) {
				throw new VGBDataInvalidException("加入購物車時軸承不符合軸承清單:" +product.getName()+ typeName);
			}
		}else if (product.istype() && (typeName >0 || typeName ==null )) {
			throw new VGBDataInvalidException("加入購物車時軸承不符合產品軸承清單:" + product.getName()+typeName);
		}else if (!product.istype()&&  typeName !=null ) {
			throw new VGBDataInvalidException("產品並無此規格:軸承-" + product.getName()+typeName);
		}

		ProductSize size = null;

		if (product.isSize() && sizeName >0) {
			size = product.findSize(sizeName);
			if (size == null) {
				throw new VGBDataInvalidException("加入購物車時尺寸不符合尺寸清單:" + product.getName()+sizeName);
			}
		}
		else if (product.isSize() && (sizeName >0||sizeName ==null)) {
			throw new VGBDataInvalidException("加入購物車時尺寸不符合產品尺寸清單:" +product.getName()+ sizeName);
		}else if (!product.isSize() && sizeName !=null) {
			throw new VGBDataInvalidException("產品並無此規格:-尺寸" +product.getName()+ sizeName);
		}

		CartItem item = new CartItem();

		item.setProduct(product);
		item.setSize(size);
		item.setType(type);

		Integer oldQty = cartMap.get(item);// 檢查cartMap是否已經有相同的產品
		if (oldQty != null) {// 有就加上之前的數量
			quantity += oldQty;
		}
		cartMap.put(item, quantity);

	}
	public void update(CartItem item,int quatity) {
		if(cartMap.get(item)!=null) {
			cartMap.put(item,quatity);
		}
	}
	
	public Integer remove(CartItem item) {
		return cartMap.remove(item);
	}

	//cart's accessors
//1.from delegate method
	public int size() {
		return cartMap.size();
	}

	public boolean isEmpty() {
		return cartMap.isEmpty();
	}

	public int getQuantity(CartItem key) {
		Integer qty = cartMap.get(key);
		return qty == null ? 0 : qty;
	}
//	public Set<CartItem> getCartItemsSet(Comparator<CartItem> comparator) {
//	TreeSet<CartItem> set = new TreeSet<>(comparator);
//	set.addAll(cartMap.keySet());		
//	return  new TreeSet<>(cartMap.keySet()); //回傳set的複本
//}
	public Set<CartItem> getCartItemSet() {
		// return cartMap.keySet();

		return new HashSet<>(cartMap.keySet());
		
	}
//2.businsee methods
/**
 * 計算該筆明細的小計金額
 * @param item
 * @return
 */
	public double getAmount(CartItem item) {
		
		int qty=this.getQuantity(item);
		double amount=item.getUnitPrice()*qty;
		return amount;
		
	}
	/**
	 * 計算購物車共有幾件商品
	 * @return
	 */	
				
	public int getTotalQunatity() {
		int sum=0;
		for(Integer qty:cartMap.values()) {
			
			if(qty!=null) {
				sum+=qty;
			}
		}
		return sum;
	}
	/**
	 * 計算該筆明細的總金額
	 * @param item
	 * @return
	 */
		public double getTotalAmount() {
			double sum=0;
			for(CartItem item:cartMap.keySet()) {
				sum+=this.getAmount(item);
			}
			return sum;
		}

	
	@Override
	public String toString() {
		return 	this.getClass().getName()
				
				+ "\n顧客=" +(member!=null?member.getName():"")
				+ "\n購物明細=" + cartMap 
				+ "\n幾項=" + size()
				+ "\n幾件=" + getTotalQunatity()
				+ "\n幾元=" + getTotalAmount()
				 ; 
				
	}
	
}
