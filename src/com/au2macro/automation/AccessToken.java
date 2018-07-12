package com.au2macro.automation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.au2macro.automation.utils.StaticVariable;

public class AccessToken implements Runnable{
	
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private static StaticVariable SV;
	
	@SuppressWarnings("static-access")
	public static Connection getMysqlConnection() throws SQLException {
		return DriverManager.getConnection(SV.DBURL, SV.DBUSER, SV.DBPASS);
	}
	
	@SuppressWarnings("static-access")
	@Override
    public void run() {
		try {
			while (token != null) {
				Thread.sleep(1800);
				ResultSet resultSet = null;
				Connection connection = getMysqlConnection();
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM `user` WHERE accessToken = '"+token+"'";
				resultSet = statement.executeQuery(sql);
				if (resultSet.first()) {
					updateLastAccess(token);
				}else {
					JOptionPane.showMessageDialog(null, "Unauthorized token. Please relogin.", "Error", JOptionPane.YES_OPTION);
					Automation.frame.logOut(token);
					Automation.checkToken.interrupt();
					Automation.frame.setVisible(false);
					Login login = new Login();
					login.main(null);
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void updateLastAccess(String token) {
		try {
			Connection connection = getMysqlConnection();
			Statement statement = connection.createStatement();
			String sql = "UPDATE `user` SET lastAccess=NOW() WHERE `accessToken`='"+token+"'";
			statement.executeUpdate(sql);
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
