package com.ran.controller.back;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ran.controller.base.BaseController;
import com.ran.entity.Page;
import com.ran.service.flight.OrderService;
import com.ran.service.flight.PassengerService;
import com.ran.service.flight.SegmentService;
import com.ran.service.flight.TicketService;
import com.ran.util.Const;
import com.ran.util.Jurisdiction;
import com.ran.util.ObjectExcelView;
import com.ran.util.PageData;

/** 
 * 类名称：OrderController
 * 创建时间：2015-08-07
 */
@Controller
@RequestMapping(value="/back/order")
public class BackOrderController extends BaseController {
	
	String menuUrl = "back/order/list.do"; //菜单地址(权限用)
	@Resource(name="orderService")
	private OrderService orderService;
	@Resource(name="passengerService")
	private PassengerService passengerService;
	@Resource(name="segmentService")
	private SegmentService segmentService;
	@Resource(name="ticketService")
	private TicketService ticketService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Order");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = orderService.list(page);	//列出Order列表
			mv.setViewName("back/order/order_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 退票
	 */
	@RequestMapping(value="/goRefund")
	public ModelAndView goRefund(){
		logBefore(logger, "去退票页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("back/order/refund");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 改期
	 */
	@RequestMapping(value="/goEndorse")
	public ModelAndView goEndorse(){
		logBefore(logger, "去改期页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("back/order/endorse");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Order页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("back/order/order_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Order页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = orderService.findById(pd);	//根据ID读取
			mv.setViewName("back/order/order_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 去订单查看页面
	 */
	@RequestMapping(value="/goView")
	public ModelAndView goView(){
		logBefore(logger, "去查看Order页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("order_id", pd.get("orderId"));
			List<PageData> passList = passengerService.listByOrder(pd);//获取该订单下的乘客
			List<PageData> segmList = segmentService.listByOrder(pd);//获取该订单下的航段信息
			List<PageData> ticketList = ticketService.listByOrder(pd);//获取该订单下的所有的机票细信息
			//处理乘客和票号的关系(判断乘客有没有和票关联)
			if (passList != null && ticketList != null){
				for (PageData pass : passList){
					pass.put("hasTicket", "0");
					for (PageData tic : ticketList){
						if (pass.getString("card_no").equals(tic.getString("card_no"))){
							pass.put("hasTicket", "1");
							break;
						}
					}
				}
			}
			pd = orderService.findById(pd);	//根据ID获取订单的信息
			mv.setViewName("back/order/order_view");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			mv.addObject("passList",passList);
			mv.addObject("segmList",segmList);
			mv.addObject("ticketList",ticketList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 去查看机票页面
	 */
	@RequestMapping(value="/goViewTicket")
	public ModelAndView goViewTicket(){
		logBefore(logger, "去查看Order页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = ticketService.getByPsr(pd);
			mv.setViewName("back/order/ticket_view");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 去添加机票页面
	 */
	@RequestMapping(value="/goViewTicketAdd")
	public ModelAndView goViewTicketAdd(){
		logBefore(logger, "去查看Order页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("back/order/ticket_add");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 添加票号
	 * @return
	 */
	@RequestMapping(value="/addTicket")
	public void addTicket(HttpServletRequest req,HttpServletResponse res){
		PageData pd = getPageData();
		String ret = "fail";
		PrintWriter out = null;
		try{
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
			out = res.getWriter();
			pd.put("order_id", pd.getString("orderId"));
			pd.put("psr_name", pd.getString("passName"));
			pd.put("card_no", pd.getString("passCardNo"));
			pd.put("ticket_no", pd.getString("ticketNo"));
			pd.put("backfill_time", new Date());
			pd.put("ticket_remark", pd.getString("ticketRemark")==null?"":pd.getString("ticketRemark"));
			ticketService.addTicket(pd);
			ret = "succ";
		}catch(Exception e){
			logger.info("添加票号出现了异常！");
			e.printStackTrace();
		}
		out.print(ret);
		out.flush();
		out.close();
	}
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Order到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			dataMap.put("titles", titles);
			List<PageData> varOList = orderService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
