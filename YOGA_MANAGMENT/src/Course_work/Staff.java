package Course_work;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Staff extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField sname;
	private JTextField sage;
	private JTextField saddress;
	private JTextField smobile;
	private JTextField ssearch;
	private JTable table;
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff frame = new Staff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	
	public Staff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 884, 561);
		contentPane.add(panel);
	
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(new Color(0, 0, 102));
		panel_2.setBounds(0, 0, 884, 72);
		panel.add(panel_2);
		
		JLabel lblMember = new JLabel("STAFF Member");
		lblMember.setForeground(Color.WHITE);
		lblMember.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblMember.setBounds(336, 30, 178, 31);
		panel_2.add(lblMember);
		
		JLabel lblMemberName = new JLabel(" Name");
		lblMemberName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMemberName.setBounds(44, 106, 91, 21);
		panel.add(lblMemberName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAge.setBounds(44, 168, 91, 21);
		panel.add(lblAge);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAddress.setBounds(44, 230, 91, 21);
		panel.add(lblAddress);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMobile.setBounds(44, 289, 91, 21);
		panel.add(lblMobile);
		
		sname = new JTextField();
		sname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		sname.setColumns(10);
		sname.setBounds(223, 107, 163, 21);
		panel.add(sname);
		
		sage = new JTextField();
		sage.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		sage.setColumns(10);
		sage.setBounds(223, 169, 163, 21);
		panel.add(sage);
		
		saddress = new JTextField();
		saddress.setColumns(10);
		saddress.setBounds(223, 231, 163, 20);
		panel.add(saddress);
		
		smobile = new JTextField();
		smobile.setColumns(10);
		smobile.setBounds(223, 290, 163, 21);
		panel.add(smobile);
		
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
			
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 
			
				if (sname.getText().isEmpty() || sage.getText().isEmpty() || saddress.getText().isEmpty() || smobile.getText().isEmpty() ) {
			            JOptionPane.showMessageDialog(null, "Missing Information", "Error", JOptionPane.ERROR_MESSAGE);
			        } else {
			            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
			                
			               PreparedStatement add = con.prepareStatement("INSERT INTO staff (name, age, address, mobile) VALUES (?, ?, ?, ?)");
			               
			                add.setString(1, sname.getText());
			                add.setInt(2, Integer.parseInt(sage.getText()));
			                add.setString(3, saddress.getText());
			                add.setInt(4, Integer.parseInt(smobile.getText()));
			               
			                
			                int rowsA = add.executeUpdate();

			                if (rowsA > 0) {
			                    JOptionPane.showMessageDialog(null, "staff saved");

			                    
			                    sname.setText("");
			                    sage.setText("");
			                    saddress.setText("");
			                    smobile.setText("");
			                  
			                    
			                    displayAllData();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Failed to save Staff");
			                }
			            } catch (SQLException e1) {
			                e1.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }
				 
				 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				        
				        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM staff");

				        
				        ResultSet rs = retrieve.executeQuery();

				      
				        DefaultTableModel model = new DefaultTableModel();
				        model.addColumn("Id");
				        model.addColumn("Name");
				        model.addColumn("Age");
				        model.addColumn("Address");
				        model.addColumn("Mobile");
				  
				        
				        while (rs.next()) {
				            model.addRow(new Object[]{
				                rs.getInt("id"),
				                rs.getString("name"),
				                rs.getInt("age"),
				                rs.getString("address"),
				                rs.getInt("mobile"),
				              
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
			      
			        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM staff");

			        
			        ResultSet rs = retrieve.executeQuery();

			       
			        DefaultTableModel model = new DefaultTableModel();
			        model.addColumn("Id");
			        model.addColumn("Name");
			        model.addColumn("Age");
			        model.addColumn("Address");
			        model.addColumn("Mobile");
			   
			        
			        while (rs.next()) {
			            model.addRow(new Object[]{
			                rs.getInt("id"),
			                rs.getString("name"),
			                rs.getInt("age"),
			                rs.getString("address"),
			                rs.getInt("mobile")			           
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
			
		
		
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAdd.setBounds(44, 385, 91, 35);
		panel.add(btnAdd);
		
		JLabel lblSearchId = new JLabel("Search Id\r\n");
		lblSearchId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSearchId.setBounds(44, 491, 91, 21);
		panel.add(lblSearchId);
		
		ssearch = new JTextField();
		ssearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				String memberId = ssearch.getText().trim();

		        if (!memberId.isEmpty()) {
		            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                
		            	
		                PreparedStatement retrieve = con.prepareStatement("SELECT * FROM staff WHERE id = ?");
		                retrieve.setInt(1, Integer.parseInt(memberId));

		                
		                
		                ResultSet rs = retrieve.executeQuery();

		                if (rs.next()) {
		                   
		                	
		                    sname.setText(rs.getString("name"));
		                    sage.setText(Integer.toString(rs.getInt("age")));
		                    saddress.setText(rs.getString("address"));
		                    smobile.setText(Integer.toString(rs.getInt("mobile")));
		                 

		                } else {
		                   
		                	
		                    sname.setText("");
		                    sage.setText("");
		                    saddress.setText("");
		                    smobile.setText("");
		                   
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException ex) {
		              
		            	
		                sname.setText("");
		                sage.setText("");
		                saddress.setText("");
		                smobile.setText("");
		               
		            }
		        } else {
		           
		            sname.setText("");
		            
		            sage.setText("");
		            saddress.setText("");
		            smobile.setText("");
		           
		        }
				
				
			}
		});
		
		ssearch.setColumns(10);
		ssearch.setBounds(223, 492, 163, 20);
		panel.add(ssearch);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);
				
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnExit.setBounds(169, 385, 91, 35);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				   sname.setText("");
			       sage.setText("");
			       saddress.setText("");
			       smobile.setText("");
			       
				
				
			}
		});
		
		
		
		
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClear.setBounds(295, 385, 91, 35);
		panel.add(btnClear);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				displayAllData();
				String memberId = ssearch.getText().trim();

		        if (!memberId.isEmpty()) {
		            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                
		            	
		                PreparedStatement update = con.prepareStatement("UPDATE staff SET name=?, age=?, address=?, mobile=? WHERE id=?");

		               
		                
		                update.setString(1, sname.getText());
		                update.setInt(2, Integer.parseInt(sage.getText()));
		                update.setString(3, saddress.getText());
		                update.setInt(4, Integer.parseInt(smobile.getText()));
		                update.setInt(5, Integer.parseInt(memberId)); 

		         
		                
		                int rowsA = update.executeUpdate();

		                if (rowsA > 0) {
		                    JOptionPane.showMessageDialog(null, "Staff updated");
		                    
		                    sname.setText("");
		                    sage.setText("");
		                    saddress.setText("");
		                    smobile.setText("");
		                    ssearch.setText(""); 

		                    
		                 
		                    
		                } else {
		                    JOptionPane.showMessageDialog(null, "Failed to update Staff");
		                }
		                displayAllData();
		                
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException ex) {
		                
		            	
		                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            
		        	
		            JOptionPane.showMessageDialog(null, "Please enter an ID to update a Staff.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
			
		        displayAllData();
		   
			}

			private void displayAllData() {
				// TODO Auto-generated method stub
				
				
				 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
				        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM staff");

				        
				        ResultSet rs = retrieve.executeQuery();

				       
				        DefaultTableModel model = new DefaultTableModel();
				        model.addColumn("Id");
				        model.addColumn("Name");
				        model.addColumn("Age");
				        model.addColumn("Address");
				        model.addColumn("Mobile");
				   
				        
				        while (rs.next()) {
				            model.addRow(new Object[]{
				                rs.getInt("id"),
				                rs.getString("name"),
				                rs.getInt("age"),
				                rs.getString("address"),
				                rs.getInt("mobile")			           
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
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnUpdate.setBounds(501, 477, 91, 35);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String memberId = ssearch.getText().trim();

		        if (!memberId.isEmpty()) {
		            int confirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Staff member?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

		            if (confirmDialog == JOptionPane.YES_OPTION) {
		                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                    
		                	
		                	
		                    PreparedStatement deleteS = con.prepareStatement("DELETE FROM Staff WHERE id=?");

		                 
		                    deleteS.setInt(1, Integer.parseInt(memberId));

		                    int rowsA = deleteS.executeUpdate();

		                    
		                    if (rowsA > 0) {
		                        JOptionPane.showMessageDialog(null, "Staff deleted");

		                       
		                       
		                        sname.setText("");
		                        sage.setText("");
		                        saddress.setText("");
		                        smobile.setText("");
		                        ssearch.setText(""); 

		                     
		                        displayAllData();
		                        
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Failed to delete Staff");
		                    }
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		                } catch (NumberFormatException ex) {
		                   
		                	
		                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		            
		        } else {
		            
		        	
		            JOptionPane.showMessageDialog(null, "Please enter an ID to delete a Staff.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
				
				
				
			}

			private void displayAllData() {
				// TODO Auto-generated method stub
				 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
				        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM staff");

				        
				        ResultSet rs = retrieve.executeQuery();

				       
				        DefaultTableModel model = new DefaultTableModel();
				        model.addColumn("Id");
				        model.addColumn("Name");
				        model.addColumn("Age");
				        model.addColumn("Address");
				        model.addColumn("Mobile");
				   
				        
				        while (rs.next()) {
				            model.addRow(new Object[]{
				                rs.getInt("id"),
				                rs.getString("name"),
				                rs.getInt("age"),
				                rs.getString("address"),
				                rs.getInt("mobile")			           
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
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelete.setBounds(640, 477, 91, 35);
		panel.add(btnDelete);
		
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
		btnBack.setBounds(770, 477, 91, 35);
		panel.add(btnBack);
		
		Container btnNewButton = null;
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(444, 106, 417, 217);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
					
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Id");
					model.addColumn("Name");
					model.addColumn("Age");
					model.addColumn("Address");
					model.addColumn("Mobile");
					
			
					
					table.setModel(model);
                  
					displayAllData();
				}

			private void displayAllData() {
				// TODO Auto-generated method stub
				
				
				
				
				
			}

		
			
			
		});
		
	
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Age", "Address", "Mobile"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(83);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		
		
		
		Table();
		
	}
	
	

	private void Table() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();

	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
	        String sql = "SELECT id, name, age, address, mobile FROM staff";
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int ID = resultSet.getInt("id");
	            String name = resultSet.getString("name");
	            int age = resultSet.getInt("age");
	            String address = resultSet.getString("address");
	            int mobile = resultSet.getInt("mobile");

	            model.addRow(new Object[] { ID, name, age, address, mobile });
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    
	    }

	}
	
	

	
	
}






