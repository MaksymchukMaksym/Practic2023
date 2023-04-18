CREATE PROCEDURE LoadPersonData
AS
BEGIN
    SET NOCOUNT ON;

    -- drop table if exists
    IF OBJECT_ID('Stage.dbo.Person', 'U') IS NOT NULL
        DROP TABLE Stage.dbo.Person;

    -- create table
    DECLARE @columns NVARCHAR(MAX) = '';
    SELECT @columns += QUOTENAME(COLUMN_NAME) + ' ' + DATA_TYPE + CASE
    WHEN DATA_TYPE IN ('varchar', 'nvarchar') THEN '(' + CAST(CHARACTER_MAXIMUM_LENGTH AS VARCHAR(5)) + ')'
    ELSE '' END + ' ' + CASE WHEN IS_NULLABLE = 'YES' THEN 'NULL' ELSE 'NOT NULL' END + ', '
    FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'Person';

    SELECT @columns;

    SET @columns = LEFT(@columns, LEN(@columns) - 1);

    DECLARE @sql NVARCHAR(MAX) = 'CREATE TABLE Stage.dbo.Person (' + @columns + ')';
    EXEC(@sql);

    -- insert data
    DECLARE @columns2 NVARCHAR(MAX) = '';
    SELECT @columns2 += QUOTENAME(COLUMN_NAME) + ',' 
    FROM AdventureWorks2012.INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'Person';

    SET @columns2 = LEFT(@columns2, LEN(@columns2) - 1);

    DECLARE @input NVARCHAR(MAX) = N'
    INSERT INTO Stage.dbo.Person
    SELECT *
    FROM (
        SELECT BusinessEntityID, PersonType, NameStyle, Title, FirstName, MiddleName, LastName, Suffix, EmailPromotion, AdditionalContactInfo, CONVERT(VARCHAR(MAX), Demographics) AS Demographics, rowguid, ModifiedDate
        FROM AdventureWorks2012.Person.Person
        UNION ALL
        SELECT BusinessEntityID, PersonType, NameStyle, Title, FirstName, MiddleName, LastName, Suffix, EmailPromotion, AdditionalContactInfo, CONVERT(VARCHAR(MAX), Demographics) AS Demographics, rowguid, ModifiedDate
        FROM AdventureWorks2016.Person.Person
    ) AS p';

    EXEC sp_executesql @input;
END;