package oop.project;
import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import java.lang.*;
import net.proteanit.sql.DbUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookPage implements ActionListener {
	//Components of the form.
	private JFrame frame;
	private JLabel lbl_heading, bg_image, lbl_id, lbl_book, lbl_catg, lbl_auth, lbl_pubr, lbl_isbn, lbl_page, lbl_edit;
	private JTextField bid, bname, bisbn, bpages, bedition;
	private JComboBox catgComboBox, authComboBox, pubrComboBox;
	private JButton btn_add, btn_updt, btn_dlt, btn_clr, btn_srch, btn_logout;
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollpane;
	
	// Initializing components with default values.
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	
	/*
	 * Create the application.
	 */
	public BookPage() {
		home();
		DbConnect();
		Display();
	}
	
	// Establishing database connection.
	public void DbConnect() {
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/lms";
         	String user = "root";
         	String pass = "ayusha1!";
         	
         	conn = DriverManager.getConnection(url,user,pass);
         }
		catch(SQLException ex) {
		}
	}
	
	// Viewing the table in the main window.
    public void Display() {
        try {
            PreparedStatement pst = conn.prepareStatement("select * from tbl_book");
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel (rs));
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
	
	@SuppressWarnings("unchecked")
	/*
	 * Constructor to initialize the components with default values.
	 */
	private void home() {
		// Setting property of JFrame.
		frame = new JFrame("Library Management System");
		frame.getContentPane().setBackground(Color.decode("#0097a7"));
		
		// Adding image to the frame.
		ImageIcon picture = new ImageIcon("C:\\Users\\Dell\\Documents\\Images/library.jpg");
		bg_image = new JLabel("",picture,JLabel.LEFT);
		bg_image.setBounds(5, 2, 250, 150);
		frame.add(bg_image);
		
		// changing icon image of the main window.
		ImageIcon img = new ImageIcon("C:\\Users\\Dell\\Documents\\Images/icon.png");

		// changing search button into image.
		ImageIcon searchIcon = new ImageIcon("C:\\Users\\Dell\\Documents\\Images/search.png");
		
		// adding border to the related JTextField.
		Border bd = BorderFactory.createTitledBorder(null,"Book",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma",1,14));
		Border border = BorderFactory.createLineBorder(Color.black,1);
		Border bd1 = BorderFactory.createLineBorder(Color.decode("#b388ff"),2);
		
		// setting values for combo box.
		String s1[] = { "Python", "Java", "Comic", "C++", "SQL" };
		String s2[] = { "Joshua", "Paul", "Jeanine", "Brian", "Alan" };
		String s3[] = { "Addison", "Rossum", "Stephen", "Dennis", "Joffe" };
		 
		panel = new JPanel();
		panel.setBounds(10, 165, 845, 380);
		panel.setBackground(Color.decode("#fafafa"));
		panel.setBorder(bd);
		frame.add(panel);
		
		lbl_heading = new JLabel("BOOKS");
		lbl_heading.setBounds(430, 90, 300, 50);
		lbl_heading.setFont(new Font("roboto",Font.BOLD,30));
		lbl_heading.setForeground(Color.white);
		frame.add(lbl_heading);
		
		lbl_id = new JLabel("Book id: ");
		lbl_id.setBounds(657, 282, 80, 25);
		lbl_id.setFont(new Font("Calibri",Font.BOLD,14));
		panel.add(lbl_id);
		
		bid = new JTextField();
		bid.setBounds(715, 280, 75, 20);
		bid.setBorder(border);
		panel.add(bid);
		
		lbl_book = new JLabel("Book Name");
		lbl_book.setBounds(20, 25, 80, 20);
		lbl_book.setFont(new Font("Calibri",Font.BOLD,14));
		panel.add(lbl_book);
		
		bname = new JTextField();
		bname.setBounds(115, 20, 140, 25);
		bname.setBorder(border);
		panel.add(bname);
		
		lbl_catg = new JLabel("Category");
		lbl_catg.setBounds(20, 65, 80, 20);
		lbl_catg.setFont(new Font("Calibri",Font.BOLD,14));
		panel.add(lbl_catg);
		
		catgComboBox = new JComboBox(s1);
		catgComboBox.setBounds(115, 60, 140, 25);
		catgComboBox.setBackground(Color.white);
		panel.add(catgComboBox);
		
		lbl_auth = new JLabel("Author");
		lbl_auth.setBounds(20, 105, 80, 20);
		lbl_auth.setFont(new Font("Calibri",Font.BOLD,14));
		panel.add(lbl_auth);
		
		authComboBox = new JComboBox(s2);
		authComboBox.setBounds(115, 100, 140, 25);
		authComboBox.setBackground(Color.white);
		panel.add(authComboBox);
		
		lbl_pubr = new JLabel("Publisher");
		lbl_pubr.setBounds(20, 145, 80, 20);
		lbl_pubr.setFont(new Font("Calibri",Font.BOLD,14));
		panel.add(lbl_pubr);
		
		pubrComboBox = new JComboBox(s3);
		pubrComboBox.setBounds(115, 140, 140, 25);
		pubrComboBox.setBackground(Color.white);
		panel.add(pubrComboBox);
		
		lbl_isbn = new JLabel("Book ISBN");
		lbl_isbn.setBounds(20, 190, 80, 20);
		lbl_isbn.setFont(new Font("Calibri",Font.BOLD,14));
		panel.add(lbl_isbn);
		
		bisbn = new JTextField();
		bisbn.setBounds(115, 185, 140, 25);
		bisbn.setBorder(border);
		panel.add(bisbn);
		
		lbl_page = new JLabel("No. of Pages");
		lbl_page.setBounds(20, 235, 80, 20);
		lbl_page.setFont(new Font("Calibri",Font.BOLD,14));
		panel.add(lbl_page);
		
		bpages = new JTextField();
		bpages.setBounds(115, 230, 140, 25);
		bpages.setBorder(border);
		panel.add(bpages);
		
		lbl_edit = new JLabel("Edition");
		lbl_edit.setBounds(20, 283, 80, 20);
		lbl_edit.setFont(new Font("Calibri",Font.BOLD,14));
		panel.add(lbl_edit);
		
		bedition = new JTextField();
		bedition.setBounds(115, 275, 140, 25);
		bedition.setBorder(border);
		panel.add(bedition);
		
		// creating object, setting properties for JTable and JScrollPane.
		table = new JTable();
		table.setGridColor(Color.white);
		table.setRowHeight(20);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

		scrollpane = new JScrollPane();
		scrollpane.setBounds(275, 20, 560, 250);
		scrollpane.setViewportView(table);
		panel.add(scrollpane);
		
		/*
		 *  creating objects, setting properties and adding action listener to JButtons.
		 */
		btn_add = new JButton("Add");
		btn_add.setBounds(45, 330, 70, 30);
		btn_add.setBorder(bd1);
		btn_add.setFont(new Font("Calibri",Font.BOLD,14));
		btn_add.setBackground(Color.black);
		btn_add.setForeground(Color.white);
		btn_add.addActionListener(this);
		panel.add(btn_add);
		
		btn_updt = new JButton("Update");
		btn_updt.setBounds(128, 330, 70, 30);
		btn_updt.setBorder(bd1);
		btn_updt.setFont(new Font("Calibri",Font.BOLD,14));
		btn_updt.setBackground(Color.black);
		btn_updt.setForeground(Color.white);
		btn_updt.addActionListener(this);
		panel.add(btn_updt);
		
		btn_dlt = new JButton("Delete");
		btn_dlt.setBounds(210, 330, 70, 30);
		btn_dlt.setBorder(bd1);
		btn_dlt.setFont(new Font("Calibri",Font.BOLD,14));
		btn_dlt.setBackground(Color.black);
		btn_dlt.setForeground(Color.white);
		btn_dlt.addActionListener(this);
		panel.add(btn_dlt);
		
		btn_clr = new JButton("Clear");
		btn_clr.setBounds(290, 330, 70, 30);
		btn_clr.setBorder(bd1);
		btn_clr.setFont(new Font("Calibri",Font.BOLD,14));
		btn_clr.setBackground(Color.black);
		btn_clr.setForeground(Color.white);
		btn_clr.addActionListener(this);
		panel.add(btn_clr);
	
		btn_srch = new JButton(searchIcon);
		btn_srch.setBounds(795, 280, 30, 20);
		btn_srch.setBackground(Color.white);
		btn_srch.addActionListener(this);
		panel.add(btn_srch);
	
		btn_logout = new JButton("Logout");
		btn_logout.setBounds(780, 5, 80, 30);
		btn_logout.setFont(new Font("arial",Font.BOLD,13));
		btn_logout.setBackground(Color.decode("#dd2c00"));
		btn_logout.setForeground(Color.white);
		btn_logout.addActionListener(this);
		frame.add(btn_logout);
	
		// Setting configurations of the JFrame and JPanel.
		panel.setLayout(null);
		frame.setSize(880,600);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(img.getImage());
	}
	
	// Driver Code.
	public static void main(String[] args) {
		new BookPage();
	}
	
	/* 
	 * method actionPerformed()  to get the action performed
	 * by the user and act accordingly 
    */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 *  Logs out from the current page
		 *  and return to the login page.
		 */
		if(e.getSource()==btn_logout) {
			JOptionPane.showMessageDialog(btn_logout, "Are you sure you want to logout?");
			frame.dispose();
			new LoginSystem();
		}
		
		// Adding and inserting records of Book in Table.
		if (e.getSource()==btn_add) {
			int bookid;
			bookid = Integer.parseInt(bid.getText());
			String b_name = bname.getText();
			String bookIsbn = bisbn.getText();
			String bookPages = bpages.getText();
			String bookEdition = bedition.getText();
			String catg = catgComboBox.getSelectedItem().toString();
			String auth = authComboBox.getSelectedItem().toString();
			String pubr = pubrComboBox.getSelectedItem().toString();
     	try {

             String query = " insert into tbl_book (id,bookname,category,author,publisher,ISBN,pages,edition)"
                     + " values (?, ?, ?, ?, ?, ?, ?, ?)";
             
             // Creating the statement object.
             Statement stmt = conn.createStatement();
              
             // Prepare Statement.
             PreparedStatement pst = conn.prepareStatement(query);
             
             // specifying the value of its parameter.
             pst.setInt (1, bookid);
             pst.setString (2, b_name);
             pst.setString (3, catg);
             pst.setString (4, auth);
             pst.setString (5, pubr);
             pst.setString (6, bookIsbn);
             pst.setString (7, bookPages);
             pst.setString(8, bookEdition);
             
             // Executing query.
             pst.execute();
             JOptionPane.showMessageDialog(null,"Record added Successfully!");
             
             // Displaying the records in Table.
             Display();
             
             //clears the screen after adding
             bid.setText("");
             bname.setText("");
             bedition.setText("");
             bisbn.setText("");
             bpages.setText("");
             catgComboBox.setSelectedIndex(0);
 			 authComboBox.setSelectedIndex(0);
 			 pubrComboBox.setSelectedIndex(0);
             bid.requestFocus();
         	}
     		catch (Exception exception) {
     			exception.printStackTrace();
     		}
		}
		
		// Modify or Change the details of Book in the table.
		if(e.getSource()==btn_updt) {
			int bookid;
			bookid = Integer.parseInt(bid.getText());
			String b_name = bname.getText();
			String bookIsbn = bisbn.getText();
			String bookPages = bpages.getText();
			String bookEdition = bedition.getText();
			String catg = catgComboBox.getSelectedItem().toString();
			String auth = authComboBox.getSelectedItem().toString();
			String pubr = pubrComboBox.getSelectedItem().toString();
			try {
				PreparedStatement pst = conn.prepareStatement("update tbl_book set bookname=?, category=?, author=?, publisher=?, ISBN=?, pages=?, edition=? where id=?");
				
				   pst.setInt (8, bookid);
		           pst.setString (1, b_name);
		           pst.setString (2, catg);
		           pst.setString (3, auth);
		           pst.setString (4, pubr);
		           pst.setString (5, bookIsbn);
		           pst.setString (6, bookPages);
		           pst.setString(7, bookEdition);
		           
		           pst.executeUpdate();
		           JOptionPane.showMessageDialog(null,"Record Updated Successfully!");
		           Display();
		           
		           bid.setText("");
		           bname.setText("");
		           bedition.setText("");
		           bisbn.setText("");
		           bpages.setText("");
		           catgComboBox.setSelectedIndex(0);
		           authComboBox.setSelectedIndex(0);
		           pubrComboBox.setSelectedIndex(0);
		           bid.requestFocus();
			}
			catch (Exception e1) {
     			e1.printStackTrace();
     		}
			 
		}
		
		// Delete all the record of selected Book in the table.
		if(e.getSource()==btn_dlt) {
			String query = " delete from tbl_book where id=?";
			int bookid;
			bookid  = Integer.parseInt(bid.getText());

			try {
			  PreparedStatement pst = conn.prepareStatement(query);

			  pst.setInt(1, bookid);
			  pst.executeUpdate();
			  JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");
			  Display();

			  bid.setText("");
			  bname.setText("");
			  bedition.setText("");
			  bpages.setText("");
			  bisbn.setText("");
			  catgComboBox.setSelectedIndex(0);
			  authComboBox.setSelectedIndex(0);
			  pubrComboBox.setSelectedIndex(0);
			  bid.requestFocus();

			}
			catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
		
		/*
		 *  Search the Book information in the table 
		 *  and insert into the JtextField.
		 */
		if(e.getSource()==btn_srch) {
			try {

                String id = bid.getText();

                PreparedStatement pst = conn.prepareStatement("select * from tbl_book where id = ?");
                pst.setString(1, id);
                ResultSet rs = pst.executeQuery();

                if(rs.next()==true)
                {

                   String bookid= rs.getString(1);
                   String bookname = rs.getString(2);
                   String bookcatg = rs.getString(3);
                   String bookauth = rs.getString(4);
                   String bookpubr = rs.getString(5);
                   String bookedition = rs.getString(8);
                   String bookisbn = rs.getString(6);
                   String bookpage = rs.getString(7);

                   bid.setText(bookid);
                   bname.setText(bookname);
                   bedition.setText(bookedition);
                   bisbn.setText(bookisbn);
                   bpages.setText(bookpage);
                   catgComboBox.setSelectedItem(bookcatg);
                   authComboBox.setSelectedItem(bookauth);
                   pubrComboBox.setSelectedItem(bookpubr);
                }
                else
                {
                   bid.setText("");
                   bname.setText("");
                   bedition.setText("");
                   bisbn.setText("");
                   bpages.setText("");
                   catgComboBox.setSelectedIndex(0);
                   authComboBox.setSelectedIndex(0);
                   pubrComboBox.setSelectedIndex(0);

                }
            } 
			catch (SQLException ex) {
			}

		}
		
		// clearing all fields.
		if(e.getSource()==btn_clr) {
			bid.setText("");
            bname.setText("");
            bedition.setText("");
            bisbn.setText("");
            bpages.setText("");
            catgComboBox.setSelectedIndex(0);
			authComboBox.setSelectedIndex(0);
			pubrComboBox.setSelectedIndex(0);
            bid.requestFocus();
		}
		
	}
	
}

