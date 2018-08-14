package com.au2macro.automation;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AutomationNumber extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4599907402647536231L;
	private JPanel contentPane;
	static JCheckBox chckbxequal = new JCheckBox("=");
	static JCheckBox chckbxminus = new JCheckBox("-");
	static JCheckBox chckbx0 = new JCheckBox("0");
	static JCheckBox chckbx9 = new JCheckBox("9");
	static JCheckBox chckbx8 = new JCheckBox("8");
	static JCheckBox chckbx7 = new JCheckBox("7");
	static JCheckBox chckbx6 = new JCheckBox("6");
	static JCheckBox chckbx5 = new JCheckBox("5");
	static JCheckBox chckbx4 = new JCheckBox("4");
	static JCheckBox chckbx3 = new JCheckBox("3");
	static JCheckBox chckbx2 = new JCheckBox("2");
	static JCheckBox chckbx1 = new JCheckBox("1");
	static JComboBox comboBox = new JComboBox();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutomationNumber frame = new AutomationNumber();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					if (frame.isShowing()) {
						isCheck();
					}
					checkItem();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
			chckbxminus.setSelected(true);
		} else {
			chckbxminus.setSelected(false);
		}
		if (Automation.isEqual == true) {
			chckbxequal.setSelected(true);
		} else {
			chckbxequal.setSelected(false);
		}
	}
	
	public static void checkItem() {
		if (chckbx1.isSelected() == true) {
			Automation.is1 = true;
		}else {
			Automation.is1 = false;
		}
		if (chckbx2.isSelected() == true) {
			Automation.is2 = true;
		}else {
			Automation.is2 = false;
		}
		if (chckbx3.isSelected() == true) {
			Automation.is3 = true;
		}else {
			Automation.is3 = false;
		}
		if (chckbx4.isSelected() == true) {
			Automation.is4 = true;
		}else {
			Automation.is4 = false;
		}
		if (chckbx5.isSelected() == true) {
			Automation.is5 = true;
		}else {
			Automation.is5 = false;
		}
		if (chckbx6.isSelected() == true) {
			Automation.is6 = true;
		}else {
			Automation.is6 = false;
		}
		if (chckbx7.isSelected() == true) {
			Automation.is7 = true;
		}else {
			Automation.is7 = false;
		}
		if (chckbx8.isSelected() == true) {
			Automation.is8 = true;
		}else {
			Automation.is8 = false;
		}
		if (chckbx9.isSelected() == true) {
			Automation.is9 = true;
		}else {
			Automation.is9 = false;
		}
		if (chckbx0.isSelected() == true) {
			Automation.is0 = true;
		}else {
			Automation.is0 = false;
		}
		if (chckbxminus.isSelected() == true) {
			Automation.isMinus = true;
		}else {
			Automation.isMinus = false;
		}
		if (chckbxequal.isSelected() == true) {
			Automation.isEqual = true;
		}else {
			Automation.isEqual = false;
		}
		Automation.changeTarget = comboBox.getSelectedItem().toString();
	}

	/**
	 * Create the frame.
	 */
	public AutomationNumber() {
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AutomationNumber.class.getResource("/com/au2macro/automation/image/icon.png")));
		setBounds(100, 100, 450, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		chckbx1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx1.setSelected(true);
		chckbx1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx1.setBounds(18, 60, 50, 23);
		contentPane.add(chckbx1);
		chckbx2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx2.setSelected(true);
		chckbx2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx2.setBounds(87, 60, 50, 23);
		contentPane.add(chckbx2);
		chckbx3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx3.setSelected(true);
		chckbx3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx3.setBounds(156, 60, 50, 23);
		contentPane.add(chckbx3);
		chckbx4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx4.setSelected(true);
		chckbx4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx4.setBounds(225, 60, 50, 23);
		contentPane.add(chckbx4);
		chckbx5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx5.setSelected(true);
		chckbx5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx5.setBounds(294, 60, 50, 23);
		contentPane.add(chckbx5);
		chckbx6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx6.setSelected(true);
		chckbx6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx6.setBounds(363, 60, 50, 23);
		contentPane.add(chckbx6);
		chckbx7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx7.setSelected(true);
		chckbx7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx7.setBounds(18, 86, 50, 23);
		contentPane.add(chckbx7);
		chckbx8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx8.setSelected(true);
		chckbx8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx8.setBounds(87, 86, 50, 23);
		contentPane.add(chckbx8);
		chckbx9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx9.setSelected(true);
		chckbx9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx9.setBounds(156, 86, 50, 23);
		contentPane.add(chckbx9);
		chckbx0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbx0.setSelected(true);
		chckbx0.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbx0.setBounds(225, 86, 50, 23);
		contentPane.add(chckbx0);
		chckbxminus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbxminus.setSelected(true);
		chckbxminus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxminus.setBounds(294, 86, 50, 23);
		contentPane.add(chckbxminus);
		chckbxequal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		
		chckbxequal.setSelected(true);
		chckbxequal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxequal.setBounds(363, 86, 50, 23);
		contentPane.add(chckbxequal);
		
		JLabel lblPleaseCheckuncheckYour = new JLabel("Please check/uncheck your option.");
		lblPleaseCheckuncheckYour.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseCheckuncheckYour.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPleaseCheckuncheckYour.setBounds(67, 11, 300, 20);
		contentPane.add(lblPleaseCheckuncheckYour);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnSave.setBounds(324, 137, 89, 23);
		contentPane.add(btnSave);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkItem();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Z", "Period"}));
		comboBox.setBounds(156, 126, 89, 20);
		contentPane.add(comboBox);
		
		JLabel lblChangeTarget = new JLabel("Change Target:");
		lblChangeTarget.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChangeTarget.setBounds(22, 129, 115, 14);
		contentPane.add(lblChangeTarget);
	}
}
