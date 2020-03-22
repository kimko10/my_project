package com.carrot.kuder.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KuderCompanyVO {

	// 회사코드 회사 등록 시 번호
    private int idx;

    // 회사명 회사명
    private String companyName;

    // 주소 회사주소
    private String companyAddress;

    // 사이트 회사 사이트
    private String companySite;

    
}
