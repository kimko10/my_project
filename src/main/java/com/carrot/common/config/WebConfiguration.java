package com.carrot.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	// 모바일용으로 m/~ 이런 url이 들어왔을 때 정적컨텐츠 제공으로 쓸 수 있음
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/m/**")
				 .addResourceLocations("classpath:/m/")
				 .setCachePeriod(20);
	}
}
