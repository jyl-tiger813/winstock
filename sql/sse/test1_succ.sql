delimiter //
CREATE PROCEDURE SSE.curdemo()
BEGIN
  DECLARE a CHAR(16);
  DECLARE b,c INT;
  DECLARE cur1 CURSOR FOR SELECT sse.record;
  OPEN cur1;
  END