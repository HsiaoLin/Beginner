/**
 * <b>项目名：</b>不忘初心方得始终<br/>
 * <b>包名：</b>com.beginner.base.utils<br/>
 * <b>文件名：</b>DruidUtil.java<br/>
 * <b>版本信息：</b><br/>
 * <b>日期：</b>2016-2-27-下午4:34:49<br/>
 * <b>Copyright (c)</b> 2016尹枭凌工作室-版权所有<br/>
 */
package com.beginner.base.utils;

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
