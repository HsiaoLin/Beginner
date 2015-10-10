package com.ran.service.flight;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ran.dao.DaoSupport;
import com.ran.entity.Page;
import com.ran.util.PageData;


@Service("passengerService")
public class PassengerService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("PassengerMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("PassengerMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("PassengerMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("PassengerMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PassengerMapper.listAll", pd);
	}
	/*
	 *列表(订单里的乘客)
	 */
	public List<PageData> listByOrder(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PassengerMapper.listByOrder", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("PassengerMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("PassengerMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

