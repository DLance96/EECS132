//David Lance
public class Customer
{
  private String firstName = "John";
  private String lastName = "Doe";
  private String address = "123 Main Street";
  private Date date = null;
  private Account electricAccount = null;
  private Account gasAccount = null;
  private Account waterAccount = null;
  
  //constructs a customer object without the date
  public Customer (String lastName, String address)
  {
    this.lastName = lastName;
    this.address = address;
  }
  
  //constructs a customer object with the date
  public Customer (String firstName, String lastName, String address, Date date)
  {
    this(lastName,address);
    this.firstName = firstName;
    this.date = date;
  }
  
  //sets the firstName field
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  //returns the firstName field
  public String getFirstName()
  {
    return firstName;
  }
  
  //sets the lastName field
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }
  
  //returns the lastName field
  public String getLastName()
  {
    return lastName;
  }
  
  //sets the address field
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  //returns the address field
  public String getAddress()
  {
    return address;
  }
  
  //sets the electric account to what was input and opens the account with date and the customer
  public void setElectricAccount(Account electricAccount)
  {
    this.electricAccount = electricAccount;
  }
  
  //returns the electric account for the customer
  public Account getElectricAccount()
  {
    return electricAccount;
  }
  
  //sets the gas account to what was input and opens the account with date and the customer
  public void setGasAccount(Account gasAccount)
  {
    this.gasAccount = gasAccount;
  }
  
  //returns the gas account for the customer
  public Account getGasAccount()
  {
    return gasAccount;
  }
  
  //sets the water account to what was input and opens the account with date and the customer
  public void setWaterAccount(Account waterAccount)
  {
    this.waterAccount = waterAccount;
  }
  
  //returns the water account for the customer
  public Account getWaterAccount()
  {
    return waterAccount;
  }
  
  //sets the date field
  public void setDate(Date date)
  {
    this.date = date;
  }
  
  //returns the date field
  public Date getDate()
  {
    return date;
  }
  
  //runs incrementDay on the date then runs on the accounts based on if they have been set
  public void incrementDate()
  {
    this.date.incrementDay();
    if (electricAccount != null)
      electricAccount.processDate(date);
    if (gasAccount != null)
      gasAccount.processDate(date);
    if (waterAccount != null)
      waterAccount.processDate(date);
  }
  
}