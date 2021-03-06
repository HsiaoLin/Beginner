package com.beginner.system.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beginner.core.dao.DAO;
import com.beginner.core.page.Page;
import com.beginner.core.plugin.PageData;

/**
 * <b>类名称：</b>UserService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Hsiao Lin Studio<br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Service("userService")
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
	 * 批量新增
	 */
	@Override
	public Object saveBatch(PageData pd) throws Exception {
		return dao.save("UserMapper.saveBatch", pd);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("UserMapper.datalistPage", page);
	}

	/*
	 *列表(全部)
	 */
	@SuppressWarnings("unchecked")
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
