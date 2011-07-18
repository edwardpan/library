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

delete from user where id in (25,26,27,28)

begin;
insert into User(loginname, realname, password) values('pan','pan','pan');

rollback;