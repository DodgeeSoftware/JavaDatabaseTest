// AWT
import java.awt.*;
import java.awt.event.*;

// SWING - GUI
import javax.swing.*;

/* CustomerWindow
 * 
 * The Customer Window displays a
 * single Customer Record
 * 
 * (c) Dodgee Software 2018
 */
public class CustomerWindow extends JFrame {

	//	Customer
	private Customer customer;
	
	// Customer ID Label
	private JLabel customerIDLabel;
	// Customer ID Text Field
	private JTextField customerIDTextField;	
	// Customer Title Label
	private JLabel customerTitleLabel;
	// Customer Title Text Field
	private JTextField customerTitleTextField;
	// Customer Given Names Label
	private JLabel customerGivenNamesLabel;
	// Customer Given names Text Field
	private JTextField customerGivenNamesTextField;	
	// Customer LastName Label
	private JLabel customerLastNameLabel;
	// Customer LastName Text Field
	private JTextField customerLastNameTextField;
	
	// Done Button
	private JButton doneButton;
	
	// Constructor
	public CustomerWindow() {
		// Setup the Window
		this.setSize(640, 480);
		this.setResizable(false);
		this.setTitle("Customer");
		
		Container contentPane = this.getContentPane();		

		contentPane.setLayout(new GridLayout(5, 0));
		
		// Make the New Customer
		this.customer = new Customer();
		
		// CustomerID Label
		this.customerIDLabel = new JLabel("ID");
		this.add(this.customerIDLabel);		
		// CustomeID TextField
		this.customerIDTextField = new JTextField();
		this.customerIDTextField.setEditable(false);
		this.add(this.customerIDTextField);

		// Customer Title Label
		this.customerTitleLabel = new JLabel("Title");
		this.add(this.customerTitleLabel);		
		// Customer Title TextField
		this.customerTitleTextField = new JTextField();
		this.customerTitleTextField.setEditable(false);
		this.add(this.customerTitleTextField);

		// CustomerGivenNames Label
		this.customerGivenNamesLabel = new JLabel("Given Names");
		this.add(this.customerGivenNamesLabel);		
		// CustomeGivenNames TextField
		this.customerGivenNamesTextField = new JTextField();
		this.customerGivenNamesTextField.setEditable(false);
		this.add(this.customerGivenNamesTextField);

		// CustomerLastName Label
		this.customerLastNameLabel = new JLabel("Last Name");
		this.add(this.customerLastNameLabel);		
		// CustomerLastName TextField
		this.customerLastNameTextField = new JTextField();
		this.customerLastNameTextField.setEditable(false);
		this.add(this.customerLastNameTextField);
		
		// Done Button
		this.doneButton = new JButton("Done");
		this.doneButton.addActionListener(new DoneButtonClickListener());
		this.add(this.doneButton);
		
		this.pack();
		this.setResizable(false);
		
	}
	
	// Get Customer
	public Customer getCustomer() { return this.customer; }
	// Set Customer
	public void setCustomer(Customer customer) {
		// Update the Interface
		this.customerIDTextField.setText(Long.toString(customer.getCustomerID()));
		this.customerTitleTextField.setText(customer.getTitle());
		this.customerGivenNamesTextField.setText(customer.getGivenNames());
		this.customerLastNameTextField.setText(customer.getLastName());
		this.customer = customer;
	}
	
	// Do Button Click Listener
	private class DoneButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Clear the Customer
			CustomerWindow.this.customer = new Customer();
			// Clear the CustomerID Text Field
			CustomerWindow.this.customerIDTextField.setText("");
			// Clear the CustomerTitle Text Field
			CustomerWindow.this.customerTitleTextField.setText("");
			// Clear the CustomerGivenNames Text Field
			CustomerWindow.this.customerGivenNamesTextField.setText("");
			// Clear the CustomerLastName Text Field
			CustomerWindow.this.customerLastNameTextField.setText("");
			// Hide the Window
			CustomerWindow.this.setVisible(false);
		}			
	}
	
}
