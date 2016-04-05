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
package com.beginner.core.utils;

import java.security.NoSuchAlgorithmException;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * <b>类名称：</b>DruidUtil<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
 * <b>创建时间：</b>2016-2-27 下午4:34:49<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DruidUtil {

	public static void main(String[] args) {

		//密码明文        
		String password = "beginner";
		System.out.println("密码[ " + password + " ]的加密信息如下：\n");
		String[] keyPair;
		try {
			keyPair = ConfigTools.genKeyPair(512);
			//私钥
			String privateKey = keyPair[0];
			//公钥
			String publicKey = keyPair[1];
			//用私钥加密后的密文
			password = ConfigTools.encrypt(privateKey, password);
			System.out.println("privateKey:" + privateKey);
			System.out.println("publicKey:" + publicKey);
			System.out.println("password:" + password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
