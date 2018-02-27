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
