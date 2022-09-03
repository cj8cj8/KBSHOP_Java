SELECT * FROM vgb.customers;

UPDATE customers
	SET password='test123', NAME='小明', gender='U', email='12afsaf16@uuu.com', phone='0920123056', birthday='1995-06-07' , 
		address='台北市' , subscribed=false
    WHERE id='A1namecustomers23456789';

UPDATE customers
	SET password=?, NAME=?, gender=?, email=?, phone=?, birthday=? , 
		address=? , phone=? , subscribed=?
    WHERE id=?;
UPDATE customers
	SET password=?
    
    WHERE id=?;