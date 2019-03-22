package com.ymk.tso.exception;

/**
 * RoleAuthException class : extends RuntimeException
 * @author yemyokyaw
 *
 */
@SuppressWarnings("serial")
public class RoleAuthException extends RuntimeException {

	public static enum RESOURCE_CANNOT_BE_ACCESSED_REASON {

		AUTH_INVALID, NON_ADMIN, USER_HAS_NO_ACCESS, NO_AUTH;

		public String errorMessage() {
			if (name().toString().equalsIgnoreCase("AUTH_INVALID")) {
				return "User Authentication Invalid";
			}
			if (name().toString().equalsIgnoreCase("NON_ADMIN")) {
				return "No Admin User Access";
			}
			if (name().toString().equalsIgnoreCase("USER_HAS_NO_ACCESS")) {
				return "User has no Access";
			}			
			if (name().toString().equalsIgnoreCase("NO_AUTH")) {
				return "No Authentication";
			}
			return name().toString();
		}

	}

	public RoleAuthException(RESOURCE_CANNOT_BE_ACCESSED_REASON reason) {
		super(String.format(reason.errorMessage()));
	}
}
