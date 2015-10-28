/**
* <b>项目名：</b>不忘初心方得始终<br/>
* <b>包名：</b>com.beginner.base.service<br/>
* <b>文件名：</b>BaseService.java<br/>
* <b>版本信息：</b><br/>
* <b>日期：</b>2015年10月27日-下午7:44:12<br/>
* <b>Copyright (c)</b> 2015尹枭凌工作室-版权所有<br/>
*/
package com.beginner.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.beginner.base.dao.BaseDAO;
import com.beginner.plugin.page.Page;
import com.beginner.plugin.page.PageData;

/**
* <b>类名称：</b>BaseService<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月28日 下午4:33:29<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@SuppressWarnings("unchecked")
@Transactional(readOnly = true)
public class BaseService implements IBaseService {

	@Resource(name = "dao")
	private BaseDAO dao;

	/*
	* 新增
	*/
	@Override
	@Transactional(readOnly = false)
	public void save(String str, PageData pd) throws Exception {
		dao.save(str, pd);
	}

	/*
	* 删除
	*/
	@Override
	@Transactional(readOnly = false)
	public void delete(String str, PageData pd) throws Exception {
		dao.delete(str, pd);
	}

	/*
	* 批量删除
	*/
	@Override
	@Transactional(readOnly = false)
	public void deleteAll(String str, String[] ArrayDATA_IDS) throws Exception {
		dao.delete(str, ArrayDATA_IDS);
	}

	/*
	* 修改
	*/
	@Override
	@Transactional(readOnly = false)
	public void edit(String str, PageData pd) throws Exception {
		dao.update(str, pd);
	}

	/*
	*列表
	*/
	@Override
	public List<PageData> list(String str, Page page) throws Exception {
		return (List<PageData>) dao.findForList(str, page);
	}

	/*
	*列表(全部)
	*/
	@Override
	public List<PageData> listAll(String str, PageData pd) throws Exception {
		return (List<PageData>) dao.findForList(str, pd);
	}

	/*
	* 通过id获取数据
	*/
	@Override
	public PageData findById(String str, PageData pd) throws Exception {
		return (PageData) dao.findForObject(str, pd);
	}

}
