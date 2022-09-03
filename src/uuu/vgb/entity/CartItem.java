package uuu.vgb.entity;

import java.time.LocalDateTime;

public class CartItem implements Comparable<CartItem>{ 

	private Product product; //Pkey
	private ProductSize size;//Pkey
	private ProductType type;//Pkey
	private LocalDateTime createdTime = LocalDateTime.now();
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductSize getSize() {
		return size;
	}
	public void setSize(ProductSize size) {
		this.size = size;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	/*以下getter是根據cart.jsp中cartItem的欄位提供的*/
	public int getProductId() {
		if(product!=null) {
			return product.getId();
		}else {
			return -1;
		}
		
	}
	public String getProductName() {
		if(product!=null) {
			return product.getName();
		}else {
			return "";
		}
		
	}
	public String getPhotoUrl() {
		if(product!=null) {
			return product.getPhotoUrl();
		}else {
			return null;
		}
		
	}
	public String getTypeName() {
		if(type!=null) {
			return type.getProductType();
		}else {
			return "";
		}
		
	}
	public String getSizeName() {
		if(size!=null) {
			return size.getProductsize();
		}else {
			return "";
		}
		
	}
	public double getListPrice() {
		
		if(product instanceof Outlet) {
			return ((Outlet)product).getListPrice();
		}else {
			return product.getUnitPrice();
		}
		
	}
	public String getDiscountSring() {
		if(product instanceof Outlet) {
			return ((Outlet)product).getDiscountString();
		}else {
			return "";
		}
	}
	public int getDiscount() {
		if(product instanceof Outlet) {
			return ((Outlet)product).getDiscount();
		}else {
			return 0;
		}
	}
	public double getUnitPrice() {
		
		
		
		if(product!=null) {
			return product.getUnitPrice();
		}else {
			return 0;
		}
	}
	

	
	@Override
	public String toString() {
		return 	this.getClass().getName()
				
				+ "\n產品ID=" + getProductId()
				+ "\n名稱=" + getProductName() 
				+ "\n照片=" + getPhotoUrl()
				+ "\n軸承=" + getTypeName()
				+ "\n尺寸=" + getSizeName() 
				+ "\n定價=" + getListPrice()
				+ "\n折數=" + getDiscountSring()
				+ "\n售價=" + getUnitPrice() ; 
				
	}
	public int getStock() {
		if(size!=null) 
			return size.getStock();
		
		if(type!=null) 
			return type.getStock();
		
		if(product!=null) {
			return product.getStock();
		}else return 0;	
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public int compareTo(CartItem anotherItem) {
		int result = this.createdTime.compareTo(anotherItem.createdTime);
		if(result==0) {
			result = this.hashCode() - anotherItem.hashCode();
		}
		return result;
	}	
}
