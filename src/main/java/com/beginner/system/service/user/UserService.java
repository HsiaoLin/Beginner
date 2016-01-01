package com.beginner.system.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beginner.base.dao.DAO;
import com.beginner.base.plugin.page.Page;
import com.beginner.base.plugin.page.PageData;

/**
* <b>类名称：</b>UserService<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>创建时间：</b>2015-10-28<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b><br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Service("userService0")
@Transactional(readOnly = true)
public class UserService implements IUserService {

	@Resource(name = "dao")
	private DAO dao;

	/*
	* 新增
	*/
	@Override
	@Transactional(readOnly = false)
	public Object save(PageData pd) throws Exception {
		return dao.save("UserMapper.save", pd);
	}

	/*
	* 删除
	*/
	@Override
	@Transactional(readOnly = false)
	public Object delete(PageData pd) throws Exception {
		return dao.delete("UserMapper.delete", pd);
	}

	/*
	* 批量删除
	*/
	@Override
	@Transactional(readOnly = false)
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
		dao.delete("UserMapper.deleteAll", ArrayDATA_IDS);
	}

	/*
	* 修改
	*/
	@Override
	@Transactional(readOnly = false)
	public Object edit(PageData pd) throws Exception {
		return dao.update("UserMapper.edit", pd);
	}

	/*
	*列表
	*/
	@Override
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("UserMapper.datalistPage", page);
	}

	/*
	*列表(全部)
	*/
	@Override
	public List<PageData> listAll(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("UserMapper.listAll", pd);
	}

	/*
	* 通过id获取数据
	*/
	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("UserMapper.findById", pd);
	}
}
