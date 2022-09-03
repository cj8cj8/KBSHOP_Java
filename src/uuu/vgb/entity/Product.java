package uuu.vgb.entity;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class Product extends Object{
	private int id;//必要,PKEY,Auto-increment
	private String name;//1~50個字元
	private double unitPrice;//必要	
	private int stock;//必要
	private String photoUrl;//非必要
	private String description="";//非必要
	private LocalDate lunchDate;//必要
	private String category;
	private String sign;



	private Map<Integer,ProductType> TypeMap=null;
	private Map<Integer,ProductSize> SizeMap=null;
	
	
	public void addType(ProductType type) {
		if(type==null||type.getTypeid()<0)			
			throw new IllegalArgumentException("選擇軸承時，物件不得為null");
		if(TypeMap==null) {
			TypeMap=new TreeMap<>();
		}
		TypeMap.put(type.getTypeid(),type);
	}
	
	public Map<Integer,ProductType> getTypeMap(){
		if(istype()) {
		//做法1:回傳不得變更的集合
		//return Collections.unmodifiableMap(TypeMap);
		//做法2:回傳該集合的複本
		return istype()?new TreeMap<>(TypeMap):null;
	}
		return null;
	}

	public void addSize(ProductSize size) {
		if(size==null||size.getSizeid()<0)			
			throw new IllegalArgumentException("選擇Size時，物件不得為null");
		if(SizeMap==null) {
			SizeMap=new TreeMap<>();
		}
		SizeMap.put(size.getSizeid(),size);
	}
	
	public Map<Integer,ProductSize> getSizeMap(){
		if(isSize()) {
		//做法1:回傳不得變更的集合
		//return Collections.unmodifiableMap(SizeMap);
		//做法2:回傳該集合的複本
		return isSize()?new TreeMap<>(SizeMap):null;
	}
		return null;
	}
	
	
	
	
	
	
public boolean istype(){
	return TypeMap!=null&& TypeMap.size()>0;
}

public ProductType findType(int productType) {
	ProductType type= TypeMap.get(productType);
	return type;
}
public boolean isSize(){
	return SizeMap!=null&& SizeMap.size()>0;
}

public ProductSize findSize(int productsize) {
	ProductSize size= SizeMap.get(productsize);
	return size;
}





	public String getSign() {
		return sign;
	}



	public void setSign(String sign) {
		this.sign = sign;
	}




	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public Product() {}
	
	
	
	public Product(int id, String name, double unitPrice) {
		//super();
		this.setId(id);
		this.setName(name);
		this.setUnitPrice(unitPrice);
	}

	public Product(int id, String name, double unitPrice,int stock) {
		//super();
		this.setId(id);
		this.setName(name);
		this.setUnitPrice(unitPrice);
		this.stock = stock;
		//this.lunchDate = lunchDate;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		
			this.id = id;
		
		
	}
	
	
	



	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name!=null&&(name=name.trim()).length()>=1&&name.length()<=100) {
			this.name = name;
		}else{
			System.err.println("產品名稱長度不符合");
		}
		
	}
	
	public double getListPrice() {//取得定價(也是售價)
		
		if(isSize()) {
			java.util.Collection<ProductSize> sizes=SizeMap.values();
			Double sum=null;
			for(ProductSize size:sizes) {
				sum+=size.getUnitPrice();
			}
		}
		if(istype()) {
			java.util.Collection<ProductType> types=TypeMap.values();
			Double sum=null;
			for(ProductType type:types) {
				sum+=type.getUnitPrice();
			}
		}
		return unitPrice;
	}
	public double getUnitPrice() {//取得售價(也是定價)
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {//設定定價(也是售價)
		
		this.unitPrice = unitPrice;
	}
	
	
	public int getStock() {
		if(istype()) {
			java.util.Collection<ProductType> types=TypeMap.values();
			int sum=0;
			for(ProductType type:types) {
				sum+=type.getStock();
			}
		}
		if(isSize()) {
			java.util.Collection<ProductSize> sizes=SizeMap.values();
			int sum=0;
			for(ProductSize size:sizes) {
				sum+=size.getStock();
			}
		}
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public LocalDate getLunchDate() {
		return lunchDate;
	}
	public void setLunchDate(LocalDate lunchDate) {
		if(lunchDate!=null) {
			this.lunchDate = lunchDate;
		}else {
			System.err.println("lunchDate:請輸入正確日期");
		}
	}
	
	public void setLunchDate(String lunchDate) {
		if(lunchDate!=null) {			
			LocalDate date=LocalDate.parse(lunchDate);
			this.setLunchDate(date);
			
		}else{
			
			this.setLunchDate((LocalDate)null);
			return;
		}
	}



	@Override
	public String toString() {
		return 	this.getClass().getName()
				+ "\n產品編號=" + id 
				+ "\n產品名稱=" + name 
				+ "\n產品定價=" + unitPrice 
				+ "\n產品庫存=" + getStock() 
				+ "\n產品相片="+ photoUrl 
				+ "\n產品簡介="+ description 
				+ "\n上架日期=" + lunchDate 
				+ "\n產品類別=" + category
				+ "\n產品廠牌=" + sign 
				+"\n有下列軸承="+TypeMap
				+"\n有下列尺寸="+SizeMap;
		
		
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}



	


//	@Override
//	public String toString() {		
//		return "id:"+this.id+"\n"
//				+"產品名稱:"+this.name+"\n"
//				+"庫存:"+this.stock+"\n"
//				+"定價:"+this.unitPrice+"\n"
//				+"產品介紹:"+this.description+"\n"
//				+"圖片位置:"+this.photoUrl+"\n"
//				+"上架日期:"+this.lunchDate+"\n";		
//	}
	
	
	

}
