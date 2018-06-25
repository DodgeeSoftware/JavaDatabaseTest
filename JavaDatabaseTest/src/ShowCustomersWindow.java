// Data Containers 
import java.util.*;

// AWT
import java.awt.*;
import java.awt.event.*;

// SWING - GUI
import javax.swing.*;

/*
 * ShowCustomersWindow
 * 
 * ShowCustomersWindow is a window which simply
 * shows a table full of the customers
 * 
 * (c) Dodgee Software 2018
 */
public class ShowCustomersWindow extends JFrame {

	// Customer Table Scroll Pane
	JScrollPane customerTableScrollPane;
	// Customer Table	
	private JTable customerTable;
	// Done Button
	private JButton doneButton;
	
	// Constructor
	public ShowCustomersWindow() {
		
		/* TODO:
		 * This class needs a refresh method that
		 * will grab the contents of the database
		 * and refresh the data
		 */
		
		// Setup the Window
		this.setSize(640, 480);
		this.setResizable(false);
		this.setTitle("Show Customers");
		
		// Grab the Content Pane
		Container contentPane = this.getContentPane();		
			// contentPane.setLayout(new FlowLayout());
			//contentPane.setLayout(new GridLayout(4, 2));
		
		// Create Scroll Pane
		this.customerTableScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(customerTableScrollPane);
		
		// Refresh the controls to reflect the contents of the database
		this.refresh();	
		this.pack();
	}
	
	// Refresh the controls to reflect the contents of the database
	public void refresh() {

		// Grab the list of customers
		ArrayList<Customer> customers = DatabaseWrapper.getInstance().getCustomers();
		// Make a vector for the ColumnNames
		Vector<String> columnNames = new Vector<String>();
			columnNames.add("CustomerID");
			columnNames.add("Title");
			columnNames.add("GivenNames");
			columnNames.add("LastName");
		// Assemble the row data
		Vector<Vector> rowData = new Vector<Vector>();
		for(Customer customer : customers) {
			Vector<String> row = new Vector<String>();
			row.addElement(Long.toString(customer.getCustomerID()));	
			row.addElement(customer.getTitle());
			row.addElement(customer.getGivenNames());
			row.addElement(customer.getLastName());
			rowData.add(row);
		}
		// Make the Table for the Customer Data
		this.customerTable = new JTable(rowData, columnNames);
			this.customerTable.setFillsViewportHeight(true);
		
		this.customerTable.setEnabled(false);
		
		// Add the CustomerTable to the customerTableScrollPane
		customerTableScrollPane.getViewport().add(this.customerTable);
		
	}
	
	// TODO: Implement me
	
}
