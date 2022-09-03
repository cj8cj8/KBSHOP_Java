package uuu.vgb.entity;

import java.util.Objects;

public class ProductType {
		private int typeid;//PKey
		private String productType;
		private int stock;//必要
		private String iconUrl;//非必要
		private Double unitPrice;
		private Double listPrice;
		
		public Double getListPrice() {
			return listPrice;
		}
		public void setListPrice(Double listPrice) {
			this.listPrice = listPrice;
		}
		public Double getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(Double unitPrice) {
			this.unitPrice = unitPrice;
		}
		public int getTypeid() {
			return typeid;
		}
		public void setTypeid(int typeid) {
			this.typeid = typeid;
		}
		public String getProductType() {
			return productType;
		}
		public void setProductType(String productType) {
			this.productType = productType;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public String getIconUrl() {
			return iconUrl;
		}
		public void setIconUrl(String iconUrl) {
			this.iconUrl = iconUrl;
		}
		@Override
		public String toString() {
			return 	this.getClass().getName()
		
			+ "\n軸承編號=" + typeid
			+ "\n軸承種類=" + productType 
			+ "\n產品庫存=" + stock 
			+ "\n軸承網址=" + iconUrl
			+ "\n價格=" + unitPrice
			+ "\n定價=" + listPrice;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + typeid;
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
			ProductType other = (ProductType) obj;
			if (typeid != other.typeid)
				return false;
			return true;
		}
		
	
		

		
}
