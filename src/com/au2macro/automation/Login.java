package com.au2macro.automation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.au2macro.automation.utils.StaticVariable;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3753084639079500389L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private static StaticVariable SV;
	private static final String key = "Au2macroAu2macro";// 128 bit key	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					//createAdminAccount();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("static-access")
	public static Connection getMysqlConnection() throws SQLException {
		return DriverManager.getConnection(SV.DBURL, SV.DBUSER, SV.DBPASS);
	}

	public static String StandardEnrypt(String password) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(key);
		String encrypted= encryptor.encrypt(password);
		return encrypted;
	}

	public static String StandardDcrypt(String password) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(key);
		String decrypted = encryptor.decrypt(password);
		return decrypted;
	}

	public static void createAdminAccount() {
		try {
			String username = "johnmike";
			String password = StandardEnrypt("password");
			String accessToken = "";
			Date expiration = new Date(System.currentTimeMillis());
			String status = "Active";

			Connection connection = getMysqlConnection();
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `user` (`username`, `password`, `accessToken`, `lastAccess`, `expiration`, `status`) "
					+"VALUES ('"+username+"', '"+password+"', '"+accessToken+"', NOW(), '"+expiration+"', '"+status+"')";
			statement.executeUpdate(sql);
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Communications link failure")) {
				JOptionPane.showMessageDialog(null, "Can't connect to server. Please check your internet connection!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				System.err.println(e.getMessage());
			}
		}
	}
	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/au2macro/automation/image/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 300);
		contentPane = new JPanel();
		txtUsername = new JTextField();
		txtUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		JLabel lblUsername = new JLabel("Username");
		txtPassword = new JPasswordField();
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		JLabel lblPassword = new JLabel("Password");
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsername.setBounds(52, 78, 200, 25);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(52, 53, 100, 20);
		contentPane.add(lblUsername);

		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(52, 114, 100, 20);
		contentPane.add(lblPassword);

		btnLogin.setBounds(107, 192, 89, 23);
		contentPane.add(btnLogin);

		txtPassword.setBounds(52, 140, 200, 25);
		contentPane.add(txtPassword);
	}

	@SuppressWarnings("static-access")
	public void login() {
		try {
			Date now = new Date(System.currentTimeMillis());
			String username = txtUsername.getText();
			@SuppressWarnings("deprecation")
			String password = txtPassword.getText();
			ResultSet resultSet = null;
			Connection connection = getMysqlConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM `user` WHERE username = '"+username+"' and status='Active'";
			resultSet = statement.executeQuery(sql);
			if (resultSet.first()) {
				int id = resultSet.getInt("id");
				String getUsername = resultSet.getString("username");
				String encrpytPassword = resultSet.getString("password");
				Date getDate = resultSet.getDate("expiration");
				String getPassword = StandardDcrypt(encrpytPassword);
				boolean isValidSubsription = false;
				if (now.compareTo(getDate) > 0) {
					isValidSubsription = false;
		        } else if (now.compareTo(getDate) < 0) {
		        	isValidSubsription = true;
		        } else {
		        	isValidSubsription = false;
		        }
				if (username.equals(getUsername) && password.equals(getPassword) && isValidSubsription == true) {
					String sqlcheck = "SELECT * FROM `user` WHERE username = '"+username+"' and lastAccess < DATE_SUB(NOW(), INTERVAL 1 MINUTE)";
					resultSet = statement.executeQuery(sqlcheck);
					if (resultSet.first()) {
						Automation automation = new Automation();
						String token = createUserToken(id);
						automation.token = token;
						dispose();
						automation.main(null);
					}else {
						JOptionPane.showMessageDialog(null, "Account currently in use.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Invalid username and password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Invalid username and password.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Can't connect at this momment", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public String createUserToken(int id) {
		try {
			String token = UUID.randomUUID().toString();
			Connection connection = getMysqlConnection();
			Statement statement = connection.createStatement();
			String sql = "UPDATE `user` SET `accessToken`='"+token+"', lastAccess=NOW() WHERE `id`='"+id+"'";
			statement.executeUpdate(sql);
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
			return token;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
