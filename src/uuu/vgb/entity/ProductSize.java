package uuu.vgb.entity;

public class ProductSize {
	private int sizeid;
	private String productsize;//必要
	private int stock;//必要
	private Double unitPrice;
	private Double listPrice;
	
	public Double getListPrice() {
		return listPrice;
	}
	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}
	public int getSizeid() {
		return sizeid;
	}
	public void setSizeid(int sizeid) {
		this.sizeid = sizeid;
	}
	public String getProductsize() {
		return productsize;
	}
	public void setProductsize(String productsize) {
		this.productsize = productsize;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Override
	public String toString() {
		return 	this.getClass().getName()
	
		+ "\n尺寸編號=" + sizeid
		+ "\n尺寸=" + productsize 
		+ "\n產品庫存=" + stock 
		+ "\n產品價格=" + unitPrice
		+ "\n產品定價=" + listPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sizeid;
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
		ProductSize other = (ProductSize) obj;
		if (sizeid != other.sizeid)
			return false;
		return true;
	}
	
	
}
