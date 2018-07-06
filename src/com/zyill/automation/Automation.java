package com.zyill.automation;

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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

public class Automation extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6900633506525697942L;
	private JPanel contentPane;
	private JTextField txtInterval;
	private JTextField txtStartInterval;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Automation frame = new Automation();
					frame.setExtendedState(JFrame.ICONIFIED);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					//detectHotkey();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static boolean isStart = false;
	public static boolean isStop = false;
	public AutomationUtils automationUtils;
	public Thread thread;
	public static int startIntrv;
	public static int intrv;
	static TrayIcon trayIcon;

	public static void detectHotkey() {
		KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_DOWN_MASK|InputEvent.SHIFT_DOWN_MASK);
	}
	public static void systemTray(Automation frame) {
		if (SystemTray.isSupported()) {
			trayIcon = new TrayIcon(createIcon("/com/zyill/automation/image/systemtrayicon.png", "Icon"));
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Automation.class.getResource("/com/zyill/automation/image/icon.png")));
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
		});
		setTitle("Au2Macro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 350);
		contentPane = new JPanel();
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
						//Thread.sleep(startIntrv);
						automationUtils = new AutomationUtils();
						thread = new Thread(automationUtils);
						thread.start();
					}
				} catch (Exception exception) {
					System.err.println(exception.getMessage());
				}

			}
		});
		btnStart.setBounds(164, 45, 89, 30);
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
						automationUtils = null;
					}
				} catch (Exception exception) {
					System.err.println(exception.getMessage());
				}
			}
		});
		btnStop.setBounds(164, 80, 89, 30);
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
		txtInterval.setBounds(38, 61, 100, 25);
		contentPane.add(txtInterval);

		JLabel lblNewLabel = new JLabel("Interval");
		lblNewLabel.setBounds(38, 36, 46, 14);
		contentPane.add(lblNewLabel);

		txtStartInterval.setText("5000");
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
		txtStartInterval.setBounds(38, 118, 100, 25);
		contentPane.add(txtStartInterval);

		JLabel lblStartInterval = new JLabel("Start Interval");
		lblStartInterval.setBounds(38, 95, 86, 14);
		contentPane.add(lblStartInterval);

		JLabel lblAutoMacro = new JLabel("Au2 Macro");
		lblAutoMacro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutoMacro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutoMacro.setBounds(94, 2, 100, 30);
		contentPane.add(lblAutoMacro);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 168, 213, 135);
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
		btnClear.setBounds(164, 115, 89, 30);
		contentPane.add(btnClear);
	}
}
