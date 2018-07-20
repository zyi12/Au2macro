package com.au2macro.automation;

import java.net.URLEncoder;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import com.au2macro.automation.utils.HttpConnection;
import com.au2macro.automation.utils.StaticVariable;

public class AccessToken implements Runnable{
	
	String response;
	JSONObject jObject = new JSONObject();
	JSONObject data = new JSONObject();
	public static StaticVariable SV;
	
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@SuppressWarnings("static-access")
	@Override
    public void run() {
		try {
			while (token != null) {
				Thread.sleep(3000);				
				@SuppressWarnings("deprecation")
				String request = "accessToken="+URLEncoder.encode(token);
				response = HttpConnection.httpRequest(request, SV.URL+"user.checkaccesstoken.php", "POST");
				if (response.contains("\"data\"")) {
					jObject = new JSONObject(response);
					data = jObject.getJSONObject("data");
					if (data.getString("status").contains("success")) {
						updateLastAccess(token);
						if (Double.parseDouble(Login.checkAppVersion()) != 0) {
							JOptionPane.showMessageDialog(null, "Please update au2macro.", "Au2macro", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Unauthorized token. Please relogin.", "Error", JOptionPane.YES_OPTION);
						Automation.frame.logOut(token);
						Automation.checkToken.interrupt();
						Automation.frame.setVisible(false);
						Login login = new Login();
						login.main(null);
					}	
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public void updateLastAccess(String token) {
		try {
			@SuppressWarnings("deprecation")
			String request = "accessToken="+URLEncoder.encode(token);
			response = HttpConnection.httpRequest(request, SV.URL+"user.update.lastaccess.php", "POST");
			if (response.contains("\"data\"")) {
				jObject = new JSONObject(response);
				data = jObject.getJSONObject("data");
				if (data.getString("status").contains("success")) {
					System.out.println("last access updated.");
				}
			}else {
				/*JOptionPane.showConfirmDialog(null, "Your internet connection is too slow. Please re-login.", "Error", JOptionPane.OK_OPTION);
				Automation.logOut(token);
				System.exit(0);*/
				System.out.println("Internet connection is too slow");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
