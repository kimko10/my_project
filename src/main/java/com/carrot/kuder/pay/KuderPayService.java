package com.carrot.kuder.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrot.common.service.AbstractService;
import com.carrot.kuder.user.KuderUserDTO;
import com.carrot.kuder.user.KuderUserMapper;
import com.carrot.kuder.user.KuderUserService;
import com.carrot.kuder.user.KuderUserVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KuderPayService extends AbstractService {

	@Autowired
	private KuderUserService kuderUserService;
	
	@Autowired
	private KuderUserMapper kuderUserMapper;
	
	/**
	 * 사용자정보 추가
	 * 결제 후 회원가입 처리
	 * 회원인지 확인 후 가입처리 하고 난 다음에 엑티베이션 코드 등록
	 * 
	 */
	public String insertUser(KuderPayDTO dto) throws Exception {
		
		// 결제 테이블에 등록 성공시
		// 회원가입 진행
		// 엑티베이션 코드 등록
		KuderUserDTO userdto = new KuderUserDTO();
		String actv = "";
		
		// 이미 가입한 회원인지 체크
		if(kuderUserService.checkUser(dto.getUserEmail())) {
			
			userdto.setUserEmail(dto.getUserEmail());
			userdto.setUserName(dto.getUserName());
			userdto.setUserPhone(dto.getUserPhone());
			userdto.setUserPassword(dto.getUserPassword());
			
			kuderUserService.insertUser(userdto);
			log.info("#### 결제자 회원가입");
			
		} else {
			log.info("##### 이미 가입한 사용자 입니다.");
		}
		
		// 사용자 등록 성공했거나 이미 등록한 사용자일 경우
		// 엑티베이션 코드 등록
		actv = kuderUserService.insertActivationCode(dto.getUserEmail(), dto.getProductName(), -1, dto.getTotalCount());
		log.info("#### activation code : " + actv);
		
		return actv;
		
	}
}
