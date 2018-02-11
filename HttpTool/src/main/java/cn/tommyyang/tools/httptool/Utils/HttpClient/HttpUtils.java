package cn.tommyyang.tools.httptool.Utils.HttpClient;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

public class HttpUtils {
	
	public static int timeout = 5000;
	
	private static Logger logger = Logger.getLogger(HttpUtils.class);
	
	public static String httpGet(String url, int timeout) {
		long before = System.currentTimeMillis();
		CloseableHttpClient httpClient = PoolConnectionManager.getInstance().getHttpClient();
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout((int) timeout)
                .setConnectTimeout((int) timeout)
                .setConnectionRequestTimeout((int) timeout)
                .build();
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		String rspStr = "";
		try {
        	response =  httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				rspStr = EntityUtils.toString(entity, Charsets.UTF_8);
				return rspStr;
			}else{
				logger.error("http status is " + response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			logger.error("http get ClientProtocolException!", e);
		} catch (IOException e) {
			logger.error("http get IOException!", e);
		} finally {
			if(response != null) {
				try {
					response.close();
				} catch (IOException e) {
					
				}
			}
			httpGet.abort();
		}
		long now = System.currentTimeMillis();
		logger.info(String.format("api : %s result: %s cost: %s", url, rspStr, now - before));
		return rspStr;
	}
}