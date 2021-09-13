create table Course
(
	Code varchar(10) primary key,
	Name nvarchar(MAX),
	Credit int
)


SELECT * FROM Course order by Credit
drop table Course