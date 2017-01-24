package com.ocean.core.common.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClient {
	private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

	// 编码方式
    private static final String CONTENT_CHARSET = "UTF-8";
    
	private volatile static HttpClient instance = null;

	private PoolingHttpClientConnectionManager connectionPoolMgr;

	private RequestConfig requestConfig;

	private CloseableHttpClient client;

	public static HttpClient getInstance() {
		if (instance != null) {
			return instance;
		}

		synchronized (HttpClient.class) {
			if (instance != null) {
				return instance;
			}

			instance = new HttpClient();
		}
		return instance;
	}

	private HttpClient() {
		try {
			ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
				public boolean verify(String paramString, SSLSession paramSSLSession) {
					return true;
				}
			});
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", plainsf).register("https", sslsf).build();
			connectionPoolMgr = new PoolingHttpClientConnectionManager(registry);

			// 最大连接数设置
			connectionPoolMgr.setMaxTotal(100);
			connectionPoolMgr.setDefaultMaxPerRoute(100);

			// 超时时间配置
			requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000).setConnectTimeout(1000)
					.setSocketTimeout(1000).setDecompressionEnabled(true).build();

			// 生成客户端
			client = HttpClients.custom().setConnectionManager(connectionPoolMgr).build();
		} catch (Throwable e) {
			logger.error("init http client error!!!", e);
		}
	}

	public String get(String url) throws HttpInvokeException {
		
		return get(url, null);
	}

	public String get(String url, Map<String, String> headers) 
			throws HttpInvokeException{
		HttpGet get = new HttpGet(url);
		return httpInvoke(get, headers);
	}
	
	public Map<String, String> getAllResponse(
			String url, Map<String, String> headers) throws HttpInvokeException{
		
		HttpGet get = new HttpGet(url);
		setHeader(get, headers);
		
		CloseableHttpResponse httpResp = null;
		try {
			httpResp = client.execute(get);
			int status = httpResp.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != status) {
				logger.error("ad request return unexpect status:{} ,url:{}",status,url);
				throw new HttpInvokeException(status);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("Content", EntityUtils.toString(httpResp.getEntity(), "UTF-8"));
			Header[] resHeaders = httpResp.getAllHeaders();
			if(resHeaders != null){
				for (Header header : resHeaders) {
					map.put(header.getName(), header.getValue());
				}
			}
			return map;
		} catch (ClientProtocolException e) {
			logger.error("ad request error(ClientProtocolException),url{}",url,e);
			throw new HttpInvokeException(0, e.getMessage());
		} catch (IOException e) {
			logger.error("ad request error(IOException),url{}",url,e);
			throw new HttpInvokeException(1, e.getMessage());
		} finally {
			try {
				if (httpResp != null)
					httpResp.close();
			} catch (Throwable e) {
				logger.error("ad request error(httpResp close throw Throwable),url{}",url,e);
			}
		}
//		return null;
	}
	
	public String post(String url, String body) throws HttpInvokeException{
		
		return post(url, body, null);
	}

	public String post(String url, String body, 
			Map<String, String> headers) throws HttpInvokeException{
		
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(body, CONTENT_CHARSET));
		return httpInvoke(post, headers);
	}
	
	public byte[] postBytes(String url, byte[] body, 
			Map<String, String> headers) throws HttpInvokeException{
		
		HttpPost method = new HttpPost(url);
		method.setEntity(new ByteArrayEntity(body));
		
		setHeader(method, headers);
		CloseableHttpResponse httpResp = null;
		try {
			httpResp = client.execute(method);
			int status = httpResp.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != status) {
				logger.error("ad request return unexpect status:{} ,url:{}",status,url);
				throw new HttpInvokeException(status);
			}
			return EntityUtils.toByteArray(httpResp.getEntity());
		} catch (ClientProtocolException e) {
			logger.error("ad request error(ClientProtocolException),url{}",url,e);
			throw new HttpInvokeException(0, e.getMessage());
		} catch (IOException e) {
			logger.error("ad request error(IOException),url{}",url,e);
			throw new HttpInvokeException(1, e.getMessage());
		} finally {
			try {
				if (httpResp != null)
					httpResp.close();
			} catch (Throwable e) {
				logger.error("ad request error(httpResp close throw IOException),url{}",url,e);
			}
		}
//		return httpInvoke(post, headers);
	}
	
	public String post(String url, List<NameValuePair> nvps) throws HttpInvokeException{
		
		return post(url, nvps, null);
	}
	
	public String post(String url, List<NameValuePair> nvps, 
			Map<String, String> headers) throws HttpInvokeException{
		
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps, CONTENT_CHARSET));
		} 
		catch (UnsupportedEncodingException e) {
			logger.error("ad request error(UnsupportedEncodingException),url{}",url,e);
			throw new HttpInvokeException(-1, "参数编码失败");
		}
		return httpInvoke(post, headers);
	}
	
	private String httpInvoke(HttpRequestBase method, Map<String, String> headers) 
		throws HttpInvokeException{
		
		setHeader(method, headers);
		
		CloseableHttpResponse httpResp = null;
		try {
			httpResp = client.execute(method);
			int status = httpResp.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != status) {
				logger.error("ad request return unexpect status:{} ,url:{}",status,method.getURI());
				throw new HttpInvokeException(status);
			}
			return EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		} catch (ClientProtocolException e) {
			logger.error("ad request error(ClientProtocolException),url{}",method.getURI(),e);
			throw new HttpInvokeException(0, e.getMessage());
		} catch (IOException e) {
			logger.error("ad request error(IOException),url{}",method.getURI(),e);
			throw new HttpInvokeException(1, e.getMessage());
		} finally {
			try {
				if (httpResp != null)
					
					httpResp.close();
			} catch (Throwable e) {
				logger.error("ad request error(httpResp close throw Throwable),url{}",method.getURI(),e);
			}
		}
	}

	private void setHeader(HttpRequestBase method, Map<String, String> headers){
		method.setConfig(requestConfig);
		if(headers != null){
			Set<String> keys = headers.keySet();
			for (String key : keys) {
				method.addHeader(key, headers.get(key));
			}
		}
	}
//	private Map<String, String> convertHeaders(Header[] headers) {
//		Map<String, String> headerMap = new HashMap<String, String>();
//		if (headers != null) {
//			for (Header header : headers) {
//				headerMap.put(header.getName(), header.getValue());
//			}
//		}
//		return headerMap;
//	}


}
