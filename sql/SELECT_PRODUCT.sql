/*查詢產品完整明細 view*/
SELECT id, productItemName, photoUrl, lunchDate, discount, product_type, type_name, product_Size,
 Dimensionsco, Category, productSign, minunprice, maxunprice, totalstock FROM product_list;
 
 
/*首頁產品隨機查詢8個 view*/
SELECT id, productItemName, photoUrl, lunchDate, discount, product_type, type_name, product_Size,
 Dimensionsco, Category, productSign, minunprice, maxunprice, totalstock FROM product_list
  order  BY rand()
  limit 8;
             
/*日期查詢產品完整明細 view*/
SELECT id, productItemName, photoUrl, lunchDate, discount, product_type, type_name, product_Size, Dimensionsco, Category, productSign, minunprice, maxunprice, totalstock FROM product_list
 WHERE lunchDate<=DATE_ADD(curtime(), INTERVAL 7 DAY)
			 order by lunchDate DESC ,id DESC ;
/*ID查詢產品完整明細 view*/
SELECT id, productItemName, stock, unitPrice, photoUrl,
			 description, lunchDate, discount, Category_id, type_id, type_stock, type_unitprice,
			type_name, icon_url, size_id, Dimensionsco, size_stock, size_unitprice  FROM product_detail
			 WHERE product_detail.id =1;
/*查詢產品完整明細*/
 SELECT product_ID, Category, productItemName, stock, unitPrice, photoUrl, descrip	tion, lunchDate, discount, productSign FROM vgb.product
left join vgb.productsign on product.Sign_id=vgb.productsign.id
left join vgb.productcategory on product.Category_id=vgb.productcategory.id;

/*依照產品分類查詢 view*/
SELECT id, productItemName, photoUrl, lunchDate, discount, Category, productSign, minunprice, maxunprice, totalstock FROM product_list
WHERE Category='鍵盤' AND productItemName like'%fi%' ;

/*依照產品廠牌查詢 view*/
SELECT id, productItemName, photoUrl, lunchDate, discount, Category, productSign, minunprice, maxunprice, totalstock FROM product_list
WHERE productSign like '%Z%';
/*依照產品分類查詢*/
 SELECT product_ID, Category, productItemName, stock, unitPrice, photoUrl, description, lunchDate, discount, productSign FROM vgb.product
left join vgb.productsign on product.Sign_id=vgb.productsign.id
left join vgb.productcategory on product.Category_id=vgb.productcategory.id
WHERE Category='鍵盤';
SELECT id, productItemName, photoUrl, lunchDate, discount, Category, productSign, minunprice, maxunprice, totalstock FROM product_list
			 WHERE productSign like '%fi%';
 /*查詢產品部分條件*/
 SELECT product_ID, Category, productItemName, stock, unitPrice, photoUrl, description, lunchDate, discount, productSign FROM vgb.product
left join vgb.productsign on product.Sign_id=vgb.productsign.id
left join vgb.productcategory on product.Category_id=vgb.productcategory.id
WHERE productItemName like'%fi%' 	/*%ZENZO%*/;
/*查詢最新上架產品(不會超過今天日期)*/
 SELECT product_ID, Category, productItemName, stock, unitPrice, photoUrl, description, lunchDate, discount, productSign FROM vgb.product
left join vgb.productsign on product.Sign_id=vgb.productsign.id
left join vgb.productcategory on product.Category_id=vgb.productcategory.id
WHERE lunchDate<=DATE_ADD(curtime(), INTERVAL 7 DAY)
 order by lunchDate DESC ,product_ID DESC ;
 
 /*查詢最近7天上架產品(最近7天上架)*/
 SELECT product_ID, Category, productItemName, stock, unitPrice, photoUrl, description, lunchDate, discount, productSign FROM vgb.product
left join vgb.productsign on product.Sign_id=vgb.productsign.id
left join vgb.productcategory on product.Category_id=vgb.productcategory.id
WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date (lunchDate)
 order by lunchDate DESC ,product_ID DESC ;
