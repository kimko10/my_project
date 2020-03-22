package com.carrot.kuder.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

//@RestController
@RequestMapping("/group")
@Slf4j
public class KuderGroupController {

	@Autowired
	private KuderGroupService kuderGroupService;
	
	/** 
	 * 그룹등록시 /group
	 * 그룹등록 시 입력된 정보로 회원가입(고객사 담당자)
	 * @param dto
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="")
	@ApiOperation(value="그룹정보 추가")
	public KuderGroupWrapper insertGroup(@RequestBody KuderGroupDTO dto) throws Exception {
		 
		log.info("###### insertGroup");
		log.info("#### group info : " + dto.toString());
		KuderGroupWrapper kpw = new KuderGroupWrapper();
		
		String actv = ""; 	// 엑티베이션 코드
		
		// 호출되려나? // 서비스에서 바로 하려나?
		// company 부터 중복되는지 체크
		actv = kuderGroupService.insertGroup(dto);
		kpw.setActivationCode(actv);
		// company 정보
		//kpw.setCompany(dto.getCompanyName());
		kpw.setGroup(dto);
		
		return kpw;
		
	}
	
}
