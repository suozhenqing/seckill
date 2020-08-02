package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

public interface SeckillDao {
	
	//减少库存
	int reduceNumer(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);
	
	//查询单个产品信息
	Seckill queryById(long seckillId);
	
	//查询所有产品信息
	List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
}