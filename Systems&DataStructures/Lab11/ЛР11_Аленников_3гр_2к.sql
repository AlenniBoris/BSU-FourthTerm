--1
CREATE PROCEDURE CreateNakladnaya
(
@SupplierName NVARCHAR(50),
@DateOfShipment DATE,
@NumberOfProduct INT,
@PriceOfProduct DECIMAL(10,2)
)
AS
BEGIN

SET NOCOUNT ON;
    
INSERT INTO [3_Alennikov_Project].[NAKLADNAYA](SupplierName, DateOfShipment, NumberOfProduct, PriceOfProduct)
VALUES (@SupplierName, @DateOfShipment, @NumberOfProduct, @PriceOfProduct)

END

EXEC CreateNakladnaya 'ООО"Борис"', '10/05/2020', 15, 1222
--2
CREATE FUNCTION getTotalPrice
(
@ShopID INT,
@TypeOfProduct NCHAR(20)
)
RETURNS INT
AS
BEGIN

DECLARE @TotalPrice INT

SELECT @TotalPrice = SUM(NumberOfProduct * PriceOfProduct) FROM DEAL 
WHERE ShopID = @ShopID AND TypeOfProduct = @TypeOfProduct

RETURN @TotalPrice

END

SELECT getTotalPrice(123, 'Аксессуары') AS TotalPrice
--3
CREATE TRIGGER CheckTotal
ON DEAL
FOR INSERT, UPDATE
AS
BEGIN

DECLARE @ShopID INT
DECLARE @TotalPrice DECIMAL(10,2)

SELECT @ShopID = ShopID, @TotalPrice = SUM(NumberOfProduct * PriceOfProduct) 
FROM inserted 
GROUP BY ShopID

IF @TotalPrice > 1000000
BEGIN

RAISERROR('Вы превысили допустимую стоимость для магазина ', 16, 1, @ShopID)
ROLLBACK TRANSACTION

END

END