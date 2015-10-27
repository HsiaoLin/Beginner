package com.java.beginner.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.java.beginner.base.dao.BaseDAO;
import com.java.beginner.plugin.page.Page;
import com.java.beginner.plugin.page.PageData;

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
