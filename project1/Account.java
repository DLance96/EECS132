//David Lance
public class Account
{
  //variables involved in caluculating bills
  private double lineFee = 0.0;
  private double rate = 0.0;
  private double taxRate = 0.0;
  private double interestRate = 0.0;
  
  //variables involved in usage
  private int monthUsage = 0;
  private int pastYearUsage = 0;
  private int currentYearUsage = 0;
  
  //variables involved in billing
  private double balance = 0.0;
  private double monthBill = 0.0;
  private double amountPaidThisMonth = 0.0;
  private boolean isMonthlyPlan = false;
  
  //variables involved in account basics
  private Date anniversaryDate = null;
  private Customer customer = null;
  private boolean open = false;
  
  //construts an account instance without customer or date
  public Account(double lineFee, double rate, double taxRate, double interestRate, int expectedMonthlyUsage)
  {
    setLineFee(lineFee);
    setRate(rate);
    setSalesTaxRate(taxRate);
    setInterestRate(interestRate);
    pastYearUsage = 12 * expectedMonthlyUsage;
  }
  
  //constructs an account instance with customer or date
  public Account(double lineFee, double rate, double taxRate, double interestRate, int expectedMonthlyUsage, Customer customer, Date date)
  {
    this(lineFee, rate, taxRate, interestRate, expectedMonthlyUsage);
    this.customer = customer;
    anniversaryDate = date;
    open = true;
  }
  
  //sets the lineFee field serving as monthly fee
  public void setLineFee(double lineFee)
  {
    this.lineFee = lineFee;
  }
  
  //gets the lineFee field serving as monthly fee
  public double getLineFee()
  {
    return this.lineFee;
  }
  
  //sets the rate field
  public void setRate(double rate)
  {
    this.rate = rate;
  }
  
  //gets the rate field
  public double getRate()
  {
    return this.rate;
  }
  
  //sets taxRate field used as tax percentage
  public void setSalesTaxRate(double taxRate)
  {
    this.taxRate = taxRate;
  }
  
  //gets taxRate field used as tax percentage
  public double getSalesTaxRate()
  {
    return this.taxRate;
  }
  
  //sets the field interestRate for past-due amounts
  public void setInterestRate(double interestRate)
  {
    this.interestRate = interestRate;
  }
  
  //gets the interestRate field for past-due amounts
  public double getInterestRate()
  {
    return this.interestRate;
  }
  
  //sets the monthUsage field used to find service usage
  public void setMonthlyUsage(int monthUsage)
  {
    this.monthUsage = monthUsage;
  }
  
  //gets the monthUsage field used to find service usage
  public int getMonthUsage()
  {
    return this.monthUsage;
  }
  
  //gets the pastYearUsage field which is predefined
  public int getPastYearUsage()
  {
    return this.pastYearUsage;
  }
  
  //returns the current year usage field
  public int getCurrentYearUsage()
  {
    return this.currentYearUsage;
  }
  
  //gets the monthy balance on the account
  public double getBalance()
  {
    return balance;
  }
  
  //gets the the money billed to the account per month
  public double getMonthBill() 
  {
    monthBill = roundToPenny(monthBill);
    return monthBill;
  }
  
  //EXTRA CREDIT 1%
  //used to round monthBill to nearest penny
  private double roundToPenny(double num)
  {
    num = 100 * num + .5;
    num = (int)(num / 1) / 100.0;
    return num;
  }
  
  //gets amountPaidThisMonth field
  public double getAmountPaidThisMonth() 
  {
    return amountPaidThisMonth;
  }
  
  //enables monthly plan on the account
  public void turnOnMonthlyPlan()
  {
    isMonthlyPlan = true;
    if(isOpen())
    {
      if (pastYearUsage - currentYearUsage <= 0)
        monthUsage = 0;
      else
        monthUsage = (pastYearUsage - currentYearUsage) / customer.getDate().monthsUntil(anniversaryDate);;
    }
  }
  
  //turns the account off the monthly plan
  public void turnOffMonthlyPlan()
  {
    isMonthlyPlan = false;
  }
  
  //checks if the account is on monthly plan
  public boolean isOnMonthlyPlan()
  {
    return isMonthlyPlan;
  }
  
  //adds to the amount paid and reduces balance based on input
  public void receivePayment(double amount) 
  {
    amountPaidThisMonth = amountPaidThisMonth + amount;
    balance = balance - amount;
  }
  
  //sets anniversary date and customer type based on inputs
  public void openAccount(Date date, Customer customer)
  {
    this.customer = customer;
    anniversaryDate = date;
    open = true;
  }
  
  //returns the open? field
  public boolean isOpen()
  {
    return open;
  }
  
  //returns the anniversary date
  public Date getAnniversaryDate()
  {
    return anniversaryDate;
  }
  
  //returns what type of customer the customer is
  public Customer getCustomer()
  {
    return customer;
  }
  
  //processes billing at the end of the year
  public void endOfYearProcessing()
  {
    if (!isOpen())
      return;
    else
    {
      if (isOnMonthlyPlan())
      {
        setMonthlyUsage((2 * getCurrentYearUsage() - getPastYearUsage()) / 12);
        pastYearUsage = getCurrentYearUsage();
        currentYearUsage = 0;
      }
      else
        setMonthlyUsage(getCurrentYearUsage() / 12);
    }
  }
  
  //processes billing at the end of the month
  public void endOfMonthProcessing()
  {
    if (!isOpen())
      return;
    else
    {
      this.currentYearUsage += getMonthUsage();
      balance += lineFee * (1 + this.taxRate);
      balance += (getMonthUsage() * getRate()) * (1 + this.taxRate);
      
      if (getAmountPaidThisMonth() < getMonthBill())
        balance += getInterestRate() * (getMonthBill() - getAmountPaidThisMonth());
      
      //solving monthly bill   
      if (!isOnMonthlyPlan())
      {
        if (getBalance() < 0)
          monthBill = 0;
        else
          monthBill = balance;
      }
      else
      {
        if (getMonthBill() >= getAmountPaidThisMonth())
          monthBill = getInterestRate() * (getMonthBill() - getAmountPaidThisMonth());
        else
          monthBill = getAmountPaidThisMonth() - getMonthBill();
        monthBill = getMonthBill() + (getLineFee() + (getRate() * getMonthUsage())) * (1 + getSalesTaxRate());
      }
    }
  }      
  
  //decides if and what processing should be done based on the date
  public void processDate (Date date)
  {
    if (!isOpen())
      return;
    
    if (date.getDay() == getAnniversaryDate().getDay())
    {
      if (date.getMonth() == getAnniversaryDate().getMonth())
        this.endOfYearProcessing();
      this.endOfMonthProcessing();
    }
    
  }
  
  
}