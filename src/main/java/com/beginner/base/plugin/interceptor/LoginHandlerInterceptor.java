package com.beginner.base.plugin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.beginner.base.common.Const;
import com.beginner.base.utils.Jurisdiction;
import com.beginner.system.bean.user.User;

/**
 * 
 * 类名称：LoginHandlerInterceptor.java
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
			return true;
		} else {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.USER);
			if (user != null) {
				path = path.substring(1, path.length());
				boolean hasJurisdiction = Jurisdiction.hasJurisdiction(path);
				if (!hasJurisdiction) {
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}
				return hasJurisdiction;
			} else {
				// 登陆过滤
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				return false;
			}
		}
	}

}
