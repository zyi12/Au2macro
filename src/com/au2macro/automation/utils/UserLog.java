package com.au2macro.automation.utils;

import java.net.URLEncoder;

import org.json.JSONObject;

public class UserLog {
	
	public static StaticVariable SV;
	static JSONObject jObject = new JSONObject();
	static JSONObject data = new JSONObject();
	static String response;
	
	@SuppressWarnings("static-access")
	public static void createLog(String id, String log) {
		try {
			System.out.println("System log saved.");
			System.out.println(id);
			System.out.println(log);
			@SuppressWarnings("deprecation")
			String request = "id="+URLEncoder.encode(String.valueOf(id))+"&log="+URLEncoder.encode(log);
			response = HttpConnection.httpRequest(request, SV.URL+"user.create.log.php", "POST");
			if (response.contains("\"data\"")) {
				jObject = new JSONObject(response);
				data = jObject.getJSONObject("data");
				if (data.getString("status").contains("success")) {
					System.out.println("Logs successfully saved.");
				}else {
					System.out.println("Error on saving logs.");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
