SELECT * FROM TEACHERS WHERE TFAM IN ('�������', '��������', '�������')

SELECT COUNT(SFAM) AS '���-��', STIP  FROM STUDENTS GROUP BY STIP ORDER BY '���-��' DESC

SELECT * FROM STUDENTS WHERE SNUM IN (SELECT SNUM FROM USP WHERE UDATE = '10/06/1999')

SELECT SNUM, SFAM FROM STUDENTS WHERE SNUM IN(SELECT SNUM FROM USP GROUP BY SNUM HAVING COUNT(OCENKA) > 1)