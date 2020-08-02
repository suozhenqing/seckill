package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	
	//插入购买明细
	int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	
	//查询SuccessKilled并携带秒杀产品对象实体
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
