package com.amaker.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	// �ŧiURL�`��
	//public static final String BASE_URL="http://192.168.0.1:8080/WirelessOrder_Server/";
	public static final String BASE_URL="http://10.9.26.31:8080/WirelessOrder_Server/";
	// �z�LGet�ШD����request
	public static HttpGet getHttpGet(String url){
		HttpGet request = new HttpGet(url);
		 return request;
	}
	// �z�LPost�ШD����request
	public static HttpPost getHttpPost(String url){
		 HttpPost request = new HttpPost(url);
		 return request;
	}
	// �ھڽШD���o�^������response
	public static HttpResponse getHttpResponse(HttpGet request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	// �ھڽШD���o�^������response
	public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	
	// �o�ePost�ШD�A�ھڦ^���d�ߵ��G
	public static String queryStringForPost(String url){
		// �ھ�url���oHttpPost����
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			// ���o�^������
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// �P�_�O�_�ШD���\
			if(response.getStatusLine().getStatusCode()==200){
				// ���o�^��
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "�������`�I";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "�������`�I";
			return result;
		}
        return null;
    }
	// ���o�^���d�ߵ��G
	public static String queryStringForPost(HttpPost request){
		String result = null;
		try {
			// ���o�^������
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// �P�_�O�_�ШD���\
			if(response.getStatusLine().getStatusCode()==200){
				// ���o�^��
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "�������`�I";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "�������`�I";
			return result;
		}
        return null;
    }
	// �o�eGet�ШD�A���o�^���d�ߵ��G
	public static  String queryStringForGet(String url){
		// ���oHttpGet����
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			// ���o�^������
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// �P�_�O�_�ШD���\
			if(response.getStatusLine().getStatusCode()==200){
				// ���o�^��
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "�������`�I";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "�������`�I";
			return result;
		}
        return null;
    }
}
