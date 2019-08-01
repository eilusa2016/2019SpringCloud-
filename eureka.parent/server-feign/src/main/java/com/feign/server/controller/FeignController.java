package com.feign.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feign.server.service.IFeignService;

@Controller
@RequestMapping("gfeigin")
public class FeignController {
	@Autowired
	private IFeignService feignService;
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	@ResponseBody
	public  String getName(@RequestParam("name") String name) {
		return  feignService.testRibbon(name);
	}
	
}
