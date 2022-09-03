SELECT id, customer_id, created_date, created_time, status,
				payment_type, payment_fee, shipping_type, shipping_fee,
			    CAST(SUM(price*quantity) AS DECIMAL(9,0))+payment_fee+shipping_fee as total_amount      FROM orders  
			JOIN order_items ON orders.id=order_items.order_id     WHERE customer_id='A123456789'GROUP BY orders.id
			 ORDER BY created_date DESC, created_time DESC;
             
             
SELECT orders.id, customer_id, created_date, created_time, status, 
			payment_type, payment_fee, payment_note, shipping_type, shipping_fee,shipping_note,
			 recipient_name, recipient_email, recipient_phone, recipient_addres,
				order_items.product_id, product.productItemName as product_name, 
					order_items.type_name, order_items.size_name,  price, quantity,
			 	product.photoUrl,Dimensionsco,productType
			 FROM orders   JOIN order_items ON orders.id=order_items.order_id   
			    JOIN product ON order_items.product_id=product. product_ID
			    LEFT JOIN product_sort ON order_items.product_id=product_sort.product_id                
                AND (order_items.type_name=product_sort.product_type OR (order_items.type_name>0 AND product_sort.product_type IS NULL))				
                LEFT JOIN productsize ON order_items.product_id=productsize.product_id
                AND (order_items.size_name=productsize.product_Size OR (order_items.size_name>0 AND productsize.product_Size IS NULL))                 
              
              LEFT JOIN producttype ON order_items.type_name=producttype.id
			   LEFT JOIN productdimensions ON order_items.size_name=productdimensions.id		
			    WHERE customer_id='A123456789' AND orders.id='11';
             
          