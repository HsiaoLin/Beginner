package com.beginner.system.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 类名称：Role.java 角色的实体类
 * 
 */
public class Role {
	private int roleId; // 角色ID
	private String roleCode; // 角色代码
	private String roleName; // 角色名称
	private String roleRemark; // 角色备注
	private int isValid; // 是否有效
	private int roleType; // 角色类型
	private ArrayList<String> rights;// 该角色所拥有的权限
	private Set<Permissions> permissionSet = new HashSet<Permissions>();// 权限列表

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public ArrayList<String> getRights() {
		return rights;
	}

	public void setRights(ArrayList<String> rights) {
		this.rights = rights;
	}

	public Set<Permissions> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<Permissions> permissionSet) {
		this.permissionSet = permissionSet;
	}
}
