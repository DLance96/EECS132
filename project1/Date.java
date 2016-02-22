//David Lance
public class Date
{
  private int day = 1;
  private Month month = Date.Month.JAN;
  
  public enum Month
  {
      JAN   (1 ,31),
      FEB   (2 ,28),
      MAR   (3 ,31),
      APR   (4 ,30), 
      MAY   (5 ,31), 
      JUNE  (6 ,30), 
      JULY  (7 ,31), 
      AUG   (8 ,31), 
      SEPT  (9 ,30), 
      OCT   (10,31), 
      NOV   (11,30), 
      DEC   (12,31);
    
    private final int monthNum;
    private final int days;
    
    //constructs all the enum constants
    Month(int monthNum, int days)
    {
      this.monthNum = monthNum;
      this.days = days;
    }
    
    //returns the month number
    public int getMonthNum()
    {
      return monthNum;
    }
    
    //returns the number of days
    public int getDays()
    {
      return days;
    }
  }
  
  //constructs a date with a day and a month
  public Date(int day, int month)
  {
    if (month == 1)
      this.month = Date.Month.JAN;
    if (month == 2)
      this.month = Date.Month.FEB;
    if (month == 3)
      this.month = Date.Month.MAR;
    if (month == 4)
      this.month = Date.Month.APR;
    if (month == 5)
      this.month = Date.Month.MAY;
    if (month == 6)
      this.month = Date.Month.JUNE;
    if (month == 7)
      this.month = Date.Month.JULY;
    if (month == 8)
      this.month = Date.Month.AUG;
    if (month == 9)
      this.month = Date.Month.SEPT;
    if (month == 10)
      this.month = Date.Month.OCT;
    if (month == 11)
      this.month = Date.Month.NOV;
    if (month == 12)
      this.month = Date.Month.DEC;
    
    this.day = day;
  }
  
  //returns the day field
  public int getDay()
  {
    return day;
  }
  
  //returns the month field
  public int getMonth()
  {
    return month.getMonthNum();
  }
  
  //adds a day to the date
  public void incrementDay()
  {
    if(month.getDays() == getDay())
    {
      if (month.getMonthNum() == 1)
        month = Date.Month.FEB;
      if (month.getMonthNum() == 2)
        month = Date.Month.MAR;
      if (month.getMonthNum() == 3)
        month = Date.Month.APR;
      if (month.getMonthNum() == 4)
        month = Date.Month.MAY;
      if (month.getMonthNum() == 5)
        month = Date.Month.JUNE;
      if (month.getMonthNum() == 6)
        month = Date.Month.JULY;
      if (month.getMonthNum() == 7)
        month = Date.Month.AUG;
      if (month.getMonthNum() == 8)
        month = Date.Month.SEPT;
      if (month.getMonthNum() == 9)
        month = Date.Month.OCT;
      if (month.getMonthNum() == 10)
        month = Date.Month.NOV;
      if (month.getMonthNum() == 11)
        month = Date.Month.DEC;
      if (month.getMonthNum() == 12)
        month = Date.Month.JAN;
      day = 1;  
    }
    day += 1;
  }
  
  
 //inputs a date and returns the months until in an int
  public int monthsUntil(Date date)
  {
    int monthDifference = (date.getMonth() - this.getMonth());
    
     //used for wrapping around the year
    if (monthDifference < 0)
      monthDifference = 12 + monthDifference;
    if (monthDifference == 0)
    {
      if (this.getDay() > date.getDay())
        return 12;
      if (this.getDay() < date.getDay())
        return 1;
      return 0;
    }
    //handling same day possible issue
    if (this.getDay() == date.getDay())
      return monthDifference;
    
    //handling end of month days not all the same
    if (this.getDay() == 28 && this.getMonth() == 2)
      return monthDifference;
    if (this.getDay() == 30 && (getMonth() == 4 || getMonth() == 6 || getMonth() == 9 || getMonth() == 11))
      return monthDifference;  
    
    if (this.getDay() < date.getDay())
    {
      return monthDifference = monthDifference + 1;
    }
    
    return monthDifference;
  }
}