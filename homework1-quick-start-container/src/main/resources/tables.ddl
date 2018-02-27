create table `user` (
  `id` int not null auto_increment,
  `username` varchar not null comment '账户名',
  `password` varchar not null comment '账户密码',
) comment='用户表' ;

create table `role` (
  `id` int not null auto_increment,
  `name` varchar not null comment '角色名',
) comment='角色表' ;
create table `role_mapping` (
  `id` int not null auto_increment,
  `user_id` int not null comment 'user id',
  `role_id` int not null comment 'role id'
) comment='关系表' ;

INSERT INTO role (id,name) VALUES (1,'学生'),(2,'老师'),(3,'主任'),(4,'校长');

---password 123456
INSERT INTO user (id,username,password) VALUES
(1,'stu1','e10adc3949ba59abbe56e057f20f883e'),
(2,'stu2','e10adc3949ba59abbe56e057f20f883e'),
(3,'stu3','e10adc3949ba59abbe56e057f20f883e'),
(4,'stu4','e10adc3949ba59abbe56e057f20f883e'),
(5,'stu5','e10adc3949ba59abbe56e057f20f883e'),
(6,'tea1','e10adc3949ba59abbe56e057f20f883e'),
(7,'tea2','e10adc3949ba59abbe56e057f20f883e'),
(8,'tea3','e10adc3949ba59abbe56e057f20f883e'),
(9,'zhuren1','e10adc3949ba59abbe56e057f20f883e'),
(10,'zhuren2','e10adc3949ba59abbe56e057f20f883e'),
(11,'xiaozhang','e10adc3949ba59abbe56e057f20f883e');

INSERT INTO role_mapping (id,user_id,role_id) VALUES
(1,1,1),
(1,2,1),
(1,3,1),
(1,4,1),
(1,5,1),
(1,6,2),
(1,7,2),
(1,8,2),
(1,9,3),
(1,10,3),
(1,11,4);






