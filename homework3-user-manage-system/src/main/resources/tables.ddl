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



INSERT INTO role (id,name) VALUES (1,'admin'),(2,'user');

---init password=123456,salt=saltvalue
INSERT INTO user (id,username,password,salt) VALUES
(1,'admin1','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(2,'user1','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue'),
(3,'user2','2153e1c85c41977618b1d3ee2b17fbb03a6581a4','saltvalue');

INSERT INTO role_mapping (id,user_id,role_id) VALUES
(1,1,1),
(1,2,2),
(1,3,2);