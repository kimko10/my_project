package com.carrot.kuder.group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KuderGroupWrapper {

	String activationCode;
	
	KuderGroupDTO group;
	
	KuderCompanyDTO company;
	
	String result;
}
