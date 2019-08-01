package com.service.riboon.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 配置策略
 * @author karl.xu
 *
 */
@Configuration
public class StrategyConfig {
	
	/**
	 * 全局修改ribbon的负载均衡的策略
	 * @return
	 */
//	@Bean
//	public  IRule ribbonRule() {
//		return new RandomRule();
//	}
	
	
	@Bean
	public  IRule ribbonRule() {
		
		return new RandomRule();
	}
	
}
