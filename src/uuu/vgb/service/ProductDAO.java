package uuu.vgb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import uuu.vgb.entity.Outlet;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.ProductSize;
import uuu.vgb.entity.ProductType;
import uuu.vgb.exception.VGBException;

import java.awt.print.Printable;
import java.sql.*;

public class ProductDAO {

//	private final static String SELECT_ALL_PRODUCT = "SELECT product_ID, Category, stock, unitPrice, photoUrl, description, lunchDate, discount, productSign, productItemName FROM product"
//			+ " left join productsign on product.Sign_id=productsign.id"						
//			+ " left join productcategory on product.Category_id=productcategory.id";

	private final static String SELECT_ALL_PRODUCT = "SELECT id, productItemName, photoUrl, lunchDate, discount, type_name, Dimensionsco, Category, productSign, minunprice, maxunprice, totalstock FROM product_list order BY rand()";
	List<Product> getALLProduct() throws VGBException {

		List<Product> list = new ArrayList<Product>();
		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_PRODUCT);) {
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理RS
				while (rs.next()) {
					Product p;
					int discount = rs.getInt("discount");
					if (discount > 0) {
						p = new Outlet();
						((Outlet) p).setDiscount(discount);
					} else {
						p = new Product();
					}
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("productItemName"));
					p.setUnitPrice(rs.getDouble("minunprice"));
					p.setStock(rs.getInt("totalstock"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					p.setCategory(rs.getString("Category"));
					//p.setDescription(rs.getString("description"));
					p.setLunchDate(rs.getString("lunchDate"));
					
					
					
					p.setSign(rs.getString("productSign"));				
					list.add(p);

				}
			}

		} catch (SQLException e) {
			throw new VGBException("查詢全部產品失敗", e);
		}
		return list;
	}
	private final static String SELECT_INDEX_RANDOM_PRODUCT = "SELECT id, productItemName, photoUrl, lunchDate,"
			+ " discount, type_name, Dimensionsco, Category,"
			+ " productSign, minunprice, maxunprice,"
			+ " totalstock FROM product_list  WHERE totalstock > 0 order BY rand()"
			+ " limit 8";
	List<Product> selectIndexRandomProduct() throws VGBException {

		List<Product> list = new ArrayList<Product>();
		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_INDEX_RANDOM_PRODUCT);) {
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理RS
				while (rs.next()) {
					Product p;
					int discount = rs.getInt("discount");
					if (discount > 0) {
						p = new Outlet();
						((Outlet) p).setDiscount(discount);
					} else {
						p = new Product();
					}
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("productItemName"));
					p.setUnitPrice(rs.getDouble("minunprice"));
					p.setStock(rs.getInt("totalstock"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					p.setCategory(rs.getString("Category"));
					//p.setDescription(rs.getString("description"));
					p.setLunchDate(rs.getString("lunchDate"));
					
					
					
					p.setSign(rs.getString("productSign"));				
					list.add(p);

				}
			}

		} catch (SQLException e) {
			throw new VGBException("查詢全部產品失敗", e);
		}
		return list;
	}
	private final static String SELECTL_PRODUCT_BY_BRAND = "SELECT id, productItemName, photoUrl, lunchDate, discount, Category, productSign, minunprice, maxunprice, totalstock FROM product_list"
			+ " WHERE productSign like ?";
	List<Product> selectProductByBrand(String keyword) throws VGBException {

		List<Product> list = new ArrayList<Product>();
		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECTL_PRODUCT_BY_BRAND);) {
			pstmt.setString(1, '%' + keyword + '%');
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理RS
				while (rs.next()) {
					Product p;
					int discount = rs.getInt("discount");
					if (discount > 0) {
						p = new Outlet();
						((Outlet) p).setDiscount(discount);
					} else {
						p = new Product();
					}
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("productItemName"));
					p.setUnitPrice(rs.getDouble("minunprice"));
					p.setStock(rs.getInt("totalstock"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					p.setCategory(rs.getString("Category"));
					//p.setDescription(rs.getString("description"));
					p.setLunchDate(rs.getString("lunchDate"));
					
					
					
					p.setSign(rs.getString("productSign"));				
					list.add(p);

				}
			}

		} catch (SQLException e) {
			throw new VGBException("查詢全部產品失敗", e);
		}
		return list;
	}
	
	private final static String SELECTL_PRODUCT_BY_NAME_STORNG = "SELECT id, productItemName, photoUrl, lunchDate, discount, Category, productSign, minunprice, maxunprice, totalstock FROM product_list\r\n"
			+ "WHERE Category= ? AND productItemName like'?";
	List<Product> selectProductByNameStorng(String opt, String keyword1) throws VGBException {

		List<Product> list = new ArrayList<Product>();
		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECTL_PRODUCT_BY_NAME_STORNG);) {
			pstmt.setString(1, opt );
			pstmt.setString(2, '%' + keyword1 + '%');
		
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理RS
				while (rs.next()) {
					Product p;
					int discount = rs.getInt("discount");
					if (discount > 0) {
						p = new Outlet();
						((Outlet) p).setDiscount(discount);
					} else {
						p = new Product();
					}
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("productItemName"));
					p.setUnitPrice(rs.getDouble("minunprice"));
					p.setStock(rs.getInt("totalstock"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					p.setCategory(rs.getString("Category"));
					//p.setDescription(rs.getString("description"));
					p.setLunchDate(rs.getString("lunchDate"));
					
					
					
					p.setSign(rs.getString("productSign"));				
					list.add(p);

				}
			}

		} catch (SQLException e) {
			throw new VGBException("查詢全部產品失敗", e);
		}
		return list;
	}

	private final static String SELECT_PRODUCT_BY_NAME = "SELECT product_ID, Category, stock, unitPrice, photoUrl, description, lunchDate, discount, productSign, productItemName FROM product"
			+ " left join productsign on product.Sign_id=productsign.id"
			+ " left join productcategory on product.Category_id=vgb.productcategory.id"
			+ " WHERE productItemName LIKE ?";

	List<Product> selectProductByName(String keyword) throws VGBException {

		List<Product> list = new ArrayList<Product>();
		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);) {
			pstmt.setString(1, '%' + keyword + '%');
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理RS
				while (rs.next()) {
					Product p;
					int discount = rs.getInt("discount");
					if (discount > 0) {
						p = new Outlet();
						((Outlet) p).setDiscount(discount);
					} else {
						p = new Product();
					}
					p.setId(rs.getInt("product_ID"));
					p.setName(rs.getString("productItemName"));
					p.setUnitPrice(rs.getDouble("unitPrice"));
					p.setStock(rs.getInt("stock"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					p.setCategory(rs.getString("Category"));
					p.setDescription(rs.getString("description"));
					p.setLunchDate(rs.getString("lunchDate"));
					
					
					p.setSign(rs.getString("productSign"));
					
					list.add(p);

				}
			}

		} catch (SQLException e) {
			throw new VGBException("查詢關鍵字產品失敗" + keyword, e);
		}
		return list;
	}

	private final static String SELECT_PRODUCT_BY_DATE = "SELECT id, productItemName, photoUrl, lunchDate, discount, product_type, type_name, product_Size, Dimensionsco, Category, productSign, minunprice, maxunprice, totalstock FROM product_list\r\n"
			+ " WHERE lunchDate<=DATE_ADD(curtime(), INTERVAL ? DAY)"
			+ "	order by lunchDate DESC ,id DESC ";

	List<Product> selectProductByDate(String date) throws VGBException {

		List<Product> list = new ArrayList<Product>();
		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_DATE);) {
				pstmt.setString(1, date);
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理RS
				while (rs.next()) {
					Product p;
					int discount = rs.getInt("discount");
					if (discount > 0) {
						p = new Outlet();
						((Outlet) p).setDiscount(discount);
					} else {
						p = new Product();
					}
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("productItemName"));
					p.setUnitPrice(rs.getDouble("minunprice"));
					p.setStock(rs.getInt("totalstock"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					p.setCategory(rs.getString("Category"));
					//	p.setDescription(rs.getString("description"));
					p.setLunchDate(rs.getString("lunchDate"));
					p.setSign(rs.getString("productSign"));
					
					
					list.add(p);

				}
			}

		} catch (SQLException e) {
			throw new VGBException("查詢最新上架商品失敗");
		}
		return list;
	}

	private final static String SELECT_PRODUCT_BY_CATEGORY = " SELECT id, productItemName, photoUrl, lunchDate, discount, Category, productSign, minunprice, maxunprice, totalstock FROM product_list "
			+" WHERE Category= ? ";

	List<Product> selectProductByCategory(String Category) throws VGBException {

		List<Product> list = new ArrayList<Product>();
		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_CATEGORY);) {
			pstmt.setString(1, Category);
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理RS
				while (rs.next()) {
					
					Product p;
					int discount = rs.getInt("discount");
					if (discount > 0) {
						p = new Outlet();
						((Outlet) p).setDiscount(discount);
					} else {
						p = new Product();
					}
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("productItemName"));
					p.setUnitPrice(rs.getDouble("minunprice"));
					p.setStock(rs.getInt("totalstock"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					p.setCategory(rs.getString("Category"));
					//p.setDescription(rs.getString("description"));
					p.setLunchDate(rs.getString("lunchDate"));
					p.setSign(rs.getString("productSign"));
					
					list.add(p);

				}
			}

		} catch (SQLException e) {
			throw new VGBException("分類查詢商品失敗");
		}
		return list;
	}

//	private final static String SELECT_PRODUCT_BY_Id = "SELECT product_ID, Category, stock, unitPrice, photoUrl, description, lunchDate, discount, productSign, productItemName FROM product"
//			+ " left join productsign on product.Sign_id=productsign.id"			
//			+ " left join productcategory on product.Category_id=productcategory.id" + "  WHERE product_ID = ?";
	

//	private final static String SELECT_PRODUCT_BY_Id = "SELECT product.product_ID as id, productItemName, product.stock as stock, product.unitPrice, photoUrl, description, lunchDate, discount, Category_id,Sign_id,"
//			+ "	product_sort.product_type AS type_id, product_sort.stock AS type_stock, producttype.productType AS type_name, producttype.icon_url,"
//			+ " productsize.product_Size AS size_id ,Dimensionsco,productsize.stock AS size_stock, productsize.unitprice AS size_unitprice"
//			+ "	FROM product"			
//			+ "	LEFT JOIN product_sort ON product.product_ID=product_sort.product_id"
//			+ "	LEFT JOIN producttype ON product_sort.product_type=producttype.id"
//			+ "	LEFT JOIN productsize ON product.product_ID =productsize.product_id"
//			+ " LEFT JOIN productdimensions ON productsize.product_Size=productdimensions.id"
//			+ "	WHERE product.product_ID = ? ";
//
//	
//	Product selectProductById(String productId) throws VGBException {
//		Product p = null;
//
//		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
//				PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_Id);) {
//			pstmt.setString(1, productId);
//			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
//			) {
//				// 5.處理RS
//				while (rs.next()) {
//					if(p==null) {
//					int discount = rs.getInt("discount");
//					
//					if (discount > 0) {
//						p = new Outlet();
//						((Outlet) p).setDiscount(discount);
//					
//					} else {
//						p = new Product();
//					}
//					p = new Product();
//					p.setId(rs.getInt("id"));
//					p.setName(rs.getString("productItemName"));
//					p.setUnitPrice(rs.getDouble("unitPrice"));
//					p.setStock(rs.getInt("stock"));
//					p.setPhotoUrl(rs.getString("photoUrl"));
//					p.setCategory(rs.getString("Category_id"));
//					p.setDescription(rs.getString("description"));
//					p.setLunchDate(rs.getString("lunchDate"));
//					//Logger.getLogger("TestProductServiceGetProductById").log(Level.SEVERE, String.valueOf(productId+":"+p.toString()));
//				
//				}
//					int typeid=rs.getInt("type_id");
//					
//					if(typeid>0) {
//						
//						
//						ProductType type=new ProductType();						
//						type.setTypeid(typeid);		
//						type.setProductType(rs.getString("type_name"));
//						type.setStock(rs.getInt("type_stock"));		
//						type.setIconUrl(rs.getString("icon_url"));	
//						p.addType(type);			
//											
//					}	
//						int size_id=rs.getInt("size_id");
//					
//					if(size_id>0) {
//						
//						
//						ProductSize size=new ProductSize();						
//						size.setSizeid(size_id);	
//						size.setProductsize(rs.getString("Dimensionsco"));
//						size.setStock(rs.getInt("size_stock"));		
//						size.setUnitPrice(rs.getDouble("size_unitprice"));
//						p.addSize(size);			
//											
//					}	
//				
//				}
//		
//			}
//
//		} catch (SQLException e) {
//			throw new VGBException("分類查詢商品失敗");
//		}
//
//		return p;
//	}
	private final static String SELECT_PRODUCT_BY_Id = "SELECT id, productItemName, stock, unitPrice, photoUrl,"
			+ " description, lunchDate, discount, Category_id, type_id, type_stock, type_unitprice,"
			+ " type_name, icon_url, size_id, Dimensionsco, size_stock, size_unitprice  FROM product_detail"
			+ " WHERE product_detail.id = ? ";

	
	Product selectProductById(String productId) throws VGBException {
		Product p = null;

		try (Connection connection = MySQLConnection.getConnection(); // 1,2取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_PRODUCT_BY_Id);) {
			pstmt.setString(1, productId);
			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理RS
				while (rs.next()) {
					if(p==null) {
					int discount = rs.getInt("discount");
					System.out.println("折數:"+discount);
					if (discount > 0) {
						p = new Outlet();
						((Outlet) p).setDiscount(discount);
					} else {
						p = new Product();
					}
					
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("productItemName"));
					p.setUnitPrice(rs.getDouble("unitPrice"));
					p.setStock(rs.getInt("stock"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					p.setCategory(rs.getString("Category_id"));
					p.setDescription(rs.getString("description"));
					p.setLunchDate(rs.getString("lunchDate"));
					//Logger.getLogger("TestProductServiceGetProductById").log(Level.SEVERE, String.valueOf(productId+":"+p.toString()));
					System.err.println("產品:"+p);
				}
					Integer typeid=(Integer)(rs.getObject("type_id"));
					
					if(typeid!=null) {
						
						
						ProductType type=new ProductType();						
						type.setTypeid(typeid);		
						type.setProductType(rs.getString("type_name"));
						type.setStock(rs.getInt("type_stock"));		
						type.setIconUrl(rs.getString("icon_url"));	
						type.setUnitPrice(rs.getDouble("type_unitprice"));
						p.addType(type);			
											
					}	
					Integer sizeid=(Integer)(rs.getObject("size_id"));
					
					if(sizeid!=null) {
						
						
						ProductSize size=new ProductSize();						
						size.setSizeid(sizeid);	
						size.setProductsize(rs.getString("Dimensionsco"));
						size.setStock(rs.getInt("size_stock"));		
						size.setUnitPrice(rs.getDouble("size_unitprice"));
						System.err.println(size);
						p.addSize(size);			
											
					}	
				
				}
		
			}

		} catch (SQLException e) {
			throw new VGBException("查詢商品失敗");
		}

		return p;
	}

}
