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
public class KuderGroupAdminDTO extends KuderGroupAdminVO{

	// 고객사 담당자 이메일 
    private String adminEmail;

    // 고객사 담당자 idx 
    private int idx;

    // 고객사 담당자 이름 
    private String adminName;

    // 고객사 담당자 연락처 
    private String adminPhone;
    
}
