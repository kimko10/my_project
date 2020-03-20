package com.carrot.kuder.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrot.common.mapper.ObjectMapper;
import com.carrot.common.service.AbstractService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KuderUserService extends AbstractService {


	@Autowired
	private KuderUserMapper mapper;
	
	@Autowired
	private ObjectMapper omapper;
	
	/**
	 * 사용자정보 조회
	 * 
	 */
	/*
	 * public KuderUserVO selectUser(int id) throws Exception {
	 * 
	 * //KuderUserVO vo = mapper.selectUser(id); KuderUserVO vo =
	 * mapper.selectUser(id);
	 * 
	 * log.info("kuderUserselect : " + vo.toString()); return vo; }
	 */
	
	public Map<Object, Object> selectUser(int idx) {
        String sql  = "select * from kuder_user where user_id=" + String.valueOf(idx) + " limit 1";
        Map<Object, Object> info = omapper.selectOne(sql);

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("err", 0);
        result.put("data", info);

        return result;
    }

	
	/**
	 * 사용자정보 검색조회
	 * 
	 */
	public void selectSearchUser() throws Exception {}
	
	/**
	 * 사용자 tins 조회
	 * 
	 */
	public void selectUserTins() throws Exception {}
	

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
	public String insertUser(KuderUserDTO dto) throws Exception { 
		/*
		PasswordEncoder passwordencoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		dto.setUserPassword(passwordencoder.encode(dto.getUserPassword()));
		*/
		dto.setAuthorityCode("MEMBER");
		KuderUserVO vo = dto;
		
		log.info("#### KuderUserService.insertUser : " + vo.toString());
		
		String result = "";
		
		// 사용자가 없으면 insert 해주고 아래 로직 이어서
		// 여기와서 중복체크 해도 되고 로그인할 때나 결제성공 시 B2B등록시에 중복체크해도 됨
		if (checkUser(dto.getUserEmail())) {
			if(mapper.insertUser(vo) >0) {
				log.info("##### 가입 성공");
				result = "insertuser success";
			} else {
				log.info("##### 가입 실패");
				result = "insertuser fail";
			}
		} else {
			log.info("#### 이미 가입한 사용자 입니다.");
		}
		
		if(!"insertuser fail".equals(result)) {
			// 엑티베이션 코드 올바르게 넣었는지 확인
			if(checkActivationCode(dto.getActivationCode())) {
				result = insertTinNum(dto.getUserEmail(), dto.getActivationCode()); // TIN 넘버 생성
				// result = tin 넘버 넣어줌 
			} else {
				log.info("#### activation code가 올바르지 않습니다.");
				result = "incorrect activation code";
			}
		}
		
		log.info("#### result : " + result);
		return result;
	}
	
	
	/**
	 * activation Code 체크
	 * 
	 */
	public boolean checkActivationCode(String activationCode) {
		
		boolean result = false;
		
		if(mapper.checkActivationCode(activationCode) > 0) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * tin num 등록
	 * 
	 */
	public String insertTinNum(String userEmail, String activationCode) throws Exception {
		
		log.info("#### userService.insertTinNum ");
		// tin table에 등록하고 activation table 수정도 해줘야됨..
		KuderTinDTO dto = new KuderTinDTO();
		dto.setUserEmail(userEmail);
		dto.setActivationCode(activationCode);
		KuderTinVO vo = dto;
		String tin = ""; 
		
		// tin 생성 성공
		if(mapper.insertTinNum(vo) > 0) {
			// activation code 데이터 수정(카운트)
			mapper.updateActivationCodeNum(activationCode);
			log.info("#### tin number : " + dto.getTinNumber());
			tin = dto.getTinNumber();
		} else {
			tin = "insert tin number fail";
		}
		
		
		return tin;
	}
	
	/**
	 * activation코드 등록
	 * 결제로 생성되는 activation은 userType = B2C
	 * B2B등록으로 생성되는 activation은 userType = B2B
	 */
	public String insertActivationCode(String userEmail, String productName, int bbGroupCode, int totalCount) throws Exception { 

		log.info("#### userService.insertActivationCode ");
		
		KuderActivationCodeDTO dto = new KuderActivationCodeDTO();
		String result, userType = "";
		
		dto.setActivationCode(createActivationCode(productName));
		dto.setTotalCount(totalCount);
		dto.setAbleCount(totalCount);
		dto.setUserEmail(userEmail);
		dto.setProductName(productName);
		dto.setBbGroupCode(bbGroupCode);
		if(bbGroupCode != -1) {
			userType = "B2B";
		} else {
			userType = "B2C";
		}
		dto.setUserType(userType);
		
		KuderActivationCodeVO vo = dto;
		
		if(mapper.insertActivationCode(vo) > 0) {
			log.info("##### activation code insert success");
			result = dto.getActivationCode();
		} else {
			log.info("##### activation code insert fail");
			result = "insert activation code fail";
		}
		
		return result;
		
	}
	
	/**
	 * activation code 생성
	 * 성인 J로 시작 중고등 N으로 시작 (영대문자 + 숫자 조합 8자리)
	 */
	public String createActivationCode(String productName) throws Exception { 

		log.info("#### userService.createActivationCode ");
		
		Random rnd = new Random();
		StringBuffer sb = new StringBuffer();
		String type = productName.substring(0, 1);
		
		if(type == "J") {
			sb.append("J"); // 성인 atlas
		} else {
			sb.append("N"); // 중고딩 atlas
		}
		
		for(int i=0; i<7; i++) {
			
			int rIndex = rnd.nextInt(2);
			
			switch(rIndex) {
			
			case 0 :
				// A-Z
				sb.append((char)((int)( rnd.nextInt(26)) + 65));
				break;
			case 1 :
				// 0-9
				sb.append(rnd.nextInt(10));
				break;
			
			}
		}
		log.info("#### activationCode : " + sb);
		
		
		return sb.toString();
	}

	/**
	 * 사용자정보 수정
	 */
	public String updateUser(KuderUserDTO dto) throws Exception {
		
		KuderUserVO vo = dto;
		String result = "";
		
		if(mapper.updateUser(vo) > 0) {
			result = "success";
		} else {
			result = "no data";
		}
		
		return result;
		
	}
		

	/**
	 * 사용자정보 삭제
	 * 
	 */
	// public void deleteUser() throws Exception {}
	
	/**
	 * 현재비밀번호 맞게 넣었는지 체크
	 * 
	 */
	// public void checkUserCurrentPassword() throws Exception {}
	
	/**
	 * 사용자패스워드 수정
	 * 
	 */
	// public void updateUserPassword() throws Exception {}
	
	/**
	 * 사용자패스워드 초기화
	 * 
	 */
	// public void initUserPassword() throws Exception {}
}
