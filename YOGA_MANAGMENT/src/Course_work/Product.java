package Course_work;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class Product extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField price;
	private JTextField search;
	private JTable table;
	private JTextField count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Product() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 915, 562);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 915, 561);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(new Color(0, 0, 102));
		panel_2.setBounds(0, 0, 915, 72);
		panel_1.add(panel_2);
		
		JLabel lblProduct = new JLabel("PRODUCT ITEAM");
		lblProduct.setForeground(Color.WHITE);
		lblProduct.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblProduct.setBounds(336, 30, 212, 31);
		panel_2.add(lblProduct);
		
		JLabel lblId = new JLabel("Product Id");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblId.setBounds(44, 106, 91, 21);
		panel_1.add(lblId);
		
		JLabel lblName = new JLabel("Product Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblName.setBounds(44, 168, 91, 21);
		panel_1.add(lblName);
		
		JLabel lblCount = new JLabel("Product Count");
		lblCount.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblCount.setBounds(44, 230, 91, 21);
		panel_1.add(lblCount);
		
		JLabel Price = new JLabel("Product Price");
		Price.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Price.setBounds(44, 289, 91, 21);
		panel_1.add(Price);
		
		id = new JTextField();
		id.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		id.setColumns(10);
		id.setBounds(223, 107, 163, 21);
		panel_1.add(id);
		
		name = new JTextField();
		name.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		name.setColumns(10);
		name.setBounds(223, 169, 163, 21);
		panel_1.add(name);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(223, 290, 163, 21);
		panel_1.add(price);
		
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
		



			@Override
			public void mouseClicked(MouseEvent e) {
				
			
		
				
				 if (id.getText().isEmpty() || name.getText().isEmpty() || count.getText().isEmpty() || price.getText().isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Missing Information", "Error", JOptionPane.ERROR_MESSAGE);
			            displayAllData();
			        } else {
			            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
			                
			                PreparedStatement add = con.prepareStatement("INSERT INTO product (id , name, count, price) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			                
			                add.setString(1, id.getText());
			                add.setString(2, name.getText());
			                add.setInt(3, Integer.parseInt(count.getText()));
			               
			                add.setInt(4, Integer.parseInt(price.getText()));

			                int rowsAf = add.executeUpdate();

			                if (rowsAf > 0) {
			                    JOptionPane.showMessageDialog(null, "Product saved");

			                    
			                    id.setText("");
			                    name.setText("");
			                    count.setText("");
			                    price.setText("");
			                    
			                    displayAllData();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Failed to save Product");
			                }
			            } catch (SQLException e1) {
			                e1.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }
			    }
		

				private void DisplayCoachs() {
				
					 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
					        
					        PreparedStatement retriev = con.prepareStatement("SELECT * FROM product");

					        
					        ResultSet rs = retriev.executeQuery();

					      
					        DefaultTableModel model = new DefaultTableModel();
					        model.addColumn("Product Id");
					        model.addColumn("Product Name");
					        model.addColumn("Product Count");
					        model.addColumn("Product Price");
					    

					        
					        while (rs.next()) {
					            model.addRow(new Object[]{
					                rs.getInt("id"),
					                rs.getString("name"),
					                rs.getInt("count"),
					                rs.getString("price")
					               
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
				      
				        PreparedStatement retriev = con.prepareStatement("SELECT * FROM product");

				        
				        ResultSet rs = retriev.executeQuery();

				       
				        DefaultTableModel model = new DefaultTableModel();
				        model.addColumn("Product ID");
				        model.addColumn("Product Name");
				        model.addColumn("Product Count");
				        model.addColumn("Product Price");
				      

				        
				        while (rs.next()) {
				            model.addRow(new Object[]{
				                rs.getInt("id"),
				                rs.getString("name"),
				                rs.getInt("count"),
				                rs.getString("price")
				         
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
		panel_1.add(btnAdd);
		
		JLabel lblSearchId = new JLabel("Search Id\r\n");
		lblSearchId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSearchId.setBounds(44, 491, 91, 21);
		panel_1.add(lblSearchId);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				

				String memberId = search.getText().trim();

		        if (!memberId.isEmpty()) {
		            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                
		            	
		                PreparedStatement retrieve = con.prepareStatement("SELECT * FROM product WHERE id = ?");
		                retrieve.setInt(1, Integer.parseInt(memberId));

		                
		                
		                ResultSet rs = retrieve.executeQuery();

		                if (rs.next()) {
		                   
		                	
		                  
		                    id.setText(Integer.toString(rs.getInt("id")));
		                    name.setText(rs.getString("name"));
		                    count.setText(rs.getString("count"));
		                    price.setText(Integer.toString(rs.getInt("price")));
		                 

		                } else {
		                   
		                	
		                    id.setText("");
		                    name.setText("");
		                    count.setText("");
		                    price.setText("");
		                   
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException ex) {
		              
		            	
		                id.setText("");
	                    name.setText("");
	                    count.setText("");
	                    price.setText("");
		               
		            }
		        } else {
		           
		            id.setText("");
                    name.setText("");
                    count.setText("");
                    price.setText("");
		           
		        }
				
				
				
				
			}
		});
		
		
		search.setColumns(10);
		search.setBounds(223, 492, 163, 20);
		panel_1.add(search);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);
				
				
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnExit.setBounds(169, 385, 91, 35);
		panel_1.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
                   search.setText("");
				   id.setText("");
			       name.setText("");
			       count.setText("");
			       price.setText("");
			       
				
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClear.setBounds(295, 385, 91, 35);
		panel_1.add(btnClear);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				displayAllData();
				String memberId = search.getText().trim();

		        if (!memberId.isEmpty()) {
		            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                
		            	PreparedStatement update = con.prepareStatement("UPDATE product SET name=?, count=?, price=? WHERE id=?");
		            	
		            	
		            	update.setString(1, name.getText());
		            	update.setInt(2, Integer.parseInt(count.getText()));
		            	
		            	update.setInt(3, Integer.parseInt(price.getText()));
		            	update.setInt(4, Integer.parseInt(id.getText()));

		            	int rowsA = update.executeUpdate();

		              

		                if (rowsA > 0) {
		                    JOptionPane.showMessageDialog(null, "Product updated");
		                    
		                    id.setText("");
		                    name.setText("");
		                    count.setText("");
		                    price.setText("");
		                    search.setText("");

		                    
		                    
		                    
		                } else {
		                    JOptionPane.showMessageDialog(null, "Failed to update Product");
		                }
		                displayAllData();
		                
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException ex) {
		                
		            	
		                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            
		        	
		            JOptionPane.showMessageDialog(null, "Please enter an ID to update a Product.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
			
		        displayAllData();
				
			}
		
			private void displayAllData() {
				// TODO Auto-generated method stub
			    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
			        PreparedStatement retriev = con.prepareStatement("SELECT * FROM product");

			        
			        ResultSet rs = retriev.executeQuery();

			       
			        DefaultTableModel model = new DefaultTableModel();
			        model.addColumn("Product ID");
			        model.addColumn("Product Name");
			        model.addColumn("Product Count");
			        model.addColumn("Product Price");
			      

			        
			        while (rs.next()) {
			            model.addRow(new Object[]{
			                rs.getInt("id"),
			                rs.getString("name"),
			                rs.getInt("count"),
			                rs.getString("price")
			         
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
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String memberId = search.getText().trim();

		        if (!memberId.isEmpty()) {
		            int confirmDialog = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Staff member?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

		            if (confirmDialog == JOptionPane.YES_OPTION) {
		                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                    
		                	
		                	
		                    PreparedStatement deleteS = con.prepareStatement("DELETE FROM product WHERE id=?");

		                 
		                    deleteS.setInt(1, Integer.parseInt(memberId));

		                    int rowsA = deleteS.executeUpdate();

		                    
		                    if (rowsA > 0) {
		                        JOptionPane.showMessageDialog(null, "Product deleted");

		                       
		                       
		                        id.setText("");
		                        name.setText("");
		                        count.setText("");
		                        price.setText("");
		                       search.setText("");

		                         
		                        displayAllData();
		                        
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Failed to delete Product");
		                    }
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		                } catch (NumberFormatException ex) {
		                   
		                	
		                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		            
		        } else {
		            
		        	
		            JOptionPane.showMessageDialog(null, "Please enter an ID to delete a Product.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
				
				
				
			}

			private void displayAllData() {
				// TODO Auto-generated method stub
			    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
				      
			        PreparedStatement retriev = con.prepareStatement("SELECT * FROM product");

			        
			        ResultSet rs = retriev.executeQuery();

			       
			        DefaultTableModel model = new DefaultTableModel();
			        model.addColumn("Product ID");
			        model.addColumn("Product Name");
			        model.addColumn("Product Count");
			        model.addColumn("Product Price");
			      

			        
			        while (rs.next()) {
			            model.addRow(new Object[]{
			                rs.getInt("id"),
			                rs.getString("name"),
			                rs.getInt("count"),
			                rs.getString("price")
			         
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
		panel_1.add(btnDelete);
		
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
		panel_1.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 106, 476, 279);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Product Id");
				model.addColumn("Product Name");
				model.addColumn("Product count");
				model.addColumn("Product Price");
				
				
		
				
				table.setModel(model);

				displayAllData();
				
				
				
			}

			private void displayAllData() {
				// TODO Auto-generated method stub
				
			}
		});
		table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Id", "Product Name", "Product Count", "Product Price"
			}
		));
		
		count = new JTextField();
		count.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		count.setColumns(10);
		count.setBounds(223, 231, 163, 21);
		panel_1.add(count);
		
		
		Table();
		
		
	}
	
	
	private void Table() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();

	    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
	        String sql = "SELECT id, name, count, price FROM product";
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        ResultSet result = preparedStatement.executeQuery();

	        while (result.next()) {
	            int ID = result.getInt("id");
	            String Name = result.getString("name");
	            int Count = result.getInt("count");
	            int Price = result.getInt("price");

	            model.addRow(new Object[] { ID, Name, Count, Price });
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    
	    }

	}
	
	
	
	
}





