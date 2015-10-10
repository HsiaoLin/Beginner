package com.ran.entity.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 类名称：User.java
 * 类描述： 用户实体类（票代、自身、99）
 */
public class User {

	private int userId; //  用户ID

	private String userType; //  用户类型

	private String name; //  姓名

	private String userName; //  登陆用户名

	private String userPassword; //  用户密码

	private String userPhone; //  个人电话

	private String userMail; //  用户邮箱

	private String userStatus; //  用户状态

	private int buyerId; //  采购商ID

	private int supplierId; //  提供商ID

	private int parentId; //  企业管理员ID  

	private String lastLogin; //  上次登录时间

	private String ip; //  用户登录的ip

	private String userSkin = "default"; //  用户皮肤

	private ArrayList<Role> roleList; //  用户角色列表

	private Set<Role> roleSet = new HashSet<Role>();//  用户角色列表

	private boolean hasUser = false;

	public String getUserSkin() {
		return userSkin;
	}

	public void setUserSkin(String userSkin) {
		this.userSkin = userSkin;
	}

	public ArrayList<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(ArrayList<Role> roleList) {
		this.roleList = roleList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public boolean isHasUser() {
		return hasUser;
	}

	public void setHasUser(boolean hasUser) {
		this.hasUser = hasUser;
	}
}
