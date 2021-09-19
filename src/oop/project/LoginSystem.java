package oop.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.Border;

public class LoginSystem implements ActionListener {
	// Components of the Form.
	private JFrame frame;
	private JLabel bg_image,lbl_heading,lbl_user,lbl_pwd;
	private JTextField username;
	private JPasswordField pass;
	private JButton btn_login,btn_reg,btn_reset;
	private JPanel panel;
	private JCheckBox show_pass;
	private String userTxt;
	private String passwordTxt;
	
	// constructor to initialize the components with default values.
	public LoginSystem() {
		/* creating object and setting properties of JFrame.
		 * changing background image of the frame.
		 */
		frame = new JFrame("Library Management System");
		ImageIcon picture = new ImageIcon("C:\\Users\\Dell\\Documents\\Images/lib.jpeg");
		bg_image = new JLabel("",picture,JLabel.CENTER);
		bg_image.setBounds(0, 0, 400, 350);
		frame.add(bg_image);
		
		// adding border to JTextField.
		Border bd = BorderFactory.createLineBorder(Color.black,2);
		
		// changing icon image of the main window.
		ImageIcon img = new ImageIcon("C:\\Users\\Dell\\Documents\\Images/icon.png");
		
		panel = new JPanel();
		panel.setBounds(35, 30, 310, 255);
		panel.setBackground(Color.decode("#37474f"));
		bg_image.add(panel);
		
		lbl_heading = new JLabel("Login Here");
		lbl_heading.setBounds(95,5,130,40);
		lbl_heading.setFont(new Font("monsterrat", Font.BOLD, 25));
		lbl_heading.setForeground(Color.white);
		panel.add(lbl_heading);
		
		lbl_user = new JLabel("USERNAME");
		lbl_user.setBounds(30, 50, 100, 25);
		lbl_user.setFont(new Font("monsterrat", Font.BOLD, 16));
		lbl_user.setForeground(Color.white);
		panel.add(lbl_user);
		
		username = new JTextField();
		username.setBounds(25, 80, 250, 30);
		username.setBorder(bd);
		username.setBackground(Color.decode("#e0e0e0"));
		username.setFont(new Font("roboto", Font.BOLD, 13));
		panel.add(username);
		
		lbl_pwd = new JLabel("PASSWORD");
		lbl_pwd.setBounds(30,120,100,25);
		lbl_pwd.setFont(new Font("monsterrat", Font.BOLD, 16));
		lbl_pwd.setForeground(Color.white);
		panel.add(lbl_pwd);
		
		pass = new JPasswordField();
		pass.setBounds(25, 150, 250, 30);
		pass.setBorder(bd);
		pass.setBackground(Color.decode("#e0e0e0"));
		pass.setFont(new Font("roboto", Font.BOLD, 13));
		panel.add(pass);
		
		show_pass = new JCheckBox("Show Password");
		show_pass.setBounds(28, 180, 150, 25);
		show_pass.setBackground(Color.decode("#37474f"));
		show_pass.setForeground(Color.white);
		show_pass.addActionListener(this);
		panel.add(show_pass);
		
		// creating objects, setting properties and adding action listener to JBUttons.
		btn_login = new JButton("Login");
		btn_login.setBounds(60, 210, 80, 30);
		btn_login.setBackground(Color.decode("#00b0ff"));
		btn_login.setForeground(Color.white);
		btn_login.setFont(new Font("monsterrat", Font.BOLD, 14));
		btn_login.addActionListener(this);
		panel.add(btn_login);
		
		btn_reset = new JButton("Reset");
		btn_reset.setBounds(150, 210, 85, 30);
		btn_reset.setBackground(Color.decode("#dd2c00"));
		btn_reset.setForeground(Color.white);
		btn_reset.setFont(new Font("monsterrat", Font.BOLD, 14));
		btn_reset.addActionListener(this);
		panel.add(btn_reset);
		
		btn_reg = new JButton("SignUp");
		btn_reg.setBounds(245, 230, 77, 25);
		btn_reg.setBackground(Color.decode("#e0e0e0"));
		btn_reg.setForeground(Color.decode("#37474f"));
		btn_reg.setFont(new Font("monsterrat", Font.BOLD, 12));
		btn_reg.addActionListener(this);
		panel.add(btn_reg);
		
		panel.setLayout(null);
		frame.setSize(400,350);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(img.getImage());
	}
	
	// Driver Code
	public static void main(String[] args) {
		new LoginSystem();
	}
	
	 /* method actionPerformed()  to get the action performed
    by the user and act accordingly */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==show_pass) {
			if (show_pass.isSelected()) {
			    pass.setEchoChar((char) 0);
            }
			else {
                pass.setEchoChar('*');
            }
		}
		
		// clearing fields.
		if(e.getSource()==btn_reset) {
			username.setText("");
			pass.setText("");
		}
		
		if(e.getSource()==btn_reg) {
			new RegistrationPage();
		}
		
		if(e.getSource()==btn_login) {
		    String userTxt = username.getText();
            String pwdTxt = pass.getText();
            
            // Database Connection.
         	String url = "jdbc:mysql://127.0.0.1:3306/lms";
         	String user = "root";
         	String pass = "ayusha1!";
         	
             try {
            	// Get a connection to database
                 Connection conn = (Connection) DriverManager.getConnection(url, user, pass);

                 // Prepare a Statement.
                 PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("Select user_name, pass from admin_tbl where user_name=? and pass=?");

                 // specifying value of its parameter.
                 stmt.setString(1, userTxt);
                 stmt.setString(2, pwdTxt);
                 
                 // Executing the query.
                 ResultSet result = stmt.executeQuery();
                 
                 if (result.next()) {
                 	JOptionPane.showMessageDialog(btn_login, "Login Successful.");
                 	frame.dispose();
                 	new BookPage();            
                 } else {
                     JOptionPane.showMessageDialog(btn_login, "Invalid Username or Password");
                 }
             } catch (SQLException sqlException) {
                 sqlException.printStackTrace();
             }
		
	}
	}
}

