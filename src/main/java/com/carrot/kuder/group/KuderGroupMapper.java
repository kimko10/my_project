package com.carrot.kuder.group;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface KuderGroupMapper {
	
	/**
	 * 그룹등록
	 */
	public int insertGroup(KuderGroupVO vo);
	
	/**
	 * company 등록
	 */
	public int insertCompany(KuderCompanyVO vo);
	
	/**
	 * company 중복 체크
	 */
	@Select("SELECT COUNT(*) FROM kuder_company WHERE companyName = #{companyName}")
	public int checkCompany(@Param("companyName") String companyName);

}
