package com.ran.service.flight;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ran.dao.DaoSupport;
import com.ran.entity.Page;
import com.ran.util.PageData;


@Service("ticketService")
public class TicketService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("TicketMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("TicketMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("TicketMapper.edit", pd);
	}
	
	/**
	 * 根据订单获取列表
	 */
	public List<PageData> listByOrder(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TicketMapper.listByOrder", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("TicketMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TicketMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TicketMapper.findById", pd);
	}
	/*
	 * 通过id获取数据
	 */
	public PageData getByPsr(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TicketMapper.getByPsr", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("TicketMapper.deleteAll", ArrayDATA_IDS);
	}
	/*
	 * 添加票号
	 */
	public void addTicket(PageData pd)throws Exception{
		dao.save("TicketMapper.save", pd);
	}
	
}

