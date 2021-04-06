create database blog
desc t_blog
desc t_type
desc t_tag

select * from t_user
select * from t_blog
select * from t_type
select * from t_tag


insert into t_user values(null,1,"",now(),"123456@qq.com","ib","a",now(),"a")
insert into t_type values(null,"Golang")
insert into t_tag values(null,"技术");
insert into t_tag values(null,"娱乐")


update t_blog set type_id = 6

alter table t_user drop column nick_name
drop database blog
create database blog

drop table t_blog
drop table t_blog_tags

insert into t_blog values(null,b'1','system.out.println("hello world!")',now(),'https://th.bing.com/th/id/Rb572cf0dcff93fe13b71af8ab43fd454?rik=g5YdMgIO9siDaA&riu=http%3a%2f%2fimg.ewebweb.com%2fuploads%2f20191006%2f20%2f1570365161-shmEFlWfHU.jpg&ehk=u5A16U5IZ7u6Wi3KtC7OBarR5lf1nOd8UfJP0USvLXg%3d&risl=&pid=ImgRaw',null,b'1',b'1',b'1','JavaSE',now(),12,7)