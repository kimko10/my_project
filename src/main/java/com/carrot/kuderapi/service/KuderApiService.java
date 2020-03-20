package com.carrot.kuderapi.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KuderApiService {
	

	public void getUserAssessmentItemByCategory() throws Exception {
		
		URL url = new URL("https://atlas.kuder.com/api/externalapi/GetUserAssessmentItemByCategory"); // 호출할 URL
    	Map<String, Object> params = new LinkedHashMap<>(); // LinkedHashMap은 HashMap과는 key의 순서가 지켜지는 차이가 있음
    	params.put("OrganizationAPIKey", "abe52eca-df09-4cb5-9934-45f37f73887d");
    	params.put("AssessmentCategoryId", 2);
    	params.put("CultureId", 5);
    	params.put("IsAdult", true);
    	
    	StringBuilder postData = new StringBuilder();
    	for(Map.Entry<String, Object> param : params.entrySet()) {
    		if(postData.length() != 0)
    			postData.append('&');
    		// UTF-8 생략되는지 나중에 확인해보기
    		postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
    		postData.append('=');
    		postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
    	}
    	
    	byte[] postDataBytes = postData.toString().getBytes("UTF-8");
    	
    	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    	conn.setRequestMethod("POST");
//    	conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // json 보내는거로
    	conn.setRequestProperty("Content-Type", "application/json"); // json 보내는거로
    	conn.setRequestProperty("Content-Length",  String.valueOf(postDataBytes.length));
    	conn.setDoOutput(true);
    	conn.getOutputStream().write(postDataBytes); // POST 호출
    	
    	int responseCode = conn.getResponseCode();
    	log.info("URL : " + url.toString());
    	log.info("Parameter : " + postData.toString());
    	log.info("Response Code : " + responseCode);
    	
    	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    	
    	String inputLine ="";
        while((inputLine = in.readLine()) != null) { // response 출력
            
            log.info("getUserAssessmentItemByCategory 조회결과 : " + inputLine);
            
    	}
    	
    	
    	in.close();

    }
    
	public void getAllCultures() throws Exception {
    	
		
    	URL url = new URL("https://atlas.kuder.com/api/externalapi/GetAllCultures/abe52eca-df09-4cb5-9934-45f37f73887d");
    	
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	
    	conn.setRequestProperty("Content-Type", "application/json");
    	conn.setDoOutput(true);
    	conn.setRequestMethod("GET");
    	
    	int responseCode = conn.getResponseCode();
    	log.info("URL : " + url.toString());
    	log.info("Response Code : " + responseCode);
    	
    	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    	
    	String inputLine;
    	
    	while((inputLine = in.readLine()) != null) {

    		log.info("getAllCultures 조회결과 : " + inputLine);
    		
    	}
    	
    	in.close();
    	
    }
	
public void getAllAssessments() throws Exception {
    	
		
    	URL url = new URL("https://atlas.kuder.com/api/externalapi/GetAllAssessments/abe52eca-df09-4cb5-9934-45f37f73887d");
    	
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	
    	conn.setRequestProperty("Content-Type", "application/json");
    	conn.setDoOutput(true);
    	conn.setRequestMethod("GET");
    	
    	int responseCode = conn.getResponseCode();
    	log.info("URL : " + url.toString());
    	log.info("Response Code : " + responseCode);
    	
    	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    	
    	String inputLine;
    	
    	while((inputLine = in.readLine()) != null) {

    		log.info("getAllAssessments 조회결과 : " + inputLine);
    		
    	}
    	
    	in.close();
    	
    }


}
