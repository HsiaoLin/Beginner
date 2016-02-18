/**
* <b>项目名：</b>勿忘初心方得始终<br/>
* <b>包名：</b>com.beginner.base.service<br/>
* <b>文件名：</b>IBaseService.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月27日-下午7:44:12<br/>
* <b>Copyright (c)</b> 2015-2016 Hsiao Lin Studio-版权所有<br/>
*/
package com.beginner.base.service;

import java.util.List;

import com.beginner.base.plugin.page.Page;
import com.beginner.base.plugin.page.PageData;

/**
* <b>类名称：</b>Service<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public interface Service {

	/**
	* 新增
	*/
	public Object save(PageData pd) throws Exception;

	/**
	 * 批量新增
	 */
	public Object saveBatch(PageData pd) throws Exception;

	/**
	 * 删除
	*/
	public Object delete(PageData pd) throws Exception;

	/**
	 * 修改
	*/
	public Object edit(PageData pd) throws Exception;

	/**
	 * 列表
	*/
	public List<PageData> list(Page page) throws Exception;

	/**
	 * 列表(全部)
	*/
	public List<PageData> listAll(PageData pd) throws Exception;

	/**
	 * 通过id获取数据
	*/
	public PageData findById(PageData pd) throws Exception;

	/**
	 * 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception;
}