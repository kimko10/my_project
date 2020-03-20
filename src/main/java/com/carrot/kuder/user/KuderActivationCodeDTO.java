package com.carrot.kuder.user;

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
public class KuderActivationCodeDTO extends KuderActivationCodeVO{
	
	// 엑티베이션코드 평가 활성 코드
    private String activationCode;

    // 총개수 평가 활성 코드가 가진 TIN 번호 개수
    private int totalCount;

    // 발급가능수 평가 활성 코드로 발급 가능한 TIN번호 개수
    private int ableCount;

    // 사용한수 평가 활성 코드로 발급 된 TIN번호 개수
    private int unableCount;

    // 상품명 Atlas 성인(Journey) / Atlas 중고등(Navigator)
    private String productName;

    // 유효기간시작일 유효기간시작일(bc 결제 or bb 유저등록 시점)
    private Date serviceStartDate;

    // 유효기간종료일 유효기간종료일(유효기간시작일 + 1년)
    private Date serviceEndDate;

    // 고객구분 B2C / B2B 구분
    private String userType;

    // B2B그룹코드 B2B그룹코드
    private int bbGroupCode;

    // 이메일 결제자 이메일
    private String userEmail;
}
