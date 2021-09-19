package oop.project;

import javax.swing.*;
import javax.swing.border.Border;
import javax.tools.Tool;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class RegistrationPage implements ActionListener {
	// Components of the form.
	private JFrame frame;
	private JLabel lbl_heading,lbl_fname,lbl_username,lbl_pwd,lbl_address,lbl_email,lbl_contact,lbl_gender;
	private JTextField txt_fname,txt_username,txt_address,txt_email,txt_contact;
	private JComboBox genderComboBox;
	private JTextArea txt_display, resadd;
	private JPasswordField txt_pwd;
	private JButton btn_reg,btn_reset;
	private JPanel panel;
	private JCheckBox showPassword;
	
	// Setting values for the JComboBox.
	String[] gender={"Male","Female"};

	/* 
	 * constructor to initialize the components with default values.
	 */
	public RegistrationPage() {
		// Setting properties of JFrame.
		frame = new JFrame("Library Management System");
		Color c= new Color(139,69,19);
		frame.getContentPane().setBackground(c);
		
		// Change icon of the main window.
		ImageIcon img = new ImageIcon("C:\\Users\\Dell\\Documents\\Images/icon.png");
		
		Border bd = BorderFactory.createLineBorder(Color.black,1);
		Border bd1 = BorderFactory.createLineBorder(Color.decode("#e53935"),1);
		
		/* Creating objects, setting properties of JPanel, JLabel and JTextField 
		 * and adding them to the JFrame.
		 */
		panel = new JPanel();
		panel.setBounds(15, 50, 385, 360);
		panel.setBackground(Color.WHITE);
		frame.add(panel);
	
		lbl_heading = new JLabel("Registration Form");
		lbl_heading.setBounds(130,8,160,40);
		lbl_heading.setFont(new Font("Times New Roman",Font.BOLD,20));
		lbl_heading.setForeground(Color.white);
		frame.add(lbl_heading);
		
		lbl_fname = new JLabel("Full Name: ");
		lbl_fname.setBounds(45, 15, 100, 25);
		lbl_fname.setFont(new Font("Roboto",Font.BOLD,16));
		panel.add(lbl_fname);
		
		txt_fname = new JTextField();
		txt_fname.setBounds(160,15,200,25);
		txt_fname.setBorder(bd);
		txt_fname.setBackground(Color.white);
		txt_fname.setFont(new Font("Roboto",Font.BOLD,13));
		panel.add(txt_fname);
		
		lbl_username = new JLabel("Username: ");
		lbl_username.setBounds(45, 60, 100, 25);
		lbl_username.setFont(new Font("Roboto",Font.BOLD,16));
		panel.add(lbl_username);
		
		txt_username = new JTextField();
		txt_username.setBounds(160,60,200,25);
		txt_username.setBorder(bd);
		txt_username.setBackground(Color.white);
		txt_username.setFont(new Font("Roboto",Font.BOLD,13));
		panel.add(txt_username);
		
		lbl_address = new JLabel("Address: ");
		lbl_address.setBounds(55, 105, 100, 25);
		lbl_address.setFont(new Font("Roboto",Font.BOLD,16));
		panel.add(lbl_address);
		
		txt_address = new JTextField();
		txt_address.setBounds(160,105,200,25);
		txt_address.setBorder(bd);
		txt_address.setBackground(Color.white);
		txt_address.setFont(new Font("Roboto",Font.BOLD,13));
		panel.add(txt_address);
		
		lbl_contact = new JLabel("Contact No: ");
		lbl_contact.setBounds(35, 150, 100, 25);
		lbl_contact.setFont(new Font("Roboto",Font.BOLD,16));
		panel.add(lbl_contact);
		
		txt_contact = new JTextField();
		txt_contact.setBounds(160,150,200,25);
		txt_contact.setBorder(bd);
		txt_contact.setBackground(Color.white);
		txt_contact.setFont(new Font("Roboto",Font.BOLD,13));
		panel.add(txt_contact);
		
		lbl_email = new JLabel("E-mail: ");
		lbl_email.setBounds(73, 195, 100, 25);
		lbl_email.setFont(new Font("Roboto",Font.BOLD,16));
		panel.add(lbl_email);
		
		txt_email = new JTextField();
		txt_email.setBounds(160,195,200,25);
		txt_email.setBorder(bd);
		txt_email.setBackground(Color.white);
		txt_email.setFont(new Font("Roboto",Font.BOLD,13));
		panel.add(txt_email);
		
		lbl_pwd = new JLabel("Password: ");
		lbl_pwd.setBounds(40, 240, 100, 25);
		lbl_pwd.setFont(new Font("Roboto",Font.BOLD,16));
		panel.add(lbl_pwd);
		
		txt_pwd = new JPasswordField();
		txt_pwd.setBounds(160,240,200,25);
		txt_pwd.setBorder(bd);
		txt_pwd.setBackground(Color.white);
		txt_pwd.setFont(new Font("Roboto",Font.BOLD,13));
		panel.add(txt_pwd);
		
		lbl_gender = new JLabel("Gender: ");
		lbl_gender.setBounds(63, 280, 100, 25);
		lbl_gender.setFont(new Font("Roboto",Font.BOLD,16));
		panel.add(lbl_gender);
		
		genderComboBox = new JComboBox(gender);
		genderComboBox.setBounds(160, 280, 200, 25);
		genderComboBox.setFont(new Font("Roboto",Font.BOLD,13));
		genderComboBox.setBackground(Color.white);
		panel.add(genderComboBox);
		
		// Creating JCheckBox in order to add the feature of showing and hiding password.
		showPassword = new JCheckBox();
		showPassword.setBounds(360, 240, 90, 25);
		showPassword.setBackground(Color.white);
		showPassword.setForeground(Color.darkGray);
		showPassword.addActionListener(this);
		panel.add(showPassword);
		
		// Creating object, setting properties and adding action listener for JButtons.
		btn_reg = new JButton("Register");
		btn_reg.setBounds(100, 320, 95, 30);
		btn_reg.setBackground(c);
		btn_reg.setForeground(Color.white);
		btn_reg.setFont(new Font("Roboto",Font.BOLD,14));
		btn_reg.addActionListener(this);
		panel.add(btn_reg);
		
		btn_reset = new JButton("Reset");
		btn_reset.setBounds(230, 320, 90, 30);
		btn_reset.setBackground(c);
		btn_reset.setForeground(Color.white);
		btn_reset.setFont(new Font("Roboto",Font.BOLD,14));
		btn_reset.addActionListener(this);
		panel.add(btn_reset);
		
		// Adding text area to the frame in order to show details of the registration form.
        txt_display = new JTextArea();
        txt_display.setFont(new Font("Arial", Font.PLAIN, 15));
        txt_display.setBounds(430, 80, 250, 290);
        txt_display.setLineWrap(true);
        txt_display.setEditable(false);
        frame.add(txt_display);
        
        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setBounds(430, 80, 250, 290);
        resadd.setLineWrap(true);
        frame.add(resadd);
        
        // Setting configurations of JFrame and JPanel.
		panel.setLayout(null);
		frame.setIconImage(img.getImage());
		frame.setSize(710,460);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	// Driver Code
	public static void main(String[] args) {
		new RegistrationPage();
	}
	
	 /* method actionPerformed()  to get the action performed
     by the user and act accordingly */
	@Override
	public void actionPerformed(ActionEvent e) {
		 String fullName = txt_fname.getText();
         String userName = txt_username.getText();
         String location = txt_address.getText();
         String mob = txt_contact.getText();
         String emailId = txt_email.getText();
		 String password = txt_pwd.getText();
		 String gen = genderComboBox.getSelectedItem().toString();
		 
		 // Database Connection.
	     	String url = "jdbc:mysql://127.0.0.1:3306/lms";
	     	String user = "root";
	     	String pass = "ayusha1!";
	
		if(e.getSource()==btn_reg) {
			String data
				= "Full Name : "
				  + txt_fname.getText() + "\n"
	              + "User Name : "
	              + txt_username.getText() + "\n"
	              + "Location : " 
	              + txt_address.getText() + "\n"
	              + "Contact No : "
	              + txt_contact.getText() + "\n"
	              + "E-mail : "
	    	      + txt_email.getText() + "\n"
	    	      + "Gender : "
	    	      + genderComboBox.getSelectedItem().toString() + "\n";
	              txt_display.setText(data);
	              txt_display.setEditable(false);

         String greet = " " + fullName;
         greet += " \n";
         
         if (fullName.isEmpty() || userName.isEmpty() || location.isEmpty() || mob.isEmpty() || emailId.isEmpty() || password.isEmpty() || gen.isEmpty()) {
         JOptionPane.showMessageDialog(btn_reg, "Enter valid details");
         }
         else {
         	try {
         		// Get a connection to database.
                

                 String query = " insert into admin_tbl (full_name, user_name, address, contact, email, pass, gender)"
                         + " values (?, ?, ?, ?, ?, ?,?)";
                  Connection conn = DriverManager.getConnection(url,user,pass);
                 // Creating the statement object.
                 Statement stmt = conn.createStatement();
                  
                 // Prepare Statement.
                 PreparedStatement myStmt = conn.prepareStatement(query);
                 
                 // specifying the value of its parameter.
                 myStmt.setString (1, fullName);
                 myStmt.setString (2, userName);
                 myStmt.setString (3, location);
                 myStmt.setString (4, mob);
                 myStmt.setString (5, emailId);
                 myStmt.setString (6, password);
                 myStmt.setString (7, gen);
                 
                 // Executing the query.
                 boolean ans = myStmt.execute();
                 if (ans == true) {
                	 JOptionPane.showMessageDialog(btn_reg, "Account not created");
                 } 
                 else {
                	  JOptionPane.showMessageDialog(btn_reg, "Welcome, " + greet + "Your account is sucessfully created");
                      frame.dispose();
                 }
                 
                 conn.close();
             } 
         	catch (Exception exception) {
                 exception.printStackTrace();
             }
         }
		}
		
		// action performed for showing or hiding password.
		if (e.getSource()==showPassword) {
			if(showPassword.isSelected()) {
				txt_pwd.setEchoChar((char) 0);
			}
			else {
				txt_pwd.setEchoChar('*');
			}
		}
		
		// clearing fields.
		if (e.getSource()==btn_reset) {
			String def = "";
			txt_fname.setText("");
			txt_username.setText("");
			txt_address.setText("");
			txt_email.setText("");
			txt_pwd.setText("");
			txt_contact.setText("");
			resadd.setText(def);
			txt_display.setText(def);
			genderComboBox.setSelectedIndex(0);
		}
		
	}
}


