SELECT * FROM Patient

SELECT * FROM Patient WHERE ADDRESS IN ('�. ��������', '�. ����������')

SELECT TITLE AS '������������', Price AS '���������', 0.3 * Price AS '�������� ���������' FROM H_Services  

SELECT Patient.SURNAME, Patient.FIRSTNAME, Patient.MIDDLE_NAME, ID_Doctor, DATE_VISIT, CODE_SRV FROM Visit LEFT OUTER JOIN Patient ON Visit.ID_Patient = Patient.N_CARD 

SELECT * FROM Speciality where CODE_SPEC NOT IN ( SELECT CODE_SPEC from Doctor)

select * from Visit where DATE_VISIT < '20/09/2022'

select COUNT(ID_DOCTOR) as '����� ��������' from Doctor

SELECT Price from H_Services GROUP BY Price HAVING Price = min(Price)