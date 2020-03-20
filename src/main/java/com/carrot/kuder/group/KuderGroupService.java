package com.carrot.kuder.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrot.kuder.user.KuderUserDTO;
import com.carrot.kuder.user.KuderUserMapper;
import com.carrot.kuder.user.KuderUserService;
import com.carrot.kuder.user.KuderUserVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KuderGroupService {

	@Autowired
	private KuderUserService kuderUserService;
	
	@Autowired
	private KuderUserMapper kuderUserMapper;
	
	@Autowired
	private KuderGroupMapper kuderGroupMapper;
	
	/**
	 * 그룹등록
	 * 
	 */
	public String insertGroup(KuderGroupDTO gdto, KuderCompanyDTO cdto) throws Exception {
		
		// 그룹 등록 성공시
		// 회원가입 진행 있으면 회원가입 없으면
		// 엑티베이션 코드 등록
		KuderGroupVO gvo = gdto;
		String result = "";
		
		// company가 중복인지 체크 후 없으면 company table에 넣음
		if(checkCompany(cdto)) {
			if(insertCompany(cdto) > 0) {
				log.info("##### insertcompany success");
			} else {
				log.info("##### insertcompany fail");
				result = "insertcompany fail";
			}
		}
		
		if(!"insertcompany fail".equals(result)) {
			if(kuderGroupMapper.insertGroup(gvo) > 0) {
				// 노출직업 테이블에 넣는 것도 추가 해줘야됨..
				log.info("#### insertgroup success");
			} else {
				log.info("#### insertgroup fail");
				result = "insertgroup fail";
			}
		
			if(!"insertgroup fail".equals(result)) {
				// 고객사 담당자 정보 넣기
				result = insertUser(gdto);
			}
		}
		 
		return result;
	}
	
	/**
	 * company 등록
	 * 
	 */
	public int insertCompany(KuderCompanyDTO dto) {
		
		KuderCompanyVO vo = dto;
		return kuderGroupMapper.insertCompany(vo);
		
	}
	
	/**
	 * company 중복체크
	 */
	public boolean checkCompany(KuderCompanyDTO dto) {
		
		boolean result = true;
		
		if(kuderGroupMapper.checkCompany(dto.getCompanyName()) > 0) {
			result = false;
		} 
		
		return result;
	}
	
	/**
	 * 사용자정보 추가
	 * 그룹등록 후 회원가입 처리
	 * 회원인지 확인 후 가입처리 하고 난 다음에 엑티베이션 코드 등록
	 * 
	 */
	public String insertUser(KuderGroupDTO dto) throws Exception {
		
		// 그룹 등록 성공시
		// 회원가입 진행
		// 엑티베이션 코드 등록
		KuderUserDTO udto = new KuderUserDTO();
		String result = "";
		
		// 이미 가입한 회원인지 체크
		if(kuderUserService.checkUser(dto.getCManagerEmail())) {
			
			// customer manager info
			udto.setUserEmail(dto.getCManagerEmail());
			udto.setAuthorityCode("MANAGER");
			udto.setUserName(dto.getCManagerName());
			udto.setUserPhone(dto.getCManagerPhone());
			udto.setUserPassword("carrot"); // B2B등록 시 고객사 담당자 초기비밀번호
			KuderUserVO vo = udto;
			
			if(kuderUserMapper.insertUser(vo) >0) {
				result = "insertuser success";			// 사용자 등록 성공
			} else {
				result = "insertuser fail";					// 사용자 등록 실패
			}
			
		} else {
			log.info("##### 이미 가입한 사용자 입니다.");
		}
		
		// 사용자 등록 성공했거나 이미 등록한 사용자일 경우
		if(!"insertuser fail".equals(result)) {
			// 엑티베이션 코드 등록
			result = kuderUserService.insertActivationCode(dto.getCManagerEmail(), dto.getProductName(), dto.getBbGroupCode(), dto.getTotalCount());
		}
		
		return result;
		
	}
	
}
