package com.beginner.base.utils.generatecode;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

/**
* <b>类名称：</b>文件下载工具类<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月22日 下午5:17:31<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class FileDownload {

	/**
	 * 文件下载
	 * @param response		HttpServletResponse
	 * @param filePath		文件完整路径(包括文件名和扩展名)
	 * @param fileName		下载后看到的文件名
	 */
	public static void fileDownload(final HttpServletResponse response, String filePath, String fileName) throws Exception {
		byte[] data = FileUtil.toByteArray2(filePath);
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		outputStream.write(data);
		outputStream.flush();
		outputStream.close();
		response.flushBuffer();
	}
}
