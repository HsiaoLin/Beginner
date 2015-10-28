package com.beginner.system.bean;

/**
 * 
 * 类名称：Permissions.java 权限的实体类
 */
public class Permissions {
	private int permId;
	private String permCode;
	private String permName;
	private String permType;
	private String permRemark;
	private String isValid;

	public int getPermId() {
		return permId;
	}

	public void setPermId(int permId) {
		this.permId = permId;
	}

	public String getPermCode() {
		return permCode;
	}

	public void setPermCode(String permCode) {
		this.permCode = permCode;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public String getPermType() {
		return permType;
	}

	public void setPermType(String permType) {
		this.permType = permType;
	}

	public String getPermRemark() {
		return permRemark;
	}

	public void setPermRemark(String permRemark) {
		this.permRemark = permRemark;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}
