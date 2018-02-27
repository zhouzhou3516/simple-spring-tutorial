drop table `account`;
create table if not exists `account` (
  `id` int not null auto_increment,
  `user_id` int not null comment '关联用户',
  `balance` decimal not null comment '余额',
) comment='账户表' ;
