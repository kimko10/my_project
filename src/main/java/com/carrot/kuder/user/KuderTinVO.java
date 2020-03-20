package com.carrot.kuder.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KuderTinVO {

	// TIN번호 평가 TIN 번호
    private String tinNumber;

    // 이메일 사용자 이메일
    private String userEmail;

    // 상품명 Atlas 성인(Journey) / Atlas 중고등(Navigator)
    private String productName;

    // 평가횟수 평가횟수 최대 2회(0/2)
    private int assessmentCount;

    // 평가완료여부 Y : 평가완료 / N : 평가진행중
    private String assessmentYn;

    // 유효기간시작일 유효기간시작일(bc 결제 or bb 유저등록 시점)
    private Date serviceStartDate;

    // 유효기간종료일 유효기간종료일(유효기간시작일 + 1년)
    private Date serviceEndDate;

    // 최근응시일 최근응시일(중간 저장 시점)
    private Date recentlyTestDate;

    // 최근평가일 최근평가일(평가 완료 시점)
    private Date recentlyAssmDate;

    // 엑티베이션코드 평가 활성 코드
    private String activationCode;
    
}
