package com.carrot.kuder.user;

import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;
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
	  public KuderUserVO selectUser(KuderUserDTO dto) throws Exception {
	  
		  KuderUserVO vo = dto; 
		  vo = mapper.selectUser(vo);
		  
		  log.info("kuderUserselect : " + vo.toString()); 
		  return vo;
	  }
	
	/*
	 * public Map<Object, Object> selectUser(int idx) { String sql =
	 * "select * from kuder_user where user_id=" + String.valueOf(idx) + " limit 1";
	 * Map<Object, Object> info = omapper.selectOne(sql);
	 * 
	 * Map<Object, Object> result = new HashMap<Object, Object>(); result.put("err",
	 * 0); result.put("data", info);
	 * 
	 * return result; }
	 */

	
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
	
	/**
	 * activation Code 체크
	 * 
	 */
	public KuderActivationCodeVO selectActivationCode(String activationCode) {
		
		
		return mapper.selectActivationCode(activationCode);
	}
	
	/**
	 * tin num 등록
	 * 
	 */
	public String insertTinNum(int userIdx, int activatinoCodeIdx) throws Exception {
		
		log.info("#### userService.insertTinNum ");
		// tin table에 등록하고 activation table 수정도 해줘야됨..
		KuderTinDTO dto = new KuderTinDTO();
		
		dto.setIdxKuderUser(userIdx);
		dto.setIdxKuderActivationCode(activatinoCodeIdx);
		KuderTinVO vo = dto;
		String tin = ""; 
		
		// tin 생성 성공
		if(mapper.insertTinNum(vo) > 0) {
			// activation code 데이터 수정(카운트)
			mapper.updateActivationCodeNum(activatinoCodeIdx);
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
	public String insertActivationCode(String userEmail, String productName, int idxKuderBbGroup, int totalCount) throws Exception { 

		log.info("#### userService.insertActivationCode ");
		
		KuderActivationCodeDTO dto = new KuderActivationCodeDTO();
		String result, userType = "";
		
		dto.setActivationCode(createActivationCode(productName));
		dto.setTotalCount(totalCount);
		dto.setAbleCount(totalCount);
		dto.setUserEmail(userEmail);
		dto.setProductName(productName);
		dto.setIdxKuderBbGroup(idxKuderBbGroup);
		if(idxKuderBbGroup != -1) {
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
	 * BCrypt.checkpw(password, hashed);
	 * hashed 는 DB에 저장된 비밀번호
	 */
	public boolean checkUserCurrentPassword(String password, String hashPassword) throws Exception {
		
		return BCrypt.checkpw(password, hashPassword);
		
	}
	
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
