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
* <b>类名称：</b>IBaseService<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>创建时间：</b>2015年10月27日 下午7:44:12<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b><br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public interface Service {

	/**
	* save(方法描述：新增) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @param pd
	* @throws Exception
	* Object
	* @exception
	* @since 1.0.0
	*/
	public Object save(PageData pd) throws Exception;

	/**
	 * saveBatch(方法描述：批量新增) <br />
	 * (方法适用条件描述： – 可选)
	 * @param str
	 * @param pd
	 * @throws Exception
	 * Object
	 * @exception
	 * @since 1.0.0
	 */
	public Object saveBatch(PageData pd) throws Exception;

	/**
	* delete(方法描述：删除) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @param pd
	* @throws Exception
	* Object
	* @exception
	* @since 1.0.0
	*/
	public Object delete(PageData pd) throws Exception;

	/**
	* edit(方法描述：修改) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @param pd
	 * @return 
	* @throws Exception
	* Object
	* @exception
	* @since 1.0.0
	*/
	public Object edit(PageData pd) throws Exception;

	/**
	* list(方法描述：列表) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @param page
	* @return
	* @throws Exception
	* List<PageData>
	* @exception
	* @since 1.0.0
	*/
	public List<PageData> list(Page page) throws Exception;

	/**
	* listAll(方法描述：列表(全部)) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @param pd
	* @return
	* @throws Exception
	* List<PageData>
	* @exception
	* @since 1.0.0
	*/
	public List<PageData> listAll(PageData pd) throws Exception;

	/**
	* findById(方法描述：通过id获取数据) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @param pd
	* @return
	* @throws Exception
	* PageData
	* @exception
	* @since 1.0.0
	*/
	public PageData findById(PageData pd) throws Exception;

	/**
	* deleteAll(方法描述：批量删除) <br />
	* (方法适用条件描述： – 可选)
	* @param str
	* @param ArrayDATA_IDS
	* @throws Exception
	* void
	* @exception
	* @since 1.0.0
	*/
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception;

}