SELECT id, password, name, gender, email, birthday, address, phone, subscribed, discount FROM vgb.customers;


/* 模擬帳號密碼登入 */
SELECT id, password, name, gender, email, birthday, address, phone, subscribed, discount FROM vgb.customers
		WHERE id='A123456789' AND password='test123';

/* 模擬(帳號或信箱)密碼登入 */
SELECT id, password, name, gender, email, birthday, address, phone, subscribed, discount FROM vgb.customers
		WHERE (id='A123456789' or email='123456@uuu.com.tw') AND password='test123';
/* 模擬帳號或信箱查詢   0~1個客戶  (忘記密碼) */
SELECT id, password, name, gender, email, birthday, address, phone, subscribed, discount FROM vgb.customers
		WHERE id='A123456789' or email='123456@uuu.com.tw';