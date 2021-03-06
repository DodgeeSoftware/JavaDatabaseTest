// AWT
import java.awt.*;
import java.awt.event.*;

// SWING - GUI
import javax.swing.*;

/*
 * RemoveCustomerWindow
 * 
 * RemoveCustomerWindow is a simple window to allow the user to
 * remove a Customer from the Database
 * 
 * (c) Dodgee Software 2018
 * 
 */
public class RemoveCustomerWindow extends JFrame {

	// CustomerID Label
	private JLabel customerIDLabel;
	// CustomerID TextField
	private JTextField customerIDTextField;
	// Remove Button
	private JButton removeButton;
	// Search Button
	private JButton cancelButton;

	// Constructor
	RemoveCustomerWindow() {
		// Setup the Window
		this.setSize(640, 480);
		this.setResizable(false);
		this.setTitle("Remove Customer");

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
		
		// Remove Button
		this.removeButton = new JButton("Remove");
		this.removeButton.addActionListener(new RemoveButtonClickListener());
		this.add(this.removeButton);
		
		this.pack();
		this.setResizable(false);
	}
	// TODO: Implement me
	
	private class CancelButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Clear the CustomerID Text Field
			RemoveCustomerWindow.this.customerIDTextField.setText("");
			// Hide the Window
			RemoveCustomerWindow.this.setVisible(false);
		}
	}

	private class RemoveButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			try {
				// Grab the CustomerID
				long customerID = Long.parseLong(RemoveCustomerWindow.this.customerIDTextField.getText());
				// Grab the DatabaseWrapper
				DatabaseWrapper databaseWrapper = DatabaseWrapper.getInstance();
				if (databaseWrapper.isCustomer(customerID) == true) {
					databaseWrapper.removeCustomer(customerID);
					// Clear the Search Field
					RemoveCustomerWindow.this.customerIDTextField.setText("");
					// Hide the Window
					RemoveCustomerWindow.this.setVisible(false);
					// Notification to the User
					JOptionPane.showMessageDialog(RemoveCustomerWindow.this, "Successfully removed customer with ID: " + customerID);
				}
				else
				{
					// Notification to the User
					JOptionPane.showMessageDialog(RemoveCustomerWindow.this, "No customer found matching ID: " + customerID);
					// Failure so return
					return;
				}
			}
			catch(NumberFormatException exception) {
				
				// Notification to the User
				JOptionPane.showMessageDialog(RemoveCustomerWindow.this, "ID: Invalid. You must use a whole integer number such as 1234 ");
				RemoveCustomerWindow.this.customerIDTextField.setText("");
				// Failure
				return;
			}
			
		}
	}

}
