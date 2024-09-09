package Course_work;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main f = new Main();
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 456, 453);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(new Color(0, 0, 102));
		panel_2.setBounds(0, 0, 456, 72);
		panel.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("YOGA MANAGMENT SYSTEM");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(86, 30, 289, 31);
		panel_2.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add Trainer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Trainer t = new Trainer();
				
				setVisible(false);
				t.setVisible(true);
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(257, 250, 118, 33);
		panel.add(btnNewButton);
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Member m = new Member();
				
				setVisible(false);
				m.setVisible(true);
			}
		});
		btnAddMember.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAddMember.setBounds(74, 250, 118, 33);
		panel.add(btnAddMember);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\eclips\\e_slip\\YOGA_MANAGMENT\\photo\\666.jfif"));
		lblNewLabel_2.setBounds(130, 91, 201, 136);
		panel.add(lblNewLabel_2);
		
		JButton btnPayment = new JButton("Add Payment");
		btnPayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Payment p = new Payment();
				
				setVisible(false);
				p.setVisible(true);
			}
		});
		btnPayment.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPayment.setBounds(74, 315, 118, 33);
		panel.add(btnPayment);
		
		JButton btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Staff s = new Staff();
				
				setVisible(false);
				s.setVisible(true);
				
			}
		});
		btnAddStaff.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAddStaff.setBounds(257, 315, 118, 33);
		panel.add(btnAddStaff);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Product p = new Product();
				
				setVisible(false);
				p.setVisible(true);
				
			}
		});
		btnAddProduct.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnAddProduct.setBounds(74, 378, 118, 33);
		panel.add(btnAddProduct);
		
		JButton btnBuyProduct = new JButton("Buy Product");
		btnBuyProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Sell s = new Sell();
				
				setVisible(false);
				s.setVisible(true);
				
				
			}
		});
		btnBuyProduct.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnBuyProduct.setBounds(257, 378, 118, 33);
		panel.add(btnBuyProduct);
	}
}



