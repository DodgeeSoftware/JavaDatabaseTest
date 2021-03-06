// Data Containers 
import java.util.*;

// AWT
import java.awt.*;
import java.awt.event.*;

// SWING - GUI
import javax.swing.*;

/*
 * MainApplicationWindow
 * 
 * MainApplicationWindow is the main window the 
 * user sees and presents all the controls
 * 
 * (c) Dodgee Software 2018
 */
public class MainApplicationWindow extends JFrame {
	
	// Add New Customer Button
	private JButton addNewCustomerButton;
	// Find Customer Button
	private JButton findCustomerButton;
	// Remove Customer Button
	private JButton removeCustomerButton;
	// Show Customers Button
	private JButton showCustomersButton;
	// Quit Button
	private JButton quitButton;
	// About Button
	private JButton aboutButton;
	
	// Add New Customer Window
	private AddNewCustomerWindow addNewCustomerWindow;
	// Customer Window
	private CustomerWindow customerWindow;
	// Find Customer Window
	private FindCustomerWindow findCustomerWindow;
	// Remove Customer Window
	private  RemoveCustomerWindow removeCustomerWindow;
	// ShowCustomersWindow
	private ShowCustomersWindow showCustomersWindow;
	
	// Constructor
	public MainApplicationWindow() {
		
		// Setup the Window
		this.setSize(640, 480);
		this.setResizable(false);
		this.setTitle("Application Main Window - Java, JDBC, Swing");
		
		// Grab the ContentPain
		Container contentPane = this.getContentPane();		
		
		// Set the Window Layout
		contentPane.setLayout(new GridLayout(3, 1));
		
		// Create the Dashboard Buttons
		this.addNewCustomerButton = new JButton("Add Customer");
		this.addNewCustomerButton.addActionListener(new AddCustomerButtonClickListener());
		contentPane.add(this.addNewCustomerButton);
		this.findCustomerButton = new JButton("Find Customer");
		this.findCustomerButton.addActionListener(new FindCustomerButtonClickListener());
		contentPane.add(this.findCustomerButton);
		this.removeCustomerButton = new JButton("Remove Customer");
		this.removeCustomerButton.addActionListener(new RemoveCustomerButtonClickListener());
		
		contentPane.add(this.removeCustomerButton);
		this.showCustomersButton = new JButton("Show Customers");
		this.showCustomersButton.addActionListener(new ShowCustomersButtonClickListener());
		contentPane.add(this.showCustomersButton);
		this.quitButton = new JButton("Quit");
		this.quitButton.addActionListener(new QuitButtonClickListener());
		contentPane.add(this.quitButton);

		this.aboutButton = new JButton("About");
		this.aboutButton.addActionListener(new AboutButtonClickListener());
		contentPane.add(this.aboutButton);
		
		// Create our application windows
		this.addNewCustomerWindow = new AddNewCustomerWindow();
		this.customerWindow = new CustomerWindow();
		this.findCustomerWindow = new FindCustomerWindow();
		this.removeCustomerWindow = new RemoveCustomerWindow();
		this.showCustomersWindow = new ShowCustomersWindow();
		
		// Make the MainApplicationWindow Visible
		this.setVisible(true);
		
		this.pack();
		this.setResizable(false);
	}

	// Entry Point
	public static void main(String[] args) {
		// Create our Database Wrapper
		DatabaseWrapper databaseWrapper = DatabaseWrapper.getInstance(); 
		// Connect the Database Wrapper to the Database
		databaseWrapper.connect("jdbc:mysql://localhost/dbTest", "admin", "admin");
		// Show our Main Application Window
		MainApplicationWindow mainApplicationWindow = new MainApplicationWindow();
	}
	
	private class QuitButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Hide the Application Windows
			MainApplicationWindow.this.addNewCustomerWindow.setVisible(false);
			MainApplicationWindow.this.customerWindow.setVisible(false);
			MainApplicationWindow.this.findCustomerWindow.setVisible(false);
			MainApplicationWindow.this.removeCustomerWindow.setVisible(false);
			MainApplicationWindow.this.showCustomersWindow.setVisible(false);
			MainApplicationWindow.this.setVisible(false);
		}
	}
		
	private class AddCustomerButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MainApplicationWindow.this.addNewCustomerWindow.setVisible(true);
		}			
	}

	private class FindCustomerButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MainApplicationWindow.this.findCustomerWindow.setVisible(true);
		}			
	}

	private class RemoveCustomerButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MainApplicationWindow.this.removeCustomerWindow.setVisible(true);
		}			
	}

	private class ShowCustomersButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MainApplicationWindow.this.showCustomersWindow.refresh();
			MainApplicationWindow.this.showCustomersWindow.setVisible(true);
		}			
	}

	private class AboutButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Notification to the User
			JOptionPane.showMessageDialog(MainApplicationWindow.this, "(c) Dodgee Software 2018");
		}			
	}

}
