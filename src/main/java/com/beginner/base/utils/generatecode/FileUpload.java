package com.beginner.base.utils.generatecode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
* <b>类名称：</b>文件上传工具类<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月22日 下午5:17:31<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class FileUpload {

	/**
	 * 文件上传
	 * @param file 		文件对象
	 * @param filePath 	上传路径
	 * @param fileName 	文件名
	 * @return String 文件名
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName) {
		String extName = "";
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName + extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName + extName;
	}

	/**
	 * 写文件到当前目录的upload目录中
	 * @param in 			输入流InputStream
	 * @param dir			路径名
	 * @param realName		文件名
	 * @throws IOException	IO异常
	 */
	private static String copyFile(InputStream in, String dir, String realName) throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
}
