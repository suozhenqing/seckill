package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 配置sping和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
	
	@Resource
	private SeckillDao seckillDao;
	
	@Test
	public void testReduceNumer(){
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumer(1000L, killTime);
		System.out.println("updateCount="+updateCount);
		
	}
	
	@Test
	public void testQueryById(){
		long id= 1000L;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	/* List<Seckill> seckill = seckillDao.queryAll(0,4);
	 * java没有保存形参的记录,需添加注解
	 * queryAll(int offset,int limit) queryAll(arg0,arg1)
	 */
	@Test
	public void testQueryAll(){
		List<Seckill> seckill = seckillDao.queryAll(0,4);
		for (Seckill seckill2 : seckill) {
			System.out.println(seckill2);
		}
	}
}
