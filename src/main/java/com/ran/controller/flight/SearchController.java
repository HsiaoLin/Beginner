package com.ran.controller.flight;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;

import com.ran.controller.base.BaseController;
import com.ran.entity.Page;
import com.ran.util.AppUtil;
import com.ran.util.ObjectExcelView;
import com.ran.util.Const;
import com.ran.util.PageData;
import com.ran.util.SerializeUtil;
import com.ran.util.Tools;
import com.ran.util.Jurisdiction;
import com.ran.service.flight.OrderService;
import com.ran.service.flight.PassengerService;
import com.ran.service.flight.RoutingService;
import com.ran.service.flight.SearchService;
import com.ran.service.flight.SegmentService;

import com.ran.protocol.entity.Contact;
import com.ran.protocol.entity.Order;
import com.ran.protocol.entity.Passenger;
import com.ran.protocol.entity.Routing;
import com.ran.protocol.entity.Segment;
import com.ran.protocol.flight.Shijie99;

/** 
 * 类名称：SearchController
 * 创建时间：2015-08-07
 */
@Controller
@RequestMapping(value="/flight/search")
public class SearchController extends BaseController {
	
	String menuUrl = "flight/search/list.do"; //菜单地址(权限用)
	@Resource(name="searchService")
	private SearchService searchService;
	@Resource(name="orderService")
	private OrderService orderService;
	@Resource(name="passengerService")
	private PassengerService passengerService;
	@Resource(name="routingService")
	private RoutingService routingService;
	@Resource(name="segmentService")
	private SegmentService segmentService;	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Search");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			String fromCity = pd.getString("fromCity");
			String toCity = pd.getString("toCity");
			String fromDate = pd.getString("fromDate");
			String retDate = pd.getString("retDate");
			if(retDate == null) {
				retDate = "";
			}
			
			List<Routing> varList = null;
			if(fromCity != null && toCity != null && fromDate != null) {
				PageData pdTemp = new PageData();
				pdTemp.put("from_city", fromCity);
				pdTemp.put("to_city", toCity);
				pdTemp.put("from_date", fromDate);
				pdTemp.put("ret_date", retDate);
				pdTemp.put("c_id", "shijie99");
				pdTemp.put("user_id", 0);
				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());
				pdTemp.put("search_time", timestamp);
				searchService.save(pdTemp);
				
				int searchId = Integer.parseInt(String.valueOf(pdTemp.get("search_id")));
				mv.addObject("searchId", searchId);
				
				//查询
				varList = Shijie99.Search(fromCity, toCity, fromDate, retDate);
				
				//存入缓存
				Jedis jedis = new Jedis("192.168.1.92");
				byte[] bt = SerializeUtil.serialize(varList);
				String key = "flight_search:"+searchId;
				jedis.set(key.getBytes(), bt);
			}
			
			mv.setViewName("flight/search/search_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	
	/**
	 * 去购买页面
	 * @throws Exception 
	 */
	@RequestMapping(value="/goBuy")
	public ModelAndView goBuy() throws Exception{
		logBefore(logger, "去购买页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		//价格校验
		int searchId = Integer.parseInt(String.valueOf(pd.get("searchId")));
		int routingId = Integer.parseInt(String.valueOf(pd.get("routingId")));
		
		if(searchId == 0 || routingId == 0)
			return mv;
		
		try {		
			Jedis jedis = new Jedis("192.168.1.92");
			String key = "flight_search:"+searchId;
			byte[] bt = jedis.get(key.getBytes());
			List<Routing> routings = (List<Routing>)SerializeUtil.deserialize(bt);
			Routing routing = null;
			int index = 0;
			for(int i = 0; i < routings.size(); i++){
				if(routingId == routings.get(i).getId()) {
					routing = routings.get(i);
					index = i;
					break;
				}
			}
			
			if(routing == null)
				return mv;
			
			Order order = Shijie99.ValidatePrice(routing);
			
			//更新信息
			Routing up = order.getRouting();
			up.setId(routingId);
			routings.set(index, up);
			byte[] btUpdate = SerializeUtil.serialize(routings);
			jedis.set(key.getBytes(), btUpdate);

			mv.setViewName("flight/search/buy");
			
			mv.addObject("routing", order.getRouting());
			mv.addObject("sessionId", order.getSessionId());
			mv.addObject("maxSeats", order.getMaxSeats());
			mv.addObject("msg", "buy");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	
	/**
	 * 去订单页面
	 */
	@RequestMapping(value="goOrder")
	public ModelAndView goOrder(){
		logBefore(logger, "去购买页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		int searchId = Integer.parseInt(String.valueOf(pd.get("searchId")));
		int routingId = Integer.parseInt(String.valueOf(pd.get("routingId")));
		String sessionId = pd.getString("sessionId");
		
		if(searchId == 0 || routingId == 0)
			return mv;
		
		try {
			Jedis jedis = new Jedis("192.168.1.92");
			String key = "flight_search:"+searchId;
			byte[] bt = jedis.get(key.getBytes());
			List<Routing> routings = (List<Routing>)SerializeUtil.deserialize(bt);
			Routing routing = null;
			int index = 0;
			for(int i = 0; i < routings.size(); i++){
				if(routingId == routings.get(i).getId()) {
					routing = routings.get(i);
					index = i;
					break;
				}
			}
			
			if(routing == null)
				return mv;
			
			//获取乘机人
			List<Passenger> passengers = new ArrayList<Passenger>();
			Passenger passenger = new Passenger();
			passenger.setName(pd.getString("p_name"));
			passenger.setAgeType(Integer.parseInt(pd.getString("p_ageType")));
			passenger.setBirthday(pd.getString("p_birthday"));
			passenger.setGender(pd.getString("p_gender"));
			passenger.setCardType(pd.getString("p_birthday"));
			passenger.setCardNum(pd.getString("p_cardNum"));
			passenger.setCardExpired(pd.getString("p_cardExpired"));
			passenger.setCardIssuePlace(pd.getString("p_cardIssuePlace"));
			passenger.setNationality(pd.getString("p_nationality"));
			passengers.add(passenger);
			
			//获取联系人
			Contact contact = new Contact();
			contact.setName(pd.getString("c_name"));
            contact.setAddress(pd.getString("c_address"));
            contact.setPostcode(pd.getString("c_postcode"));
            contact.setEmail(pd.getString("c_email"));
            contact.setMobile(pd.getString("c_mobile"));
			
			//调用生单接口
			Order order = Shijie99.SaveOrder(sessionId, routing, passengers, contact);
			if(order == null)
				return mv;
			
			//生成订单
			PageData pdSearch = new PageData();
			pdSearch.put("search_id", searchId);
			pdSearch = searchService.findById(pdSearch);
			
			PageData pdOrder = new PageData();
			pdOrder.put("session_id", sessionId);
			pdOrder.put("order_code", order.getOrderNo());
			pdOrder.put("trip_pnr", order.getPnrCode());
			pdOrder.put("contact_name", contact.getName());
			pdOrder.put("contact_address", contact.getAddress());
			pdOrder.put("contact_postcode", contact.getPostcode());
			pdOrder.put("contact_mail", contact.getEmail());
			pdOrder.put("contact_phone", contact.getMobile());
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			pdOrder.put("order_time", timestamp);			
			pdOrder.put("from_city", pdSearch.getString("from_city"));
			pdOrder.put("to_city", pdSearch.getString("to_city"));
			pdOrder.put("from_date", pdSearch.getString("from_date"));
			pdOrder.put("ret_date", pdSearch.getString("ret_date"));
			orderService.save(pdOrder);
			
			if(pdOrder.get("order_id") == null)
				return mv;
			
			routing = order.getRouting();
			PageData pdRouting = new PageData();
			pdRouting.put("order_id", pdOrder.get("order_id"));
			pdRouting.put("adult_price", routing.getAdultPrice());
			pdRouting.put("adult_tax", routing.getAdultTax());
			pdRouting.put("child_price", routing.getChildPrice());
			pdRouting.put("child_tax", routing.getChildTax());
			pdRouting.put("price_type", routing.getPriceType());
			pdRouting.put("apply_type", routing.getApplyType());
			pdRouting.put("adult_taxtype", routing.getAdultTaxType());
			pdRouting.put("child_taxtype", routing.getChildTaxType());
			pdRouting.put("data", routing.getData());
			routingService.save(pdRouting);
			
			List<Segment> fromSegments = routing.getFromSegments();
			for(int i = 0; i < fromSegments.size(); i++) {
				Segment segment = fromSegments.get(i);
				PageData pdSeg = new PageData();
				pdSeg.put("segment_type", "from");
				pdSeg.put("carrier_code", segment.getCarrier());
				pdSeg.put("flight_number", segment.getFlightNumber());
				pdSeg.put("dep_airport", segment.getDepAirport());
				pdSeg.put("arr_airport", segment.getArrAirport());
				pdSeg.put("dep_time", segment.getDepTime());
				pdSeg.put("arr_time", segment.getArrTime());
				pdSeg.put("stop_cities", segment.getStopCities());
				pdSeg.put("code_share", segment.isCodeShare());
				pdSeg.put("aircraft_code", segment.getAircraftCode());
				pdSeg.put("order_id", pdOrder.get("order_id"));
				segmentService.save(pdSeg);
			}
			
			List<Segment> retSegments = routing.getRetSegments();
			if(retSegments != null) {
				for(int i = 0; i < retSegments.size(); i++) {
					Segment segment = fromSegments.get(i);
					PageData pdSeg = new PageData();
					pdSeg.put("segment_type", "ret");
					pdSeg.put("carrier_code", segment.getCarrier());
					pdSeg.put("flight_number", segment.getFlightNumber());
					pdSeg.put("dep_airport", segment.getDepAirport());
					pdSeg.put("arr_airport", segment.getArrAirport());
					pdSeg.put("dep_time", segment.getDepTime());
					pdSeg.put("arr_time", segment.getArrTime());
					pdSeg.put("stop_cities", segment.getStopCities());
					pdSeg.put("code_share", segment.isCodeShare());
					pdSeg.put("aircraft_code", segment.getAircraftCode());
					pdSeg.put("order_id", pdOrder.get("order_id"));
					segmentService.save(pdSeg);
				}
			}
			
			for(int i = 0; i < passengers.size(); i++) {
				passenger = passengers.get(i);
				PageData pdPsg = new PageData();
				pdPsg.put("passenger_name", passenger.getName());
				pdPsg.put("passenger_type", passenger.getAgeType());
				pdPsg.put("passenger_nationality", passenger.getNationality());
				pdPsg.put("passenger_birthday", passenger.getBirthday());
				pdPsg.put("passenger_gender", passenger.getGender());
				pdPsg.put("card_type", passenger.getCardType());
				pdPsg.put("card_no", passenger.getCardNum());
				pdPsg.put("card_Issueplace", passenger.getCardIssuePlace());
				pdPsg.put("card_expired", passenger.getCardExpired());
				pdPsg.put("order_id", pdOrder.get("order_id"));
				passengerService.save(pdPsg);
			}
			
			//返回订单详情页
			mv.setViewName("flight/search/confirm");
			
			mv.addObject("pdOrder", pdOrder);
			mv.addObject("routing", routing);
			mv.addObject("passengers", passengers);
			mv.addObject("pd", pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 去支付页面
	 */
	@RequestMapping(value="goPay")
	public ModelAndView goPay(){
		logBefore(logger, "去支付页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		//支付选择，余额或者支付宝
		
		try {
			pd = searchService.findById(pd);	//根据ID读取
			mv.setViewName("flight/search/pay");
			mv.addObject("msg", "buy");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 支付
	 */
	@RequestMapping(value="doPay")
	public ModelAndView doPay() throws Exception{
		logBefore(logger, "订单");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "buy")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		//支付校验
		int orderId = Integer.parseInt(String.valueOf(pd.get("orderId")));
		
		PageData pdOrder = new PageData();
		pdOrder.put("order_id", orderId);
		pdOrder = orderService.findById(pdOrder);
		if(pdOrder == null)
			return mv;
		
		PageData pdRouting = new PageData();
		pdRouting.put("order_id", orderId);
		pdRouting = routingService.findById(pdRouting);
		if(pdRouting == null)
			return mv;		
		
		//用户校验
		
		Routing routing = Routing.fromDb(pdRouting);
		
		PageData pdSeg = new PageData();
		pdSeg.put("order_id", orderId);
		pdSeg.put("segment_type", "from");
		List<PageData> listSeg = segmentService.listAll(pdSeg);
		routing.setFromSegments(Segment.fromDbList(listSeg));
		
		pdSeg.put("order_id", orderId);
		pdSeg.put("segment_type", "ret");
		listSeg = segmentService.listAll(pdSeg);
		routing.setRetSegments(Segment.fromDbList(listSeg));
		
		Order order = Shijie99.CheckOrder(pdOrder.getString("session_id"), pdOrder.getString("order_code"), pdOrder.getString("trip_pnr"), routing);
		
		//扣款
		
		//通知支付成功
		Map<String, Object> ret = Shijie99.UpdateOrderStatus(order.getOrderNo(), order.getPnrCode(), "3", "1000");
		
		searchService.save(pd);
		mv.addObject("msg",ret.get("msg"));
		mv.setViewName("flight/search/pay_result");
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
