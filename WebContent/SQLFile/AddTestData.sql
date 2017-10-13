--use master
use StudentUnion
go
--====================添加公共表的测试数据===================
--添加测试班级
insert into su_class values('暂无')
insert into su_class values('S130')
insert into su_class values('S131')
insert into su_class values('S132')
insert into su_class values('S133')
insert into su_class values('S134')
insert into su_class values('S135')
insert into su_class values('S136')
insert into su_class values('S137')
insert into su_class values('S138')
insert into su_class values('S139')
insert into su_class values('S140')
insert into su_class values('T130')
insert into su_class values('T131')
insert into su_class values('T132')
insert into su_class values('T133')
insert into su_class values('T134')
insert into su_class values('T135')
insert into su_class values('T136')
insert into su_class values('T137')
insert into su_class values('T138')
insert into su_class values('T139')
insert into su_class values('T140')
go
--添加所有部门及角色
insert into su_dept values('管理员')
insert into su_dept values('学员')
insert into su_dept values('生活部') --000002
insert into su_dept values('纪检部')
insert into su_dept values('秘书部') --000004
insert into su_dept values('学习部') --000005
insert into su_dept values('宣传部')
insert into su_dept values('文体部') --000007
insert into su_dept values('播音部') --000008
insert into su_dept values('社团部') --000009
insert into su_dept values('外联部') --000010
go
--添加学生及部分角色
insert into su_student values('000000','admin','00000000',0,0)
insert into su_student values('000001','陈一',default,1,1)
insert into su_student values('000002','黄二',default,2,2)
insert into su_student values('000003','张三',default,3,3)
insert into su_student values('000004','李四',default,4,4)
insert into su_student values('000005','王五',default,5,5)
insert into su_student values('000006','赵六',default,6,6)
insert into su_student values('000007','钱七',default,7,7)
insert into su_student values('000008','孙八',default,8,8)
insert into su_student values('000009','杨九',default,9,9)
insert into su_student values('000010','吴十',default,10,10)
insert into su_student values('000011','学员1',default,11,1)
insert into su_student values('000012','学员2',default,12,1)
insert into su_student values('000013','学员3',default,13,1)
insert into su_student values('000014','学员4',default,14,1)
insert into su_student values('000015','学员5',default,15,1)
insert into su_student values('000016','学员6',default,16,1)
insert into su_student values('000017','学员7',default,17,1)
go
--添加5条测试公告
insert into su_notice values('系统',0,default,default,'这是用于测试的公告001','这是一条测试消息001','public')
insert into su_notice values('系统',0,default,default,'这是用于测试的公告002','这是一条测试消息002','public')
insert into su_notice values('系统',0,default,default,'这是用于测试的公告003','这是一条测试消息003','public')
insert into su_notice values('系统',0,default,default,'这是用于测试的公告004','这是一条测试消息004','public')
insert into su_notice values('系统',0,default,default,'这是用于测试的公告005','这是一条测试消息005','public')
go


--=====================生活部===================

--批量添加用于测试的所有寝室信息
go
declare @i int
declare @n int
declare @j int
declare @d int
declare @a varchar(500)
declare @m varchar(50)
set @m='100'
set @a=''
set @j=1
set @n=100
	while(@n<600)
	begin
	set @i=1
		while(@i<16)
		begin
		set @d=0
		insert into su_dorminfo values('a',@n+@i,@j)
		while(@d<30)
			begin
			insert into su_dormregister values('a',@n+@i,@j,'',@a,'','',@m,'张三',GETDATE()-@d);
			set @d=@d+1
			set @a=(case when @d%2=0 then '桌面乱-5' else '' end)
			set @m=(case when @d%2=0 then '95' else '100' end)
		end
		set @i=@i+1
		set @j=@j+1
		set @j=(case when @j=23 then 1 else @j end)
		end
	set @n=@n+100
	end
go
declare @i int
declare @n int
declare @j int
declare @d int
declare @a varchar(500)
declare @m varchar(50)
set @m='100'
set @a=''
set @j=1
set @n=100
	while(@n<600)
	begin
	set @i=1
		while(@i<16)
		begin
		set @d=0
		insert into su_dorminfo values('b',@n+@i,@j)
		while(@d<30)
			begin
			insert into su_dormregister values('b',@n+@i,@j,'',@a,'','',@m,'张三',GETDATE()-@d);
			set @d=@d+1
			set @a=(case when @d%3=0 then '桌面乱-5' else '' end)
			set @m=(case when @d%3=0 then '95' else '100' end)
		end
		set @i=@i+1
		set @j=@j+1
		set @j=(case when @j=23 then 1 else @j end)
		end
	set @n=@n+100
	end
go
declare @i int
declare @n int
declare @j int
declare @d int
declare @a varchar(500)
declare @m varchar(50)
set @m='100'
set @a=''
set @j=1
set @n=100
	while(@n<600)
	begin
	set @i=1
		while(@i<16)
		begin
		set @d=0
		insert into su_dorminfo values('c',@n+@i,@j)
		while(@d<30)
			begin
			insert into su_dormregister values('c',@n+@i,@j,'',@a,'','',@m,'张三',GETDATE()-@d);
			set @d=@d+1
			set @a=(case when @d%5=0 then '桌面乱-5' else '' end)
			set @m=(case when @d%5=0 then '95' else '100' end)
		end
		set @i=@i+1
		set @j=@j+1
		set @j=(case when @j=23 then 1 else @j end)
		end
	set @n=@n+100
	end
go
--select * from su_dorminfo
--select * from su_dormregister
--select d.*,c.cname from su_dormregister d left join su_class c on d.dcid=c.cid where datediff(mm,d.ddate,'2017-6-1')=0 and d.dbuilding='a' and d.dnumber='106'
--批量添加用于测试的一个月的内务检查数据


--====================秘书部===================

insert into Property values('奖状纸',40,0.5,20,GETDATE());
insert into Property values('佩戴用品',10,5,50,GETDATE());
insert into Property values('学生奖品',10,5,50,GETDATE());
insert into Property values('白板笔',2,5,10,GETDATE());
insert into Property values('奖状纸2',40,0.5,20,GETDATE());
insert into Property values('佩戴用品2',10,5,50,GETDATE());
insert into Property values('学生奖品2',10,5,50,GETDATE());
insert into Property values('白板笔2',2,5,10,GETDATE());
insert into Property values('奖状纸3',40,0.5,20,GETDATE());
insert into Property values('佩戴用品3',10,5,50,GETDATE());
insert into Property values('学生奖品3',10,5,50,GETDATE());
insert into Property values('白板笔3',2,5,10,GETDATE());

--====================社团部===================

insert into dp_clubDept values('国术社')
insert into dp_clubDept values('书法社')
insert into dp_clubDept values('吉他社')
insert into dp_clubDept values('羽毛球社')
insert into dp_clubDept values('蔓延社')
insert into dp_clubDept values('乒乓球社')

insert into dp_clubDept_attendance values(40,30,10,default,1)
insert into dp_clubDept_attendance values(50,30,20,default,2)
insert into dp_clubDept_attendance values(30,30,0,default,3)
insert into dp_clubDept_attendance values(40,30,10,default,4)
insert into dp_clubDept_attendance values(50,30,20,default,5)
insert into dp_clubDept_attendance values(30,30,0,default,6)

go
insert into br_type values('寻物启事')
insert into br_type values('失物招领')


insert into br_notice values('Lucy','寻物启事1','2017.06.23',1)
insert into br_notice values('Lucy','寻物启事2','2017.06.22',1)

insert into br_notice values('Lucy','失物招领1','2017.06.23',2)
insert into br_notice values('Lucy','失物招领2','2017.06.22',2)


insert into br_StuEssay values('小明','一路风清，人生静好','“静”字，透着草木不与花香争锋的相安，....。','2017.06.22')
insert into br_StuEssay values('小明','镌刻一份温情寄予明天','人生，是一场孤独的漫旅，....。','2017.06.22')
insert into br_StuEssay values('小明2','标题','人生，是一场孤独的漫旅，....。','2017.06.22')
insert into br_StuEssay values('小明3','标题','人生，是一场孤独的漫旅，....。','2017.06.22')
insert into br_StuEssay values('小明4','标题','人生，是一场孤独的漫旅，....。','2017.06.22')


--====================播音部===================
---------为寻物失物表插入30条数据------------
go
declare @i int
set @i=1
while(@i<21)
begin
insert into br_notice values('admin'+convert(varchar,@i*3/2),'寻物启事'+convert(varchar,@i),getdate(),1)
insert into br_notice values('Jack'+convert(varchar,@i*3/2),'失物招领'+convert(varchar,@i),getdate(),2)
	set @i=@i+1
end
go


--====================外联部===================

insert into sponsor values('千年商店','赞助矿泉水2000瓶','2017-5-20','张三','成功')
insert into sponsor values('箱庭奇缘','无','2017-5-20','李四','未成功')
insert into sponsor values('萌0-0','现金5000元','2017-5-20','哈利','成功')
insert into sponsor values('丒','无','2017-5-20','X','不成功')
insert into sponsor values('龙傲天','天下','2017-5-20','白起','成功')


insert into Event values('校内运动会','布置场地,维持场面','外联部全体男生','2017-4-20')
insert into Event values('中秋节晚会','布置场地,维持场面','外联部全体','2017-8-15')


insert into Lecture values('邓超','分手大师','2017-5-20')
insert into Lecture values('徐峥','催眠大师','2017-7-15')


insert into Propaganda values('校内宣传','海报、广播','2017-3-20')
insert into Propaganda values('校外宣传','网络、横幅','2017-5-15')
insert into Propaganda values('学校告示栏','告示','2017-5-20')



--====================文体部===================

insert into Stu_Sports values('南方校庆','广东南方IT学院成立20周年晚会','2017-7-7','张三','李四','敬请观看','123456789',default)
insert into Stu_Sports values('南方校庆2','广东南方IT学院成立20周年晚会,请全校师生,社团做好相关节目准备','2017-7-7','张三','李四','敬请观看','123456789',default)
insert into Stu_Sports values('sss','ccc','2017-7-7','vv','vv','vvv','123456789',default)



--====================学习部===================


insert into lifeDep_book_state values('待借')
insert into lifeDep_book_state values('借出')
insert into lifeDep_book_state values('损坏')
insert into lifeDep_book_state values('丢失')


insert into Assistant values(2,3)
insert into Assistant values(4,8)
insert into Assistant values(6,9)
insert into Assistant values(3,2)
insert into Assistant values(6,7)

go
insert into lifeDep_book_info values('ax17230','老人与海','海明威',1)
insert into lifeDep_book_info values('al00326','雾都孤儿','狄更斯',1)
insert into lifeDep_book_info values('bw27003','简爱','夏洛蒂·勃朗特',1)
insert into lifeDep_book_info values('ax62039','海底两万里','儒勒·凡尔纳',1)
insert into lifeDep_book_info values('fs00268','宇宙简史','史蒂芬·霍金',1)
insert into lifeDep_book_info values('xa02147','唐吉坷德','塞万提斯',1)
insert into lifeDep_book_info values('wg26130','骆驼祥子','老舍',1)
insert into lifeDep_book_info values('bw00261','边城','沈从文',1)
insert into lifeDep_book_info values('lz25000','草房子','曹文轩',1)
insert into lifeDep_book_info values('wg26140','骆驼祥子','老舍',1)
insert into lifeDep_book_info values('bw00861','边城','沈从文',3)
insert into lifeDep_book_info values('lz25900','草房子','曹文轩',4)


--select d.*,c.cname from su_dormregister d left join su_class c on d.dcid=c.cid where datediff(dd,d.ddate,'2017-6-30')=0 and d.dcid=1


----------------查询测试数据------------
--use StudentUnion
--select * from su_class
--select * from su_dept
--select * from su_student
--select * from su_notice
--select * from su_dorminfo
--select * from su_dormregister
--drop table su_dormregister

-------------查询案例-------------

--联合查询学生信息：select s.*,c.cname,d.dname from su_class c,su_dept d left join su_student s on sno='000002' and spwd='gdnf2017' where s.scid = c.cid and s.sdid = d.did

--分页联合查询：select n.*,d.dname from su_dept d left join (select row_number() over(order by ndate desc) rn,* from su_notice where ndid=4 or ndid=0 and npublisher='系统') n on n.rn>0 and n.rn<=3 where n.ndid = d.did

--中文星期查询：set language N'Simplified Chinese' select datename(weekday, getdate())

--星期编号查询：select datepart(weekday, getdate())

--格式化日期：Select CONVERT(varchar(100), GETDATE(), 20)

--联合查询寝室信息：select d.*,c.cname from su_dorminfo d left join su_class c on d.dcid=c.cid where dbuilding='a' and dnumber like '1%'

--联合查询当天寝室登记信息：select d.*,c.cname from su_dormregister d left join su_class c on d.dcid=c.cid where d.dnumber like '1%' and datediff(dd,d.ddate,getdate())=0

--分页查询寝室当月信息：select d.*,c.cname from su_class c left join (select row_number() over(order by ddate) rn,* from su_dormregister where datediff(mm,ddate,'2017-06-01')=0 and dbuilding='a' and dnumber='101' and dcid='1') d on d.rn>0 and d.rn<=12  where dcid=c.cid

--查询寝室当月信息总条数：select COUNT(*) from su_dormregister where datediff(mm,ddate,'2017-07-01')=0 and dbuilding='a' and dnumber='101' and dcid='1'