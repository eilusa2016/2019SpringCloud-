package com.service.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.hystrix.service.HelloService;

@RestController
public class HystrixController {
	@Autowired
	private HelloService helloService;
	
	@GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }
}
