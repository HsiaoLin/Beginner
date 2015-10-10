package com.ran.protocol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ran.util.PageData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Routing implements Serializable {
	private static final long serialVersionUID = 1L;
	private String data;		//可保存必要信息，之后生单按原值回传。最大 1000 个字符
	private int adultPrice;		//成人单价（以元为单位）
	private int adultTax;		//成人税费（以元为单位）
	private int childPrice;		//儿童单价（以元为单位）
	private int childTax;		//儿童税费（以元为单位）
	private int priceType;		//报价类型：0 普通价 / 1 留学生价
	private int applyType;		//报价类型：0 预定价 / 1 申请价
	private int adultTaxType;	//成人税费类型：0 未含税 / 1 已含税
	private int childTaxType;	//儿童税费类型：0 未含税 / 1 已含税
	private List<Segment> fromSegments;		//去程航段按顺序，参考下面的 Segment Element
	private List<Segment> retSegments;		//回程航段按顺序，参考下面的 Segment Element（单程搜索为空）
	private Rule rule;
	
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(int adultPrice) {
		this.adultPrice = adultPrice;
	}
	public int getAdultTax() {
		return adultTax;
	}
	public void setAdultTax(int adultTax) {
		this.adultTax = adultTax;
	}
	public int getChildPrice() {
		return childPrice;
	}
	public void setChildPrice(int childPrice) {
		this.childPrice = childPrice;
	}
	public int getChildTax() {
		return childTax;
	}
	public void setChildTax(int childTax) {
		this.childTax = childTax;
	}
	public int getPriceType() {
		return priceType;
	}
	public void setPriceType(int priceType) {
		this.priceType = priceType;
	}
	public int getApplyType() {
		return applyType;
	}
	public void setApplyType(int applyType) {
		this.applyType = applyType;
	}
	public int getAdultTaxType() {
		return adultTaxType;
	}
	public void setAdultTaxType(int adultTaxType) {
		this.adultTaxType = adultTaxType;
	}
	public int getChildTaxType() {
		return childTaxType;
	}
	public void setChildTaxType(int childTaxType) {
		this.childTaxType = childTaxType;
	}
	public List<Segment> getFromSegments() {
		return fromSegments;
	}
	public void setFromSegments(List<Segment> fromSegments) {
		this.fromSegments = fromSegments;
	}
	public List<Segment> getRetSegments() {
		return retSegments;
	}
	public void setRetSegments(List<Segment> retSegments) {
		this.retSegments = retSegments;
	}
	
	public static Routing fill(JSONObject jo){
		Routing o = new Routing();
		if (jo.containsKey("data")) {
			o.setData(jo.getString("data"));
		}
		if (jo.containsKey("adultPrice")) {
			o.setAdultPrice(jo.getInt("adultPrice"));
		}
		if (jo.containsKey("adultTax")) {
			o.setAdultTax(jo.getInt("adultTax"));
		}
		if (jo.containsKey("childPrice")) {
			o.setChildPrice(jo.getInt("childPrice"));
		}
		if (jo.containsKey("childTax")) {
			o.setChildTax(jo.getInt("childTax"));
		}
		if (jo.containsKey("adultTaxType")) {
			o.setAdultTaxType(jo.getInt("adultTaxType"));
		}
		if (jo.containsKey("childTaxType")) {
			o.setChildTaxType(jo.getInt("childTaxType"));
		}
		if (jo.containsKey("priceType")) {
			o.setPriceType(jo.getInt("priceType"));
		}
		if (jo.containsKey("applyType")) {
			o.setApplyType(jo.getInt("applyType"));
		}
		if (jo.containsKey("fromSegments")) {
			o.setFromSegments(Segment.fillList(jo.getJSONArray("fromSegments")));
		}
		if (jo.containsKey("retSegments")) {
			o.setRetSegments(Segment.fillList(jo.getJSONArray("retSegments")));
		}
		if (jo.containsKey("rule")) {
			o.setRule(Rule.fill(jo.getJSONObject("rule")));
		}		
		return o;
	}
	public static List<Routing> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<Routing> sqs = new ArrayList<Routing>();
		for (int i = 0; i < ja.size(); i++) {
			Routing temp = fill(ja.getJSONObject(i));
			temp.setId(i+1);
			sqs.add(temp);
		}
		return sqs;
	}
	public static Routing fromDb(PageData pd) {
		Routing o = new Routing();
		
		o.setData(pd.getString("data"));
		o.setAdultPrice(Integer.parseInt(String.valueOf(pd.get("adult_price"))));
		o.setAdultTax(Integer.parseInt(String.valueOf(pd.get("adult_tax"))));
		o.setChildPrice(Integer.parseInt(String.valueOf(pd.get("child_price"))));
		o.setChildTax(Integer.parseInt(String.valueOf(pd.get("child_tax"))));
		o.setAdultTaxType(Integer.parseInt(String.valueOf(pd.get("adult_taxtype"))));
		o.setChildTaxType(Integer.parseInt(String.valueOf(pd.get("child_taxtype"))));
		o.setPriceType(Integer.parseInt(String.valueOf(pd.get("price_type"))));
		o.setApplyType(Integer.parseInt(String.valueOf(pd.get("apply_type"))));

//		o.setRule(Rule.fill());
		
		return o;
	}
}
