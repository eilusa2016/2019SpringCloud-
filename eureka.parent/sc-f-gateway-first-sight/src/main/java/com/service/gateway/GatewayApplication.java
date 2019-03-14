package com.service.gateway;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.gateway.resolver.HostAddrKeyResolver;
import com.service.gateway.resolver.UriKeyResolver;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class GatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	public RouteLocator getRoute(RouteLocatorBuilder builder) {
		
		 ZoneId zone = ZoneId.of("America/Denver");
		 LocalDate date = LocalDate.of(2017, Month.AUGUST, 19);
		 ZonedDateTime zonedDateTime=date.atStartOfDay(zone);
		 return builder.routes()
			.route(p->p.after(zonedDateTime)
					.uri("https://www.baidu.com")).build();
//		 String httpUri = "http://httpbin.org:80";
//		return builder.routes()
//				.route(p->p.path("/get")
//						.filters(f->f.addRequestHeader("hello", "geteway"))
//						.uri("http://httpbin.org"))
//				.route(fn-> fn.host("*.hystrix.com")
//						.filters(f -> f.hystrix(config -> config
//			                            .setName("mycmd")
//			                            .setFallbackUri("forward:/fallback")))
//						.uri(httpUri)).build();
	}
	
	@RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
	
	@Bean
	public  HostAddrKeyResolver getHostAddrKeyResolver() {
		return  new HostAddrKeyResolver();
	}
	
	@Bean
    public UriKeyResolver getUriKeyResolver() {
        return new UriKeyResolver();
    }
}
