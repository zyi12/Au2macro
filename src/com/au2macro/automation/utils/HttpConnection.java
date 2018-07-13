package com.au2macro.automation.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
	
	public static String httpRequest(String parameters,String url,String method) throws Exception{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(method.toUpperCase());
		if (method.toUpperCase() == "POST") {
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		}else{
			con.setRequestProperty("Content-Type", "application/json");
		}
		if(method.toUpperCase() == "POST"){
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(parameters);
			wr.flush();
			wr.close();
		}
		int responseCode = con.getResponseCode();
		BufferedReader in = null;
		if(responseCode == HttpURLConnection.HTTP_OK){
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		}else{
			in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
