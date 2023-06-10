--1
CREATE PROCEDURE AddNewProductToShop
@TypeOfProduct NCHAR(20),
@DateOFShipment DATE,
@NumberOfProduct INT,
@PriceOfProduct INT,
@BrandName NCHAR(30),
@ID INT,
@SupplierName NCHAR(30),
@ProductName NCHAR(40)
AS
BEGIN

DECLARE @SupplierID INT
SELECT TOP 1 @SupplierID = SUPPLIER.SupplierID FROM SUPPLIER
INNER JOIN PRODUCT ON SUPPLIER.SupplierID = PRODUCT.SupplierID
WHERE SUPPLIER.SupplierName = @SupplierName
GROUP BY SUPPLIER.SupplierID
ORDER BY SUM(PRODUCT.NumberOfProduct) DESC

DECLARE @ShopID INT
SELECT TOP 1 @ShopID = ShopID FROM SHOP
WHERE SupplierID = @SupplierID
GROUP BY ShopID
ORDER BY SUM(NumberOfProduct) DESC

INSERT INTO PRODUCT(TypeOfProduct, DateOfShipment, NumberOfProduct, PriceOfProduct, ShopName, BrandName, ID, SupplierID, ProductName)
VALUES(@TypeOfProduct, @DateOFShipment, @NumberOfProduct, @PriceOfProduct, 'Кошык', @BrandName, @ID, @SupplierID, @ProductName)

END

EXEC AddNewProductToShop 'Аксессуар', NULL, 10, 100, 'Gucci', 21, 'ООО"Борис"', 'Тестовый'
--2
CREATE PROCEDURE TransferToOtherShop
    @sourceShopID INT,
    @destinationShopID INT
AS
BEGIN

SELECT p.ID, p.ProductName, p.NumberOfProduct, p.NumberOnOrder
INTO #TempProducts
FROM PRODUCT p
LEFT JOIN DEAL deal ON p.ID = deal.IDofProduct
WHERE deal.IDofProduct IS NULL

UPDATE PRODUCT
SET ShopID = @destinationShopID
WHERE ID IN (SELECT ID FROM #TempProducts)
AND ShopID = @sourceShopID

DROP TABLE #TempProducts

END

EXEC TransferToOtherShop @sourceShopID = 1, @destinationShopID = 2;
--3
CREATE PROCEDURE WriteOff
AS
BEGIN
    SELECT p.ProductName, p.SupplierID, p.NumberOfProduct, p.PriceOfProduct, p.PriceOfProduct * p.NumberOfProduct AS TotalCost, p.DateOfShipment
	INTO #ObsoleteProducts
    FROM PRODUCT p
    LEFT JOIN DEAL deal ON p.ID = deal.IDofProduct
    WHERE deal.IDofProduct IS NULL AND DATEDIFF(day, p.DateOfShipment, GETDATE()) >= 10

    INSERT INTO ARCHIVEDPRODUCT (ProductName, SupplierID, NumberOfProduct, TotalCost, DateOfShipment)
    SELECT ProductName, SupplierID, NumberOfProduct, TotalCost, DateOfShipment
    FROM #ObsoleteProducts

    -- Списать товары со склада
    DELETE FROM PRODUCT
    WHERE ID IN (SELECT ID FROM #ObsoleteProducts)

    -- Удалить временную таблицу
    DROP TABLE #ObsoleteProducts
END

EXEC WriteOff