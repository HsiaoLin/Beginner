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
package com.beginner.module.api.wechat;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* <b>类名称：</b>WeChatCtroller<br/>
* <b>类描述：</b>微信接口API<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@RestController
@RequestMapping(value = "/api/wechat")
public class WeChatCtroller {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 微信红包
	 */
	@RequestMapping(value = "redPacket", method = RequestMethod.POST)
	public JSONObject weChatRedPacket(HttpServletRequest request, @RequestBody(required = false) String param) {
		return new JSONObject().element("status", "0").element("msg", "恭喜发财红包拿来");
	}
}
