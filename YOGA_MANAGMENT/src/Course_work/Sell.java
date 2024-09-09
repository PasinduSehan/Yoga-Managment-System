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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sell extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField price;
	private JTextField count;
	private JTextField total;
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sell frame = new Sell();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Sell() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
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
		
		JLabel lblMember = new JLabel("BUY PRODUCT");
		lblMember.setForeground(Color.WHITE);
		lblMember.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblMember.setBounds(336, 30, 178, 31);
		panel_2.add(lblMember);
		
		JLabel lblMemberName = new JLabel("Product Name");
		lblMemberName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMemberName.setBounds(44, 168, 91, 21);
		panel.add(lblMemberName);
		
		JLabel lblAddress = new JLabel("Product Price");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblAddress.setBounds(44, 230, 91, 21);
		panel.add(lblAddress);
		
		JLabel lblMobile = new JLabel("Enter Count");
		lblMobile.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMobile.setBounds(44, 289, 91, 21);
		panel.add(lblMobile);
		
		
		
		id = new JTextField();
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				

				String memberId = id.getText().trim();

		        if (!memberId.isEmpty()) {
		            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "")) {
		                
		            	
		                PreparedStatement retrieve = con.prepareStatement("SELECT * FROM product WHERE id = ?");
		                retrieve.setInt(1, Integer.parseInt(memberId));

		                
		                
		                ResultSet rs = retrieve.executeQuery();

		                if (rs.next()) {
		                   
		                	
		                  
		                    id.setText(Integer.toString(rs.getInt("id")));
		                    name.setText(rs.getString("name"));
		                    
		                    price.setText(Integer.toString(rs.getInt("price")));
		                 

		                } else {
		                   
		                	
		                    id.setText("");
		                    name.setText("");
		                  count.setText("");
		                    price.setText("");
		                   total.setText("");
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (NumberFormatException ex) {
		              
		            	
		                id.setText("");
	                    name.setText("");
	                    count.setText("");
	                    price.setText("");
	                    total.setText("");
		            }
		        } else {
		           
		            id.setText("");
                    name.setText("");
                    count.setText("");
                    price.setText("");
                    total.setText("");
		        }
				
				
			}
		});
		id.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		id.setColumns(10);
		id.setBounds(223, 107, 163, 21);
		panel.add(id);
		
		name = new JTextField();
		name.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		name.setColumns(10);
		name.setBounds(223, 169, 163, 21);
		panel.add(name);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(223, 231, 163, 20);
		panel.add(price);
		
		count = new JTextField();
		count.setColumns(10);
		count.setBounds(223, 290, 163, 21);
		panel.add(count);
		
		
		
		JButton totalbtn = new JButton("Total");
		totalbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			
				

				 try {
			            int eCount = Integer.parseInt(count.getText());
			            
			            int pId = Integer.parseInt(id.getText());

			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/yogamanagment", "root", "");

			            PreparedStatement fetch = con.prepareStatement("SELECT count, price, name FROM product WHERE id = ?");
			            fetch.setInt(1, pId);
			            
			            ResultSet result = fetch.executeQuery();

			            if (result.next()) {
			                int cCount = result.getInt("count");
			                int pPrice = result.getInt("price");
			                String pName = result.getString("name");
			                

			                
			                displayAllData();
			                
			                if (eCount > cCount) {
			                	
			                    JOptionPane.showMessageDialog(null, "Entered count is too high.", "Error", JOptionPane.ERROR_MESSAGE);
			                    
			                } else {
			               
			                    int Total = eCount * pPrice;

			                    total.setText(Integer.toString(Total));
			                    
			               
			                    
			                  
			                    int uCount = cCount - eCount;

			                    
			                    
			                    
			                    PreparedStatement uPCount = con.prepareStatement("UPDATE product SET count = ? WHERE id = ?");
			                    uPCount.setInt(1, uCount);
			                    uPCount.setInt(2, pId);
			                    
			                    uPCount.executeUpdate();

			                    PreparedStatement insert = con.prepareStatement("INSERT INTO sell (id, name, count, total) VALUES (?, ?, ?, ?)");
			                    insert.setInt(1, pId);
			                    insert.setString(2, pName);
			                    insert.setInt(3, eCount);
			                    
			                    insert.setInt(4, Total);
			                    insert.executeUpdate();

			                    DefaultTableModel model = (DefaultTableModel) table.getModel();
			                    
			                    model.setValueAt(uCount, table.getSelectedRow(), 2);

			                    JOptionPane.showMessageDialog(null, "Product count updated.");
			                }
			            } else {
			                JOptionPane.showMessageDialog(null, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
			            }

			           
			            con.close();
			        } catch (SQLException ex) {
			        	
			            ex.printStackTrace();
			            
			            
			            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			        } catch (NumberFormatException ex) {
			        	
			            JOptionPane.showMessageDialog(null, "Please enter valid count .", "Error", JOptionPane.ERROR_MESSAGE);
			            
			        }
				
		        displayAllData();
		       
				  
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
		totalbtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		totalbtn.setBounds(44, 376, 91, 29);
		panel.add(totalbtn);
		
		JLabel lblSearchId = new JLabel("Search Id\r\n");
		lblSearchId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSearchId.setBounds(44, 106, 91, 21);
		panel.add(lblSearchId);
		
		total = new JTextField();
		total.setColumns(10);
		total.setBounds(223, 382, 163, 20);
		panel.add(total);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				

				System.exit(0);
				
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnExit.setBounds(44, 477, 91, 35);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				  total.setText("");
				   id.setText("");
			       name.setText("");
			       count.setText("");
			       price.setText("");
				
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClear.setBounds(223, 477, 91, 35);
		panel.add(btnClear);
		
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
		scrollPane.setBounds(452, 106, 409, 299);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Id", "Product Name", "Product Count", "Product Price"
			}
			
			
		));
		
		JButton refresh = new JButton("Refresh Table");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
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
		refresh.setFont(new Font("Times New Roman", Font.BOLD, 18));
		refresh.setBounds(452, 416, 147, 29);
		panel.add(refresh);
		
		
		
		
		
		
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





