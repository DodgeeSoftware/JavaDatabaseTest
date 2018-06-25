/* 
 * Customer.java
 * 
 * The Customer Class is a container for the properties 
 * which make up a a customer 
 * 
 * Version 1.0
 * 
 * (c) Dodgee Software 2018 
 * 
 * 
 * */
public class Customer {
	
	// Member Variables
	protected long customerID;
	protected String title;	
	protected String givenNames;
	protected String lastName;
	
	// Constructor
	public Customer() {
		customerID = -1;
		title = "";
		givenNames = "";
		lastName = "";
	}
	
	// Get CustomerID
	public long getCustomerID() { return this.customerID; }
	// Set CustomerID
	public void setCustomerID(long customerID) { this.customerID = customerID; }
	// Get Title
	public String getTitle() { return this.title; }
	// Set Title
	public void setTitle(String title) { this.title = title; }
	// Get GivenNames
	public String getGivenNames() { return this.givenNames; }
	// Set GivenNames
	public void setGivenNames(String givenNames) { this.givenNames = givenNames; }
	// Get LastName
	public String getLastName() { return this.lastName; }
	// Set LastName
	public void setLastName(String lastName) { this.lastName = lastName; }
	
}
