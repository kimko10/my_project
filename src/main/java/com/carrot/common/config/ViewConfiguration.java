package com.carrot.common.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class ViewConfiguration {
	
	@Bean(name="viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean(name="urlViewController")
	public UrlFilenameViewController getUrlViewController() {
		
		UrlFilenameViewController urlViewController = new UrlFilenameViewController();
		urlViewController.setPrefix("/WEB-INF/jsp");
		urlViewController.setSuffix(".jsp");
		
		return urlViewController;
		
	}
	/// WEB-INF/jsp/ezpay/index.jsp
	@Bean
	public SimpleUrlHandlerMapping getUrlHandlerMapping() {
		
		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		mappings.put("/ezpay/index.do", "urlViewController");
		
		handlerMapping.setMappings(mappings);
		
		return handlerMapping;
		
	}
	
}
