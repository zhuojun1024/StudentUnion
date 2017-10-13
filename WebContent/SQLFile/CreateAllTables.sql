use master
go
if exists(select * from sysdatabases where name='StudentUnion')
	drop database StudentUnion
go
create database StudentUnion
go
use StudentUnion
go
--==========================创建公共表==============================
----------------班级表----------------
create table su_class(
	cid int primary key identity(0,1), --班级id
	cname varchar(50) unique not null  --班级名称
)
go
----------------部门表----------------
create table su_dept(
	did int primary key identity(0,1),  --部门id
	dname varchar(50) not null  --部门名称
)
go
---------------学生表-------------
create table su_student(
	[sid] int primary key identity(1,1),  --学生id
	sno varchar(50) unique not null,  --学号
	sname varchar(50) not null,  --姓名
	spwd varchar(50) default('gdnf2017'),  --密码
	scid int foreign key references su_class(cid) not null,  --所属班级
	sdid int foreign key references su_dept(did) not null  --所属部门
)
go
-------------公告表--------------
create table su_notice(
	nid int primary key identity(1,1),  --公告id
	npublisher varchar(20) not null,  --发布者
	ndid int foreign key references su_dept(did) not null,  --部门id
	ndate datetime default(CONVERT(varchar(100), GETDATE(), 20)) not null,  --发布时间
	nweek varchar(10) default(DATEPART(weekday, GETDATE())) not null,  --星期
	ntitle varchar(100) not null,  --标题
	ncontent varchar(8000) not null,  --内容
	nregion varchar(10) not null  --发布区域
)

go

--==========================生活部==============================

----------------寝室信息----------------
create table su_dorminfo(
	did int primary key identity(1,1),  --编号
	dbuilding varchar(10) not null,  --栋别
	dnumber varchar(20) not null,  --宿舍号
	dcid int foreign key references su_class(cid) not null,  --所属班级
)
go
----------------寝室登记----------------
create table su_dormregister(
	did int primary key identity(1,1),  --a栋id
	dbuilding varchar(10) not null,  --栋别
	dnumber varchar(50) not null,  --宿舍号
	dcid int foreign key references su_class(cid) not null,  --所属班级
	dstandarda varchar(200),  --评分标准a
	dstandardb varchar(200),  --评分标准b
	dstandardc varchar(200),  --评分标准c
	dstandardd varchar(200),  --评分标准d
	dmark varchar(50),  --分数
	dliable varchar(50),  --负责人
	ddate datetime default(CONVERT(varchar(100), GETDATE(), 23))  --日期
)
--==========================秘书部==============================

create table Department_check(
	cid int IDENTITY(1,1) NOT NULL,
	dname varchar(50) NOT NULL,
	allnum int NOT NULL,
	num int NOT NULL,
	lacknum int NOT NULL,
	ntime varchar(50) NOT NULL
)

create table Property( --财物设计表
	id int primary key identity(1,1),--财物编号
	Goods varchar(200) not null, --物品名称
	Num int not null, --物品数量
	Price float not null, --物品价格
	TPrice float not null, --物品总价
	BuyTime datetime not null   --当前购买时间
)


--==========================社团部==============================


--创建社团部所有社团的表
create table dp_clubDept
(
	clubid int primary key identity(1,1),
	clubname varchar(20),--社团名字
)
--社团的考勤表
create table dp_clubDept_attendance
(
	attid int primary key identity(1,1),
	peoplesnum int ,--总人数
	peoplecomenum int,--实到人数
	peoplenocome int,--缺勤人数
	thetime datetime default(getdate()),
	aclubid int foreign key references dp_clubDept(clubid) not null--所属社团
)


--==========================播音部==============================

--------------类型表---------------------
create table br_type(
	tid int identity(1,1) primary key,
	tname varchar(50) not null
)

----------------寻物失物表--------------------
go
create table br_notice(
	nid int primary key identity(1,1),  --编号id
	nname varchar(50) not null,  --用户名
	ncontent varchar(400) not null,  --内容
	ntime datetime,  --发布当前时间
	ntid int foreign key references br_type(tid)
)
------------同学来稿表-----------------
go
create table br_StuEssay(
	eid int primary key identity(1,1),  --编号id
	ename varchar(50) not null,  --用户名
	etitle varchar(50) not null,  --标题
	econtent varchar(4000) not null,  --内容
	etime datetime  --发布当前时间
)


--==========================外联部==============================
/*人员及其任务*/ 
/*赞助信息表*/
create table sponsor(
sid int primary key identity(1,1),
sevent varchar(50) not null,
things varchar(500) not null,
datatime  datetime,
sname varchar(50) not null,
result varchar(10)
)
/*活动信息*/
create table Event(
eid int primary key identity(1,1),
event varchar(50) not null,
ethings varchar(500) not null,
ename varchar(50) not null,
datatime  datetime
)
/*讲座信息*/
create table Lecture(
lid int primary key identity(1,1),
pname varchar(50) not null,
lname varchar(30) not null,
ldatatime datetime
)
/*宣传信息*/
create table Propaganda(
pid int primary key identity(1,1),
pname varchar(50) not null,
content varchar(100) not null,
pdatatime datetime
)


--==========================文体部==============================

create table Stu_Sports(
	id int primary key identity(1,1),
	stitle varchar(200) not null, --标题
	sncontent varchar(1000) not null, --内容
	stime datetime not null, --时间
	sleader varchar(100) not null, --负责人
	shelp varchar(100), --协助部门
	scontact varchar(100) not null, --联系方式
	sremarks varchar(200), --备注
	stest int not null default(0)
)


--==========================学习部==============================

--================================================第一步执行=================================================
use StudentUnion
go
--================================================第二步执行=================================================
--书籍状态表，表2
create table lifeDep_book_state
(
	l_b_s_id int primary key identity,
	l_b_s_state varchar(5) not null
)
go
--书籍信息表,表1
create table lifeDep_book_info
(
	l_b_id int primary key identity,
	--编号，名字，作者
	l_b_no varchar(50) unique not null,
	l_b_name varchar(999) not null,
	l_b_author varchar(999) not null,
	--书籍状态信息（可借，借出，损坏，丢失）
	l_b_state int default(1) foreign key references lifeDep_book_state(l_b_s_id) not null
)
go
--书籍状态信息表，表3
create table lifeDep_book_state_info
(
	l_b_s_i_id int primary key identity,
	l_b_s_i_bookno int foreign key references lifeDep_book_info(l_b_id) not null,--被借书籍编号
	l_b_s_i_stuno int foreign key references su_student([sid]) not null,--借书人学号
	l_b_s_i_outtime datetime default(GETDATE()),--借出时间
	l_b_s_i_intime datetime default(null),--归还时间
	l_b_s_i_state int foreign key references lifeDep_book_state(l_b_s_id) default(2)--当前状态
)
go
create table Assistant	--创建助教表
(
	aid int primary key identity,
	asno int foreign key references su_student([sid]) not null,--助教学号
	acno int foreign key references su_class(cid) not null --助教教的班级
)

--=============================================第六步执行，一直到最后====================================================
go
--还书触发器
--更改表1中书籍状态触发将表3中的书籍状态改为已归还，并更改归还时间为当前时间
create trigger book_state_update on lifeDep_book_info
after update
as
	if(select l_b_state from inserted) = 1
	begin
		update a 
		set a.l_b_s_i_state = b.l_b_state, 
			a.l_b_s_i_intime = GETDATE() 
		from lifeDep_book_state_info a,inserted b 
		where a.l_b_s_i_bookno = b.l_b_id 
			and a.l_b_s_i_id = 
				(select top 1 l_b_s_i_id from lifeDep_book_state_info order by l_b_s_i_id desc)
end
go
--借书触发器
--借书会在表3增加一条记录（日志），同时改变表1中书籍状态为2（借出）
create trigger book_state_loan on lifeDep_book_state_info
after insert
as
	update a 
	set l_b_state = b.l_b_s_i_state 
	from lifeDep_book_info a, inserted b 
	where a.l_b_id = b.l_b_s_i_bookno
go
--分页存储过程
go
create proc usp_pag
 @bookno varchar(50),
 @bookname varchar(999),
 @pageIndex int,   --当前页码
 @pageSize int,   --每页多少条
 @pageCount int output  --计算  总共多少页
as
 declare @count int --总共多少条
 select @count =COUNT(*) from (select a.l_b_id, a.l_b_no, a.l_b_name, a.l_b_author, b.l_b_s_state from lifeDep_book_info a join lifeDep_book_state b on a.l_b_state=b.l_b_s_id where a.l_b_no like '%'+@bookno+'%' or a.l_b_name like '%'+@bookname+'%') as a
 set @pageCount = CEILING( @count*1.0/@pageSize)
 select *,@pageCount as ys from
(select *,ROW_NUMBER() over(order by l_b_id) as num
from (select a.l_b_id, a.l_b_no, a.l_b_name, a.l_b_author, b.l_b_s_state from lifeDep_book_info a join lifeDep_book_state b on a.l_b_state=b.l_b_s_id where a.l_b_no like '%'+@bookno+'%' or a.l_b_name like '%'+@bookname+'%')as b) as t
where num between @pageSize*(@pageIndex-1) + 1 and @pageSize*@pageIndex
--助教查询存储过程
go
create proc usp_ass
 @str varchar(50),
 @pageIndex int,   --当前页码
 @pageSize int,   --每页多少条
 @pageCount int output  --计算  总共多少页
as
 declare @count int
 select @count=COUNT(*) from 
 (select t.* from (
  select a.aid, s.sno, s.sname, 
   (select top 1 cname  
   from su_student s2, su_class c2 
   where s2.scid = c2.cid and s2.[sid]=s.[sid]) owncname, --所在班级
   c.cname --所教班级
  from Assistant a, su_student s, su_class c 
  where a.asno = s.[sid]  and a.acno = c.cid 
 )t
 where owncname like '%'+@str+'%' 
  or t.sno = '%'+@str+'%' 
  or t.sname like '%'+@str+'%' 
  or t.cname like '%'+@str+'%')a
  set @pageCount = CEILING( @count*1.0/@pageSize)
 select *,@pageCount as ys from
(select *,ROW_NUMBER() over(order by sno) as num
from (
select t.* from (
  select a.aid, s.sno, s.sname, 
   (select top 1 cname  
   from su_student s2, su_class c2 
   where s2.scid = c2.cid and s2.[sid]=s.[sid]) owncname, --所在班级
   c.cname --所教班级
  from Assistant a, su_student s, su_class c 
  where a.asno = s.[sid]  and a.acno = c.cid 
 )t
 where owncname is not null and (owncname like '%'+@str+'%'
  or t.sno like '%'+@str+'%' 
  or t.sname like '%'+@str+'%' 
  or t.cname like '%'+@str+'%')
) as t5)t4
where num between @pageSize*(@pageIndex-1) + 1 and @pageSize*@pageIndex

go

create proc usp_log
 @bno varchar(50), --书籍编号
 @pageIndex int, --当前页码
 @pageSize int, --每页多少条
 @pageCount int output --计算 总共多少页
as
 declare @count int  --总条数
 select @count = count(*) from (select a.l_b_s_i_id, b.l_b_no, b.l_b_name, c.sno, c.sname, a.l_b_s_i_outtime, a.l_b_s_i_intime, d.l_b_s_state from lifeDep_book_state_info a, lifeDep_book_info b, su_student c, lifeDep_book_state d where a.l_b_s_i_bookno = b.l_b_id and a.l_b_s_i_stuno = c.[sid] and a.l_b_s_i_state = d.l_b_s_id and b.l_b_no = @bno) as t1 
 set @pageCount = CEILING( @count*1.0/@pageSize) 
 select *, @pageCount as ys from 
 (select *, ROW_NUMBER() over(order by l_b_s_i_id desc) as num from
 (select a.l_b_s_i_id, b.l_b_no, b.l_b_name, c.sno, c.sname, a.l_b_s_i_outtime, a.l_b_s_i_intime, d.l_b_s_state from lifeDep_book_state_info a, lifeDep_book_info b, su_student c, lifeDep_book_state d where a.l_b_s_i_bookno = b.l_b_id and a.l_b_s_i_stuno = c.[sid] and a.l_b_s_i_state = d.l_b_s_id and b.l_b_no = @bno) as t2) as t3  
 where num between (@pageIndex-1)*@pageSize+1 and @pageIndex*@pageSize
 
 
 --=====================================管理员=====================================
 
--查询班级分页（管理员）
go
create proc adm_claInfo
 @pageIndex int,
 @pageSize int,
 @pageCount int output
as
 declare @count int
 select @count = count(*) from (select cid, cname , count(scid) as l from (select c.cid, c.cname, s.scid from su_class c left join su_student s on c.cid = s.scid) as t1 group by cid, cname)as t2
 set @pageCount = CEILING( @count*1.0/@pageSize) 
   select *, @pageCount as ys from 
   (select *, ROW_NUMBER() over(order by cid) as num from
	(select cid, cname , count(scid) as l from (select c.cid, c.cname, s.scid from su_class c left join su_student s on c.cid = s.scid) as t1 group by cid, cname)t3)t4
  where num between (@pageIndex-1)*@pageSize+1 and @pageIndex*@pageSize
go

--学生信息查询（管理员）
create proc adm_stuInfo
 @sno varchar(50), 
 @sclass varchar(50), 
 @sdep varchar(50), 
 @pageIndex int,   --当前页码
 @pageSize int,   --每页多少条
 @pageCount int output  --计算  总共多少页
as
 declare @count int  --总条数
 select @count = count(*) from (select s.sno, s.sname, s.spwd, c.cname, d.dname from su_student s, su_class c, su_dept d where s.scid = c.cid and s.sdid = d.did and ((s.sno like '%'+@sno+'%' or s.sname like '%'+@sno+'%') and c.cname like '%'+@sclass+'%' and d.dname like '%'+@sdep+'%') and s.sno != '000000' ) as t1
 set @pageCount = CEILING( @count*1.0/@pageSize) 
  select *, @pageCount as ys from 
   (select *, ROW_NUMBER() over(order by sno) as num from
	(select s.sno, s.sname, s.spwd, c.cname, d.dname from su_student s, su_class c, su_dept d where s.scid = c.cid and s.sdid = d.did and ((s.sno like '%'+@sno+'%' or s.sname like '%'+@sno+'%') and c.cname like '%'+@sclass+'%' and d.dname like '%'+@sdep+'%') and s.sno != '000000' )t2)t3
  where num between (@pageIndex-1)*@pageSize+1 and @pageIndex*@pageSize
go


--=========================纪检部===============================
--drop table su_inspection
create table su_inspection(
	iid int primary key identity(1,1),
	icid int foreign key references su_class(cid) not null,  --班级id
	iall int not null,  --应到人数
	ione int,  --一次实到
	itwo int,  --二次实到
	istandarda int,  --早退
	istandardb int,  --请假
	istandardc int,  --缺勤
	istandardd int,  --违纪
	istandarde int,  --教室卫生扣分
	imark int,  --总扣分
	iclaperson varchar(50),  --班级负责人
	iperson varchar(50),  --负责人
	idate datetime default(GETDATE())  --日期
)