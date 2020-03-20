package com.carrot.kuder.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 회원가입 처리
 * @author D83
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class KuderUserController {
	
	@Autowired
	private KuderUserService kuderUserService;
	
	// 사용자정보 조회 /user/{id}
	/*
	 * @GetMapping(value="/{id}")
	 * 
	 * @ApiOperation(value="사용자정보 조회") public KuderUserWrapper
	 * selectUser(@PathVariable("id") int id) throws Exception {
	 * 
	 * log.info("###### userSelect id : " + id); KuderUserWrapper kuw = new
	 * KuderUserWrapper(); kuw.setUser(kuderUserService.selectUser(id));
	 * 
	 * return kuw; }
	 */
	
	@GetMapping(value="/info/{idx}")
	    public ResponseEntity<Map<Object, Object>> dataone(@PathVariable("idx") int idx) {
	        ResponseEntity<Map<Object, Object>> rtn = new ResponseEntity<Map<Object, Object>>(kuderUserService.selectUser(idx), HttpStatus.OK);
	        return rtn;
	    }

	 
	
	// 사용자정보 검색조회 /user/search
	// B2B쪽으로 가야 할 것 같기도...
	// 페이징 처리 등의 값이 필요 할 때 @RequestParam
	@GetMapping(value="/search")
	@ApiOperation(value="사용자정보 검색조회")
	public void selectSearchUser(@RequestParam int page1, @RequestParam int page2) throws Exception {
		
		log.info("###### selectSearchUser");
		log.info("###### selectSearchUser page1 : "  + page1 + " page2 : " + page2);
		
		
	}
	
	// 만약에 TIN 넘버 조회면 /user/{id}/tin
	@GetMapping(value="/{id}/tin")
	@ApiOperation(value="사용자 TIN'S 조회")
	public void selectUserTins(@PathVariable("id") int id) throws Exception {
		
		log.info("###### selectUserTin");
	}
	
	/** 
	 * 사용자정보 추가 /user
	 * activation code를 치고 로그인 하는 사용자들용 회원가입
	 * @param dto
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="")
	@ApiOperation(value="사용자정보 추가")
	public KuderUserWrapper insertUser(@RequestBody KuderUserDTO dto) throws Exception {
		 
		log.info("###### insertUser");
		log.info("#### user Info : " + dto.toString());
		KuderUserWrapper kuw = new KuderUserWrapper();
		String tin = "";
		
		tin = kuderUserService.insertUser(dto);
		
		kuw.setUser(dto);
		kuw.setTin(tin);
		
		return kuw;
		
	}
	
	//@PatchMapping
	// 사용자정보 수정 /user/{id} -> pathch
	@PutMapping(value="/{id}")
	@ApiOperation(value="사용자정보 수정")
	public KuderUserWrapper updateUser(@PathVariable("id") int id, @RequestBody KuderUserDTO dto) throws Exception {
		
		log.info("###### updateUser");
		log.info("#### user Info : " + dto.toString());
		
		KuderUserWrapper kuw = new KuderUserWrapper();
		String result = "";
		dto.setUserId(id);
		result = kuderUserService.updateUser(dto);
		
		kuw.setUser(dto);
		kuw.setResult(result);
		
		return kuw;
		
	}
	
	// 사용자정보 삭제 /user/{id}
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="사용자정보 삭제")
	public void deleteUser() throws Exception {
		
		log.info("###### deleteUser");
		
	}
	
	// 사용자 패스워드 수정 /user/{id}/password
	@PutMapping(value="/{id}/password")
	@ApiOperation(value="사용자패스워드 수정")
	public void updateUserPassword() throws Exception {
		
		log.info("###### updateUserPassword");
		
	}
	
	// 사용자 패스워드 초기화 /user/{id}/password-init
	@PutMapping(value="/{id}/password-init")
	@ApiOperation(value="사용자패스워드 초기화")
	public void initUserPassword() throws Exception {
		
		log.info("###### initUserPassword");
		
	}
	
	
}
