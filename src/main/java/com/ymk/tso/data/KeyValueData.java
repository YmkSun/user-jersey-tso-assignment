package com.ymk.tso.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * KeyValues class for values of dropdowns and setup forms
 * @author yemyokyaw
 *
 */
@XmlRootElement
public class KeyValueData {
	
	private int key;
	private String value;
	
	public KeyValueData() {
		this.key = 0;
		this.value = "";
	}
	
	public KeyValueData(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
