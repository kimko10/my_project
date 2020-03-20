package com.carrot.kuder.user;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KuderUserWrapper {
	
	List<KuderUserVO> users;
	
	KuderUserVO user;
	
	List<KuderTinVO> tins;
	
	String activationCode;
	
	String tin;
	
	String result;
	
}
