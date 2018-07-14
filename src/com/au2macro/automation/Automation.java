package com.au2macro.automation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

import org.json.JSONObject;

import com.au2macro.automation.utils.AutomationAutoNumKey;
import com.au2macro.automation.utils.AutomationAutoRightClick;
import com.au2macro.automation.utils.AutomationAutoShout;
import com.au2macro.automation.utils.AutomationAutoSpace;
import com.au2macro.automation.utils.HttpConnection;
import com.au2macro.automation.utils.StaticVariable;

public class Automation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6900633506525697942L;
	private JPanel contentPane;
	private JTextField txtInterval;
	private JTextField txtStartInterval;
	public static String token;
	public static Thread checkToken;
	public static Automation frame;
	
	public static boolean is1 = true;
	public static boolean is2 = true;
	public static boolean is3 = true;
	public static boolean is4 = true;
	public static boolean is5 = true;
	public static boolean is6 = true;
	public static boolean is7 = true;
	public static boolean is8 = true;
	public static boolean is9 = true;
	public static boolean is0 = true;
	public static boolean isMinus = true;
	public static boolean isEqual = true;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Toolkit.getDefaultToolkit().addAWTEventListener(new Listener(), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
					frame = new Automation();
					frame.setExtendedState(JFrame.ICONIFIED);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					AccessToken accessToken = new AccessToken();
					accessToken.setToken(token);
					checkToken = new Thread(accessToken);
					checkToken.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*private static class Listener implements AWTEventListener {
        public void eventDispatched(AWTEvent event) {
            System.out.print(MouseInfo.getPointerInfo().getLocation() + " | ");
            System.out.println(event);
        }
    }*/

	/**
	 * Create the frame.
	 */
	public static boolean isStart = false;
	public static boolean isStop = false;
	public AutomationAutoNumKey automationAutoNumKey;
	public AutomationAutoRightClick automationAutoRightClick;
	public AutomationAutoShout automationAutoShout;
	public AutomationAutoSpace automationAutoSpace;
	public Thread thread;
	public static int startIntrv;
	public static int intrv;
	static TrayIcon trayIcon;

	public static void detectHotkey() {
		KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK);
	}
	public static void systemTray(Automation frame) {
		if (SystemTray.isSupported()) {
			trayIcon = new TrayIcon(createIcon("/com/au2macro/automation/image/systemtrayicon.png", "Icon"));
			trayIcon.setToolTip("Au2macro");
			final SystemTray tray = SystemTray.getSystemTray();
			final PopupMenu menu = new PopupMenu();
			MenuItem show = new MenuItem("Show");
			MenuItem exit = new MenuItem("Exit");
			menu.add(show);
			menu.add(exit);

			show.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					tray.remove(trayIcon);
					frame.setVisible(true);
				}
			});
			exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					logOut(token);
					System.exit(0);
				}
			});
			trayIcon.setPopupMenu(menu);
			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						tray.remove(trayIcon);
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					}
				}
			});
			try {
				tray.add(trayIcon);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}else {
			JOptionPane.showMessageDialog(null, "System tray is not supported.");
		}
	}
	public static Image createIcon(String path, String desc) {
		URL imageURL = Automation.class.getResource(path);
		return (new ImageIcon(imageURL,desc)).getImage();
	}

	public Automation() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Automation.class.getResource("/com/au2macro/automation/image/icon.png")));
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				System.out.println("keyTyped");
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				Automation frame = new Automation();
				systemTray(frame);
				setLocationRelativeTo(null);
				setVisible(false);
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				logOut(token);
			}
		});
		setTitle("Au2Macro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnStart = new JButton("Start");
		btnStart.setForeground(new Color(0, 128, 0));
		btnStart.setBackground(UIManager.getColor("Button.background"));
		JButton btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.setForeground(new Color(128, 0, 0));
		btnStop.setBackground(UIManager.getColor("Button.background"));
		txtInterval = new JTextField();
		txtStartInterval = new JTextField();
		List listLogs = new List();
		JRadioButton rdbtnClick = new JRadioButton("Auto Right Click");
		rdbtnClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInterval.setText("50");
			}
		});
		JRadioButton rdbtnSpace = new JRadioButton("Auto Space");
		rdbtnSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInterval.setText("50");
			}
		});
		JRadioButton rdbtnShout = new JRadioButton("Auto Shout");
		rdbtnShout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtInterval.setText("20000");
			}
		});
		JRadioButton rdbtnNumber = new JRadioButton("Auto 1 ~ =");
		rdbtnNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInterval.setText("50");
			}
		});
		rdbtnNumber.setSelected(true);
		ButtonGroup buttonGroup = new ButtonGroup();

		buttonGroup.add(rdbtnClick);
		buttonGroup.add(rdbtnSpace);
		buttonGroup.add(rdbtnShout);
		buttonGroup.add(rdbtnNumber);

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!txtInterval.getText().isEmpty() && txtStartInterval.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please insert interval");
					}else {
						isStart = true;
						btnStop.setEnabled(true);
						btnStart.setEnabled(false);
						txtInterval.setEnabled(false);
						txtStartInterval.setEnabled(false);
						startIntrv = Integer.parseInt(txtStartInterval.getText());
						intrv = Integer.parseInt(txtInterval.getText());
						listLogs.add(new SimpleDateFormat("h:mm a").format(new Date(System.currentTimeMillis()))+": Starting service...");
						/*System.out.println("is1: "+is1);
						System.out.println("is2: "+is2);
						System.out.println("is3: "+is3);
						System.out.println("is4: "+is4);
						System.out.println("is5: "+is5);
						System.out.println("is6: "+is6);
						System.out.println("is7: "+is7);
						System.out.println("is8: "+is8);
						System.out.println("is9: "+is9);
						System.out.println("is0: "+is0);
						System.out.println("is-: "+isMinus);
						System.out.println("is=: "+isEqual);*/
						if (rdbtnClick.isSelected() == true) {
							//System.out.println("rdbtnClick");
							automationAutoRightClick = new AutomationAutoRightClick();
							thread = new Thread(automationAutoRightClick);
							thread.start();
						}else if (rdbtnSpace.isSelected() == true) {
							//System.out.println("rdbtnSpace");
							automationAutoSpace = new AutomationAutoSpace();
							thread = new Thread(automationAutoSpace);
							thread.start();
						}else if (rdbtnShout.isSelected() == true) {
							//System.out.println("rdbtnSpace");
							automationAutoShout = new AutomationAutoShout();
							thread = new Thread(automationAutoShout);
							thread.start();
						}else if (rdbtnNumber.isSelected() == true) {
							//System.out.println("rdbtnNumber");
							automationAutoNumKey = new AutomationAutoNumKey();
							thread = new Thread(automationAutoNumKey);
							thread.start();
						}else {
							JOptionPane.showMessageDialog(null, "Option is not available", "au2macro", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (Exception exception) {
					System.err.println(exception.getMessage());
				}

			}
		});
		btnStart.setBounds(388, 177, 89, 30);
		contentPane.add(btnStart);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (isStart == true) {
						isStart = false;
						btnStart.setEnabled(true);
						btnStop.setEnabled(false);
						txtInterval.setEnabled(true);
						txtStartInterval.setEnabled(true);
						listLogs.add(new SimpleDateFormat("h:mm a").format(new Date(System.currentTimeMillis()))+": Stopping service!");
						thread.interrupt();
						if (automationAutoNumKey != null) {
							automationAutoNumKey = null;
						}
						if (automationAutoRightClick != null) {
							automationAutoRightClick = null;
						}
						if (automationAutoSpace != null) {
							automationAutoSpace = null;
						}
						if (automationAutoShout != null) {
							automationAutoShout = null;
						}
					}
				} catch (Exception exception) {
					System.err.println(exception.getMessage());
				}
			}
		});
		btnStop.setBounds(388, 212, 89, 30);
		contentPane.add(btnStop);

		txtInterval.setText("50");
		txtInterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtInterval.getText().isEmpty()) {
					try {
						int i = Integer.parseInt(txtInterval.getText());
						System.out.println(i);
						if (txtInterval.getText().length() > 6) {
							Document doc = txtInterval.getDocument();
							if (doc.getLength() > 0) {
								doc.remove(doc.getLength() - 1, 1);
							}
						}
					} catch (Exception exception) {
						txtInterval.setText("");
					}
				}
			}
		});
		txtInterval.setBounds(262, 193, 100, 25);
		contentPane.add(txtInterval);

		JLabel lblNewLabel = new JLabel("Interval");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(262, 168, 46, 14);
		contentPane.add(lblNewLabel);

		txtStartInterval.setText("2000");
		txtStartInterval.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtStartInterval.getText().isEmpty()) {
					try {
						int i = Integer.parseInt(txtStartInterval.getText());
						System.out.println(i);
						if (txtStartInterval.getText().length() > 6) {
							Document doc = txtStartInterval.getDocument();
							if (doc.getLength() > 0) {
								doc.remove(doc.getLength() - 1, 1);
							}
						}
					} catch (Exception exception) {
						txtStartInterval.setText("");
					}
				}
			}
		});
		txtStartInterval.setBounds(262, 250, 100, 25);
		contentPane.add(txtStartInterval);

		JLabel lblStartInterval = new JLabel("Start Interval");
		lblStartInterval.setForeground(Color.BLACK);
		lblStartInterval.setBounds(262, 227, 86, 14);
		contentPane.add(lblStartInterval);

		JLabel lblAutoMacro = new JLabel("Au2macro");
		lblAutoMacro.setForeground(Color.BLACK);
		lblAutoMacro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutoMacro.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAutoMacro.setBounds(148, 33, 200, 30);
		contentPane.add(lblAutoMacro);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 84, 213, 220);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(listLogs);

		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(0, 0, 0));
		btnClear.setBackground(UIManager.getColor("Button.background"));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listLogs.removeAll();
			}
		});
		btnClear.setBounds(388, 247, 89, 30);
		contentPane.add(btnClear);

		JPanel panel = new JPanel();
		panel.setBounds(0, 1, 500, 21);
		contentPane.add(panel);
		panel.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 500, 21);
		panel.add(menuBar);

		JMenu mnFile = new JMenu("Menu");
		menuBar.add(mnFile);

		JMenuItem mntmSwitchAccount = new JMenuItem("Switch Account");
		mntmSwitchAccount.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				checkToken.interrupt();
				logOut(token);
				Login login = new Login();
				dispose();
				login.main(null);
			}
		});
		mntmSwitchAccount.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkToken.interrupt();
				logOut(token);
				Login login = new Login();
				dispose();
				login.main(null);
			}
		});
		mnFile.add(mntmSwitchAccount);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Au2macro v1.0\nContact: au2macro@gmail.com");
			}
		});
		
		JMenuItem mntmNumkey = new JMenuItem("Edit 1 ~ =");
		mntmNumkey.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				AutomationNumber automationNumber = new AutomationNumber();
				automationNumber.main(null);
			}
		});
		mnFile.add(mntmNumkey);
		mnFile.add(mntmAbout);

		JMenu mnExit = new JMenu("Exit");
		mnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int message = JOptionPane.showConfirmDialog(null, "Are you sure?", "Au2macro", 0);
				if (message == 0) {
					logOut(token);
					System.exit(0);
				}
			}
		});
		menuBar.add(mnExit);

		rdbtnClick.setForeground(Color.BLACK);
		rdbtnClick.setBackground(new Color(95, 158, 160));
		rdbtnClick.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnClick.setBounds(262, 96, 109, 23);
		rdbtnClick.setVisible(false);
		rdbtnClick.setEnabled(false);
		contentPane.add(rdbtnClick);

		rdbtnSpace.setForeground(Color.BLACK);
		rdbtnSpace.setBackground(new Color(95, 158, 160));
		rdbtnSpace.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnSpace.setBounds(262, 122, 109, 23);
		contentPane.add(rdbtnSpace);

		rdbtnShout.setForeground(Color.BLACK);
		rdbtnShout.setBackground(new Color(95, 158, 160));
		rdbtnShout.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnShout.setBounds(373, 96, 109, 23);
		contentPane.add(rdbtnShout);

		rdbtnNumber.setForeground(Color.BLACK);
		rdbtnNumber.setBackground(new Color(95, 158, 160));
		rdbtnNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnNumber.setBounds(373, 122, 109, 23);
		contentPane.add(rdbtnNumber);
	}
	
	static String response;
	static JSONObject jObject = new JSONObject();
	static JSONObject data = new JSONObject();
	public static StaticVariable SV;
	
	@SuppressWarnings("static-access")
	public static void logOut(String token) {
		try {
			@SuppressWarnings("deprecation")
			String request = "accessToken="+URLEncoder.encode(token);
			response = HttpConnection.httpRequest(request, SV.URL+"user.logout.php", "POST");
			jObject = new JSONObject(response);
			data = jObject.getJSONObject("data");
			if (data.getString("status").contains("success")) {
				System.out.println("logout success.");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
