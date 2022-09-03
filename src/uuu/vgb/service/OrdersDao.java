package uuu.vgb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import uuu.vgb.entity.Order;
import uuu.vgb.entity.OrderItem;
import uuu.vgb.entity.OrderStatusLog;
import uuu.vgb.entity.PaymentType;
import uuu.vgb.entity.Product;
import uuu.vgb.entity.ProductSize;
import uuu.vgb.entity.ProductType;
import uuu.vgb.entity.ShippingType;
import uuu.vgb.exception.StockShortageException;
import uuu.vgb.exception.VGBException;

class OrdersDao {
	
	private static final String UPDATE_PRODUCTS_TYPE_STOCK="UPDATE product_sort SET stock=stock-? "			
			+ " WHERE stock>=? AND product_id=? AND product_type =?";
	private static final String UPDATE_PRODUCTS_SIZE_STOCK="UPDATE productsize SET stock=stock-?"			
			+ " WHERE stock>=? AND product_id=? AND product_Size =?";
	
	private static final String INSERT_ORDERS="INSERT INTO orders"
			+ "(id, customer_id, created_date, created_time,"
			+ " payment_type, payment_fee, shipping_type, shipping_fee,"
			+ " recipient_name, recipient_email, recipient_phone, recipient_addres , status)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)";
	private static final String INSERT_ORDER_ITEMS="INSERT INTO order_items"
			+ " (order_id, product_id, type_name, size_name, price, quantity)"
			+ " VALUES(?,?,?,?,?,?)";
	public void insert(Order order) throws VGBException{
		
		
				try (
						Connection connection = MySQLConnection.getConnection();//1,2 取得連線
						
						PreparedStatement pstmt01 = connection.prepareStatement(UPDATE_PRODUCTS_TYPE_STOCK); 
						PreparedStatement pstmt02 = connection.prepareStatement(UPDATE_PRODUCTS_SIZE_STOCK); 
						
						
						PreparedStatement pstmt1 = connection.prepareStatement(INSERT_ORDERS, Statement.RETURN_GENERATED_KEYS); //3.準備指令1
						PreparedStatement pstmt2 = connection.prepareStatement(INSERT_ORDER_ITEMS); //3.準備指令2

					){
					connection.setAutoCommit(false);
					try {
						
						for(OrderItem item:order.getOrderItemSet()) {
							PreparedStatement pstmt;
							System.out.println(item);
						 if(item.getTypeId()!=null) {						
							 pstmt=pstmt01;
							 pstmt01.setInt(4, item.getTypeId().intValue());
								
								//System.out.println(item.getProductId()+",typeid"+item.getTypeId());								
						}else {
								pstmt=pstmt02;								
								pstmt02.setInt(4, item.getSizeId().intValue());
								//System.out.println(item.getProductId()+",sizeid"+item.getSizeId());
								
							}
							pstmt.setInt(1, item.getQuantity());
							pstmt.setInt(2, item.getQuantity());
							pstmt.setInt(3, item.getProductId());
							//4.執行指令
							int rows = pstmt.executeUpdate();
							if(rows==0) {
								throw new StockShortageException(item);
							}
							
						}			
					//3.1傳入 pstmts1的?值
					pstmt1.setInt(1, order.getId());
					pstmt1.setString(2,order.getMember().getId());
					pstmt1.setString(3,String.valueOf(order.getCreatedDate()));
					pstmt1.setString(4,String.valueOf(order.getCreatedTime()));
					pstmt1.setString(5,order.getPaymentType().name());
					pstmt1.setDouble(6,order.getPaymentType().getFee());
					pstmt1.setString(7,order.getShippingType().name());
					pstmt1.setDouble(8,order.getShippingType().getFee());
					pstmt1.setString(9,order.getRecipientName());
					pstmt1.setString(10,order.getRecipientEmail());
					pstmt1.setString(11,order.getRecipientPhone());
					pstmt1.setString(12,order.getShippingAddres());
					//4.執行pstmt1
					
					pstmt1.executeUpdate();
						
					//5.讀取rs
					try(
							ResultSet rs = pstmt1.getGeneratedKeys();
						){
							while(rs.next()) {
								int orderId = rs.getInt(1);				
								order.setId(orderId);
							}
						}
				
					
					
					for(OrderItem orderItem:order.getOrderItemSet()) {
						pstmt2.setInt(1, order.getId());
						pstmt2.setInt(2, orderItem.getProduct().getId());
					
						pstmt2.setInt(3, orderItem.getTypeId()==null?-1:orderItem.getTypeId());
						pstmt2.setInt(4, orderItem.getSizeId()==null?-1:orderItem.getSizeId());
						
						pstmt2.setDouble(5, orderItem.getPrice());
						pstmt2.setInt(6, orderItem.getQuantity());
						pstmt2.executeUpdate(); //4.執行pstmt2
					}
					connection.commit();
				}catch(Exception e) {
					connection.rollback();
					throw e;
					
				}finally {
					connection.setAutoCommit(true);
				}
					} catch (SQLException e) {
					throw new VGBException("新增訂單失敗",e);
				
				}
		
			
				
	}
	private static final String SELECT_ORDERS_BY_CUSTOMER_ID = "SELECT id, customer_id, created_date, created_time, status,"
			+ "	payment_type, payment_fee, shipping_type, shipping_fee,"
			+ "	CAST(SUM(price*quantity) AS DECIMAL(9,0))+payment_fee+shipping_fee as total_amount FROM orders"
			+ "	JOIN order_items ON orders.id=order_items.order_id WHERE customer_id=? GROUP BY orders.id"
			+ "	ORDER BY created_date DESC, created_time DESC";

	List<Order> selectOrdersHistory(String customerId) throws VGBException {
		List<Order> list = new ArrayList<Order>();
		try (Connection connection = MySQLConnection.getConnection(); // 1,2 取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ORDERS_BY_CUSTOMER_ID); // 3.準備指令
		) {
			// 3.1傳入?的值
			pstmt.setString(1, customerId);

			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理rs
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getInt("id"));
					order.setCreatedDate(LocalDate.parse(rs.getString("created_date")));
					order.setCreatedTime(LocalTime.parse(rs.getString("created_time")));
					order.setStatus(rs.getInt("status"));

					order.setPaymentType(PaymentType.valueOf(rs.getString("payment_type")));
					order.setPaymentFee(rs.getDouble("payment_fee"));
					order.setShippingType(ShippingType.valueOf(rs.getString("shipping_type")));
					order.setShippingFee(rs.getDouble("shipping_fee"));
					order.setTotalAmount(rs.getDouble("total_amount"));
					list.add(order);
				}
			}
		} catch (SQLException e) {
			throw new VGBException("查詢歷史訂單失敗", e);
		}

		return list;
	}
	
	private static final String SELECT_ORDER_BY_ID = "SELECT orders.id, customer_id, created_date, created_time, status,"
			+ "	payment_type, payment_fee, payment_note, shipping_type, shipping_fee,shipping_note,"
			+ "	recipient_name, recipient_email, recipient_phone, recipient_addres,"
			+ "	order_items.product_id, product.productItemName as product_name,"
			+ "	order_items.type_name, order_items.size_name,  price, quantity,"
			+ "	product.photoUrl,Dimensionsco,productType"
			
			+ "	FROM orders JOIN order_items ON orders.id=order_items.order_id"
			+ "	JOIN product ON order_items.product_id=product. product_ID\r\n"
			+ "	LEFT JOIN product_sort ON order_items.product_id=product_sort.product_id"
			+ " AND (order_items.type_name=product_sort.product_type OR (order_items.type_name>0 AND product_sort.product_type IS NULL))"
			+ " LEFT JOIN productsize ON order_items.product_id=productsize.product_id"
			+ " AND (order_items.size_name=productsize.product_Size OR (order_items.size_name>0 AND productsize.product_Size IS NULL))"
			+ " LEFT JOIN producttype ON order_items.type_name=producttype.id"
			+ "	LEFT JOIN productdimensions ON order_items.size_name=productdimensions.id"
			+ "	WHERE customer_id=? AND orders.id=?";

	Order selectOrderById(String customerId, String orderId) throws VGBException {
		Order order = null;
		try (Connection connection = MySQLConnection.getConnection(); // 1,2 取得連線
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ORDER_BY_ID); // 3.準備指令
		) {
			// 3.1傳入?的值
			pstmt.setString(1, customerId);
			pstmt.setString(2, orderId);

			try (ResultSet rs = pstmt.executeQuery();// 4.執行指令
			) {
				// 5.處理rs
				while (rs.next()) {
					if (order == null) {
						order = new Order();
						order.setId(rs.getInt("id"));
						order.setCreatedDate(LocalDate.parse(rs.getString("created_date")));
						order.setCreatedTime(LocalTime.parse(rs.getString("created_time")));
						order.setStatus(rs.getInt("status"));

						order.setPaymentType(PaymentType.valueOf(rs.getString("payment_type")));
						order.setPaymentFee(rs.getDouble("payment_fee"));
						order.setPaymentNote(rs.getString("payment_note"));
						order.setShippingType(ShippingType.valueOf(rs.getString("shipping_type")));
						order.setShippingFee(rs.getDouble("shipping_fee"));
						order.setShippingNote(rs.getString("shipping_note"));

						order.setRecipientName(rs.getString("recipient_name"));
						order.setRecipientEmail(rs.getString("recipient_email"));
						order.setRecipientPhone(rs.getString("recipient_phone"));
						order.setShippingAddres(rs.getString("recipient_addres"));
					
					}

					OrderItem item = new OrderItem();
					item.setOrderId(order.getId());
					Product p = new Product();
					p.setId(rs.getInt("product_id"));
					p.setName(rs.getString("product_name"));
					p.setPhotoUrl(rs.getString("photoUrl"));
					item.setProduct(p);
					
					
					ProductType productType=new ProductType();
					productType.setTypeid(rs.getInt("type_name"));					
					productType.setProductType(rs.getString("productType"));
					item.setType(productType);
					
					
					
					ProductSize productSize=new ProductSize();
					productSize.setSizeid(rs.getInt("size_name"));
					productSize.setProductsize(rs.getString("Dimensionsco"));	
					item.setSize(productSize);
					
					
					
					item.setPrice(rs.getDouble("price"));
					item.setQuantity(rs.getInt("quantity"));
					order.add(item);
				}
			}
		} catch (SQLException e) {
			throw new VGBException("查詢訂單明細失敗", e);
		}
		return order;
	}
	   

    private static final String UPDATE_STATUS_TO_TRANSFERED = "UPDATE orders SET status=1" //狀態設定為已轉帳

            + ", payment_note=? WHERE status=0 AND payment_type='" + PaymentType.ATM.name()

            + "' AND id=? AND customer_id=?";         

    void updateStatusToTransfered(int orderId, String memberId, String paymentNote) throws VGBException {

        try (Connection connection = MySQLConnection.getConnection(); //2. 建立連線

                PreparedStatement pstmt = connection.prepareStatement(UPDATE_STATUS_TO_TRANSFERED) //3. 準備指令

                ) {

            //3.1 傳入?的值

            pstmt.setString(1, paymentNote);
            pstmt.setInt(2, orderId);
            pstmt.setString(3, memberId); 
            //4. 執行指令
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new VGBException("[通知轉帳]失敗!", ex);
        }

    }
	
	
	
	private static final String SELECT_ORDER_STATUS_LOG = "SELECT order_id, update_time, old_status, new_status "

       + " FROM vgb.order_logs WHERE order_id=?";

  List<OrderStatusLog> selectOrderStatusLog(String orderId)throws VGBException{
       List<OrderStatusLog> list = new ArrayList<>();
       try(Connection connection = MySQLConnection.getConnection();
               PreparedStatement pstmt = connection.prepareStatement(SELECT_ORDER_STATUS_LOG);

                ){
           //3.1 傳入?的值

           pstmt.setString(1, orderId);     
           //4. 執行指令
           try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()) {
                    OrderStatusLog log = new OrderStatusLog(); //記得要import OrderStatusLog
                    log.setId(rs.getInt("order_id"));
                    log.setOldStatus(rs.getInt("old_status"));
                    log.setStatus(rs.getInt("new_status"));
                    log.setLogTime(rs.getString("update_time"));
                    list.add(log);
                }
           }
           return list;
       }catch(SQLException ex) {
           throw new VGBException("查詢訂單狀態Log失敗", ex);
       }  
 }
  private static final String UPDATE_STATUS_TO_PAID = "UPDATE orders SET status=2" //狀態設定為已付款
          + ", payment_note=? WHERE status=0 AND payment_type='" + PaymentType.CARD.name() 
          + "' AND id=? AND customer_id=?"; 
  void updateOrderStatusToPAID(int orderId,String memberId, String paymentNote) throws VGBException {
      try (Connection connection = MySQLConnection.getConnection(); //2. 建立連線
           PreparedStatement pstmt = connection.prepareStatement(UPDATE_STATUS_TO_PAID) //3. 準備指令
      ) {
          //3.1 傳入?的值
          pstmt.setString(1, paymentNote);
          pstmt.setInt(2, orderId);
          pstmt.setString(3, memberId); 

          //4. 執行指令
          pstmt.executeUpdate();
      } catch (SQLException ex) {
          System.out.println("修改信用卡付款入帳狀態失敗-" + ex);
          throw new VGBException("修改信用卡付款入帳狀態失敗!", ex);
      }
  }

}
