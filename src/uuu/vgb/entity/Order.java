package uuu.vgb.entity;

import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Order {
	public static  final SimpleDateFormat DATE_FORMAT_ALL= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private int id;
	private Customer member;
	private LocalDate createdDate;
	private LocalTime createdTime;
	
	private int status;//0-新訂單,1-已付款,2-已入帳,3-已出貨,4-已到貨,5-已簽收,6-已完成
	private PaymentType paymentType;
	private double paymentFee;//
	private String paymentNote;//非必要
	
	private ShippingType shippingType;
	private double shippingFee;
	private String shippingNote;//非必要

	private String recipientName;
	private String recipientEmail;
	private String recipientPhone;
	private String shippingAddres;
	
	private double totalAmount;
	private Set<OrderItem> orderItemSet=new HashSet<>();
	

	
	
	
	//orderItem's accessor()s
	public Set<OrderItem> getOrderItemSet() {
		return new HashSet<>(orderItemSet) ;
	}
	//from delegate method	
	public int size() {
		return orderItemSet.size();
	}
	public boolean isEmpty() {
		return orderItemSet.isEmpty();
	}
	public int getTotalQuantity() {
		int sum=0;
		for(OrderItem item:orderItemSet) {
			sum+=item.getQuantity();
		}
		return sum;
	}
	
	
	public void add(OrderItem orderItem) {//for OrdersDAO讀取資料庫的訂單明細
		if(orderItem==null) {
			throw new IllegalArgumentException("查詢訂單明細時購物車不得為空的");
		  }
		orderItemSet.add(orderItem);
	}
	
	public void add(ShoppingCart cart) {//for CheckoutServlet讀取session與requset的FormData
		if(cart==null||cart.isEmpty()) {
			throw new IllegalArgumentException("結帳時購物車不得為空的");
		}
		
		if(cart.getMember()!=null)	this.setMember(cart.getMember());
		for(CartItem cartItem:cart.getCartItemSet()) {
			OrderItem orderItem =new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setType(cartItem.getType());
			orderItem.setSize(cartItem.getSize());
			orderItem.setPrice(cartItem.getUnitPrice());
			orderItem.setQuantity(cart.getQuantity(cartItem));
			orderItemSet.add(orderItem);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getMember() {
		return member;
	}
	public void setMember(Customer member) {
		this.member = member;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public double getPaymentFee() {
		return paymentFee;
	}
	public void setPaymentFee(double paymentFee) {
		this.paymentFee = paymentFee;
	}
	public String getPaymentNote() {
		return paymentNote;
	}
	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}
	public ShippingType getShippingType() {
		return shippingType;
	}
	public void setShippingType(ShippingType shippingType) {
		this.shippingType = shippingType;
	}
	
	public double getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}
	public String getShippingNote() {
		return shippingNote;
	}
	public void setShippingNote(String shippingNote) {
		this.shippingNote = shippingNote;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	public String getRecipientPhone() {
		return recipientPhone;
	}
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
	public String getShippingAddres() {
		return shippingAddres;
	}
	public void setShippingAddres(String recipientAddres) {
		this.shippingAddres = recipientAddres;
	}
	public double getTotalAmount() {
		if(orderItemSet!=null && orderItemSet.size()>0) {
			double sum = 0;
			
			for(OrderItem item:orderItemSet) {
				sum += item.getPrice()* item.getQuantity();
			}
			return sum;
		}
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTotalAmountWithFee() {
		   double sum = getTotalAmount();

	       sum += paymentFee+shippingFee;

	       return Math.round(sum);
	}
	public enum Status {
		NEW("新訂單"), TRANSFERMED("已轉帳"), PAID("已付款"), SHIPPED("已出貨"), ARRIVED("已送達"), CHECKED("已簽收"), COMPLETED("已完成");

		private final String description;

		private Status(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

	}	
	public String getStatusDescription() {		
		return getStatusDescription(this.status);
	}

	public String getStatusDescription(int status) {
		if (status >= 0 && status < Status.values().length) {
			return Status.values()[status].description;
		} else {
			return String.valueOf(status);
		}
	}
	@Override
	public String toString() {
		String orderItemSetStr = "";
		for(OrderItem item:orderItemSet) {
			orderItemSetStr+= item.getTypeId();
		}
		return "訂單 [訂單編號=" + id + "\n訂購人=" + member + "\n購買日期=" + createdDate + "\n購買時間="
				+ createdTime + "\n出貨狀況=" + status + "\n付款方式=" + paymentType + "\n手續費=" + paymentFee
				+ "\n paymentNote=" + paymentNote + "\n運送方式 =" + shippingType + ",\n運費=" + shippingFee
				+ "\n shippingNote=" + shippingNote + "\n收貨人=" + recipientName + "\n收貨人信箱="
				+ recipientEmail + "\n收貨人電話=" + recipientPhone + "\n收貨人地址=" + shippingAddres
				+  ", orderItemSet=\n" 
					+ orderItemSetStr 
				+ "\n共=" + size()+"項"
				+ getTotalAmount() + "件"+"\n 總金額=" + getTotalAmount() +"\n含運費總金額=" + getTotalAmountWithFee() +"]";
	}

	
}
