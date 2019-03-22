package com.ymk.tso.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Login data model
 * @author yemyokyaw
 *
 */
@XmlRootElement
public class LoginData {

	private long id;
	private String username;
	private String password;
	private String auth;
	private short role;
	
	public LoginData() {
		this.clearProperties();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public short getRole() {
		return role;
	}

	public void setRole(short role) {
		this.role = role;
	}

	/**
	 * clearProperties : set default values
	 */
	public void clearProperties() {
		this.id = 0L;
		this.username = "";
		this.password = "";
		this.auth = "";
		this.role = (short) 0;
	}
}
