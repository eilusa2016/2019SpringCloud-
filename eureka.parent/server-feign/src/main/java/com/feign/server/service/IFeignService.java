package com.feign.server.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//, url = "https://www.gitee.com"
@FeignClient(name="service-ribbon")
public interface IFeignService {
	
//	@RequestMapping(value = "/ribbon/name",method = RequestMethod.GET)
	@GetMapping("/ribbon/name")
	String testRibbon(@RequestParam("name")String name);
	
	
	@GetMapping("/ribbon/name")
	ResponseEntity<byte[]> testRibbonGZIP(@RequestParam("name")String name);
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    ResponseEntity<byte[]> searchRepo(@RequestParam("q") String query);
	
}
