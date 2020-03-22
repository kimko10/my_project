package com.carrot.kuder.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KuderUserMapper {
	
	/**
	 * 사용자정보 조회
	 * 
	 */
	public KuderUserVO selectUser(KuderUserVO vo);
	
	/**
	 * 사용자 tin 조회
	 * 
	 */
	//public void selectUserTin();
	
	/**
	 * 사용자정보 중복체크
	 * count
	 */
	public int checkUser(String userEmail);
	
	/**
	 * 사용자정보 추가
	 * 
	 */
	public int insertUser(KuderUserVO vo);
	
	/**
	 * tin코드 등록
	 * 
	 */
	public int insertTinNum(KuderTinVO vo);
	
	/**
	 * activation코드 등록
	 * 성인 J로 시작 중고등 N으로 시작 (영대문자 + 숫자 조합 8자리)
	 */
	public int insertActivationCode(KuderActivationCodeVO vo);
		
	/**
	 * activation코드 체크
	 */
	public KuderActivationCodeVO selectActivationCode(String activationCode);
	
	/**
	 * activation 코드 수량 수정
	 */
	public int updateActivationCodeNum(int idx);
	
	
	/**
	 * 사용자정보 수정
	 */
	public int updateUser(KuderUserVO vo);
		

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
