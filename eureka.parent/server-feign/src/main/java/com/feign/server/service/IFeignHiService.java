package com.feign.server.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name="SERVICE-HI")
public interface IFeignHiService {
	
	@RequestMapping("/info")
	public  String  info();
}
