package com.feign.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.feign.server.FeignApp;
import com.feign.server.controller.FeignController;
import com.feign.server.service.IFeignHiService;
import com.feign.server.service.IFeignService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=FeignApp.class)
@WebAppConfiguration
public class FeignTest {

	Logger LOG=LoggerFactory.getLogger(FeignTest.class);
	
	@Autowired
	private IFeignService feignService;
	@Autowired
	private FeignController feignController;
	@Autowired
	private  IFeignHiService feignHiService;
	@Test
	public  void  testFeignService() {
		for(int  i=0;i<10;i++) {
			ResponseEntity<byte[]> entity=feignService.testRibbonGZIP("TO_"+i);
			LOG.info("结果="+new String(entity.getBody()));
			//LOG.info("结果="+feignService.testRibbon("TO_"+i));
		}
		//LOG.info("结果="+feignHiService.info());
	
		while(true) {
			
		}
		
	}
	
	@Test
	public  void  testController() {
		for(int  i=0;i<3;i++) {
			LOG.info("结果="+feignController.getName("TO_"+i));
		}
		while(true) {
			
		}
		
	}
	
	
}
