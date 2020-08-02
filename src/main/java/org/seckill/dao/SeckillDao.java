package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	
	//���ٿ��
	int reduceNumer(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);
	
	//��ѯ������Ʒ��Ϣ
	Seckill queryById(long seckillId);
	
	//��ѯ���в�Ʒ��Ϣ
	List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
}