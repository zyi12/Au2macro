package com.au2macro.automation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.json.JSONObject;

import com.au2macro.automation.utils.HttpConnection;
import com.au2macro.automation.utils.StaticVariable;
import com.au2macro.automation.utils.UserLog;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3753084639079500389L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private static final String key = "Au2macroAu2macro";// 128 bit key	
	public static StaticVariable SV;
	static String response;
	static JSONObject jObject = new JSONObject();
	static JSONObject data = new JSONObject();
	private static final double appVersion = 1.04;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (Double.parseDouble(checkAppVersion()) == 0) {
						Login frame = new Login();
						frame.getContentPane();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Please update au2macro.", "Au2macro", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	@SuppressWarnings("static-access")
	public static void createAdminAccount() {
		try {
			String username = "gok";
			String password = StandardEnrypt("password");
			String accessToken = "";
			Date expiration = new Date(System.currentTimeMillis());
			String status = "Active";

			@SuppressWarnings("deprecation")
			String request = "username="+URLEncoder.encode(username)+"&password="+URLEncoder.encode(password)+"&accessToken="+URLEncoder.encode(accessToken)+"&expiration="+URLEncoder.encode(expiration.toString())+"&status="+URLEncoder.encode(status)+"";
			String response = HttpConnection.httpRequest(request, SV.URL+"user.create.php", "POST");
			if (response.contains("\"data\"")) {
				System.out.println(response);
			}else {
				System.err.println("Error response.");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/au2macro/automation/image/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 304);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
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

		txtUsername.setBounds(84, 78, 150, 25);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(78, 53, 100, 20);
		contentPane.add(lblUsername);

		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(78, 114, 100, 20);
		contentPane.add(lblPassword);

		btnLogin.setBounds(115, 192, 89, 23);
		contentPane.add(btnLogin);

		txtPassword.setBounds(84, 140, 150, 25);
		contentPane.add(txtPassword);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 0, 319, 276);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		try {
			String path = SV.URL+"Background.jpg";
			URL url = new URL(path);
			BufferedImage image = ImageIO.read(url);
			JLabel label = new JLabel(new ImageIcon(image));
			panel.add(label);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@SuppressWarnings("static-access")
	public void login() {
		try {
			Date now = new Date(System.currentTimeMillis());
			String username = txtUsername.getText();
			@SuppressWarnings("deprecation")
			String password = txtPassword.getText();

			if (!username.isEmpty() && !password.isEmpty()) {
				response = HttpConnection.httpRequest(null, SV.URL+"user.php?username="+username, "GET");
				if (response.contains("\"data\"")) {
					jObject = new JSONObject(response);
					data = jObject.getJSONObject("data");
					if (data.getString("status").contains("success")) {
						JSONObject message = data.getJSONObject("message");
						System.out.println(message.getString("lastAccess"));
						int id = Integer.parseInt(message.getString("id"));
						String getUsername = message.getString("username");
						String encrpytPassword = message.getString("password");
						Date getDate = new SimpleDateFormat("yyyy-MM-dd").parse(message.getString("expiration"));
						String getPassword = StandardDcrypt(encrpytPassword);
						boolean isValidSubsription = false;
						if (now.compareTo(getDate) > 0) {
							isValidSubsription = false;
						} else if (now.compareTo(getDate) < 0) {
							isValidSubsription = true;
						} else {
							isValidSubsription = false;
						}
						System.out.println(isValidSubsription);
						if (username.equals(getUsername) && password.equals(getPassword) && isValidSubsription == true && message.getString("status").equals("Active")) {
							System.out.println(message.getString("lastAccess"));
							response = HttpConnection.httpRequest(null, SV.URL+"user.lastaccess.php?username="+username, "GET");
							if (response.contains("\"data\"")) {
								jObject = new JSONObject(response);
								data = jObject.getJSONObject("data");
								if (data.getString("status").contains("success")) {
									Automation automation = new Automation();
									String token = createUserToken(id);
									automation.token = token;
									automation.id = id;
									AccessToken.id = id;
									dispose();
									automation.main(null);
								}else {
									UserLog.createLog(String.valueOf(id), "Multiple use of account.");
									JOptionPane.showMessageDialog(null, "Account currently in use.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Slow internet connection.", "Au2macro", JOptionPane.INFORMATION_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Invalid username and password.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Invalid username and password.", "Error", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}else {
				JOptionPane.showMessageDialog(null, "Invalid username and password.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Can't connect at this momment", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@SuppressWarnings("static-access")
	public String createUserToken(int id) {
		try {
			String token = UUID.randomUUID().toString();
			@SuppressWarnings("deprecation")
			String request = "id="+URLEncoder.encode(String.valueOf(id))+"&accessToken="+URLEncoder.encode(token);
			//should change naming convention on user.create.accesstoken to user.update.accesstoken
			response = HttpConnection.httpRequest(request, SV.URL+"user.create.accesstoken.php", "POST");
			if (response.contains("\"data\"")) {
				jObject = new JSONObject(response);
				data = jObject.getJSONObject("data");
				if (data.getString("status").contains("success")) {
					UserLog.createLog(String.valueOf(id), "Login and create token.");
					return token;
				}else {
					JOptionPane.showMessageDialog(null, "Account currently in use.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			//System.err.println(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public static String checkAppVersion() {
		try {
			String request = "version="+URLEncoder.encode(String.valueOf(appVersion));
			response = HttpConnection.httpRequest(request, SV.URL+"app.version.php", "POST");
			if (response.contains("\"data\"")) {
				jObject = new JSONObject(response);
				data = jObject.getJSONObject("data");
				if (data.getString("status").contains("success")) {
					data = data.getJSONObject("message");
					return data.getString("version");
				}else {
					checkAppVersion();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
