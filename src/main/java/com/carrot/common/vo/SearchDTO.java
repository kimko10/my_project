package com.carrot.common.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchDTO {
	
	Date recentlyAssmDate;			// 최근평가일
	
	Date registDate;					// 등록일
	
	String userType;					// 구분
	
	String companyId;					// Company
	
	String userName;					// 평가관리 : 이름, B2B관리 : 담당자이름
	
	String userEmail;					// Email
	
	String bbGroupName;			// 그룹 명
	
	String bManagerName;			// HQ매니저
	
}
