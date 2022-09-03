package uuu.vgb.entity;

import com.mysql.cj.conf.ConnectionUrl.Type;

public class OrderItem {
	private int orderId;	//PKey
	private Product product;//PKey
	private ProductType type;//PKey
	private ProductSize size;//PKey
	private double price;
	private int quantity;
	
	public Integer getTypeId() {	
		return type!=null?type.getTypeid():null;		
	}
	
	public Integer getSizeId() {
		return size!=null?size.getSizeid():null;
	}
	public String getPhotoUrl() {
	 if(product!=null){
			return product.getPhotoUrl();
		}else return null;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return product!=null?product.getId():0;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public ProductSize getSize() {
		return size;
	}
	public void setSize(ProductSize size) {
		this.size = size;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderId;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OrderItem))
			return false;
		OrderItem other = (OrderItem) obj;
		if (orderId != other.orderId)
			return false;
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
	public String toString() {
		return getClass().getName()+"訂購明細 [\n訂單編號=" + orderId + ",\n購買產品=" + product.getId() 
				+ ",\n產品規格=" +  String.valueOf(type) 
				+ ",\n產品尺寸=" + String.valueOf(size) 
				+ ",\n成交價=" + price + ",\n成交量=" + quantity + "]";
	}
	
}
