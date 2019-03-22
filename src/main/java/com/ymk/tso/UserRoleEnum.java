package com.ymk.tso;

/**
 * UserRole enum
 * @author yemyokyaw
 *
 */
public enum UserRoleEnum {

	ADMIN(1, "Admin"), MANAGER(2, "Manager"), USER(3, "User");

	private final Integer key;
	private final String value;

	UserRoleEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static UserRoleEnum fromInt(Integer key) {
		UserRoleEnum userRole = null;

		switch (key) {
		case 1:
			userRole = UserRoleEnum.ADMIN;
			break;
		case 2:
			userRole = UserRoleEnum.MANAGER;
			break;
		case 3:
			userRole = UserRoleEnum.USER;
			break;
		default:
			userRole = UserRoleEnum.USER;
			break;
		}

		return userRole;
	}

	public boolean isAdmin() {
		return this.value.equals(UserRoleEnum.ADMIN.getValue());
	}

	public boolean isManager() {
		return this.value.equals(UserRoleEnum.MANAGER.getValue());
	}

	public boolean isUser() {
		return this.value.equals(UserRoleEnum.USER.getValue());
	}
}
