package com.ran.protocol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;		//联系人姓名，不单独区分姓和名
	private String address;		//详细地址
	private String postcode;	//邮编
	private String email;		//联系人邮箱
	private String mobile;		//联系人手机号
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public static Contact fill(JSONObject jo){
		Contact o = new Contact();
		if (jo.containsKey("name")) {
			o.setName(jo.getString("name"));
		}
		if (jo.containsKey("address")) {
			o.setAddress(jo.getString("address"));
		}
		if (jo.containsKey("postcode")) {
			o.setPostcode(jo.getString("postcode"));
		}
		if (jo.containsKey("email")) {
			o.setEmail(jo.getString("email"));
		}
		if (jo.containsKey("mobile")) {
			o.setMobile(jo.getString("mobile"));
		}
		return o;
	}
	public static List<Contact> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<Contact> sqs = new ArrayList<Contact>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}
}
