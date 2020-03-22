package com.carrot.kuder.group;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrot.kuder.user.KuderUserDTO;
import com.carrot.kuder.user.KuderUserMapper;
import com.carrot.kuder.user.KuderUserService;
import com.carrot.kuder.user.KuderUserVO;

import lombok.extern.slf4j.Slf4j;

//@Service
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
	public String insertGroup(KuderGroupDTO gdto) throws Exception {
		
		// 그룹 등록 성공시
		// 회원가입 진행 있으면 회원가입 없으면
		// 엑티베이션 코드 등록
		KuderGroupVO gvo = gdto;
		String result = "";
		int uidx = 0;
		KuderCompanyDTO cdto = new KuderCompanyDTO();
		
		cdto.setCompanyAddress(gdto.getCompanyAddress());
		cdto.setCompanyName(gdto.getCompanyName());
		cdto.setCompanySite(gdto.getCompanySite());
		
		KuderCompanyVO cvo = selectCompany(cdto);
		// company가 중복인지 체크 후 없으면 company table에 넣음
		if(cvo == null) {
			cvo = insertCompany(cdto);
		}
		
		// 고객사 담당자 정보 넣기
		uidx = insertUser(gdto);
		
		if(kuderGroupMapper.insertGroup(gvo) > 0) {
			// 노출직업 테이블에 넣는 것도 추가 해줘야됨..
			log.info("#### insertgroup success");
		} else {
			log.info("#### insertgroup fail");
			result = "insertgroup fail";
		}
		
		// 사용자 등록 성공했거나 이미 등록한 사용자일 경우
		// 엑티베이션 코드 등록
		result = kuderUserService.insertActivationCode(dto.getCManagerEmail(), dto.getProductName(), dto.getBbGroupCode(), dto.getTotalCount());
				
		 
		return result;
	}
	
	/**
	 * company 등록
	 * 
	 */
	public KuderCompanyVO insertCompany(KuderCompanyDTO dto) {
		
		KuderCompanyVO vo = dto;
		return kuderGroupMapper.insertCompany(vo);
		
	}
	
	/**
	 * company 중복체크
	 */
	public KuderCompanyVO selectCompany(KuderCompanyDTO dto) {
		
		KuderCompanyVO vo = kuderGroupMapper.selectCompany(dto.getCompanyName());
		
		return vo;
	}
	
	/**
	 * 사용자정보 추가
	 * 그룹등록 후 회원가입 처리
	 * 회원인지 확인 후 가입처리 하고 난 다음에 엑티베이션 코드 등록
	 * 
	 */
	public int insertUser(KuderGroupDTO dto) throws Exception {
		
		// 그룹 등록 성공시
		// 회원가입 진행
		// 엑티베이션 코드 등록
		KuderUserDTO udto = new KuderUserDTO();
		KuderUserVO uvo = new KuderUserVO();
		int uidx = 0;
		// 이미 가입한 회원인지 체크
		if(checkUser(dto.getCManagerEmail())) {
			
			// customer manager info
			udto.setUserEmail(dto.getCManagerEmail());
			udto.setUserName(dto.getCManagerName());
			udto.setUserPhone(dto.getCManagerPhone());
			// B2B등록 시 고객사 담당자 초기비밀번호
			udto.setUserPassword("carrot");
			
			uidx = kuderUserService.insertUser(udto);
			
		} else {
			uvo = selectUser(udto);
			uidx = uvo.getIdx();
			log.info("##### 이미 가입한 사용자 입니다.");
		}
		// 담당자 테이블에 넣어주기
		
		
		return uidx;
		
	}
	
	/**
	 * 사용자정보 중복체크
	 * 
	 */
	public boolean checkUser(String userEmail) throws Exception {
		int count = mapper.checkUser(userEmail);
		boolean result = true;
		
		if(count > 0) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 사용자정보 추가
	 * 
	 */
	public int insertUser(KuderUserDTO dto) throws Exception { 
		
		// 비밀번호 암호화
		dto.setUserPassword(BCrypt.hashpw(dto.getUserPassword(), BCrypt.gensalt()));
		KuderUserVO vo = dto;
		
		if(mapper.insertUser(vo) >0) {
			log.info("##### 가입 성공");
		} else {
			log.info("##### 가입 실패");
		}
		
		log.info("#### KuderUserService.insertUser : " + vo.toString());
		
		return vo.getIdx();
	}
	
}
