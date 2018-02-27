create table `user` (
  `id` int not null auto_increment,
  `username` varchar not null comment '账户名',
  `password` varchar not null comment '账户密码',
) comment='账户表' ;
