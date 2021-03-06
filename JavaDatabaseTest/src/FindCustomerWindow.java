// AWT
import java.awt.*;
import java.awt.event.*;

// SWING - GUI
import javax.swing.*;

/*
 * FindCustomerWindow
 * 
 * FindCustomerWindow is a simple window which allows
 * the user to search for a customer
 * 
 * (c) Dodgee Software 2018
 * 
 */
public class FindCustomerWindow extends JFrame {
	
	// CustomerID Label
	private JLabel customerIDLabel;
	// CustomerID TextField
	private JTextField customerIDTextField;
	// Search Button
	private JButton searchButton;
	// Search Button
	private JButton cancelButton;
	
	// Customer Window
	CustomerWindow customerWindow;
	
	// Constructor
	public FindCustomerWindow() {
		// Setup the Window
		this.setSize(640, 480);
		this.setResizable(false);
		this.setTitle("Find Customer");
		
		Container contentPane = this.getContentPane();		
		//contentPane.setLayout(new FlowLayout());
		contentPane.setLayout(new GridLayout(4, 2));
		
		// CustomerID Label
		this.customerIDLabel = new JLabel("ID");
		this.add(this.customerIDLabel);		
		// CustomeID TextField
		this.customerIDTextField = new JTextField();		
		this.add(this.customerIDTextField);
		
		// Cancel Button
		this.cancelButton = new JButton("Cancel");
		this.cancelButton.addActionListener(new CancelButtonClickListener());
		this.add(this.cancelButton);
		
		// Search Button
		this.searchButton = new JButton("Search");
		this.searchButton.addActionListener(new SearchButtonClickListener());
		this.add(this.searchButton);
		
		// Create a new Customer Window
		this.customerWindow = new CustomerWindow();
		
		this.pack();
		this.setResizable(false);
	}

	private class CancelButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Clear the CustomerID Text Field
			FindCustomerWindow.this.customerIDTextField.setText("");
			// Hide the Window
			FindCustomerWindow.this.setVisible(false);
		}
	}
	
	private class SearchButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			try {
				// Grab the CustomerID
				long customerID = Long.parseLong(FindCustomerWindow.this.customerIDTextField.getText());
				// Grab the DatabaseWrapper
				DatabaseWrapper databaseWrapper = DatabaseWrapper.getInstance();
				if (databaseWrapper.isCustomer(customerID) == true) {
					Customer customer = databaseWrapper.getCustomer(customerID);
					// Clear the Search Field
					FindCustomerWindow.this.customerIDTextField.setText("");
					// Hide the Window
					FindCustomerWindow.this.setVisible(false);
					// Customer
					FindCustomerWindow.this.customerWindow.setCustomer(customer);
					// Show the Customer Window
					FindCustomerWindow.this.customerWindow.setVisible(true);
				}
				else
				{
					// Notification to the User
					JOptionPane.showMessageDialog(FindCustomerWindow.this, "No customer found matching ID: " + customerID);
					// Failure so return
					return;
				}
			}
			catch(NumberFormatException exception) {
				
				// Notification to the User
				JOptionPane.showMessageDialog(FindCustomerWindow.this, "ID: Invalid. You must use a whole integer number such as 1234 ");
				FindCustomerWindow.this.customerIDTextField.setText("");
				// Failure
				return;
			}
			
		}
	}
	
}
