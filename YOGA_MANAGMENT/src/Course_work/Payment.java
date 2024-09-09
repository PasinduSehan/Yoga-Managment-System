package Course_work;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Payment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField payment;
	private JTextField amount;
	private JTextField pid;
	private JTextField pname;
	private JTextField page;
	private JTextField paddress;
	private JTextField pmobile;
	private JTable table;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Payment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 834, 461);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Manage Finance");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(352, 39, 228, 40);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblPhoneNumber = new JLabel("Payments");
		lblPhoneNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPhoneNumber.setBounds(422, 114, 103, 20);
		panel_1.add(lblPhoneNumber);
		
		payment = new JTextField();
		payment.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		payment.setColumns(10);
		payment.setBounds(422, 143, 103, 20);
		panel_1.add(payment);
		
		JLabel lblNewLabel_2_2 = new JLabel("Amount");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(171, 282, 84, 20);
		panel_1.add(lblNewLabel_2_2);
		
		amount = new JTextField();
		amount.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		amount.setColumns(10);
		amount.setBounds(171, 313, 103, 20);
		panel_1.add(amount);
		
		class Connect {
		    private Connection con;
		    private PreparedStatement pat;

		    public Connect() {
		        try {
		            
		        	
		        	
		            Class.forName("com.mysql.cj.jdbc.Driver");

		            String jdbcUrl = "jdbc:mysql://localhost:3310/yogamanagment";
		            String username = "your_username";
		            String password = "your_password";

		            con = DriverManager.getConnection(jdbcUrl, username, password);

		           
		            pat = con.prepareStatement("Your SQL Query");

		           
		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
		        }
		    }
		}
		
		            Connection Con = null;
					PreparedStatement pst = null;
					ResultSet Rs = null;
					Statement St = null;
					
		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Member Id");
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2_1_1.setBounds(287, 143, 84, 20);
		panel_1.add(lblNewLabel_2_1_1);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPay.addMouseListener(new MouseAdapter() {	

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (pid.getText().isEmpty() ||pname.getText().isEmpty() || page.getText().isEmpty() || paddress.getText().isEmpty() || pmobile.getText().isEmpty() /*||month.getText().isEmpty()*/ || amount.getText().isEmpty() ) {
		            JOptionPane.showMessageDialog(null, "Missing Information", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                
		               PreparedStatement add = con.prepareStatement("INSERT INTO payment (id,name, age, address, mobile,amount) VALUES (?, ?, ?, ?, ?,?)");
		               
		               add.setInt(1, Integer.parseInt(pid.getText()));
		                add.setString(2, pname.getText());
		                add.setInt(3, Integer.parseInt(page.getText()));
		                add.setString(4, paddress.getText());
		                add.setInt(5, Integer.parseInt(pmobile.getText()));
		            
		                add.setInt(6, Integer.parseInt(amount.getText()));
		                
		                int rowsAffected = add.executeUpdate();

		                if (rowsAffected > 0) {
		                    JOptionPane.showMessageDialog(null, "Payment saved");

		                    pid.setText("");
		                    pname.setText("");
		                    page.setText("");
		                    paddress.setText("");
		                    pmobile.setText("");
		               
		                    amount.setText("");
		                    
		                    displayAllData();
		                } else {
		                    JOptionPane.showMessageDialog(null, "Failed to save Payment");
		                }
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
			 
			 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
			        
			        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM payment");

			        
			        ResultSet rs = retrieve.executeQuery();

			      
			        DefaultTableModel model = new DefaultTableModel();
			        model.addColumn("Id");
			        model.addColumn("Name");
			        model.addColumn("Age");
			        model.addColumn("Address");
			        model.addColumn("Mobile");
			        model.addColumn("Month");
			        model.addColumn("Amount");
			        
			        while (rs.next()) {
			            model.addRow(new Object[]{
			                rs.getInt("id"),
			                rs.getString("name"),
			                rs.getInt("age"),
			                rs.getString("address"),
			                rs.getInt("mobile"),
			                rs.getString("month"),
			                rs.getInt("amount"),
			            });
			        }

			        
			        table.setModel(model);
			        displayAllData();
			    } catch (SQLException e1) {
			        e1.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			    }
				
				
			

			}
			private void displayAllData() {
			
				 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
				        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM payment");

				        
				        ResultSet rs = retrieve.executeQuery();

				       
				        DefaultTableModel model = new DefaultTableModel();
				        model.addColumn("Id");
				        model.addColumn("Name");
				        model.addColumn("Age");
				        model.addColumn("Address");
				        model.addColumn("Mobile");
				        model.addColumn("Month");
				        model.addColumn("Amount");
				        
				        while (rs.next()) {
				            model.addRow(new Object[]{
				                rs.getInt("id"),
				                rs.getString("name"),
				                rs.getInt("age"),
				                rs.getString("address"),
				                rs.getInt("mobile"),
				                rs.getInt("month"),
				                rs.getInt("amount")
				            });
				        }

				        Table();
				        table.setModel(model);

				    } catch (SQLException e) {
				        e.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				    }
				
				
			}
		});
		
		
		
		btnPay.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPay.setBounds(171, 356, 84, 28);
		panel_1.add(btnPay);
		
		
		
		
		JButton btnSerch = new JButton("Search");
		btnSerch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				 String memberId = payment.getText().trim();

			        if (!memberId.isEmpty()) {
			            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
			                PreparedStatement retrieve = con.prepareStatement("SELECT * FROM payment WHERE id = ?");
			                retrieve.setInt(1, Integer.parseInt(memberId));

			                ResultSet rs = retrieve.executeQuery();

			                DefaultTableModel model = new DefaultTableModel();
			                model.addColumn("Id");
			                model.addColumn("Name");
			                model.addColumn("Age");
			                model.addColumn("Address");
			                model.addColumn("Mobile");
			                model.addColumn("Month");
			                model.addColumn("Amount");

			                while (rs.next()) {
			                    model.addRow(new Object[]{
			                        rs.getInt("id"),
			                        rs.getString("name"),
			                        rs.getInt("age"),
			                        rs.getString("address"),
			                        rs.getInt("mobile"),
			                        rs.getString("month"),  
			                        rs.getInt("amount")
			                    });
			                }

			                table.setModel(model);
			                payment.setText("");
			                
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Please enter a valid Member ID to search.");
			        }
				
				
				
			}
		});
		
		
		
		btnSerch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSerch.setBounds(535, 141, 84, 24);
		panel_1.add(btnSerch);
		
		
		
		JButton btnRefresh = new JButton("Delete all");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				  int selecte = table.getSelectedRow();

			        if (selecte != -1) {
			            int memberDelete = (int) table.getValueAt(selecte, 0); 

			            
			            int Delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
			            
			            
			            if (Delete == JOptionPane.YES_OPTION) {
			                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
			                    PreparedStatement deletestatement = con.prepareStatement("DELETE FROM payment WHERE id = ?");
			                    deletestatement.setInt(1, memberDelete);

			                    DisplayAllData();
			                    int rowsDelete = deletestatement.executeUpdate();

			                    if (rowsDelete > 0) {
			                        JOptionPane.showMessageDialog(null, "Deleted successfully.");
			                        DefaultTableModel model = (DefaultTableModel) table.getModel();
			                        model.removeRow(selecte); 
			                        
			                    } else {
			                        JOptionPane.showMessageDialog(null, "Failed to delete.");
			                    }
			                } catch (SQLException ex) {
			                    ex.printStackTrace();
			                    
			                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
			        }
				
			}

			private void DisplayAllData() {
				// TODO Auto-generated method stub
				 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
				        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM payment");

				        
				        ResultSet rs = retrieve.executeQuery();

				       
				        DefaultTableModel model = new DefaultTableModel();
				        model.addColumn("Id");
				        model.addColumn("Name");
				        model.addColumn("Age");
				        model.addColumn("Address");
				        model.addColumn("Mobile");
				        model.addColumn("Month");
				        model.addColumn("Amount");
				        
				        while (rs.next()) {
				            model.addRow(new Object[]{
				                rs.getInt("id"),
				                rs.getString("name"),
				                rs.getInt("age"),
				                rs.getString("address"),
				                rs.getInt("mobile"),
				                rs.getInt("month"),
				                rs.getInt("amount")
				            });
				        }

				        Table();
				        table.setModel(model);

				    } catch (SQLException e) {
				        e.printStackTrace();
				        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				    }
				
				
				
			}
		});
		
		
		
		btnRefresh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnRefresh.setBounds(629, 141, 103, 24);
		panel_1.add(btnRefresh);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Main m = new Main();
				setVisible(false);
				m.setVisible(true);
				
				
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnBack.setBounds(733, 415, 91, 35);
		panel_1.add(btnBack);
		
		pid = new JTextField();
		pid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				String memberId = pid.getText().trim();

		        if (!memberId.isEmpty()) {
		            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                
		            	
		                PreparedStatement retrieve = con.prepareStatement("SELECT * FROM member WHERE id = ?");
		                retrieve.setInt(1, Integer.parseInt(memberId));

		                
		                
		                ResultSet rs = retrieve.executeQuery();

		                if (rs.next()) {
		                   
		                	
		                	
		                    pname.setText(rs.getString("name"));
		                    page.setText(Integer.toString(rs.getInt("age")));
		                    paddress.setText(rs.getString("address"));
		                    pmobile.setText(Integer.toString(rs.getInt("mobile")));
		                } else {
		                   
		                	
		                    pname.setText("");
		                    page.setText("");
		                    paddress.setText("");
		                    pmobile.setText("");
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException ex) {
		              
		            	
		            	pname.setText("");
	                    page.setText("");
	                    paddress.setText("");
	                    pmobile.setText("");
		            }
		        } else {
		           
		        	
		        	pname.setText("");
                    page.setText("");
                    paddress.setText("");
                    pmobile.setText("");
		        }
				
				
				
				
				
			}
		});
		
		
		
		pid.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		pid.setColumns(10);
		pid.setBounds(171, 143, 103, 20);
		panel_1.add(pid);
		
		pname = new JTextField();
		pname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		pname.setColumns(10);
		pname.setBounds(171, 177, 103, 20);
		panel_1.add(pname);
		
		page = new JTextField();
		page.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		page.setColumns(10);
		page.setBounds(171, 203, 103, 20);
		panel_1.add(page);
		
		paddress = new JTextField();
		paddress.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		paddress.setColumns(10);
		paddress.setBounds(171, 228, 103, 20);
		panel_1.add(paddress);
		
		pmobile = new JTextField();
		pmobile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		pmobile.setColumns(10);
		pmobile.setBounds(171, 251, 103, 20);
		panel_1.add(pmobile);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(352, 174, 472, 209);
		panel_1.add(scrollPane);
		

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int c = table.getColumnModel().getColumnIndex("Delete");
		        int r = table.rowAtPoint(e.getPoint());
		        
		        if (c == 7 && r >= 0) {
		            int memberIdDelete = (int) table.getValueAt(r, 0); 
		            

		            int Delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

		            if (Delete == JOptionPane.YES_OPTION) {
		                DefaultTableModel model = (DefaultTableModel) table.getModel();
		                model.removeRow(r); 
		                

		                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                    PreparedStatement deletesta = con.prepareStatement("DELETE FROM payment WHERE id = ?");
		                    deletesta.setInt(1, memberIdDelete);
		                    deletesta.executeUpdate();
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        }
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Age", "Address", "Mobile", "Month", "Amount"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(125);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		
		JLabel lblSearchId = new JLabel("Enter Id\r\n");
		lblSearchId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSearchId.setBounds(171, 118, 91, 21);
		panel_1.add(lblSearchId);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(0, 0, 128));
		panel_1_1.setBounds(0, 0, 161, 461);
		panel_1.add(panel_1_1);
		
		
		
		Table();
		
	}
	
	private void Table() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();

	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
	        String sql = "SELECT id, name, age, address, mobile, month , amount FROM payment";
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        ResultSet result = preparedStatement.executeQuery();

	        while (result.next()) {
	            int ID = result.getInt("id");
	            String Name = result.getString("name");
	            int age = result.getInt("age");
	            String address = result.getString("address");
	            String mobile = result.getString("mobile");
	            String month = result.getString("month");
	            int amount = result.getInt("amount");

	            model.addRow(new Object[] { ID, Name, age, address, mobile, month, amount });
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    
	    }

	}
	
	
	
	
	
}


