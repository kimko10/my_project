package com.carrot.kuder.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.carrot.kuder.user.KuderActivationCodeVO;
import com.carrot.kuder.user.KuderUserDTO;
import com.carrot.kuder.user.KuderUserService;
import com.carrot.kuder.user.KuderUserVO;
import com.carrot.kuder.user.KuderUserWrapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class KuderLoginController {

	@Autowired
	private KuderUserService kuderUserService;
	
	// login
	@PostMapping("/login")
	public KuderUserWrapper login(@RequestBody KuderUserDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("login 시도");
		
		KuderUserVO vo = new KuderUserVO();
		KuderUserWrapper wrap = new KuderUserWrapper();
		boolean result = false;
		int idx = 0;
		String tin = "";
		HttpSession session = request.getSession();
		
		/*
		// 1. activationcode 체크
		KuderActivationCodeVO avo = kuderUserService.selectActivationCode(dto.getActivationCode());
		
		if(avo != null) {
			// 2. 회원인지 체크
			if(kuderUserService.checkUser(dto.getUserEmail())) {
				idx = kuderUserService.insertUser(dto);
			} else {
				// 3. ID, PWD 체크
				log.info("#### 이미 가입한 사용자 입니다.");
				vo = kuderUserService.selectUser(dto);
				idx = vo.getIdx();
				result = kuderUserService.checkUserCurrentPassword(dto.getUserPassword(), vo.getUserPassword());
			}
			
			wrap.setUser(dto);
			
			if(result) {
				// 4. TIN번호 생성
				wrap.setTin(kuderUserService.insertTinNum(idx, avo.getIdx()));
			} else {
				log.info("user password uncorrect");
				wrap.setResult("user password uncorrect");
			}
			
		} else {
			log.info("activation code uncorrect");
			wrap.setResult("activation code uncorrect");
		}
		session.setAttribute("user", dto);
		session.setAttribute("tin", wrap.getTin());
		 */
		session.setAttribute("test", "뭐야");
		return wrap;
	}
	
	// logout
	@GetMapping("/logout")
	public void logout(HttpServletRequest request) throws Exception {
		
		log.info("logout 시도");
		HttpSession session = request.getSession();
		
		session.invalidate();
	}
}
