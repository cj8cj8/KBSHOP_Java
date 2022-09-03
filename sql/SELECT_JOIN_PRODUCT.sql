
/***查詢product所有資料***/



SELECT *FROM product_test;



             
          /***CROSSJOIN***/   
  SELECT product.product_ID, productItemName, product.stock, unitPrice, photoUrl, description, lunchDate, discount, Category_id, Sign_id
, product_sort.product_id AS type_id, product_sort.product_type AS type_type, product_sort.stock AS type_stock
	FROM product
    JOIN product_test; 
    
 /*inner join(內連接)*/   
             
  SELECT product.product_ID, productItemName, product.stock, unitPrice, photoUrl, description, lunchDate, discount, Category_id, Sign_id
, product_sort.product_id AS type_id, product_sort.product_type AS type_type, product_sort.stock AS type_stock
	FROM product
    JOIN product_test ON product.product_ID=product_test.product_id;
  
  /*inner join(內連接)不適合使用於檢視產品明細*/ 
  SELECT product.product_ID, productItemName, product.stock, unitPrice, photoUrl, description, lunchDate, discount, Category_id, Sign_id
, product_sort.product_id AS type_id, product_sort.product_type AS type_type, product_sort.stock AS type_stock
	FROM product
    JOIN product_test ON product.product_ID=product_test.product_id
    WHERE product_test.product_id IN('7','8','9');
        
     /***LEFT OUTER JOIN***/ 
     /*E05:查詢指定代號產品*/
    SELECT product.product_ID, productItemName, product.stock, unitPrice, photoUrl, description, lunchDate, discount, Category_id, product_sort.product_id AS type_id, product_sort.product_type AS type_type, product_sort.stock AS type_stock
	FROM product
    LEFT JOIN product_sort ON product.product_ID=product_sort.product_id
    WHERE product.product_ID IN('8');
    
        /***LEFT OUTER JOIN***/ 
     /*E05:查詢指定代號產品*/
 
create view product_detail as
               
               SELECT product.product_ID as id, productItemName, product.stock as stock,
                product.unitPrice, photoUrl, description, lunchDate, discount, Category_id,
                Sign_id, product_sort.product_type AS type_id, product_sort.stock AS type_stock,
                product_sort.unitPrice AS type_unitprice, producttype.productType AS type_name,
                producttype.icon_url, productsize.product_Size AS size_id ,Dimensionsco,
                productsize.stock AS size_stock, productsize.unitprice AS size_unitprice			
				FROM product
				LEFT JOIN product_sort ON product.product_ID=product_sort.product_id
				LEFT JOIN producttype ON product_sort.product_type=producttype.id
				LEFT JOIN productsize ON product.product_ID =productsize.product_id
				LEFT JOIN productdimensions ON productsize.product_Size=productdimensions.id;
                
              
				
  /**E05:查詢指定代號產品 min max price total stock**/
				SELECT product.product_ID as id, productItemName, product.stock as stock,
                product.unitPrice, photoUrl, description, lunchDate, discount, Category_id,
                Sign_id, product_sort.product_type AS type_id, product_sort.stock AS type_stock,
                product_sort.unitPrice AS type_unitprice, producttype.productType AS type_name,
                producttype.icon_url, productsize.product_Size AS size_id , Dimensionsco,
                productsize.stock AS size_stock, productsize.unitprice AS size_unitprice,
				ifnull(min(productsize.unitprice),min(product_sort.unitPrice)) AS minunprice,
				ifnull(max(productsize.unitprice),max(product_sort.unitPrice)) AS maxunprice,
				ifnull(SUM(product_sort.stock),SUM(productsize.stock)) AS totalstock
				FROM product
				LEFT JOIN product_sort ON product.product_ID=product_sort.product_id
				LEFT JOIN producttype ON product_sort.product_type=producttype.id
				LEFT JOIN productsize ON product.product_ID =productsize.product_id
				LEFT JOIN productdimensions ON productsize.product_Size=productdimensions.id
				
                group by id;
                
      /**查詢全部**/    
  
				SELECT product.product_ID as id, productItemName, photoUrl,  lunchDate, discount, product_sort.product_type,
				producttype.productType AS type_name,
				productsize.product_Size , Dimensionsco,Category,productSign,       
				ifnull(min(productsize.unitprice),min(product_sort.unitPrice)) AS minunprice,
				ifnull(max(productsize.unitprice),max(product_sort.unitPrice)) AS maxunprice,
				ifnull(SUM(product_sort.stock),SUM(productsize.stock)) AS totalstock
				FROM product
				LEFT JOIN product_sort ON product.product_ID=product_sort.product_id
				LEFT JOIN producttype ON product_sort.product_type=producttype.id
				LEFT JOIN productsize ON product.product_ID =productsize.product_id
				LEFT JOIN productdimensions ON productsize.product_Size=productdimensions.id
                left join vgb.productsign on product.Sign_id=productsign.id
				left join vgb.productcategory on product.Category_id=productcategory.id
				WHERE product.product_ID
                group by id;
                 

 /********/    
    SELECT product.product_ID as id, productItemName, product.stock as stock, product.unitPrice, photoUrl, description, lunchDate, discount, Category_id,Sign_id,
                productsize.stock AS size_stock, productsize.unitprice AS size_unitprice 
				FROM product
				
                LEFT JOIN productsize ON product.product_ID =productsize.product_id
                
				WHERE product.product_ID IN('8') ;
    
    
    
    
     /***right  outer join***/ 
     /*查詢指定代號產品*/
     SELECT product.product_ID, productItemName, product.stock, unitPrice, photoUrl, description, lunchDate, discount, Category_id, Sign_id
	, product_sort.product_id AS type_id, product_sort.product_type AS type_type, product_sort.stock AS type_stock
	FROM product
    RIGHT JOIN product_sort ON product.product_ID=product_sort.product_id;
    
     
    
    