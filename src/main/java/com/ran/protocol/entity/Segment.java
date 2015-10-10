package com.ran.protocol.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ran.util.PageData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Segment implements Serializable {
	private static final long serialVersionUID = 1L;
	private String carrier;			//航司 IATA 二字码，必须与 flightNumber 航司相同
	private String flightNumber;	//航班号，如：CA123。航班号数字前若有多余的数字 0，必须去掉，如 CZ006 需返回 CZ6
	private String depAirport;		//出发机场 IATA 三字码
	private String depTime;			//起飞日期时间，格式：YYYYMMDDHHMM  例：201203100300 表示 2012 年 3 月 10 日 3 时 0 分
	private String arrAirport;		//到达机场 IATA 三字码
	private String arrTime;			//到达日期时间，格式：YYYYMMDDHHMM  例：201203101305 表示 2012 年 3 月 10 日 13 时 5 分
	private String stopCities;		//经停地，/分隔城市三字码
	private boolean codeShare;		//代码共享标识（true 代码共享/false 非代码共享）
	private String cabin;			//舱位
	private String aircraftCode;	//机型
	
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDepAirport() {
		return depAirport;
	}
	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getArrAirport() {
		return arrAirport;
	}
	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}
	public String getArrTime() {
		return arrTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public String getStopCities() {
		return stopCities;
	}
	public void setStopCities(String stopCities) {
		this.stopCities = stopCities;
	}
	public boolean isCodeShare() {
		return codeShare;
	}
	public void setCodeShare(boolean codeShare) {
		this.codeShare = codeShare;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getAircraftCode() {
		return aircraftCode;
	}
	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
	}
	
	public static Segment fill(JSONObject jo) {
		Segment o = new Segment();
		if (jo.containsKey("carrier")) {
			o.setCarrier(jo.getString("carrier"));
		}
		if (jo.containsKey("depAirport")) {
			o.setDepAirport(jo.getString("depAirport"));
		}
		if (jo.containsKey("depTime")) {
			o.setDepTime(jo.getString("depTime"));
		}
		if (jo.containsKey("arrAirport")) {
			o.setArrAirport(jo.getString("arrAirport"));
		}
		if (jo.containsKey("arrTime")) {
			o.setArrTime(jo.getString("arrTime"));
		}
		if (jo.containsKey("stopCities")) {
			o.setStopCities(jo.getString("stopCities"));
		}
		if (jo.containsKey("codeShare")) {
			o.setCodeShare(jo.getBoolean("codeShare"));
		}
		if (jo.containsKey("cabin")) {
			o.setCabin(jo.getString("cabin"));
		}
		if (jo.containsKey("aircraftCode")) {
			o.setAircraftCode(jo.getString("aircraftCode"));
		}
		if (jo.containsKey("flightNumber")) {
			o.setFlightNumber(jo.getString("flightNumber"));
		}
		return o;
	}
	public static List<Segment> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<Segment> sqs = new ArrayList<Segment>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}
	
	public static Segment fromDb(PageData pd) {
		Segment o = new Segment();
		
		o.setCarrier(pd.getString("carrier_code"));
		o.setDepAirport(pd.getString("dep_airport"));
		o.setDepTime(pd.getString("dep_time"));
		o.setArrAirport(pd.getString("arr_airport"));
		o.setArrTime(pd.getString("arr_time"));
		o.setStopCities(pd.getString("stop_cities"));
		o.setCodeShare(Boolean.parseBoolean(String.valueOf(pd.get("code_share"))));
		o.setCabin(pd.getString("cabin"));
		o.setAircraftCode(pd.getString("aircraft_code"));
		o.setFlightNumber(pd.getString("flight_number"));		
		
		return o;
	}
	
	public static List<Segment> fromDbList(List<PageData> pdList) {
		if (pdList == null || pdList.size() == 0)
			return null;
		List<Segment> sqs = new ArrayList<Segment>();
		for (int i = 0; i < pdList.size(); i++) {
			sqs.add(Segment.fromDb(pdList.get(i)));
		}
		return sqs;
	}	
}
