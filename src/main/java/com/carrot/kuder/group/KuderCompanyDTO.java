package com.carrot.kuder.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KuderCompanyDTO extends KuderCompanyVO{

	// 회사코드 회사 등록 시 번호
    private int companyId;

    // 회사명 회사명
    private String companyName;
    
}
