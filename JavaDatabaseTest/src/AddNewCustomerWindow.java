// AWT
import java.awt.*;
import java.awt.event.*;

// SWING - GUI
import javax.swing.*;

/* AddNewCustomerWindow
 * 
 * The Add New Customer Window collects
 * input from the user and can be used to
 * add new customer information into the database
 * 
 * (c) Dodgee Software 2018
 */
public class AddNewCustomerWindow extends JFrame {
	
	// Ok Button
	private JButton okButton;
	// Cancel Button
	private JButton cancelButton;

	// Title Label
	JLabel titleLabel;
	// Title ComboBox
	JComboBox<String> titleComboBox;
	// Given Names Label
	JLabel givenNamesLabel;
	// Given Names Text Field
	JTextField givenNamesTextField;
	// Last Name Label
	JLabel lastNameLabel;
	// Last Name Text Field
	JTextField lastNameTextField;
	
	// Constructor
	public AddNewCustomerWindow() {
		
		// Setup the Window
		this.setSize(640, 480);
		this.setResizable(false);
		this.setTitle("Add new Customer");
		
		Container contentPane = this.getContentPane();		
			//contentPane.setLayout(new FlowLayout());
		contentPane.setLayout(new GridLayout(4, 2));

		// Add the Title Label and Title ComboBox
		this.titleLabel = new JLabel("Title");
		contentPane.add(this.titleLabel);
		String[] titles = new String[] {"MR", "MRS", "MISS"};
	    this.titleComboBox = new JComboBox<String>(titles);
	    contentPane.add(this.titleComboBox);
		
	    // Add the GivenNames Label and the GivenNames TextField
	    this.givenNamesLabel = new JLabel("Given Names");
		contentPane.add(this.givenNamesLabel);	    
	    this.givenNamesTextField = new JTextField(25);
	    contentPane.add(this.givenNamesTextField);

	    // Add the LastName Label and the LastNameTextField
	    this.lastNameLabel = new JLabel("Last Name");
		contentPane.add(this.lastNameLabel);	    
	    this.lastNameTextField = new JTextField(25);
	    contentPane.add(this.lastNameTextField);
	    
	    // Add the Ok Button
		this.okButton = new JButton();
		this.okButton.setText("Ok");
		this.okButton.setSize(100, 50);
		this.okButton.addActionListener(new OkButtonClickListener());
		contentPane.add(this.okButton);
		
		// Add the Cancel Button
		this.cancelButton = new JButton();
		this.cancelButton.setSize(100, 50);
		this.cancelButton.setText("Cancel");
		this.cancelButton.addActionListener(new CancelButtonClickListener());
		contentPane.add(this.cancelButton);
		
		this.setResizable(false);
		this.pack();
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void clear() {
        this.titleComboBox.setSelectedItem(0);
        this.givenNamesTextField.setText("");
        this.lastNameTextField.setText("");
	}

	private class OkButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	        //String command = e.getActionCommand();  
	        DatabaseWrapper databaseWrapper = DatabaseWrapper.getInstance();
	        String customerTitle = (String)AddNewCustomerWindow.this.titleComboBox.getSelectedItem();
	        String customerGivenNames = AddNewCustomerWindow.this.givenNamesTextField.getText();
	        String customerLastName = AddNewCustomerWindow.this.lastNameTextField.getText();
	        AddNewCustomerWindow.this.clear();
	        if (databaseWrapper.addCustomer(customerTitle, customerGivenNames, customerLastName) == false) {
	        	System.out.println("Failed to add Customer: " + customerTitle + " " + customerGivenNames + " " + customerLastName);
				// Notification to the User
				JOptionPane.showMessageDialog(AddNewCustomerWindow.this, "Failed to add Customer " + customerTitle + " " + customerGivenNames + " " + customerLastName);
				return;
	        }
	        else	        	
	        {
				// Notification to the User
				JOptionPane.showMessageDialog(AddNewCustomerWindow.this, "Recorded Added to the Database for: " + customerTitle + " " + customerGivenNames + " " + customerLastName);
	        }
	        AddNewCustomerWindow.this.setVisible(false);
	        System.out.println("ok clicked");
		}
	}
		
	private class CancelButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
        //	String command = e.getActionCommand();  
			AddNewCustomerWindow.this.clear();
	        AddNewCustomerWindow.this.setVisible(false);
	        System.out.println("cancel clicked");
		}			
	}
	
	
}
