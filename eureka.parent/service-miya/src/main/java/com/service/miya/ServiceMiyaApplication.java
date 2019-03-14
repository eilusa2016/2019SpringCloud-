package com.service.miya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@SpringBootApplication
@RestController
public class ServiceMiyaApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiceMiyaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ServiceMiyaApplication.class, args);
	}
	@RequestMapping("/hi")
	public String home(){
		LOG.info(Level.INFO+"hi is being called");
		return "hi i'm miya!";
	}

	@RequestMapping("/miya")
	public String miya(){
		LOG.info(Level.INFO+"info is being called");
		return restTemplate.getForObject("http://localhost:8762/info",String.class);
	}

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
