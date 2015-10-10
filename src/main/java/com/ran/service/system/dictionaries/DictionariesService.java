package com.ran.service.system.dictionaries;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ran.dao.DaoSupport;
import com.ran.entity.Page;
import com.ran.util.PageData;

@Service("dictionariesService")
public class DictionariesService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	//列出所有父节点
	public List<PageData> listAll() throws Exception {
		return (List<PageData>) dao.findForList("DictionariesMapper.listAll", null);
		
	}
	//列出同一父类id下的数据
	public List<PageData> getByParentId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("DictionariesMapper.getByParentId", pd);
		
	}
	//根据id查找数据
	public PageData getById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("DictionariesMapper.getById", pd);
	}
	//添加字典数据
	public void addDic(PageData pd) throws Exception {
		dao.save("DictionariesMapper.addDic", pd);
	}
	
	//删除数据字典
	public void delDic(PageData pd) throws Exception {
		dao.delete("DictionariesMapper.delDic", pd);
		
	}
	//删除数据字典
	public void delChildDic(PageData pd) throws Exception {
		dao.delete("DictionariesMapper.delChildDic", pd);
	}
	//修改数据字典
	public void updateDic(PageData pd) throws Exception {
		dao.update("DictionariesMapper.updateDic", pd);
	}
	
}
