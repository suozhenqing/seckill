package org.seckill.controller;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("")
public class SeckillController {
	
	private final Logger Logger =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		//获取列表页
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model){
		if(null == seckillId){
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if(null == seckill){
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	@RequestMapping(value="/{seckillId}/exposer",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
		SeckillResult<Exposer> result;
		try {
			
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true,exposer);
		} catch (Exception e) {
			Logger.error(e.getMessage(),e);
			result = new SeckillResult<Exposer>(true,e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/{seckillId}/{md5}/execution",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId, @CookieValue(value="killPhone",required=false) Long userPhone, @PathVariable("md5") String md5){
		if(null == userPhone){
			return new SeckillResult<SeckillExcution>(false,"未注册");
		}
		
		try {
			SeckillExcution excution = seckillService.executeSeckill(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExcution>(true, excution);
		} catch (RepeatKillException e) {
			SeckillExcution execution = new SeckillExcution(seckillId,SeckillEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExcution>(true,execution);
		} catch (SeckillCloseException e) {
			SeckillExcution execution = new SeckillExcution(seckillId,SeckillEnum.END);
			return new SeckillResult<SeckillExcution>(true,execution);
		} catch (Exception e) {
			Logger.error(e.getMessage(),e);
			SeckillExcution execution = new SeckillExcution(seckillId,SeckillEnum.INNER_ERROR);
			return new SeckillResult<SeckillExcution>(true,execution);
		}
	}
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time(){
		Date now = new Date();
		return new SeckillResult<Long>(true,now.getTime());
	}
	
}