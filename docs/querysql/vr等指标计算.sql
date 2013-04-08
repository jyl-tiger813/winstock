SELECT trade_date,COUNT(1) num FROM index_vr_related_data
WHERE index_id = 4 AND count_days = 10 AND index_value>1000 GROUP BY trade_date

SELECT * FROM index_vr_related_data ORDER BY ts DESC 

SELECT * FROM stock_trade_daily_detail ORDER BY id DESC 
TRUNCATE TABLE stock_trade_daily_detail
TRUNCATE TABLE index_vr_related_data

SELECT * FROM sse.stocknames WHERE isdealed <>1

SELECT * FROM sse.stocknames WHERE isdealed <>0


UPDATE stocknames SET isdealed =0 

SELECT * FROM stock_trade_daily_detail ORDER BY trade_date DESC 

SELECT COUNT(1) num FROM stock_trade_daily_detail

SELECT * FROM index_vr_related_data

SELECT COUNT(1) FROM stock_trade_daily_detail WHERE trade_date = '2013-04-03 00:00:00'

SELECT * FROM stock_trade_daily_detail WHERE stock_code = '600004' ORDER BY  trade_date DESC 

SELECT * FROM stock_trade_daily_detail ORDER BY  trade_date DESC 