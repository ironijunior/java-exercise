SELECT d.name
     , sum(CASE WHEN d.value > 0 THEN d.value ELSE 0) deposit
     , sum(CASE WHEN d.value <= 0 THEN d.value * -1 ELSE 0) withdrawl
  FROM deposits d
 GROUP BY d.name
 ORDER BY d.name