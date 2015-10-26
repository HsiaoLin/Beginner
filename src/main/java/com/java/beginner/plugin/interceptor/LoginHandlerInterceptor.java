package com.java.beginner.plugin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.java.beginner.common.Constant;
import com.java.beginner.system.bean.User;
import com.java.beginner.utils.Jurisdiction;

/**
 * 
 * 类名称：LoginHandlerInterceptor.java
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(Constant.NO_INTERCEPTOR_PATH)) {
			return true;
		} else {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Constant.USER);
			if (user != null) {
				path = path.substring(1, path.length());
				boolean hasJurisdiction = Jurisdiction.hasJurisdiction(path);
				if (!hasJurisdiction) {
					response.sendRedirect(request.getContextPath() + Constant.LOGIN);
				}
				return hasJurisdiction;
			} else {
				// 登陆过滤
				response.sendRedirect(request.getContextPath() + Constant.LOGIN);
				return false;
			}
		}
	}

}
