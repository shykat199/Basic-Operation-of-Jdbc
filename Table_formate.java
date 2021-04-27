package insertData;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.xdevapi.Statement;
import com.sun.jdi.connect.spi.Connection;
//import com.sun.tools.sjavac.pubapi.PubApi;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;

public class Table_formate {

	private JFrame frame;
	private JTextField nametextField;
	private JTextField idtextField;
	private JTextField textFieldname;
	private JTextField textFieldid;
	private JTable table;
	//Connection connection;
	JButton Refreshbtn;
	java.sql.Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table_formate window = new Table_formate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Table_formate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		frame.setLocationRelativeTo(null);
		
		nametextField = new JTextField();
		nametextField.setBounds(140, 11, 152, 37);
		frame.getContentPane().add(nametextField);
		nametextField.setColumns(10);
		
		idtextField = new JTextField();
		idtextField.setColumns(10);
		idtextField.setBounds(140, 68, 152, 37);
		frame.getContentPane().add(idtextField);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");//load driver	
					connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");    //Establish connection
					System.out.println("sucess");
					
					String drop="insert into student values(?,?) ";
					java.sql.PreparedStatement ps=((java.sql.Connection) connection).prepareStatement(drop);
					//System.out.println(ps);
					ps.setInt(1, Integer.parseInt(idtextField.getText()));
					ps.setString(2, nametextField.getText());
					
					System.out.println("id="+idtextField.getText()+"     "+"name="+nametextField.getText());
					
					ps.executeUpdate();
					ps.close();
					
					Refreshbtn.doClick();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(324, 36, 89, 46);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(27, 11, 76, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id:");
		lblNewLabel_1.setBounds(27, 68, 76, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		 Refreshbtn = new JButton("Refresh");
		Refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					String name = null;
					int id = 0;
					DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
					tableModel.setRowCount(0);
				    tableModel.setColumnCount(0);
				    tableModel.addColumn("id");
				    tableModel.addColumn("Name");
					Class.forName("com.mysql.jdbc.Driver");
					 connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
					java.sql.Statement stmt=((java.sql.Connection) connection).createStatement();
					System.out.println("connection sucessful");
					ResultSet rs= ((java.sql.Statement) stmt).executeQuery("select * from student order by id");
					//PreparedStatement ps=connection.prepareStatement("select * from student order by id");
					while ( rs.next()) {
						
						name=rs.getString("name");
						id=rs.getInt("id");
						
						System.out.println(id+ "name="+name);
						tableModel.addRow(new Object[] {id,name});
					} 
					
					stmt.close();
					rs.close();
					
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		Refreshbtn.setBounds(27, 145, 89, 30);
		frame.getContentPane().add(Refreshbtn);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(27, 395, 76, 37);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Id:");
		lblNewLabel_1_1.setBounds(27, 452, 76, 37);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textFieldname = new JTextField();
		textFieldname.setColumns(10);
		textFieldname.setBounds(140, 395, 152, 37);
		frame.getContentPane().add(textFieldname);
		
		textFieldid = new JTextField();
		textFieldid.setColumns(10);
		textFieldid.setBounds(140, 452, 152, 37);
		frame.getContentPane().add(textFieldid);
		
		JButton btnUpdat = new JButton("update");
		btnUpdat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String drop="update student set name=? where id=?";
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
					PreparedStatement ps=((java.sql.Connection) connection).prepareStatement(drop);
					
					ps.setString(1, textFieldname.getText());
					ps.setInt(2, Integer.parseInt(textFieldid.getText()));
					
					
					ps.executeUpdate();
					
				   System.out.println("Record updated");
					ps.close();
					
					Refreshbtn.doClick();
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnUpdat.setBounds(324, 420, 89, 46);
		frame.getContentPane().add(btnUpdat);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
				//tableModel.fireTableDataChanged();
				int row=table.getSelectedRow();
				
				String id=(String) tableModel.getValueAt(row, 0).toString();
				String name=(String) tableModel.getValueAt(row, 1);
				
				textFieldid.setText(id);
				textFieldname.setText(name);
			}
		});
		btnEdit.setBounds(177, 145, 89, 30);
		frame.getContentPane().add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 186, 414, 190);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Name"
			}
		));
		
		JButton btnDlt = new JButton("dlt");
		btnDlt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
					String dropString="delete from student where id=?";
					Class.forName("com.mysql.jdbc.Driver");
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
					int row=table.getSelectedRow();
					String nameString=(String) tableModel.getValueAt(row, 0).toString();
					
					PreparedStatement pStatement=connection.prepareStatement(dropString);
					pStatement.setString(1, nameString);
					pStatement.executeUpdate();
					System.out.println("Deleat sucessfull...");
					pStatement.close();
					Refreshbtn.doClick();
					
					
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnDlt.setBounds(324, 145, 89, 30);
		frame.getContentPane().add(btnDlt);
	}
}
