package com.weidou.com.zoopool.proxy;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;


public class ProxyTool {
	public static boolean testProxyserver(String host , int port ,String webUrl,String username ,String serect) {
		boolean status = false;
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			if (!StringUtils.isEmpty(username)) {
				httpclient.getCredentialsProvider().setCredentials(
						AuthScope.ANY,
						new UsernamePasswordCredentials(
								username,
								serect));
			}
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
			httpclient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 2000);
			HttpHost proxy = new HttpHost(host,
					port);
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					proxy);
			HttpHost targetHost = new HttpHost("open.weibo.com");
			HttpGet httpget = new HttpGet("");
			HttpResponse response = httpclient.execute(targetHost, httpget);
			StatusLine st = response.getStatusLine();
			if (st.getStatusCode() == 200) {
				status = true;
			} else {
				System.out.println(st);
			}
		} catch (Exception e) {

		}
		return status;
	}
	
	
}
