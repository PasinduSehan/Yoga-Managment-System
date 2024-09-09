package Course_work;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtuname;
	private JPasswordField txtpass;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(216, 85, 42, 0);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 486, 452);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 102));
		panel_2.setForeground(new Color(0, 0, 0));
		panel_2.setBounds(0, 0, 486, 72);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YOGA MANAGMENT SYSTEM");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(110, 30, 289, 31);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(74, 262, 69, 26);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(74, 318, 69, 26);
		panel_1.add(lblNewLabel_2);
		
		txtuname = new JTextField();
		txtuname.setBounds(231, 263, 161, 26);
		panel_1.add(txtuname);
		txtuname.setColumns(10);
	
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String uname = txtuname.getText();
		        String pass = new String(txtpass.getPassword());
		        
		        if (uname.isEmpty() && pass.isEmpty()) {
		        	 JOptionPane.showMessageDialog(null, "User Name Or Password Blank");
		        }

		        else if (uname.equals("Admin") && pass.equals("123")) {
		            Main m = new Main();
		            setVisible(false); 
		            m.setVisible(true); 
		        } else {
		            JOptionPane.showMessageDialog(null, "User Name Or Password Do Not Match");
		        }
		    }
		});
		
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(193, 383, 98, 26);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANSAL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(329, 383, 98, 26);
		panel_1.add(btnNewButton_1);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(231, 319, 161, 26);
		panel_1.add(txtpass);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("C:\\eclips\\e_slip\\YOGA_MANAGMENT\\photo\\5.jfif"));
		lblNewLabel_2_1.setBounds(74, 99, 318, 130);
		panel_1.add(lblNewLabel_2_1);
	}
}


