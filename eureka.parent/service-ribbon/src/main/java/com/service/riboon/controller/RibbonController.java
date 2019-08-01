package com.service.riboon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.riboon.service.RibbonService;

@RestController
@RequestMapping("ribbon")
public class RibbonController {
	@Autowired
	private RibbonService ribbonService;
	
	

	@GetMapping(value = "/name")
//	@RequestMapping(value = "/name",method=RequestMethod.GET)
    public String testRibbon(HttpServletRequest request, @RequestParam String name) {
        return "服务端口:"+request.getServerPort()+";结果="+name;
    }
	
	@GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return ribbonService.hiService( name );
    }
}
