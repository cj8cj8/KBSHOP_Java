package uuu.vgb.entity;



public class VIP extends Customer {
	private int discount=5;

	public VIP() {
	//super();
	
	}
	public VIP(String id, String password, String name,char gender, String email, String birthday) {
		super(id, password, name, gender, email, birthday);
	}
	public VIP(String id, String password, String name) {
		super(id, password, name);
	
	}
	public int getDiscount() {
		//
		return discount;
	}
	/**
	 * 設定VIP優惠折數,預設5%off
	 * @param discount 1-90%off
	 */
	public void setDiscount(int discount) {
		if(discount>0&&discount<=90) {
			this.discount = discount;
		}else{
			System.err.println("VIP discount必須1-90之間");
		}
		
	}
	
	public String getDiscountString() {
		int discount = 100 - this.discount;
        if(discount % 10 == 0){
            discount = discount / 10;
        }
        return discount + "折";
		
	}
	@Override
	public String toString() {
		return super.toString() 
				+"\n折扣:-" + discount 
				+"%off"
				+"\n"+getDiscountString();
	}
	
	
	
		
}
	


