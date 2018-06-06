package com.silence.bing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * description 
 * @author lixiaobing
 * @date 2018年6月6日
 */
public class HttpUtil {
	
	/**
	 * description 
	 * @author lixiaobing
	 * @date 2018年6月6日
	 * @param url
	 * @param params  参数键值对
	 * @param charset 编码格式
	 */
	public String sendGet(String url,Map<String,String> params,String charset){
		StringBuilder sb=new StringBuilder();
		for(Map.Entry<String,String> entry:params.entrySet()){
			String name=entry.getKey();
			String value=entry.getValue();
			try {
				sb.append(name).append("=").append(java.net.URLEncoder.encode(value, charset)+"&");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String urlParams=sb.substring(0, sb.length()-1).toString();
		return sendGet(url,urlParams,charset);
	}
	
	/**
	 * description 
	 * @author lixiaobing
	 * @date 2018年6月6日
	 * @param url
	 * @param params  name=lixiaobing&age=22
	 * @param charset 编码格式
	 */
	public String sendGet(String url,String params,String charset){
		url+="?"+params;
		BufferedReader in=null;
		StringBuilder sb=new StringBuilder();
		try {
			//创建URL对象
			java.net.URL connURL=new java.net.URL(url);
			//打开URL连接 
			java.net.HttpURLConnection httpConn=(java.net.HttpURLConnection) connURL.openConnection();
			// 设置通用属性    
            httpConn.setRequestProperty("Accept", "*/*");    
            httpConn.setRequestProperty("Connection", "Keep-Alive");    
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接    
            httpConn.connect();
            in=new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charset));
            
            String line=null;
            while((line=in.readLine())!=null)
            	sb.append(line);
            
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(in!=null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	
	public String sendPost(String url,Map<String,String> params,String charset){
		StringBuilder sb=new StringBuilder();
		for(Map.Entry<String,String> entry:params.entrySet()){
			String name=entry.getKey();
			String value=entry.getValue();
			try {
				sb.append(name).append("=").append(java.net.URLEncoder.encode(value, charset)+"&");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String urlParams=sb.substring(0, sb.length()-1).toString();
		return sendPost(url,urlParams,charset);
	}
	
	/**
	 * description 
	 * @author lixiaobing
	 * @date 2018年6月6日
	 * @param url
	 * @param params  name=lixiaobing&age=22
	 * @param charset 编码格式
	 */
	public String sendPost(String url,String params,String charset){
		BufferedReader in=null;
		PrintWriter out=null;
		StringBuilder sb=new StringBuilder();
		
		
		try {
			// 创建URL对象    
	        java.net.URL connURL = new java.net.URL(url);
	        // 打开URL连接    
	        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
	        // 设置通用属性    
            httpConn.setRequestProperty("Accept", "*/*");    
            httpConn.setRequestProperty("Connection","Keep-Alive");    
            httpConn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)"); 
            
	        // 设置POST方式    
            httpConn.setDoInput(true);    
            httpConn.setDoOutput(true); 
            // 获取HttpURLConnection对象对应的输出流    
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数    
            out.write(params);
            // flush输出流的缓冲    
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式    
            in=new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charset));
            
	        String line=null;
            while((line=in.readLine())!=null){
            	sb.append(line);
            }
            
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(out!=null)
					out.close();
				if(in!=null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("message", "这时一条测试信息");
		map.put("toUser", "13651210262");
		HttpUtil httpUtil=new HttpUtil();
		String returnMsg=httpUtil.sendPost("url", map, "UTF-8");
		System.out.println(returnMsg);
	}
}
