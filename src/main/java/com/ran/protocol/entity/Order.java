package com.ran.protocol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sessionId;
	private String orderNo;
	private String pnrCode;
	private int maxSeats;
	private Routing routing;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPnrCode() {
		return pnrCode;
	}
	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}
	public int getMaxSeats() {
		return maxSeats;
	}
	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}
	public Routing getRouting() {
		return routing;
	}
	public void setRouting(Routing routing) {
		this.routing = routing;
	}
	
	public static Order fill(JSONObject jo){
		Order o = new Order();
		if (jo.containsKey("sessionId")) {
			o.setSessionId(jo.getString("sessionId"));
		}
		if (jo.containsKey("orderNo")) {
			o.setOrderNo(jo.getString("orderNo"));
		}
		if (jo.containsKey("pnrCode")) {
			o.setPnrCode(jo.getString("pnrCode"));
		}
		if (jo.containsKey("maxSeats")) {
			o.setMaxSeats(jo.getInt("maxSeats"));
		}
		if (jo.containsKey("routing")) {
			o.setRouting(Routing.fill(jo.getJSONObject("routing")));
		}
		return o;
	}
	public static List<Order> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<Order> sqs = new ArrayList<Order>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}		
}
