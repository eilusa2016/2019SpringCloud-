package com.service.riboon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.riboon.service.RibbonService;

@RestController
public class RibbonController {
	@Autowired
	private RibbonService ribbonService;
	
	@GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return ribbonService.hiService( name );
    }
}
