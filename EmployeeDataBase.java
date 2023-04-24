import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class EmployeeDatabase extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public static void createFrame()
	{
		
                JFrame frame = new JFrame("Employee Registration");//information at the top of the window
		frame.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel(new FlowLayout());
		
			
		
		JLabel label = new JLabel("Employee Registration");
		label.setPreferredSize(new Dimension(150, 100));
		label.setHorizontalAlignment(JLabel.CENTER);
				
		
		frame.add(label);
		
		 
		JLabel firstName= new JLabel("First Name: ", JLabel.LEFT);
		firstName.setHorizontalAlignment(JLabel.LEFT);
		JTextField textfield = new JTextField(10);
		
                panel.add(firstName);
		panel.add(textfield);
		
		
		
		
		JLabel lastName= new JLabel("Last Name: ", SwingConstants.LEFT);
		lastName.setHorizontalAlignment(JLabel.LEFT);
		panel.add(lastName);
		JTextField textfield2 = new JTextField(10);
		textfield.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(textfield2);
		
		
		JLabel email= new JLabel("Email: ");
		email.setHorizontalAlignment(JLabel.LEFT);
		JTextField textfield3 = new JTextField(10);
		
		panel.add(email);
		panel.add(textfield3);
		
			
		
		JButton submitButton = new JButton();
		submitButton.setText("Register");
		panel.add(submitButton);
			
		submitButton.addActionListener(e -> {
			try {
				
                                String url ="jdbc:mysql://localhost:3306/employees";
				String username = "root";
				String password = ""; //removed password for security reasons
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection(url,username,password);
					
					register(textfield, textfield2, textfield3, connection);
					
				}
				catch(Exception error)
				{
					System.out.println(error);
				}
					
				
				
			} 
                        catch (Exception e1) 
                        {
				e1.printStackTrace();
			} 
		});
		
				
		
		frame.add(panel);
		frame.setSize(750,400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	
	
	public static void register(JTextField firstName, JTextField lastName, JTextField employeeEmail,  Connection con)
	{
		try {
			String first = firstName.getText();
			String last = lastName.getText();
			String email =employeeEmail.getText();
			PreparedStatement statement = con.prepareStatement("INSERT INTO employees.employee(first_name, last_name, email) VALUES(?, ?, ?)");//goes into the database
			
			statement.setString(1, first);
			statement.setString(2, last);
			statement.setString(3, email);
			
			statement.execute();
			System.out.println("Successfully entered into database"); 
		} 
                catch (SQLException e) 
                { 
			
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		
		
		createFrame();//this method initializes the java fx frame, textfields, button
		


	}

}
