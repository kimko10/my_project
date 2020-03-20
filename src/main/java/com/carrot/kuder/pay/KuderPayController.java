package com.carrot.kuder.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pay")
@Slf4j
public class KuderPayController {

	@Autowired
	private KuderPayService kuderPaymentService;
	
	/** 
	 * 결제사용자정보 추가 /pay
	 * 결제하고서 로그인 하는 사용자들용 회원가입
	 * @param dto
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="")
	@ApiOperation(value="결제사용자정보 추가")
	public KuderPayWrapper insertUser(@RequestBody KuderPayDTO dto) throws Exception {
		 
		log.info("###### insertUser");
		log.info("#### user Info : " + dto.toString());
		KuderPayWrapper kpw = new KuderPayWrapper();
		String actv = ""; 	// 엑티베이션 코드
		
		// 호출되려나? // 서비스에서 바로 하려나?
		actv = kuderPaymentService.insertUser(dto);
		kpw.setPay(dto);
		kpw.setActivationCode(actv);
		
		return kpw;
		
	}
	
}
