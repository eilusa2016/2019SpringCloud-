package com.feign.server.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import feign.Logger;
import feign.codec.Decoder;

/**
 *  该Feign Client的配置类，注意：
 * 1. 该类可以独立出去；
 * 2. 该类上也可添加@Configuration声明是一个配置类；
 *  配置类上也可添加@Configuration注解，声明这是一个配置类；
 *  但此时千万别将该放置在主应用程序上下文@ComponentScan所扫描的包中，
 *  否则，该配置将会被所有Feign Client共享，无法实现细粒度配置！
 *  个人建议：像我一样，不加@Configuration注解
 * @author karl.xu
 *
 */
public class FeignConfig {

	 	@Bean
	    Logger.Level feignLoggerLevel() {
	        return Logger.Level.FULL;
	    }

	 
	 	
	 	

	    @Bean
	    public Decoder feignDecoder() {
	    	
	        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
	    }

	    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
	        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new PhpMappingJackson2HttpMessageConverter());
	        return new ObjectFactory<HttpMessageConverters>() {
	            @Override
	            public HttpMessageConverters getObject() throws BeansException {
	                return httpMessageConverters;
	            }
	        };
	    }

	    public class PhpMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
	        PhpMappingJackson2HttpMessageConverter(){
	            List<MediaType> mediaTypes = new ArrayList<>();
	            mediaTypes.add(MediaType.TEXT_HTML); //关键
	            mediaTypes.add(MediaType.TEXT_PLAIN); //关键
	            mediaTypes.add(MediaType.APPLICATION_JSON); //关键
	            mediaTypes.add(MediaType.TEXT_XML); //关键
	            setSupportedMediaTypes(mediaTypes);
	        }
	    }
}
