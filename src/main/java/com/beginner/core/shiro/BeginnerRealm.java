/*
 * Copyright 2015-9999 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beginner.core.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * <b>类名称：</b>BeginnerRealm<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class BeginnerRealm extends AuthorizingRealm {

	// @Resource
	// private UserService userService;

	/*
	 * 登录信息和用户验证信息验证(non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org
	 * .apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码

		if (null != username && null != password) {
			return new SimpleAuthenticationInfo(username, password, getName());
		} else {
			return null;
		}

	}

	/*
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法(non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
	 * .shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		try {
			/*
			 * Collection<?> collection = pc.fromRealm(getName()); if
			 * (CollectionUtils.isEmpty(collection)) { return null; } // 登陆账户
			 * String username = (String) collection.iterator().next(); //
			 * 根据登陆账户获取用户信息 User user =
			 * userService.getUserRolePermissionByName(username); // 用户角色列表
			 * Set<Role> roles = user.getRoleSet(); List<String> rLst = new
			 * ArrayList<String>(); List<String> pLst = new ArrayList<String>();
			 * if (!CollectionUtils.isEmpty(roles)) { for (Role role : roles)
			 * {// 角色列表 int valid = role.getIsValid(); if (0 == valid) {// 有效的角色
			 * if (!StringUtils.isEmpty(role.getRoleCode()))
			 * rLst.add(role.getRoleCode()); Set<Permissions> permissionSet =
			 * role.getPermissionSet(); if (!CollectionUtils.isEmpty(roles)) {
			 * for (Permissions permissions : permissionSet) {// 权限列表 String
			 * isValid = permissions.getIsValid(); if (null == isValid ||
			 * "0".equals(isValid)) {// 有效的权限 if
			 * (!StringUtils.isEmpty(permissions.getPermCode()))
			 * pLst.add(permissions.getPermCode()); } } } } } }
			 * 
			 * SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			 * info.addRoles(rLst); info.addStringPermissions(pLst); return
			 * info;
			 */
			return new SimpleAuthorizationInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
