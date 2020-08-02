package org.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ����sping��junit���ϣ�junit����ʱ����springIOC����
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {
	
	@Resource
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled(){
		/* ��һ�Σ�resutl=1
		 * �ڶ��Σ�resutl=0
		 */
		long seckillId = 1001L;
		long userPhone = 18368033809L;
		int result = successKilledDao.insertSuccessKilled(seckillId, userPhone);
		System.out.println("resutl="+result);
	}
	
	@Test
	public void testQueryByIdWithSeckill(){
		long seckillId = 1000L;
		long userPhone = 13938569113L;
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());
	}
}
