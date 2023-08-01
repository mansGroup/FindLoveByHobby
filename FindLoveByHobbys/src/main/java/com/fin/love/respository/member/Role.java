package com.fin.love.respository.member;

public enum Role {
	USER("ROLE_USER", "USER"),
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
