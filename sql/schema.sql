create database seckill;
use seckill;

create table seckill(
	`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '��Ʒ���id',
	`name` varchar(120) NOT NULL COMMENT '��Ʒ����',
	`number` int NOT NULL COMMENT '�������',
	`start_time` timestamp NOT NULL COMMENT '��ɱ����ʱ��',
	`end_time` timestamp NOT NULL COMMENT '��ɱ����ʱ��',
	`create_time` timestamp NOT NULL default current_timestamp COMMENT '����ʱ��',
primary key (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT =1000 default CHARACTER SET=utf8 COMMENT='��ɱ����';

insert into 
	seckill(name,number,start_time,end_time)
values
	('1000Ԫ��ɱiphoneX',100,'2018-10-01 00:00:00','2018-10-02 00:00:00'),
	('500Ԫ��ɱipad2',200,'2018-10-01 00:00:00','2018-10-02 00:00:00'),
	('300Ԫ��ɱС��4',300,'2018-10-01 00:00:00','2018-10-02 00:00:00'),
	('200Ԫ��ɱ����note',400,'2018-10-01 00:00:00','2018-10-02 00:00:00');

create table success_killed(
	`seckill_id` bigint NOT NULL COMMENT '��ɱ��Ʒid',
	`user_phone` bigint NOT NULL COMMENT '�û��ֻ���',
	`state` tinyint NOT NULL DEFAULT -1 COMMENT '״̬��ʶ��-1����Ч 0���ɹ� 1���Ѹ��� 2:�ѷ���',
	`create_time` timestamp NOT NULL COMMENT '����ʱ��',
primary key(seckill_id,user_phone),
key idx_create_time(create_time)
)ENGINE=InnoDB default CHARACTER SET=utf8 COMMENT='��ɱ�ɹ���ϸ��';

mysql -uroot -p
