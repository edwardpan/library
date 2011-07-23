create database firecoder;

use firecoder;
create table if not exists User(
    id int AUTO_INCREMENT primary key,
    loginName varchar(50),
    realName varchar(50),
    password varchar(100)
);

drop table user;

insert into User(loginname, realname, password) values('admin', '管理员', 'admin');
insert into User(loginname, realname, password) values('panchao', '潘超', 'panchao');

select * from user;

delete from user where id in (25,26,27,28);

begin;
insert into User(loginname, realname, password) values('pan','pan','pan');

rollback;


create table if not exists Formula(
    id int auto_increment primary key,
    expression varchar(1000),
    isSetScale varchar(100) default 'Y',
    scale int default 4,
    resultUnit varchar(100)
);

create table if not exists FormulaElement(
    id int auto_increment primary key,
    formulaId int not null,
    name varchar(100),
    value double,
    valueUnit varchar(100),
    dataType varchar(100),
    writable varchar(10) default 'Y',
    isFinal varchar(10) default 'N',
    minVal double,
    maxVal double
);

insert into formula(expression, issetscale, scale, resultunit) values('(4+2)*A','Y',4,'');
insert into formulaelement(formulaid,name,value,valueUnit,dataType,writable,isfinal,minval,maxval) values(1,'A',0.003,'','double','Y','N','1','10');

insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');
insert into formula(expression, issetscale, scale, resultunit) values('4-2','Y',4,'');


select * from formula;



