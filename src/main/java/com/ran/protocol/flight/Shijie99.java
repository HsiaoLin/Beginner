package com.ran.protocol.flight;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import redis.clients.jedis.Jedis;

import com.ran.protocol.entity.Contact;
import com.ran.protocol.entity.Order;
import com.ran.protocol.entity.Passenger;
import com.ran.protocol.entity.Routing;
import com.ran.protocol.entity.Segment;
import com.ran.util.AESDec;
import com.ran.util.AESEnc;
import com.ran.util.MD5;
import com.ran.util.SerializeUtil;

import net.sf.json.*;

public class Shijie99 {
	private static String cid="jptest201410";
	private static String aesKey="N6AG2WHLH74S5WC5";
	private static String urlSearch="http://cs1.shijie99.com:801/airprice!search.web";
	private static String urlValidatePrice="http://cs1.shijie99.com:801/validateair!ValidatePriceair.web";
	private static String urlSaveOrder="http://cs1.shijie99.com:801/orderair!newSave.web";
	private static String urlCheckOrder="http://cs1.shijie99.com:801/orderair!check.web";
	private static String urlUpdateOrder="http://cs1.shijie99.com:802/?go=updateOrderStatus";
	
	public static void main(String[] args) throws Exception {
		
		TestOrderIssued();
		
/*		UpdateOrderStatus("jp150107113", "RTEHKA", "4", "1000");
		
		List<Routing> routings = Search("HKG", "BJS", "20150828", "20150831");
		byte[] bt = SerializeUtil.serialize(routings);
		
		Jedis jedis = new Jedis("192.168.1.92");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		
		jedis.set("airsearch:1".getBytes(), bt);
		byte[] bt2 = jedis.get("airsearch:1".getBytes());
		
		List<Routing> routings2 = (List<Routing>)SerializeUtil.deserialize(bt2);
		for(int i = 0; i < routings.size(); i++) {
			Order order = null;
			order = ValidatePrice(routings.get(i));
			
			List<Passenger> passengers = new ArrayList<Passenger>();
			Passenger passenger = new Passenger();
			passenger.setName("Marcus/Tom");
			passenger.setAgeType(0);
			passenger.setBirthday("19740225");
			passenger.setGender("M");
			passenger.setCardType("PP");
			passenger.setCardNum("G756789");
			passenger.setCardExpired("20200101");
			passenger.setCardIssuePlace("CN");
			passenger.setNationality("CN");
			passengers.add(passenger);
			
			Contact contact = new Contact();
			contact.setName("Marcus/Tom");
            contact.setAddress("xxxx spring valley road");
            contact.setPostcode("11111");
            contact.setEmail("");
            contact.setMobile("13800000000");
			
			SaveOrder("", routings.get(i), passengers, contact);
			
//			CheckOrder(routings.get(i));
		}*/
	}
	
	public static List<Routing> Search(String fromCity, String toCity, String fromDate, String retDate) throws Exception {
		List<Routing> routings = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
            HttpPost httppost = new HttpPost(urlSearch);
            System.out.println("Executing request " + httppost.getRequestLine());
            
            JSONObject object = new JSONObject();
            object.element("cid", cid);
            object.element("fromCity", fromCity);
            object.element("toCity", toCity);
            object.element("fromDate", fromDate);
            object.element("retDate", retDate);
            
            String json = object.toString();
            
            httppost.setEntity(new StringEntity(json, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httppost);
            
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                String ret = EntityUtils.toString(entity);
                System.out.println(ret);
                JSONObject jo = JSONObject.fromObject(ret);
                routings = Routing.fillList(jo.getJSONArray("routings"));
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            
		} finally {
            httpclient.close();
        }
		
		return routings;
	}
	
	public static Order ValidatePrice(Routing routing) throws Exception {
		Order order = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
            HttpPost httppost = new HttpPost(urlValidatePrice);
            System.out.println("Executing request " + httppost.getRequestLine());
            
            JSONObject object = new JSONObject();
            object.element("cid", cid);
            JSONObject obRouting = new JSONObject();
            
            obRouting.element("data", routing.getData());
            obRouting.element("fromSegments", JSONArray.fromObject(routing.getFromSegments()));
            obRouting.element("retSegments", JSONArray.fromObject(routing.getRetSegments()));
            
            object.element("routing", obRouting);
            
            String json = object.toString();
            
            httppost.setEntity(new StringEntity(json, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httppost);
            
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                String ret = EntityUtils.toString(entity);
                System.out.println(ret);
                order = Order.fill(JSONObject.fromObject(ret));            
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            
		} finally {
            httpclient.close();
        }
		
		return order;
	}
	
	public static Order SaveOrder(String sessionId, Routing routing, List<Passenger> passengers, Contact contact) throws Exception {
		Order order = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
            HttpPost httppost = new HttpPost(urlSaveOrder);
            System.out.println("Executing request " + httppost.getRequestLine());
            
            JSONObject object = new JSONObject();
            object.element("cid", cid);
            object.element("sessionId", sessionId);
            
            JSONObject obRouting = new JSONObject();
            obRouting.element("data", routing.getData());
            obRouting.element("fromSegments", JSONArray.fromObject(routing.getFromSegments()));
            obRouting.element("retSegments", JSONArray.fromObject(routing.getRetSegments()));
            object.element("routing", obRouting);
            object.element("passengers", JSONArray.fromObject(passengers));
            object.element("contact", JSONObject.fromObject(contact));
            
            String json = object.toString();
            
            json = AESEnc.encode(aesKey, json);
            
            httppost.setEntity(new StringEntity(json, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httppost);
            
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                
                String ret = EntityUtils.toString(entity);
                System.out.println(ret);
                ret = AESDec.decode(aesKey, ret);
                order = Order.fill(JSONObject.fromObject(ret));
                System.out.println(ret);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            
		} finally {
            httpclient.close();
        }
		
		return order;
	}
	
	public static Order CheckOrder(String sessionId, String orderNo, String pnrCode, Routing routing) throws Exception {
		Order order = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
            HttpPost httppost = new HttpPost(urlCheckOrder);
            System.out.println("Executing request " + httppost.getRequestLine());
            JSONObject object = new JSONObject();
            object.element("cid", cid);
            object.element("sessionId", sessionId);
            object.element("orderNo", orderNo);
            object.element("pnrCode", pnrCode);
            
            JSONObject obRouting = new JSONObject();
            obRouting.element("data", routing.getData());
            obRouting.element("fromSegments", JSONArray.fromObject(routing.getFromSegments()));
            obRouting.element("retSegments", JSONArray.fromObject(routing.getRetSegments()));
            
            object.element("routing", obRouting);
            
            String json = object.toString();
            
            json = AESEnc.encode(aesKey, json);
            
            httppost.setEntity(new StringEntity(json, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httppost);
            
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                
                String ret = EntityUtils.toString(entity);
                System.out.println(ret);
                ret = AESDec.decode(aesKey, ret);
                order = Order.fill(JSONObject.fromObject(ret));
                System.out.println(ret);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            
		} finally {
            httpclient.close();
        }
		
		return order;
	}
	
	public static Map<String, Object> UpdateOrderStatus(String orderno, String pnr, String status, String Price) throws ClientProtocolException, IOException {
		Map<String, Object> mapRet = new HashMap<String, Object>();
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
            JSONObject object = new JSONObject();
            object.element("cid", cid);
            object.element("orderno", orderno);
            object.element("pnr", pnr);
            object.element("status", status);
            object.element("Price", Price);
            object.element("str", MD5.md5("123"+orderno+pnr+status));
            
            String json = URLEncoder.encode(object.toString(), "UTF-8");
            
            HttpGet httpget = new HttpGet(urlUpdateOrder+"&data="+json);
            System.out.println("Executing request " + httpget.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpget);
            
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                
                String ret = EntityUtils.toString(entity);
                System.out.println(ret);
                
                JSONObject jo = JSONObject.fromObject(ret);
                mapRet.put("flag", jo.getInt("flag"));
                mapRet.put("msg", jo.getString("msg"));
                EntityUtils.consume(entity);
            } finally {
                response.close();
                mapRet.put("flag", 999);
                mapRet.put("msg", "结果解析异常");                
            }
            
		} finally {
            httpclient.close();
            if(mapRet.get("flag") == null) {
                mapRet.put("flag", 998);
                mapRet.put("msg", "网络请求异常");            	
            }
        }
		
		return mapRet;
	}
	
	public static void TestOrderIssued() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		try {
            HttpPost httppost = new HttpPost("http://192.168.3.145:8080/buyer/api/flight/setOrderIssued");
            System.out.println("Executing request " + httppost.getRequestLine());
            
            httppost.setEntity(new StringEntity("{\"otaUserID\": \"admin\",\"otaPwd\": \"123\",\"orderNo\": \"abc2131264873362423\",\"ticketNoItems\": [{\"psrName\": \"ZHANG/SAN\",\"idNo\": \"123456789\",\"tktNo\": \"132-124312343\"},{\"psrName\": \"LI/SI\",\"idNo\": \"123456788\",\"tktNo\": \"222-124312343\"},{\"psrName\": \"WANG/WU\",\"idNo\": \"123456778\",\"tktNo\": \"333124312343\"}]}", "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httppost);
            
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                
                String ret = EntityUtils.toString(entity);
                System.out.println(ret);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            
		} finally {
            httpclient.close();
        }		
	}
}
