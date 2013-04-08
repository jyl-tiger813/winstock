-- 按年分组计算总市值，总流通市值，换手率
USE sse

SELECT changetime,AVG(makettotal),AVG(circulatetotal),AVG(changeratio)    FROM `sse`.`record`  GROUP BY YEAR(`changeTime`) 


-- 指数高位，换手率连降3月，必须逃跑，如2007.5月
SELECT changetime,AVG(makettotal),AVG(circulatetotal),AVG(changeratio) ratio,AVG(perGet)    FROM `sse`.`record`   GROUP BY DATE_FORMAT(changeTime,'%y%m') ORDER BY changetime DESC 

SELECT changetime,AVG(makettotal),AVG(circulatetotal),AVG(changeratio) ratio,AVG(perGet)    FROM `sse`.`record`   GROUP BY DATE_FORMAT(changeTime,'%y') ORDER BY changetime DESC 


ORDER BY ratio DESC

ORDER BY ratio DESC 

-- 

SELECT changetime,makettotal,circulatetotal,changeratio,perGet
   FROM `sse`.`record` ORDER BY changetime DESC 
 WHERE 
 perGet < 16  ORDER BY changeratio DESC 
 
 perGet可以设为16，按这个标准只有2005和2008两次低谷才能被筛选出来。
 
 SELECT * FROM `sse`.`record` WHERE changeRatio < 0.01 AND perGet <15
