package com.fin.love.respository.member;

public enum Role {
	USER("ROLE_USER", "USER"),
	UNVARIFIED_USER("ROLE_UNVARIFIED_USER", "UNVARIFIED_USER"),
	RIP_USER("ROLE_RIP_USER", "RIP_USER"),
	ADMIN("ROLE_ADMIN", "ADMIN");
	
	private final String key;
	private final String value;
	
	Role(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
}
