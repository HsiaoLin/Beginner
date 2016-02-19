/**
 * <b>项目名：</b>勿忘初心方得始终<br/>
 * <b>包名：</b>com.beginner.base<br/>
 * <b>文件名：</b>BaseController.java<br/>
 * <b>版本信息：</b><br/>
 * <b>日期：</b>2015年10月26日-下午3:24:40<br/>
 * <b>Copyright (c)</b> 2015-2016 Hsiao Lin Studio-版权所有<br/>
 */
package com.beginner.base.controller;

import java.beans.PropertyEditorSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.beginner.base.common.Const;
import com.beginner.base.plugin.page.Page;
import com.beginner.base.plugin.page.PageData;
import com.beginner.base.utils.DateUtil;
import com.beginner.base.utils.UUIDUtil;

/**
 * <b>类名称：</b>BaseController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>创建时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class BaseController {

	/**
	 * getPageData(方法描述：获取PageData) <br />
	 * (方法适用条件描述： – 可选)
	 * @return
	 * PageData
	 * @exception
	 * @since 1.0.0
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
	 * @since 1.0.0
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
	 * @since 1.0.0
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public String getRequestBody() throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
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
	 * @since 1.0.0
	 */
	public String get32UUID() {
		return UUIDUtil.get32UUID();
	}

	/**
	 * getUUID(方法描述：获取原始UUID带减号) <br />
	 * (方法适用条件描述： – 可选)
	 * @return
	 * String
	 * @exception
	 * @since 1.0.0
	 */
	public String get36UUID() {
		return UUIDUtil.get36UUID();
	}

	/**
	 * getPage(方法描述：获取分页列表的信息 ) <br />
	 * (方法适用条件描述： – 可选)
	 * @return
	 * Page
	 * @exception
	 * @since 1.0.0
	 */
	public Page getPage() {
		return new Page();
	}

	/**
	 * getRights(方法描述：权限验证) <br />
	 * (方法适用条件描述： – 可选)
	 * @return
	 * Map<String,String>
	 * @exception
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getRights() {
		Subject currentUser = SecurityUtils.getSubject(); //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>) session.getAttribute(Const.ROLE_RIGHTS);
	}

	/**
	 * htmlEscape(方法描述：HTML转义-不会转义中文) <br />
	 * (方法适用条件描述： – 可选)
	 * @param text
	 * @return
	 * String
	 * @exception
	 * @since 1.0.0
	 */
	public String htmlEscape(String text) {
		return HtmlUtils.htmlEscape(text);
	}

	/**
	 * htmlUnescape(方法描述：HTML转义复原) <br />
	 * (方法适用条件描述： – 可选)
	 * @param text
	 * @return
	 * String
	 * @exception
	 * @since 1.0.0
	 */
	public String htmlUnescape(String text) {
		return HtmlUtils.htmlUnescape(text);
	}

	/**
	 * initBinder(方法描述：日期、字符串和Javabean中的日期类型的属性自动转换) <br />
	 * (方法适用条件描述：支持的日期格式详见com.beginner.utils.DateUtil)
	 * @param binder
	 * void
	 * @exception
	 * @since 1.0.0
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

			@Override
			public void setAsText(String text) {
				setValue(DateUtil.parseDate(text));
			}
		});
		//下面一段代码将对所有传入的String类型进行HTML编码用来防止XSS攻击-按需放入需要防止攻击的Controller
		/*binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});*/
	}
}
