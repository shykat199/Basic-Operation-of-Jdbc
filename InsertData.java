	package insertData;
	
	import java.awt.EventQueue;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
	import javax.swing.JFrame;
	import javax.swing.JTextField;
	
	import com.mysql.cj.xdevapi.Statement;
	import com.sun.jdi.connect.spi.Connection;
	
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
	
	public class InsertData {
	
		private JFrame frame;
		private JTextField NametextField;
		Connection connection;
		private JTextField IdtextField;
	
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						InsertData window = new InsertData();
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
		public InsertData() {
			initialize();
			//createConnection();
		}
	
		/**
		 * Initialize the contents of the frame.
		 */
		
		
		/*public void createConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");//load driver	
				java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");    //Establish connection
				System.out.println("sucess");
							
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
		}*/
			
		
		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			NametextField = new JTextField();
			NametextField.setBounds(85, 86, 174, 44);
			frame.getContentPane().add(NametextField);
			NametextField.setColumns(10);
			
			JButton btnNewButton = new JButton("Submit\r\n");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						Class.forName("com.mysql.jdbc.Driver");//load driver	
						java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");    //Establish connection
						System.out.println("sucess");
						
						//int id=Integer.parseInt(IdtextField.getText()) ;	
						//String name=IdtextField.getText();	
						
						String drop="insert into student values(?,?) ";
						java.sql.PreparedStatement ps=connection.prepareStatement(drop);
						//System.out.println(ps);
						ps.setInt(1, Integer.parseInt(IdtextField.getText()));
						ps.setString(2, NametextField.getText());
						
						ps.executeUpdate();
						ps.close();
						
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(313, 54, 89, 44);
			frame.getContentPane().add(btnNewButton);
			
			IdtextField = new JTextField();
			IdtextField.setColumns(10);
			IdtextField.setBounds(85, 26, 174, 44);
			frame.getContentPane().add(IdtextField);
			
			JLabel lblNewLabel = new JLabel("Id :");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setBounds(10, 26, 65, 44);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblName = new JLabel("name\r\n :");
			lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblName.setBounds(10, 86, 65, 44);
			frame.getContentPane().add(lblName);
		}
	}
