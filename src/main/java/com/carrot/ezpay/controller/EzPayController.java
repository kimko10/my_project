package com.carrot.ezpay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * EasyPay Module
 * 이지페이 모듈
 * @author D83
 *
 */
@RestController
@Slf4j
@RequestMapping(value="/ezpay")
public class EzPayController{
	
	@RequestMapping(value="/{type}/{pageurl}")
	public ModelAndView ezPayPage(@PathVariable String pageurl, @PathVariable String type) {
		
		/*
		 * if (device.isMobile()) { log.info("Hello mobile user!"); } else if
		 * (device.isTablet()) { log.info("Hello tablet user!"); } else {
		 * log.info("Hello desktop user!"); }
		 */
		log.info("#####expay " + type + " module " + pageurl + " page");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ezpay/" + type + "/" + pageurl);
		
		return mav;
		
	}

	@RequestMapping(value="/{type}/{pageurl1}/{pageurl2}")
	public ModelAndView ezPayPage2(HttpServletRequest request, @PathVariable String type, @PathVariable String pageurl1, @PathVariable String pageurl2) {
		
		/*
		 * Device device = DeviceUtils.getCurrentDevice(request);
		 * 
		 * log.info("######## mobile : " + device.isMobile());
		 * log.info("######## desktop : " + device.isNormal());
		 */
		
		log.info("####expay " + type + " module " + pageurl1 + "/" + pageurl2 + " page");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ezpay/" + type + "/" + pageurl1 + "/" + pageurl2);
		
		return mav;
		
	}
	

    
}
