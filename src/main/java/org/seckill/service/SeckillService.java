package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

/**
 * ҵ��ӿڣ�վ�ڡ�ʹ���ߡ��Ƕ���ƽӿ�
 *     1.������������
 *     2.����
 *     3.�������ͣ�����/�쳣
 */
public interface SeckillService {
	
	List<Seckill> getSeckillList();
	
	Seckill getById(long seckillId);
	
	/**
	 * ��ɱ����ʱ�������ɱ�ӿڵ�ַ��
	 * �������ϵͳʱ�����ɱʱ��
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * ִ����ɱ����
	 */
	SeckillExcution executeSeckill(Long seckillId,long userPhone,String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
}
