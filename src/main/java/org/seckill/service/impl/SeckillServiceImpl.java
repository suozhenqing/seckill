package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
//@Component @Service @Dao @Controller
@Service
public class SeckillServiceImpl implements SeckillService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired //@Resource j2EE�淶���ע��
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	//MD5��ֵ�ַ������ڻ���
	private final String salt = "suozhenqingsuozhenhong";
	
	public List<Seckill> getSeckillList() {
		
		return seckillDao.queryAll(0, 4);
	}

	public Seckill getById(long seckillId) {
		
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		
		Seckill seckill = seckillDao.queryById(seckillId);
		if(null==seckill){
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		if(nowTime.getTime() < startTime.getTime() 
				|| nowTime.getTime() > endTime.getTime()){
			return new Exposer(false,seckillId,nowTime.getTime(),
					startTime.getTime(),endTime.getTime());
		}
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}
	
	private String getMD5(long seckillId){
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	@Transactional
	/**
	 * ʹ��ע��������񷽷����ŵ㣺
	 *   1.��ȷ��ע���񷽷��ı�̷��
	 *   2.��֤���񷽷���ִ��ʱ�価���̣ܶ���Ҫ�������������������http����
	 */
	public SeckillExcution executeSeckill(Long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if(null == md5 || !md5.equals(getMD5(seckillId))){
			throw new SeckillException("seckill data rewrite");
		}
		Date killTime = new Date();
		try {
			//���ٿ��
			int updateCount = seckillDao.reduceNumer(seckillId, killTime);
			if(updateCount <= 0){
				throw new SeckillCloseException("seckill is closed");
			}else{
				//��¼������Ϊ
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				if(insertCount <= 0){
					throw new RepeatKillException("seckill repeated");
				}else{
					//��ɱ�ɹ�
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExcution(seckillId, SeckillEnum.SUCCESS,successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SeckillException("seckill inner error"+e.getMessage());
		}
	}
}
