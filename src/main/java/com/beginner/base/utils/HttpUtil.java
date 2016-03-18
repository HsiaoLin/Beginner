/*
 * Copyright 2015-2017 the original author or authors.
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
package com.beginner.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
* <b>类名称：</b>HttpUtil<br/>
* <b>类描述：</b>HTTP请求工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class HttpUtil {

	/**
	 * <p>To request the POST way.</p>
	 * 
	 * @param url		request URI
	 * @param json		request parameter(json format string)
	 * @param timeout	request timeout time in milliseconds(The default timeout time for 30 seconds.)
	 * @return String	response result
	 * @throws Exception
	 * @since 1.0.0
	 */
	public static String post(String url, String json, Integer timeout) throws Exception {

		// Validates input
		if (StringUtils.isBlank(url))
			throw new IllegalArgumentException("The url cannot be null and cannot be empty.");

		//The default timeout time for 30 seconds
		if (null == timeout)
			timeout = 30000;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse httpResponse = null;
		String result = null;

		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();

			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Content-Type", "text/plain");
			httpPost.setEntity(new StringEntity(json, "UTF-8"));

			httpResponse = httpClient.execute(httpPost);

			HttpEntity entity = httpResponse.getEntity();

			result = EntityUtils.toString(entity);

			EntityUtils.consume(entity);
		} catch (Exception e) {
			throw new Exception();
		} finally {
			if (null != httpResponse)
				httpResponse.close();
			if (null != httpClient)
				httpClient.close();
		}
		return result;
	}

	/**
	 * <p>To request the GET way.</p>
	 * 
	 * @param url		request URI
	 * @param timeout	request timeout time in milliseconds(The default timeout time for 30 seconds.)
	 * @return String	response result
	 * @throws Exception
	 * @since 1.0.0
	 */
	public static String get(String url, Integer timeout) throws Exception {

		// Validates input
		if (StringUtils.isBlank(url))
			throw new IllegalArgumentException("The url cannot be null and cannot be empty.");

		//The default timeout time for 30 seconds
		if (null == timeout)
			timeout = 30000;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse httpResponse = null;
		String result = null;

		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();

			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);

			httpResponse = httpClient.execute(httpGet);

			HttpEntity entity = httpResponse.getEntity();
			result = EntityUtils.toString(entity);

			EntityUtils.consume(entity);
		} catch (Exception e) {
			throw new Exception();
		} finally {
			if (null != httpResponse)
				httpResponse.close();
			if (null != httpClient)
				httpClient.close();
		}
		return result;
	}
}
