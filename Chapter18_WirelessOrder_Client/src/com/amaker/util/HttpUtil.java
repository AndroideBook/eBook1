package com.amaker.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	// 宣告URL常數
	//public static final String BASE_URL="http://192.168.0.1:8080/WirelessOrder_Server/";
	public static final String BASE_URL="http://10.9.26.31:8080/WirelessOrder_Server/";
	// 透過Get請求物件request
	public static HttpGet getHttpGet(String url){
		HttpGet request = new HttpGet(url);
		 return request;
	}
	// 透過Post請求物件request
	public static HttpPost getHttpPost(String url){
		 HttpPost request = new HttpPost(url);
		 return request;
	}
	// 根據請求取得回應物件response
	public static HttpResponse getHttpResponse(HttpGet request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	// 根據請求取得回應物件response
	public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	
	// 發送Post請求，根據回應查詢結果
	public static String queryStringForPost(String url){
		// 根據url取得HttpPost物件
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			// 取得回應物件
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判斷是否請求成功
			if(response.getStatusLine().getStatusCode()==200){
				// 取得回應
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "網路異常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "網路異常！";
			return result;
		}
        return null;
    }
	// 取得回應查詢結果
	public static String queryStringForPost(HttpPost request){
		String result = null;
		try {
			// 取得回應物件
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判斷是否請求成功
			if(response.getStatusLine().getStatusCode()==200){
				// 取得回應
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "網路異常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "網路異常！";
			return result;
		}
        return null;
    }
	// 發送Get請求，取得回應查詢結果
	public static  String queryStringForGet(String url){
		// 取得HttpGet物件
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			// 取得回應物件
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// 判斷是否請求成功
			if(response.getStatusLine().getStatusCode()==200){
				// 取得回應
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "網路異常！";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "網路異常！";
			return result;
		}
        return null;
    }
}
