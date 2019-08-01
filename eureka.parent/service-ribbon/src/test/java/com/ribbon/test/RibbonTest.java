package com.ribbon.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.service.riboon.ServiceRibbonApplication;



@RunWith(SpringRunner.class)
@SpringBootTest(classes =ServiceRibbonApplication.class )
public class RibbonTest {
	Logger LOG=LoggerFactory.getLogger(RibbonTest.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Test
	public void  testGetName() {
		for(int i=0;i<10;i++) {
			String  ss=restTemplate.getForObject("http://SERVICE-RIBBON/name/?name="+("TOM_"+i), String.class);;
			LOG.info("打印结果="+(ss));	
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
