package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
	
	//���빺����ϸ
	int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	
	//��ѯSuccessKilled��Я����ɱ��Ʒ����ʵ��
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
