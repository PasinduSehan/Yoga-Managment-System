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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Member extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField mname;
	private JTextField mage;
	private JTextField maddress;
	private JTextField mmobile;
	private JTextField txtsearch;
	//private JTable table;
	private JTable table_1;
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member frame = new Member();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Member() {
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
		
		JLabel lblMember = new JLabel("MEMBER");
		lblMember.setForeground(Color.WHITE);
		lblMember.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblMember.setBounds(353, 30, 115, 31);
		panel_2.add(lblMember);
		
		JLabel lblMemberName = new JLabel("Member Name");
		lblMemberName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMemberName.setBounds(44, 106, 91, 21);
		panel.add(lblMemberName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAge.setBounds(44, 159, 91, 21);
		panel.add(lblAge);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAddress.setBounds(44, 209, 91, 21);
		panel.add(lblAddress);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMobile.setBounds(44, 258, 91, 21);
		panel.add(lblMobile);
		
		mname = new JTextField();
		mname.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mname.setColumns(10);
		mname.setBounds(223, 107, 163, 21);
		panel.add(mname);
		
		mage = new JTextField();
		mage.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mage.setColumns(10);
		mage.setBounds(223, 159, 163, 21);
		panel.add(mage);
		
		maddress = new JTextField();
		maddress.setColumns(10);
		maddress.setBounds(223, 210, 163, 20);
		panel.add(maddress);
		
		mmobile = new JTextField();
		mmobile.setColumns(10);
		mmobile.setBounds(223, 258, 163, 21);
		panel.add(mmobile);
		
		
		Connection con = null;
		PreparedStatement pat;
			

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

		
		
		
		Container btnNewButton = null;
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("ID");
				model.addColumn("Name");
				model.addColumn("Age");
				model.addColumn("Address");
				model.addColumn("Mobile");

			
				
				
				table_1.setModel(model);

				
				displayAllData();
			}

			private void displayAllData() {
				
				
			}
		});
		
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				   if (mname.getText().isEmpty() || mage.getText().isEmpty() || maddress.getText().isEmpty() || mmobile.getText().isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Missing Information", "Error", JOptionPane.ERROR_MESSAGE);
			            displayAllData();
			        } else {
			            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
			                
			                PreparedStatement add = con.prepareStatement("INSERT INTO member (name, age, address, mobile) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			                
			                add.setString(1, mname.getText());
			                add.setInt(2, Integer.parseInt(mage.getText()));
			                add.setString(3, maddress.getText());
			                add.setInt(4, Integer.parseInt(mmobile.getText()));

			                int rowsAff = add.executeUpdate();

			                if (rowsAff > 0) {
			                    JOptionPane.showMessageDialog(null, "Member saved");

			                    
			                    mname.setText("");
			                    mage.setText("");
			                    maddress.setText("");
			                    mmobile.setText("");

			                    
			                    displayAllData();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Failed to save Trainer");
			                }
			            } catch (SQLException e1) {
			                e1.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }
			    }
		

				private void DisplayCoachs() {
					
					 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
					        
					        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM member");

					        ResultSet rs = retrieve.executeQuery();
				    
					        DefaultTableModel model = new DefaultTableModel();
					        model.addColumn("ID");
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

					        table.setModel(model);
					        displayAllData();
					    } catch (SQLException e) {
					        e.printStackTrace();
					        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					    }
				}
				
						
				
				private void displayAllData() {
				    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
				        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM member");

				       
				        ResultSet rs = retrieve.executeQuery();

				        DefaultTableModel model = new DefaultTableModel();
				        model.addColumn("ID");
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
		
		txtsearch = new JTextField();
		txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String memberId = txtsearch.getText().trim();

		        if (!memberId.isEmpty()) {
		            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                
		            	
		                PreparedStatement retrieve = con.prepareStatement("SELECT * FROM member WHERE id = ?");
		                retrieve.setInt(1, Integer.parseInt(memberId));

		                
		                
		                ResultSet rs = retrieve.executeQuery();

		                if (rs.next()) {
		                   
		                	
		                    mname.setText(rs.getString("name"));
		                    mage.setText(Integer.toString(rs.getInt("age")));
		                    maddress.setText(rs.getString("address"));
		                    mmobile.setText(Integer.toString(rs.getInt("mobile")));
		                } else {
		                   
		                	
		                    mname.setText("");
		                    mage.setText("");
		                    maddress.setText("");
		                    mmobile.setText("");
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException ex) {
		              
		            	
	                    mname.setText("");
	                    mage.setText("");
	                    maddress.setText("");
	                    mmobile.setText("");
		            }
		        } else {
		           
                    mname.setText("");
                    mage.setText("");
                    maddress.setText("");
                    mmobile.setText("");
		        }
				
				
			}
		});
		
		
		txtsearch.setColumns(10);
		txtsearch.setBounds(223, 492, 163, 20);
		panel.add(txtsearch);
		
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
				
			    mname.setText("");
		        mage.setText("");
		        maddress.setText("");
		        mmobile.setText("");
			
			}
		});
		
		
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClear.setBounds(295, 385, 91, 35);
		panel.add(btnClear);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 String memberId = txtsearch.getText().trim();

			        if (!memberId.isEmpty()) {
			            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
			                
			            	
			                PreparedStatement update = con.prepareStatement("UPDATE member SET name=?, age=?, address=?, mobile=? WHERE id=?");

			                
			                update.setString(1, mname.getText());
			                update.setInt(2, Integer.parseInt(mage.getText()));
			                update.setString(3, maddress.getText());
			                update.setInt(4, Integer.parseInt(mmobile.getText()));
			                update.setInt(5, Integer.parseInt(memberId)); 

			                int rowsAff = update.executeUpdate();

			                if (rowsAff > 0) {
			                    JOptionPane.showMessageDialog(null, "Member updated");

			                    
			                    mname.setText("");
			                    mage.setText("");
			                    maddress.setText("");
			                    mmobile.setText("");
			                    txtsearch.setText(""); 

			                   
			                    displayAllData();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Failed to update Member");
			                }
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			            } catch (NumberFormatException ex) {
			                
			            	
			            	
			                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        } else {
			            
			        	
			        	
			            JOptionPane.showMessageDialog(null, "Please enter an ID to update a Member.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			        displayAllData();
			}
			
			private void displayAllData() {
				// TODO Auto-generated method stub
				 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
				        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM member");

				        
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
				
				String memberId = txtsearch.getText().trim();

		        if (!memberId.isEmpty()) {
		            int confirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Member?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

		            if (confirmDialog == JOptionPane.YES_OPTION) {
		                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                    
		                	
		                	
		                    PreparedStatement delete = con.prepareStatement("DELETE FROM member WHERE id=?");

		                 
		                    delete.setInt(1, Integer.parseInt(memberId));

		                    int rowsAff = delete.executeUpdate();

		                    
		                    if (rowsAff > 0) {
		                        JOptionPane.showMessageDialog(null, "Member deleted");

		                       
		                        
		                        mname.setText("");
		                        mage.setText("");
		                        maddress.setText("");
		                        mmobile.setText("");
		                        txtsearch.setText(""); 

		                        
		                        displayAllData();
		                        
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Failed to delete Member");
		                    }
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		                } catch (NumberFormatException ex) {
		                   
		                	
		                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		            
		        } else {
		            
		        	
		            JOptionPane.showMessageDialog(null, "Please enter an ID to delete a Member.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
				
				
				
			}

			private void displayAllData() {
				// TODO Auto-generated method stub
				 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
				        PreparedStatement retrieve = con.prepareStatement("SELECT * FROM member");

				        
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(413, 106, 449, 198);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name", "Age", "Address", "Mobile"
			}
		));
		
		
		Table();
		
		
	}
	
	
	

	private void Table() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();

	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
	        String sql = "SELECT id, name, age, address, mobile FROM member";
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        ResultSet result = preparedStatement.executeQuery();

	        while (result.next()) {
	            int ID = result.getInt("id");
	            String name = result.getString("name");
	            int age = result.getInt("age");
	            String address = result.getString("address");
	            int mobile = result.getInt("mobile");

	            model.addRow(new Object[] { ID, name, age, address, mobile });
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    
	    }

	}
	
	
	
	
	
	
	
	
	
}



