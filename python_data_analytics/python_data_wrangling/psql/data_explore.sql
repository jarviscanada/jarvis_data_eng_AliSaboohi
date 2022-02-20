-- Show table schema 
\d+ retail;

-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Check # of records
select COUNT(*) from retail;

-- number of clients (e.g. unique client ID)
select COUNT(DISTINCT customer_id) from retail;

-- invoice date range (e.g. max/min dates)
select MAX(invoice_date), MIN(invoice_date) from retail;

-- number of SKU/merchants (e.g. unique stock code)
select COUNT(DISTINCT stock_code) from retail;

-- Calculate average invoice amount excluding invoices with a negative amount (e.g. canceled orders have negative amount)
select AVG(inv_price) from 
	(select SUM(unit_price*quantity) as inv_price 
	from retail 
	GROUP BY invoice_no 
	HAVING SUM(unit_price*quantity)>0) as sub;

-- Calculate total revenue (e.g. sum of unit_price * quantity)
select SUM(unit_price*quantity) from retail;

-- Calculate total revenue by YYYYMM
select CAST(EXTRACT(YEAR FROM invoice_date) as INTEGER)*100
	+CAST(EXTRACT(MONTH FROM invoice_date) as INTEGER) as yyyymm, 
	SUM(quantity*unit_price) 
from retail 
group by yyyymm 
order by yyyymm asc;
