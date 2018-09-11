--数据库初始化脚本
--创建数据库
create database seckill;
--使用
use seckill;
--创建秒杀库存表
create table seckill(
`seckill_id` bigint not null AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar (120) not null COMMENT '商品名称',
`number` int not null COMMENT '库存数量',
`start_time` timestamp not null comment '秒杀开启时间',
`end_time` timestamp not null comment '秒杀结束时间',
`create_time` timestamp not null default current_timestamp comment '创建时间',
primary key (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

--初始化数据
insert into seckill(name ,number ,start_time,end_time)
values
('1000元秒杀iphone8',100,'2018-09-04 00:00:00','2018-09-05 00:00:00' ),
('200元秒杀iphone5',300,'2018-09-04 00:00:00','2018-09-05 00:00:00' ),
('1500元秒杀小猫',50,'2018-09-04 00:00:00','2018-09-05 00:00:00'),
('800元秒杀小狗',30,'2018-09-04 00:00:00','2018-09-05 00:00:00');

--秒杀成功明细表
--用户登录认证相关信息
create table success_killed(
`seckill_id` bigint not null comment '秒杀商品id',
`user_phone` bigint not null comment '用户手机号',
`state` tinyint not null default -1 comment '状态标示:-1:无效 0:成功 1:已付款 2:已发货 3:已收货',
`create_time` timestamp not null comment '创建时间',
primary key (seckill_id,user_phone),/*联合主键:*/
key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表'



ALTER TABLE seckill
drop index idx_create_time,
add INDEX idx_c_s(start_time,create_time)