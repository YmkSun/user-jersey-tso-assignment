package com.ymk.tso.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ymk.tso.DepartmentEnum;
import com.ymk.tso.UserRoleEnum;
import com.ymk.tso.data.KeyValueData;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Utility class to implement necessary features
 * @author yemyokyaw
 *
 */
public class Utility {
	
	/**
	 * getUserRoleList method to get roles from UserRoleEnum
	 * @return
	 */
	public static List<KeyValueData> getUserRoleList() {
		List<KeyValueData> dataList = new ArrayList<>();
		KeyValueData data = null;
		for(UserRoleEnum u: UserRoleEnum.values()) {
			data = new KeyValueData(u.getKey(), u.getValue());
			dataList.add(data);
		}
		return dataList;
	}
	
	/**
	 * getDepartmentList method to get department data from DepartmentEnum
	 * @return
	 */
	public static List<KeyValueData> getDepartmentList() {
		List<KeyValueData> dataList = new ArrayList<>();
		KeyValueData data = null;
		for(DepartmentEnum u: DepartmentEnum.values()) {
			data = new KeyValueData(u.getKey(), u.getValue());
			dataList.add(data);
		}
		return dataList;
	}
	
	/**
	 * generateAuth method to create auth code
	 * @param username
	 * @param password
	 * @return
	 */
	public static String generateAuth(String username, String password) {
		String authString = username + ":" + password;
        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
		return authStringEnc;
	}
	
	/**
	 * decodeAuth method to decode the auth code
	 * @param authString
	 * @return
	 */
	public static String decodeAuth(String authString) {
		byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String decodedAuth = new String(bytes);
        return decodedAuth;
	}
	

}
