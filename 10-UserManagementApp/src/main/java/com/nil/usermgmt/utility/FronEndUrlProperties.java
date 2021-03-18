package com.nil.usermgmt.utility;

import org.springframework.stereotype.Component;

@Component
public class FronEndUrlProperties {
	private String frontEndHost;

	public String getFrontEndHost() {
		return frontEndHost;
	}

	public void setFrontEndHost(String frontEndHost) {
		this.frontEndHost = frontEndHost;
	}

}
