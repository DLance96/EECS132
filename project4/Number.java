//David Lance
/** class used to represent ints */
public class Number extends Variable implements ExpressionInt{
  /** a String for the number */
  private int number;
  
  /** constructor for Variables using the name
   * @param number used to call the variabe*/ 
  public Number(int number) {
    super(Integer.toString(number));
    this.number = number;
  }
  
  /** used to get value of variable
   * @param state used to call the HashTable and get the variable value*/
  public int value(State state) {
    return number;
  }
}