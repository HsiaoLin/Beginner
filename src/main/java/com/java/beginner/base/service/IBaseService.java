/**
* <b>项目名：</b>不忘初心方得始终<br/>
* <b>包名：</b>com.java.beginner.base.service<br/>
* <b>文件名：</b>IBaseService.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月27日-下午7:44:12<br/>
* <b>Copyright (c)</b> 2015尹枭凌工作室-版权所有<br/>
*/
package com.java.beginner.base.service;

import java.util.List;

import com.java.beginner.plugin.page.Page;
import com.java.beginner.plugin.page.PageData;

/**
* <b>类名称：</b>IBaseService<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>创建时间：</b>2015年10月27日 下午7:44:12<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b><br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public interface IBaseService {

	/*
	* 新增
	*/
	public abstract void save(String str, PageData pd) throws Exception;

	/*
	* 删除
	*/
	public abstract void delete(String str, PageData pd) throws Exception;

	/*
	* 修改
	*/
	public abstract void edit(String str, PageData pd) throws Exception;

	/*
	*列表
	*/
	public abstract List<PageData> list(String str, Page page) throws Exception;

	/*
	*列表(全部)
	*/
	public abstract List<PageData> listAll(String str, PageData pd) throws Exception;

	/*
	* 通过id获取数据
	*/
	public abstract PageData findById(String str, PageData pd) throws Exception;

	/*
	* 批量删除
	*/
	public abstract void deleteAll(String str, String[] ArrayDATA_IDS) throws Exception;

}