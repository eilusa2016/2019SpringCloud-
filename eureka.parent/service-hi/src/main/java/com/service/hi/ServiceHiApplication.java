package com.service.hi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//import brave.sampler.Sampler;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ServiceHiApplication {
	private static final Logger LOG = LoggerFactory.getLogger(ServiceHiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceHiApplication.class, args);
	}

	@Value("${server.port}")
    String port;

    @RequestMapping("/hi")
	@HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "XXXX") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

//	@RequestMapping("/hi")
//	@HystrixCommand(fallbackMethod = "hiError")
//	public String callHome() {
//		LOG.info(Level.INFO + ":calling trace service-hi  ");
//		return restTemplate.getForObject("http://localhost:8763/miya", String.class);
//	}

	@RequestMapping("/info")
	public String info() {
		LOG.info(Level.INFO + ":calling trace service-hi ");
		return "i'm service-hi";
	}

	public String hiError(String name) {
		return "hi," + name + ",sorry,error!";
	}
//	@Bean
//	public Sampler defaultSampler() {
//		return Sampler.ALWAYS_SAMPLE;
//	}

}
