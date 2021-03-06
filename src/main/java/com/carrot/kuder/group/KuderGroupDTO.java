package com.carrot.kuder.group;

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
public class KuderGroupDTO extends KuderGroupVO {

	// B2B그룹코드 B2B그룹코드
    private int idx;

    // B2B그룹명 B2B그룹명
    private String bbGroupName;

    // 회사코드 회사코드
    private int idxKuderCompany;

    // HQ담당자 idx HQ 담당자 idx
    private int idxHq;

    // 고객사 담당자 idx 고객사의 담당자 이메일
    private int idxKuderAdmin;
    
    // 회사명 회사명
    private String companyName;

    // 사이트 회사 사이트
    private String companySite;

    // HQ담당자 이름 B2B 담당자명
    private String bManagerName;

    // 고객사 담당자 이메일 고객사의 담당자 이메일
    private String cManagerEmail;
    
    // 고객사 담당자 이름
    private String cManagerName;
    
    // 고객사 담당자 연락처
    private String cManagerPhone;
    
    // 상품명
    private String productName;
    
    // 수량
    private int totalCount;
    
}
