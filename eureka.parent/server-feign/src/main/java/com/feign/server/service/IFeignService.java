package com.feign.server.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-ribbon")
public interface IFeignService {
	
//	@RequestMapping(value = "/ribbon/name",method = RequestMethod.GET)
	@GetMapping("/ribbon/name")
	String testRibbon(@RequestParam("name")String name);
}
