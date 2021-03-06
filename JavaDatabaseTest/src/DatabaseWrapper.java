// Data Containers 
import java.util.ArrayList;

// JDBC - Database Includes
import java.sql.*;

/*
 * Database Wrapper
 * 
 * The Database Wrapper Class is tool to simplify 
 * interactions with the Database. It follows the singleton
 * design pattern.
 * 
 * (c) Dodgee Software 2018
 */
public class DatabaseWrapper {

	// The Singleton Instance
	private static DatabaseWrapper instance = null;
	
	// Database Connection
	private Connection connection;
	// Connected Flag
	private boolean connectedFlag;
	
	// Constructor
	private DatabaseWrapper() {
		this.connection = null;
		this.connectedFlag = false;
	}
	
	// 
	public static DatabaseWrapper getInstance() {
		//return new DatabaseWrapper();
		if (DatabaseWrapper.instance == null) {
			DatabaseWrapper.instance = new DatabaseWrapper();
		}
		return DatabaseWrapper.instance;
	}
	
	// Connect to the Database
	public boolean connect(String url, String username, String password) {
		try {
			// Message to the Console
			System.out.println("Trying to connect to the Database");
			// Try and Open the Connection
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/dbTest", "neipo", "neipo");
			// Message to the Console
			System.out.println("Database connection Successful");
			// Set our Connection flag
			this.connectedFlag = true;			
		}
		catch(SQLException se) {
		      //Handle errors for JDBC
		      se.printStackTrace();
		      // Failure
		      return false;
		}
		// Success
		return true;
	}

	// Disconnect from the Database
	public void disconnect() {
		try  {
			// Message to the console
			System.out.println("Closing Database");
			// Try and Close the database connection
			this.connection.close();
			// Set connectedFlag to false
			this.connectedFlag = false;
		}
		catch(SQLException se)
		{
		      //Handle errors for JDBC
		      se.printStackTrace();
		}
	}
	
	// Add Customer
	public boolean addCustomer(String title, String givenNames, String lastName) {
		// Validate Parameters
		if (title.length() == 0 || givenNames.length() == 0 || lastName.length() == 0) { return false; }
		// Validate Database Connection
		if (this.connectedFlag == false) { return false; }
		/// Try and Add the new Customer
		try {

			// Create a statement
			Statement statement = this.connection.createStatement();
			String insert = "INSERT INTO Customers(Title, GivenNames, LastName) " + "VALUES('" + title + "', '" + givenNames + "', '" + lastName + "')";
			System.out.println(insert);
			// Run an Insert
			statement.executeUpdate(insert);
			System.out.println("INSERT Successful");
			// Success
			return true;
		}
		catch(SQLException se)
		{
 
			// Handle errors for JDBC
			se.printStackTrace();
		}
		// failed
		return false;
	}

	// Is Customer
	public boolean isCustomer(long customerID) {
		// Validate CustomerID
		if (customerID < 0) { return false; }
		// Validate Database Connection
		if (this.connectedFlag == false) { return false; }
		/// Try and Get the Customer
		try {

			// Create a statement
			Statement statement = this.connection.createStatement();
			String query = "SELECT CustomerID FROM Customers WHERE CustomerID=" + Long.toString(customerID);			
			// Run a Query
			ResultSet resultSet = statement.executeQuery(query);
			// If there is no last record there are no records so return false
			if (resultSet.last() == false) { return false; }
			/* Where the last row is the first then there 
			 * is a Customer matching the ID so return true */
			if (resultSet.getRow() == 1) { return true; }
			// Failure
			return false;
		}
		catch(SQLException se)
		{
 
			// Handle errors for JDBC
			se.printStackTrace();
		}
		// failed
		return false;
	}
	
	// Get's a Customer
	public Customer getCustomer(long customerID) {
		// Make a Customer
		Customer customer = new Customer();
		// Validate CustomerID
		if (customerID > 0 && this.connectedFlag == true) {
			// Try and Get the Customer
			try {

				// Create a statement
				Statement statement = this.connection.createStatement();
				String query = "SELECT * FROM Customers WHERE CustomerID=" + Long.toString(customerID);			
				// Run a Query
				ResultSet resultSet = statement.executeQuery(query);
				resultSet.last();
				if (resultSet.getRow() == 1) {
					// Grab Customer Fields		         
					customer.setCustomerID(resultSet.getLong("CustomerID"));
					customer.setTitle(resultSet.getString("Title"));
					customer.setGivenNames(resultSet.getString("GivenNames"));
					customer.setLastName(resultSet.getString("LastName"));
				}
			}
			catch(SQLException se)
			{
	 
				// Handle errors for JDBC
				se.printStackTrace();
			}			
		}
		// Return Customer
		return customer;
	}
	
	// Remove Customer
	public boolean removeCustomer(long customerID) {
		// Validate CustomerID
		if (customerID < 0) { return false; }
		// Validate Database Connection
		if (this.connectedFlag == false) { return false; }
		// Validate CustomerID
		if (this.isCustomer(customerID) == false) { return false; }
		try {
			// Create a statement
			Statement statement = this.connection.createStatement();
			String sql = "DELETE FROM Customers WHERE CustomerID=" + Long.toString(customerID);
			statement.executeUpdate(sql);
			// Success
			return true;
		}
		catch(SQLException se)
		{
 
			// Handle errors for JDBC
			se.printStackTrace();
		}
		// Failure
		return false;
	}
	
	// Grab a list of all the Customers
	public ArrayList<Customer> getCustomers() {
		// Create our List of customers
		ArrayList<Customer> customers = new ArrayList<Customer>();
		if (this.connectedFlag == true) {
			try {
				// Create a statement
				Statement statement = this.connection.createStatement();
				// Run a Query
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers");
				// Go through the result set one record (row) at a time		
				while(resultSet.next()) {
					// Grab Customer Fields		         
					long customerID = resultSet.getLong("CustomerID");
			        String title = resultSet.getString("Title");
			        String lastName = resultSet.getString("LastName");
			        String givenNames = resultSet.getString("GivenNames");
			        // Create a Customer
			        Customer customer = new Customer();
			        	customer.setCustomerID(customerID);
			         	customer.setTitle(title);
			         	customer.setLastName(lastName);
			         	customer.setGivenNames(givenNames);
			         // Add the Customer to the Customers list
			        customers.add(customer);
			      }
			}
			catch(SQLException se)
			{
			      //Handle errors for JDBC
			      se.printStackTrace();
			}
		}		
		// Return the list of Customers
		return customers;
	}
}
