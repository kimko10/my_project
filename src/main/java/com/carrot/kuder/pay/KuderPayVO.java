package com.carrot.kuder.pay;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KuderPayVO {

	// 주문번호 atlas 결제 주문번호
    private Integer orderNum;

    // 이름 결제자 이름
    private String userName;

    // 연락처 결제자 연락처
    private String userPhone;

    // 이메일 결제자 이메일
    private String userEmail;

    // 상품명 Atlas 성인(Journey) / Atlas 중고등(Navigator)
    private String productName;

    // 결제액 결제한 상품가격
    private  int payPrice;

    // 결제타입 이지/카카오/네이버/stripe
    private String payType;

    // 할부기간 할부개월
    private int payInstallment;

    // 결제일 결제일
    private Date payDate;
    
}
