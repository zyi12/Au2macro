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
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
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
import com.au2macro.automation.utils.UserLog;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Automation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6900633506525697942L;
	private JPanel contentPane;
	private static JTextField txtInterval;
	private static JTextField txtStartInterval;
	public static String token;
	public static int id;
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
	public static String changeTarget = "Z";
	
	static JCheckBox chckbxEqual = new JCheckBox("=");
	static JCheckBox chckbx6 = new JCheckBox("6");
	static JCheckBox chckbx5 = new JCheckBox("5");
	static JCheckBox chckbxMinus = new JCheckBox("-");
	static JCheckBox chckbx0 = new JCheckBox("0");
	static JCheckBox chckbx4 = new JCheckBox("4");
	static JCheckBox chckbx3 = new JCheckBox("3");
	static JCheckBox chckbx9 = new JCheckBox("9");
	static JCheckBox chckbx2 = new JCheckBox("2");
	static JCheckBox chckbx8 = new JCheckBox("8");
	static JCheckBox chckbx7 = new JCheckBox("7");
	static JCheckBox chckbx1 = new JCheckBox("1");
	static JComboBox cbTarget = new JComboBox();
	
	JRadioButton rdbtnClick = new JRadioButton("Auto Right Click");
	JRadioButton rdbtnSpace = new JRadioButton("Auto Space");
	JRadioButton rdbtnShout = new JRadioButton("Auto Shout");
	JRadioButton rdbtnNumber = new JRadioButton("Auto 1 ~ =");
	
	static List listLogs = new List();
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
					if (frame.isShowing()) {
						isCheck();
					}
					AccessToken accessToken = new AccessToken();
					accessToken.setToken(token);
					listLogs.add(new SimpleDateFormat("h:mm a").format(new Date(System.currentTimeMillis()))+": Authorized login.");
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
	public static AutomationAutoNumKey automationAutoNumKey;
	public static AutomationAutoRightClick automationAutoRightClick;
	public static AutomationAutoShout automationAutoShout;
	public static AutomationAutoSpace automationAutoSpace;
	public static Thread thread;
	public static int startIntrv;
	public static int intrv;
	static TrayIcon trayIcon;
	static JButton btnStart = new JButton("Start");
	static JButton btnStop = new JButton("Stop");

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
			MenuItem stop = new MenuItem("Stop");
			MenuItem exit = new MenuItem("Exit");
			menu.add(show);
			menu.add(stop);
			menu.add(exit);
			
			stop.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					stopService();
					tray.remove(trayIcon);
					frame.setVisible(true);
				}
			});
			show.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					tray.remove(trayIcon);
					if (btnStop.isEnabled() == false && btnStart.isEnabled() == false) {
						btnStop.setEnabled(true);
					}
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
				//System.out.println("keyTyped");
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
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnStart.setForeground(new Color(0, 128, 0));
		btnStart.setBackground(UIManager.getColor("Button.background"));
		btnStop.setEnabled(false);
		btnStop.setForeground(new Color(128, 0, 0));
		btnStop.setBackground(UIManager.getColor("Button.background"));
		txtInterval = new JTextField();
		txtStartInterval = new JTextField();
		
		rdbtnClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbDisable();
				txtInterval.setText("50");
			}
		});
		
		rdbtnSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbDisable();
				txtInterval.setText("50");
			}
		});
		
		rdbtnShout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rbDisable();
				txtInterval.setText("20000");
			}
		});
		
		rdbtnNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbEnable();
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
		cbTarget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		cbTarget.setModel(new DefaultComboBoxModel(new String[] {"Z", "Period"}));
		cbTarget.setBounds(191, 170, 89, 20);
		contentPane.add(cbTarget);
		
		JLabel lblChangeTarget = new JLabel("Change Target:");
		lblChangeTarget.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChangeTarget.setBounds(57, 173, 115, 14);
		contentPane.add(lblChangeTarget);
		chckbxEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		chckbxEqual.setSelected(true);
		chckbxEqual.setBackground(new Color(0,0,0,0));
		chckbxEqual.setOpaque(false);
		chckbxEqual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxEqual.setBounds(398, 130, 50, 23);
		contentPane.add(chckbxEqual);
		chckbx6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx6.setSelected(true);
		chckbx6.setBackground(new Color(0,0,0,0));
		chckbx6.setOpaque(false);
		chckbx6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx6.setBounds(398, 104, 50, 23);
		contentPane.add(chckbx6);
		chckbx5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx5.setSelected(true);
		chckbx5.setBackground(new Color(0,0,0,0));
		chckbx5.setOpaque(false);
		chckbx5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx5.setBounds(329, 104, 50, 23);
		contentPane.add(chckbx5);
		chckbxMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbxMinus.setSelected(true);
		chckbxMinus.setBackground(new Color(0,0,0,0));
		chckbxMinus.setOpaque(false);
		chckbxMinus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxMinus.setBounds(329, 130, 50, 23);
		contentPane.add(chckbxMinus);
		chckbx0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx0.setSelected(true);
		chckbx0.setBackground(new Color(0,0,0,0));
		chckbx0.setOpaque(false);
		chckbx0.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx0.setBounds(260, 130, 50, 23);
		contentPane.add(chckbx0);
		chckbx4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx4.setSelected(true);
		chckbx4.setBackground(new Color(0,0,0,0));
		chckbx4.setOpaque(false);
		chckbx4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx4.setBounds(260, 104, 50, 23);
		contentPane.add(chckbx4);
		chckbx3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx3.setSelected(true);
		chckbx3.setBackground(new Color(0,0,0,0));
		chckbx3.setOpaque(false);
		chckbx3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx3.setBounds(191, 104, 50, 23);
		contentPane.add(chckbx3);
		chckbx9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx9.setSelected(true);
		chckbx9.setBackground(new Color(0,0,0,0));
		chckbx9.setOpaque(false);
		chckbx9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx9.setBounds(191, 130, 50, 23);
		contentPane.add(chckbx9);
		chckbx2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx2.setSelected(true);
		chckbx2.setBackground(new Color(0,0,0,0));
		chckbx2.setOpaque(false);
		chckbx2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx2.setBounds(122, 104, 50, 23);
		contentPane.add(chckbx2);
		chckbx8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx8.setSelected(true);
		chckbx8.setBackground(new Color(0,0,0,0));
		chckbx8.setOpaque(false);
		chckbx8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx8.setBounds(122, 130, 50, 23);
		contentPane.add(chckbx8);
		chckbx7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx7.setSelected(true);
		chckbx7.setBackground(new Color(0,0,0,0));
		chckbx7.setOpaque(false);
		chckbx7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx7.setBounds(53, 130, 50, 23);
		contentPane.add(chckbx7);
		chckbx1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isCheck();
				checkItem();
			}
		});
		
		
		chckbx1.setSelected(true);
		chckbx1.setBackground(new Color(0,0,0,0));
		chckbx1.setOpaque(false);
		chckbx1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx1.setBounds(53, 104, 50, 23);
		contentPane.add(chckbx1);
		btnStart.setBounds(386, 315, 89, 30);
		contentPane.add(btnStart);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopService();
			}
		});
		btnStop.setBounds(386, 350, 89, 30);
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
		txtInterval.setBounds(260, 331, 80, 25);
		contentPane.add(txtInterval);

		JLabel lblNewLabel = new JLabel("Interval");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(260, 306, 46, 14);
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
		txtStartInterval.setBounds(260, 388, 80, 25);
		contentPane.add(txtStartInterval);

		JLabel lblStartInterval = new JLabel("Start Interval");
		lblStartInterval.setForeground(Color.BLACK);
		lblStartInterval.setBounds(260, 365, 86, 14);
		contentPane.add(lblStartInterval);

		JLabel lblAutoMacro = new JLabel("Au2macro");
		lblAutoMacro.setForeground(Color.BLACK);
		lblAutoMacro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutoMacro.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAutoMacro.setBounds(148, 33, 200, 30);
		contentPane.add(lblAutoMacro);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 223, 213, 220);
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
		btnClear.setBounds(386, 385, 89, 30);
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
				int i = JOptionPane.showConfirmDialog(null, "Are you sure to logout?", "au2macro", JOptionPane.OK_CANCEL_OPTION);
				if (i == 0) {
					checkToken.interrupt();
					logOut(token);
					Login login = new Login();
					dispose();
					login.main(null);	
				}
			}
		});
		mntmSwitchAccount.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null, "Are you sure to logout?", "au2macro", JOptionPane.OK_CANCEL_OPTION);
				if (i == 0) {
					checkToken.interrupt();
					logOut(token);
					Login login = new Login();
					dispose();
					login.main(null);	
				}
			}
		});
		mnFile.add(mntmSwitchAccount);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Au2macro v1.4\nContact: au2macro@gmail.com");
			}
		});
		
		/*JMenuItem mntmNumkey = new JMenuItem("Edit 1 ~ =");
		mntmNumkey.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				AutomationNumber automationNumber = new AutomationNumber();
				automationNumber.main(null);
			}
		});
		mnFile.add(mntmNumkey);*/
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
		rdbtnClick.setBackground(new Color(0,0,0,0));
		rdbtnClick.setOpaque(false);
		rdbtnClick.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnClick.setBounds(314, 197, 150, 23);
		rdbtnClick.setVisible(false);
		rdbtnClick.setEnabled(false);
		contentPane.add(rdbtnClick);

		rdbtnSpace.setForeground(Color.BLACK);
		rdbtnSpace.setBackground(new Color(0,0,0,0));
		rdbtnSpace.setOpaque(false);
		rdbtnSpace.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnSpace.setBounds(314, 247, 150, 23);
		contentPane.add(rdbtnSpace);

		rdbtnShout.setForeground(Color.BLACK);
		rdbtnShout.setBackground(new Color(0,0,0,0));
		rdbtnShout.setOpaque(false);
		rdbtnShout.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnShout.setBounds(314, 222, 150, 23);
		contentPane.add(rdbtnShout);

		rdbtnNumber.setForeground(Color.BLACK);
		rdbtnNumber.setBackground(new Color(0,0,0,0));
		rdbtnNumber.setOpaque(false);
		rdbtnNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNumber.setBounds(314, 272, 150, 23);
		contentPane.add(rdbtnNumber);
		
		JLabel lblMs = new JLabel("ms");
		lblMs.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMs.setForeground(Color.BLACK);
		lblMs.setBounds(344, 393, 25, 20);
		contentPane.add(lblMs);
		
		JLabel label = new JLabel("ms");
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setForeground(Color.BLACK);
		label.setBounds(344, 336, 25, 20);
		contentPane.add(label);
		
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBackground(Color.GREEN);
		backgroundPanel.setBounds(0, 1, 500, 471);
		contentPane.add(backgroundPanel);
		backgroundPanel.setLayout(new BorderLayout(0, 0));
		try {
			String path = SV.URL+"Background.jpg";
			URL url = new URL(path);
			BufferedImage image = ImageIO.read(url);
			JLabel labels = new JLabel(new ImageIcon(image));
			backgroundPanel.add(labels);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void stopService() {
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
			if (response.contains("\"data\"")) {
				jObject = new JSONObject(response);
				data = jObject.getJSONObject("data");
				if (data.getString("status").contains("success")) {
					System.out.println("logout success.");
					UserLog.createLog(String.valueOf(id), "Logout account.");
				}	
			}else {
				logOut(token);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void rbDisable() {
		chckbx1.setEnabled(false);
		chckbx2.setEnabled(false);
		chckbx3.setEnabled(false);
		chckbx4.setEnabled(false);
		chckbx5.setEnabled(false);
		chckbx6.setEnabled(false);
		chckbx7.setEnabled(false);
		chckbx8.setEnabled(false);
		chckbx9.setEnabled(false);
		chckbx0.setEnabled(false);
		chckbxMinus.setEnabled(false);
		chckbxEqual.setEnabled(false);
		cbTarget.setEnabled(false);
	}
	
	public static void rbEnable() {
		chckbx1.setEnabled(true);
		chckbx2.setEnabled(true);
		chckbx3.setEnabled(true);
		chckbx4.setEnabled(true);
		chckbx5.setEnabled(true);
		chckbx6.setEnabled(true);
		chckbx7.setEnabled(true);
		chckbx8.setEnabled(true);
		chckbx9.setEnabled(true);
		chckbx0.setEnabled(true);
		chckbxMinus.setEnabled(true);
		chckbxEqual.setEnabled(true);
		cbTarget.setEnabled(true);
	}
	
	public static void isCheck() {
		if (Automation.is1 == true) {
			chckbx1.setSelected(true);
		} else {
			chckbx1.setSelected(false);
		}
		if (Automation.is2 == true) {
			chckbx2.setSelected(true);
		} else {
			chckbx2.setSelected(false);
		}
		if (Automation.is3 == true) {
			chckbx3.setSelected(true);
		} else {
			chckbx3.setSelected(false);
		}
		if (Automation.is4 == true) {
			chckbx4.setSelected(true);
		} else {
			chckbx4.setSelected(false);
		}
		if (Automation.is5 == true) {
			chckbx5.setSelected(true);
		} else {
			chckbx5.setSelected(false);
		}
		if (Automation.is6 == true) {
			chckbx6.setSelected(true);
		} else {
			chckbx6.setSelected(false);
		}
		if (Automation.is7 == true) {
			chckbx7.setSelected(true);
		} else {
			chckbx7.setSelected(false);
		}
		if (Automation.is8 == true) {
			chckbx8.setSelected(true);
		} else {
			chckbx8.setSelected(false);
		}
		if (Automation.is9 == true) {
			chckbx9.setSelected(true);
		} else {
			chckbx9.setSelected(false);
		}
		if (Automation.is0 == true) {
			chckbx0.setSelected(true);
		} else {
			chckbx0.setSelected(false);
		}
		if (Automation.isMinus == true) {
			chckbxMinus.setSelected(true);
		} else {
			chckbxMinus.setSelected(false);
		}
		if (Automation.isEqual == true) {
			chckbxEqual.setSelected(true);
		} else {
			chckbxEqual.setSelected(false);
		}
	}
	
	public static void checkItem() {
		if (chckbx1.isSelected() == true) {
			is1 = true;
		}else {
			is1 = false;
		}
		if (chckbx2.isSelected() == true) {
			is2 = true;
		}else {
			is2 = false;
		}
		if (chckbx3.isSelected() == true) {
			is3 = true;
		}else {
			is3 = false;
		}
		if (chckbx4.isSelected() == true) {
			is4 = true;
		}else {
			is4 = false;
		}
		if (chckbx5.isSelected() == true) {
			is5 = true;
		}else {
			is5 = false;
		}
		if (chckbx6.isSelected() == true) {
			is6 = true;
		}else {
			is6 = false;
		}
		if (chckbx7.isSelected() == true) {
			is7 = true;
		}else {
			is7 = false;
		}
		if (chckbx8.isSelected() == true) {
			is8 = true;
		}else {
			is8 = false;
		}
		if (chckbx9.isSelected() == true) {
			is9 = true;
		}else {
			is9 = false;
		}
		if (chckbx0.isSelected() == true) {
			is0 = true;
		}else {
			is0 = false;
		}
		if (chckbxMinus.isSelected() == true) {
			isMinus = true;
		}else {
			isMinus = false;
		}
		if (chckbxEqual.isSelected() == true) {
			isEqual = true;
		}else {
			isEqual = false;
		}
		changeTarget = cbTarget.getSelectedItem().toString();
	}
}
