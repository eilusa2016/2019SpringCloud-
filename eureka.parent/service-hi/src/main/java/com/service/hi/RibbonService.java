package com.service.hi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
	@Autowired
	RestTemplate restTemplate;
	
	public  String  getRibbonResult(String name) {
		return restTemplate.getForObject("http://SERVICE-RIBBON/name/?name="+name, String.class);
	}
	
}
