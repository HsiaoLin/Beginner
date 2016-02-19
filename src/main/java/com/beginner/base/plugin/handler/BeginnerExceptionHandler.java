package com.beginner.base.plugin.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
* <b>类名称：</b>统一异常处理器<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class BeginnerExceptionHandler implements HandlerExceptionResolver {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		logger.error("==============异常开始=============");
		logger.error("应用程序发生异常", ex);
		logger.error("==============异常结束=============");
		ModelAndView mv = new ModelAndView("500");
		mv.addObject("ex", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}
}
