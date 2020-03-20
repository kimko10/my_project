package com.carrot.kuder.pay;

import com.carrot.kuder.user.KuderUserVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KuderPayWrapper {
	
	String result;
	
	String activationCode;
	
	//KuderUserVO user;
	
	KuderPayVO pay;
	
}
