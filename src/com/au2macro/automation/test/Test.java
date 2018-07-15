package com.au2macro.automation.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Get Array
		/*String response = httpRequest(null, "http://localhost/au2macro/users.php", "GET");
		JSONObject jObject = new JSONObject(response);
		System.err.println(jObject);
		JSONArray data = jObject.getJSONArray("data");
		System.err.println(data);
		for (int i = 0; i < data.length(); i++) {
			String tmp = data.get(i).toString();
			JSONObject user = new JSONObject(tmp);
			System.err.println(user);
			System.err.println(user.getString("username"));
			System.err.println(user.getString("password"));
			System.err.println(user.get("username"));
			System.err.println(user.get("password"));
		}*/
		
		//Get Object
		/*String response = httpRequest(null, "http://localhost/au2macro/user.php?username=root", "GET");
		JSONObject jObject = new JSONObject(response);
		System.err.println(jObject);
		JSONObject data = jObject.getJSONObject("data");
		System.err.println(data);*/
		
		//createAdminAccount();
		try {
			Robot robot = new Robot();
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			System.out.println(KeyEvent.VK_UP);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			System.out.println(KeyEvent.VK_ENTER);
			Thread.sleep(100);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
