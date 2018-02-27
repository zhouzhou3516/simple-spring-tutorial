create table `user` (
  `id` int not null auto_increment,
  `username` varchar not null comment '账户名',
  `password` varchar not null comment '账户密码',
  `salt` varchar not null comment 'salt'
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

---init password=123456,salt=saltvalue
INSERT INTO user (id,username,password,salt) VALUES
(1,'stu1','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(2,'stu2','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(3,'stu3','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(4,'stu4','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(5,'stu5','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(6,'tea1','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(7,'tea2','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(8,'tea3','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(9,'zhuren1','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(10,'zhuren2','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(11,'xiaozhang','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue');

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