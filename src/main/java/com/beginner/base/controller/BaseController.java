/**
 * <b>项目名：</b>不忘初心方得始终<br/>
 * <b>包名：</b>com.beginner.base<br/>
 * <b>文件名：</b>BaseController.java<br/>
 * <b>版本信息：</b><br/>
 * <b>日期：</b>2015年10月26日-下午3:24:40<br/>
 * <b>Copyright (c)</b> 2015尹枭凌工作室-版权所有<br/>
 */
package com.beginner.base.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.beginner.common.Constant;
import com.beginner.plugin.page.Page;
import com.beginner.plugin.page.PageData;
import com.beginner.utils.Logger;
import com.beginner.utils.UUIDUtils;

/**
 * <b>类名称：</b>BaseController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
 * <b>创建时间：</b>2015年10月26日 下午3:24:40<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	* getPageData(方法描述：获取PageData) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* PageData
	* @exception
	* @since  1.0.0
	*/
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	* getModelAndView(方法描述：获取ModelAndView) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* ModelAndView
	* @exception
	* @since  1.0.0
	*/
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	* getRequest(方法描述：取得request对象) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* HttpServletRequest
	* @exception
	* @since  1.0.0
	*/
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public String getRequestBody() throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (request == null)
			return "";
		BufferedReader reader = request.getReader();
		StringBuilder inputStringBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			inputStringBuilder.append(line);
		}
		return inputStringBuilder.toString();
	}

	/**
	 * get32UUID(方法描述：获取32位UUID不带减号) <br />
	 * (方法适用条件描述： – 可选)
	 * @return
	 * String
	 * @exception
	 * @since  1.0.0
	 */
	public String get32UUID() {
		return UUIDUtils.get32UUID();
	}

	/**
	 * getUUID(方法描述：获取原始UUID带减号) <br />
	 * (方法适用条件描述： – 可选)
	 * @return
	 * String
	 * @exception
	 * @since  1.0.0
	 */
	public String getUUID() {
		return UUIDUtils.getUUID();
	}

	/**
	* getPage(方法描述：获取分页列表的信息 ) <br />
	* (方法适用条件描述： – 可选)
	* @return
	* Page
	* @exception
	* @since  1.0.0
	*/
	public Page getPage() {
		return new Page();
	}

	/**
	* before(方法描述：方法开始的Log日志) <br />
	* (方法适用条件描述： – 可选)
	* @param logger
	* @param interfaceName
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void before(Logger logger, String interfaceName) {
		logger.info("");
		logger.info(Constant.START_CN);
		logger.info(interfaceName);
	}

	/**
	* after(方法描述：方法结束的Log日志) <br />
	* (方法适用条件描述： – 可选)
	* @param logger
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void after(Logger logger) {
		logger.info(Constant.END_CN);
		logger.info(StringUtils.EMPTY);
	}
}
