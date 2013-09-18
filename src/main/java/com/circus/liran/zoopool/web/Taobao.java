package com.circus.liran.zoopool.web;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;



public class Taobao {
	public static void main(String args[])
	{
		WebClient webClient = new WebClient();
		webClient.setJavaScriptEnabled(true);
		webClient.setCssEnabled(true);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.setTimeout(10000);
		webClient.setThrowExceptionOnScriptError(false);
		HtmlPage htmlPage;
		try {
			htmlPage = webClient.getPage("http://detail.tmall.com/item.htm?id=15179732651&spm=a230r.1.14.4.qTmlz8&ad_id=&am_id=&cm_id=140105335569ed55e27b&pm_id=");
			System.out.println(htmlPage.getTextContent());
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
