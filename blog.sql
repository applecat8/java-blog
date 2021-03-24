create database blog
desc t_blog
desc t_type
select * from t_user
select * from t_blog
select * from t_type
select * from t_tag
insert into t_user values(null,1,"",now(),"123456@qq.com","ib","a",now(),"a")
insert into t_type values(null,"Golang")
alter table t_user drop column nick_name
drop database blog
create database blog