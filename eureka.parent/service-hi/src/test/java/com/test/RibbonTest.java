package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.service.hi.RibbonService;
import com.service.hi.ServiceHiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =ServiceHiApplication.class )
public class RibbonTest {
	Logger LOG=LoggerFactory.getLogger(RibbonTest.class);
	
	@Autowired
	private  RibbonService ribbonService;
	
	@Test
	public void  testGetName() {
		for(int i=0;i<10;i++) {
			LOG.info("打印结果="+(ribbonService.getRibbonResult("TOM_"+i)));	
		}
		System.out.println("-----");
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
