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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.beginner.core.common.Const;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
* <b>类名称：</b>Watermark<br/>
* <b>类描述：</b>添加文字、图片水印工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@SuppressWarnings("restriction")
public class Watermark {

	private static String strFWATERM, strIWATERM;

	/**
	 * 初始化水印配置
	 */
	static {
		strFWATERM = Tools.readTxtFile(Const.FWATERM); //读取文字水印配置
		strIWATERM = Tools.readTxtFile(Const.IWATERM); //读取图片水印配置
	}

	/**
	 * 刷新水印配置
	 */
	public static void fushValue() {
		strFWATERM = Tools.readTxtFile(Const.FWATERM); //读取文字水印配置
		strIWATERM = Tools.readTxtFile(Const.IWATERM); //读取图片水印配置
	}

	/**
	 * 添加水印
	 * @param imagePath 图片全路径
	 */
	public static void setWatemark(String imagePath) {
		//文字水印
		if (null != strFWATERM && !"".equals(strFWATERM)) {
			String strFW[] = strFWATERM.split(",beginner,");
			if (strFW.length == 5) {
				if ("yes".equals(strFW[0])) {
					pressText(strFW[1].toString(), imagePath, "", 1, Color.RED, Integer.parseInt(strFW[2]), Integer.parseInt(strFW[3]),
							Integer.parseInt(strFW[4])); //文字
				}
			}
		}
		//图片水印
		if (null != strIWATERM && !"".equals(strIWATERM)) {
			String strIW[] = strIWATERM.split(",beginner,");
			if (strIW.length == 4) {
				if ("yes".equals(strIW[0])) {
					pressImage(PublicUtil.getClasspath() + ""/*2015/08/20 Const.FILEPATHIMG*/+ strIW[1], imagePath, Integer.parseInt(strIW[2]),
							Integer.parseInt(strIW[3]));
				}
			}
		}
	}

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg 	水印文件
	 * @param targetImg 目标文件
	 * @param x 		x坐标
	 * @param y 		y坐标
	 */
	public final static void pressImage(String pressImg, String targetImg, int x, int y) {
		try {
			//目标文件
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			//水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			//g.drawImage(src_biao, (wideth - wideth_biao) / 2,(height - height_biao) / 2, wideth_biao, height_biao, null);
			g.drawImage(src_biao, x, y, wideth_biao, height_biao, null);
			//水印文件结束
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 打印文字水印图片
	 * @param pressText 文字
	 * @param targetImg 目标图片
	 * @param fontName 	字体名
	 * @param fontStyle 字体样式
	 * @param color 	字体颜色
	 * @param fontSize 	字体大小
	 * @param x 		x坐标偏移量
	 * @param y 		y坐标偏移量
	 */
	public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, Color color, int fontSize, int x, int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.drawString(pressText, x, y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
