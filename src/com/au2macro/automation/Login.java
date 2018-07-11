package com.au2macro.automation;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3753084639079500389L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

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
					createAdminAccount();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Connection getMysqlConnection() throws SQLException {
		String dburl = "jdbc:mysql://localhost:3306/au2macro";
		String dbuser = "root";
		String dbpass = "";
		return DriverManager.getConnection(dburl, dbuser, dbpass);
	}
	
	public static void createAdminAccount() {
		try {
			Connection myConn = getMysqlConnection();
			Statement myStmt = myConn.createStatement();
			String sql = "INSERT INTO `user` (`username`, `password`, `accessToken`, `lastAccess`, `expiration`, `status`) VALUES ('root', 'password', '', '2018-07-11 08:48:25', '2019-07-11', 'active')";
			myStmt.executeUpdate(sql);
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception e) {
			if (e.getMessage().contains("Communications link failure")) {
				JOptionPane.showMessageDialog(null, "Can't connect to server. Please check your internet connection!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/au2macro/automation/image/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		txtUsername.setBounds(117, 78, 200, 25);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(117, 53, 100, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(117, 114, 100, 20);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnLogin.setBounds(172, 192, 89, 23);
		contentPane.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		txtPassword.setBounds(117, 140, 200, 25);
		contentPane.add(txtPassword);
	}
	
	public void login() {
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		if (username.equals("root") && password.equals("root")) {
			Automation automation = new Automation();
			dispose();
			automation.main(null);
		}else {
			JOptionPane.showMessageDialog(null, "Invalid username and password.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
