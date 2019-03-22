package com.ymk.tso;

/**
 * Department enum
 * @author yemyokyaw
 *
 */
public enum DepartmentEnum {
	
	HEADDEPT(1, "Head Department"), TECHDEPT(2, "Tech Department"), SALEDEPT(3, "Sale Department");
	
	private final Integer key;
	private final String value;
	
	DepartmentEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public static DepartmentEnum fromInt(Integer key) {
		DepartmentEnum dept = null;
		
		switch (key) {
		case 1:
			dept = DepartmentEnum.HEADDEPT;
			break;
		case 2:
			dept = DepartmentEnum.TECHDEPT;
			break;
		case 3:
			dept = DepartmentEnum.SALEDEPT;
			break;
		default:
			dept = DepartmentEnum.HEADDEPT;
			break;
		}
		return dept;
	}
	
	public boolean isHeadDept() {
		return this.value.equals(DepartmentEnum.HEADDEPT.getValue());
	}
	
	public boolean isTechDept() {
		return this.value.equals(DepartmentEnum.TECHDEPT.getValue());
	}
	
	public boolean isSaleDept() {
		return this.value.equals(DepartmentEnum.SALEDEPT.getValue());
	}
}
