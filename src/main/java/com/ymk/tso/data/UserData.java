package com.ymk.tso.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserData {
	
	private long id;
	private String username;
	private String fullName;
	private String password;
	private String email;
	private String phone;
	private int dept;
	private short role;
	private short status;

	public UserData() {
		clearProperties();
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public short getRole() {
		return role;
	}

	public void setRole(short role) {
		this.role = role;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	private void clearProperties() {
		this.id = 0L;
		this.username = "";
		this.fullName = "";
		this.password = "";
		this.email = "";
		this.phone = "";
		this.dept = 0;
		this.role = 0;
		this.status = 0;
	}
}
