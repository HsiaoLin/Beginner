/**
* <b>项目名：</b>勿忘初心方得始终<br/>
* <b>包名：</b>com.beginner.${packageName}.service<br/>
* <b>文件名：</b>${objectName}Service.java<br/>
* <b>日期：</b>2015年05月21日 下午6:18:18<br/>
* <b>Copyright (c)</b> 2015-2016 Hsiao Lin Studio-版权所有<br/>
*/
package com.beginner.system.service.${packageName};

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beginner.base.dao.DAO;
import com.beginner.base.plugin.page.Page;
import com.beginner.base.plugin.page.PageData;

/**
 * <b>类名称：</b>${objectName}Service<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>创建时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b><br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Service("${objectNameLower}Service")
@Transactional(readOnly = true)
public class ${objectName}Service implements I${objectName}Service {

	@Resource(name = "dao")
	private DAO dao;

	/**
	 * 新增
	 */
	@Override
	@Transactional(readOnly = false)
	public Object save(PageData pd) throws Exception {
		return dao.save("${objectName}Mapper.save", pd);
	}

	/**
	 * 批量新增
	 */
	@Override
	@Transactional(readOnly = false)
	public Object saveBatch(PageData pd) throws Exception {
		return dao.save("${objectName}Mapper.saveBatch", pd);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional(readOnly = false)
	public Object delete(PageData pd) throws Exception {
		return dao.delete("${objectName}Mapper.delete", pd);
	}

	/**
	 * 批量删除
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
		dao.delete("${objectName}Mapper.deleteAll", ArrayDATA_IDS);
	}

	/**
	 * 修改
	 */
	@Override
	@Transactional(readOnly = false)
	public Object edit(PageData pd) throws Exception {
		return dao.update("${objectName}Mapper.edit", pd);
	}

	/**
	 * 分页查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("${objectName}Mapper.datalistPage", page);
	}

	/**
	 * 全部查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listAll(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("${objectName}Mapper.listAll", pd);
	}

	/**
	 * 通过id获取数据
	 */
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("${objectName}Mapper.findById", pd);
	}
}
