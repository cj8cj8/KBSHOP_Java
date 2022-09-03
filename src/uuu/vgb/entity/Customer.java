package uuu.vgb.entity;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

import org.eclipse.jdt.internal.compiler.ast.IntLiteralMinValue;

import uuu.vgb.exception.VGBDataInvalidException;
public class Customer {
	 private String id;//必要 ROC ID
	 private String password;//必要,6~20個字元
	 private String name;//必要,2~20個字元
	 private char gender;//必要,'M':男,'F':女
	 private String email;//必要
	 private LocalDate birthday;//必須年滿12歲
	 //public Date birthday; import java.time.Date;
	 private String address="";//非必要,not null,0~100個字元 
	 private String phone="";//非必要,not null,0~30個字元
	 private Boolean subscribed;
	 public Customer() {}
	 
	 public Customer(String id, String password,String name) {
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
	}
	
	 
	 public Customer(String id, String password, String name, 
				char gender, String email, String birthday) {
		//super();
		this(id,name,password);
		
		this.setGender(gender);
		this.setEmail(email);
		this.setBirthday(birthday);
	}

		//輸入姓名
	 public static int MIN_NAME_LENGTH=2;
	 public static int MAX_NAME_LENGTH=20;
		public void setName(String name) {
			if(name!=null&&(name=name.trim()).length()>=MIN_NAME_LENGTH&&name.length()<=MAX_NAME_LENGTH) {
				this.name = name;
			}else{
				//System.err.println("客戶姓名長度不符合");
				//throw new VGBDataInvalidException("客戶姓名長度不符合:"+name);
				String msg=String.format("客戶姓名必須%d~%d個字:%s",MIN_NAME_LENGTH,MAX_NAME_LENGTH,name);
				
				 throw new VGBDataInvalidException(msg); 
			}
		}
		//取得姓名
		public String getName() {
			return this.name;
		}
		public static final char MALE='M';
		public static final char FEMALE='F';
		public static final char UNKNOW='U';
		//輸入性別
		public void setGender(char gender) {	
			gender=Character.toUpperCase(gender);
			if(gender==MALE || gender==FEMALE || gender==UNKNOW) {
				this.gender = gender;
			}else {
				
				
				this.gender='U';
				System.err.println("客戶性別資料輸入錯誤: " + gender);
				
				throw new VGBDataInvalidException("客戶性別資料輸入錯誤:" + gender);

			}
					
		}
		
		//取得性別
		public char getGender() {
			return gender;
		}
		public void checkPhone(int phone) 
		{
		//	input.matches("\\d")
		}
		//輸入電話號碼
		public void setPhone(String phone) {
			if(phone!=null&&phone.matches("[0][9][0-9]{8}")){
				this.phone = phone;
				
			}else {
				throw new VGBDataInvalidException("電話號碼輸入錯誤:" + phone);

			}
			
		}
//		public void setPhone(String phone) {
//			if(phone!=null&&phone.length()==10&&phone.matches("[0][9][0-9]{8}")){
//				this.phone = phone;
//				
//			}
//			
//		}
		//取得電話號碼
		public String getPhone() {
			return this.phone;
		}
		//輸入Email
		public void setEmail(String email) {//^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$
		
			if(email!=null&&email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
				
				this.email = email;
			}else{
				//System.err.println("Email格式不正確"+email);	
				throw new VGBDataInvalidException("Email格式不正確" + email);

			}
		}
		
		//取得Email
		public String getEmail() {
			return email;
		}
		//是否訂閱
		public void setSubscribed(Boolean subscribed) {			
				this.subscribed =subscribed;			
		}
		//取得訂閱資訊
		public Boolean isSubscribed() {
			return subscribed;
		}		
		//輸入地址
		public void setAddress(String address) {
			if(address==null)
				address="";
			this.address=address.trim();
					
		}
		//取得地址
		public String getAddress() {
			return address;
		}
/**
 * 檢查身份證
 * @param id
 * @return
 */
		private static final String idFirstCharSequence="ABCDEFGHJKLMNPQRSTUVWXYZIO";
		private static final String idpattern="[A-Z][1289][0-9]{8}";
		public boolean checkROCId(String id) {//A123456789
		  id = id.toUpperCase();
			String idFirstCharSequence="ABCDEFGHJKLMNPQRSTUVWXYZIO";							
					
			// int n1=idFirstCharSequence.indexOf(c1)+10;
			// if(n1==9)return false;
			
			 if(id!=null&&id.matches(idpattern)) {//用regular expression檢查格式
				 final char c1=id.charAt(0);
				 int n1=0;
				 if (c1>='A' && c1<='H'){//10-17
				n1=c1-'A'+10;	 
				 }else if(c1>='J'&&c1<='N'){//23-29
					 n1=c1-'J'+18;
				 }else if(c1>='P'&&c1<='V'){//XYWZIO
					 n1=c1-'P'+23;
				 }else  {
					 switch(c1) {//XYZWIO>>30,31,32,33,34,35
					 case 'X':
						 n1=30;
					 break;
					 case 'Y':
						 n1=31;
					 break;
					 case 'Z':
						 n1=32;
					 break;
					 case 'W':
						 n1=33;
					 break;
					 case 'I':
						 n1=34;
					 break;
					 case 'O':
						 n1=35;
					 break;
				default:
					return false;
					 }					 
				 }
				 int sum = (n1/10) * 1 + (n1%10) * 9;				 
				    for (int i=8,j=1;i>0;i--,j++){		
				    	 int n= id.charAt(j)-'0';
				        sum =sum+n * i;
				   //    System.err.println("sum="+i+"*"+j);				        
				 }
//				 sum=sum+(id.charAt(2)-'0')*8;
//				 sum=sum+(id.charAt(2)-'0')*7;
//				 sum=sum+(id.charAt(2)-'0')*6;
//				 sum=sum+(id.charAt(2)-'0')*5;
//				 sum=sum+(id.charAt(2)-'0')*4;
//				 sum=sum+(id.charAt(2)-'0')*3;
//				 sum=sum+(id.charAt(2)-'0')*2;
//				 sum=sum+(id.charAt(2)-'0')*1;				
				 sum=sum+id.charAt(9)-'0'*1;			
				// System.err.println(sum);    
				 
		
				 
			return sum%10==0;
			
		}
			
			return false; 
	}
	 //輸入身份證
	 public void setId(String id) {
		if(checkROCId(id)) {
			
			this.id=id;
			
			
		}else {
			//System.err.println("身分證號不正確: " + id);
			throw new VGBDataInvalidException("身份證輸入錯誤"+id); 
			
			
		}
	}
	 //取得身份證
	 public String getId() {
		return this.id;
	}
	 public static int MIN_PASSWORD_LENGTH=6;
	 public static int MAX_PASSWORD_LENGTH=20;
	 
	 //輸入密碼
	 public void setPassword(String passWord){
		 if(passWord!=null&&passWord.length()>=MIN_PASSWORD_LENGTH&&
				 passWord.length()<=MAX_PASSWORD_LENGTH) {
			 password=passWord;
			 
		 }else {
		String msg=String.format("密碼必須%d~%d個字:%s",MIN_PASSWORD_LENGTH,MAX_PASSWORD_LENGTH,passWord);
			
			 throw new VGBDataInvalidException(msg); 
		 }
	 }
	 //取得密碼
	 public String getPassword() {
		 return this.password;
	 }
	 //取得生日
	 public LocalDate getBirthday(){
		 return this.birthday;
	 }
	 //輸入生日
	 public static int MIN_AGE=12;
	 public void setBirthday(LocalDate birthday) {//Encapsulate要的Setter
			//進行年齡運算
			//檢查參數birthday年齡是否符合規則符合才指派給birthday	
		 	if(birthday==null) {
		 		throw new VGBDataInvalidException("未輸入生日"); 
			}
				 Period period=Period.between(birthday, LocalDate.now());
					int age=period.getYears();
						
					if(birthday.isBefore(LocalDate.now())&&age>=MIN_AGE) {
						this.birthday=birthday;
					}else if(birthday.isBefore(LocalDate.now())&&age<=MIN_AGE){
						//System.err.printf("年齡須大於%d歲:%s",MIN_AGE,birthday);
						throw new VGBDataInvalidException("年齡須大於%d歲:%s"+MIN_AGE+birthday); 
					}else{
						//System.err.println("生日輸入錯誤:"+birthday);
						throw new VGBDataInvalidException("生日輸入錯誤:"+birthday); 
					}
			}
	
	 
	 
	 /**
	  * int
	  * 將year month day三個整數轉換成localdate物件
	  * 並傳送到setBirthday(LocalDate birthday)作為參數
	  * @param year
	  * @param month
	  * @param day
	  */
	 public void setBirthday(int year,int month,int day) {
		 //TODO:將year,month,day三個整數參數轉換成LocalDate物件並指派給birthday作為屬性
		 try {
		 LocalDate date=LocalDate.of(year, month, day);
		 this.setBirthday(date);
		 }catch(java.time.DateTimeException e) {
			 throw new VGBDataInvalidException("客戶生日日期資料不正確: "+e.getMessage());
		 }
	}
	/**
	 * String
	 * dateStr字串參數轉換成LocalDate物件
	 * 並傳送到setBirthday(LocalDate birthday)作為參數
	 * @param dateStr:生日日期字串，必須符合iso-8601(yyyy-MM-dd)
	 */
	 public void setBirthday(String dateStr) {
			if(dateStr==null) this.setBirthday((LocalDate)null);
			
			//將dateStr字串參數轉換成LocalDate物件
			try {
			LocalDate date = LocalDate.parse(dateStr);
			
			//呼叫this.setBirthday(date)來完成檢查與指派
			this.setBirthday(date);
			}catch(DateTimeException e) {
				throw new VGBDataInvalidException("客戶生日日期資料不正確:正確格式yyyy-MM-dd，您輸入的是:"+dateStr);
			}
	 }
	 /**
	  * 從客戶birthday屬性計算客戶年齡
	  * @return int型態的客戶年齡
	  */
	 public int getAge(){
		 return getAge(this.birthday);
	 }
	 
	 /**
	  * 從客戶生日計算客戶年齡
	  * @return int型態客戶年齡
	  */
	

		private int getAge(LocalDate birthday){
			 if(this.birthday!=null){
				 Period period=Period.between(this.birthday, LocalDate.now());
				int age=period.getYears();
//				if(period.getMonths()==6&&period.getDays()>0||period.getMonths()>6){
//					age=age+1;
//				}
				return age;
				 
			 }else{
				 //TODO:第13章，改成throw XXXException
				 throw new VGBDataInvalidException("未輸入生日，無法計算年齡"); 
			 }
			 
		 }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getName()
				+"\n身份證:" + id 
				+"\n密碼:" + password 
				+"\n姓名:" + name 
				+"\n性別:" + gender 
				+"\n電子信箱:"+ email 
				+"\n生日:" + birthday 
				+"\n年齡:"+(birthday!=null?getAge()+"歲":"無法計算年齡")
				+"\n地址:" + address 
				+"\n手機號碼:" + phone 
				+ "\n訂閱電子報:"+ subscribed;
	}
		
 
}
