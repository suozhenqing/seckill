package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	//Closing non transactional SqlSession
	public void testGetSeckillList(){
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}",list);
	}
	
	@Test
	public void testGetById(){
		Seckill seckill = seckillService.getById(1000L);
		logger.info("seckill={}",seckill);
	}
	
	@Test
	public void testSeckillLogic(){
		long id = 1000L;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()){
			logger.info("exposer={}",exposer);
			try {
				long phone = 13938569114L;
				String md5 = exposer.getMd5();
				SeckillExcution seckillExcution = seckillService.
						executeSeckill(id,phone,md5);
				logger.info("seckillExcution={}",seckillExcution);
			} catch (RepeatKillException e) {
				logger.info(e.getMessage());
			} catch (SeckillCloseException e) {
				logger.info(e.getMessage());
			}
		}else{
			//秒杀未开启
			logger.warn("exposer={}",exposer);
		}
	}
	
	//@Test
	/**
	 * [exposed=true, md5=0a996d16df6eb0eeabf617b03e151234, 
	 * seckillId=1000, now=0, start=0, end=0]
	 */
	/*public void testExportSeckillUrl(){
		Exposer exposer = seckillService.exportSeckillUrl(1000L);
		logger.info("exposer={}",exposer);
	}*/
	
	//@Test
	/**
	 * 注意时间性
	 * Transaction synchronization closing SqlSession
	 */
	/*public void testExecuteSeckill(){
		
		try {
			SeckillExcution seckillExcution = seckillService.
					executeSeckill(1000L, 13938569114l, "0a996d16df6eb0eeabf617b03e151234");
			logger.info("seckillExcution={}",seckillExcution);
		} catch (RepeatKillException e) {
			logger.info(e.getMessage());;
		} catch (SeckillCloseException e) {
			logger.info(e.getMessage());;
		} 
	}*/
}
