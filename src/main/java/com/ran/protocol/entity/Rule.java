package com.ran.protocol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;
	private String refund;		//退票规定，最大 300 个字符
	private String endorse;		//改签规定，最大 300 个字符
	private String baggage;		//行李额规定，最大 300 个字符
	private String other;		//其他说明，最大 300 个字符
	
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public String getEndorse() {
		return endorse;
	}
	public void setEndorse(String endorse) {
		this.endorse = endorse;
	}
	public String getBaggage() {
		return baggage;
	}
	public void setBaggage(String baggage) {
		this.baggage = baggage;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	public static Rule fill(JSONObject jo){
		Rule o = new Rule();
		if (jo.containsKey("other")) {
			o.setOther(jo.getString("other"));
		}
		if (jo.containsKey("refund")) {
			o.setRefund(jo.getString("refund"));
		}
		if (jo.containsKey("endorse")) {
			o.setEndorse(jo.getString("endorse"));
		}
		if (jo.containsKey("baggage")) {
			o.setBaggage(jo.getString("baggage"));
		}
		return o;
	}
	public static List<Rule> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<Rule> sqs = new ArrayList<Rule>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}	
}
