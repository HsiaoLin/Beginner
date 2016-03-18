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

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * <b>类名称：</b>Base64Util<br/>
 * <b>类描述：</b>Base64加解密工具类<br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class Base64Util extends Base64 {

	private static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * Base64加密(UTF-8)
	 * @param plaintext 	明文
	 * @return 				密文
	 */
	public static String encrypt(String plaintext) {
		return encrypt(plaintext, DEFAULT_ENCODING);
	}

	/**
	 * Base64解密(UTF-8)
	 * @param ciphertext 	密文
	 * @return 				明文
	 */
	public static String decrypt(String ciphertext) {
		return decrypt(ciphertext, DEFAULT_ENCODING);
	}

	/**
	 * Base64加密
	 * @param plaintext 	明文
	 * @param encoding 		编码方式
	 * @return 				密文
	 */
	public static String encrypt(String plaintext, String encoding) {
		if (StringUtils.isBlank(plaintext))
			return plaintext;
		try {
			return new String(encodeBase64(plaintext.getBytes(encoding)));
		} catch (UnsupportedEncodingException e) {
			return plaintext;
		}
	}

	/**
	 * Base64解密
	 * @param ciphertext 	密文
	 * @param encoding 		编码方式
	 * @return 				明文
	 */
	public static String decrypt(String ciphertext, String encoding) {
		if (StringUtils.isBlank(ciphertext))
			return ciphertext;
		try {
			return new String(decodeBase64(ciphertext.getBytes(encoding)));
		} catch (UnsupportedEncodingException e) {
			return ciphertext;
		}
	}
}