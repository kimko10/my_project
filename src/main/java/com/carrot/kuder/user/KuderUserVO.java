package com.carrot.kuder.user;

import com.carrot.common.vo.CommonVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KuderUserVO extends CommonVO {
	
	// 이메일 사용자 이메일
    private String userEmail;

    // 패스워드 사용자 비밀번호
    private String userPassword;

    // 이름 사용자 이름
    private String userName;

    // 연락처 사용자 연락처
    private String userPhone;

    // 사용자ID 사용자 코드(자동증가)
    private int userId;

    // 권한코드 ROLE_B2B_ADMIN/ROLE_B2C_ADMIN/ROLE_ADMIN/ROLE_USER
    private String authorityCode;
    
}
