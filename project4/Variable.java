//David Lance
/** class used to reperesent what stores ints */
public class Variable implements ExpressionInt{
  /** a String for the name of the variable 
    * accessable in subclasses because it is protected*/
  protected String name;
  
  /** constructor for Variables using the name
   * @param name used to call the variabe*/ 
  public Variable(String name) {
    this.name = name;
  }
  
  /** used to get value of variable
   * @param state used to call the HashTable and get the variable value*/
  public int value(State state) {
    return state.lookup(name);
  }
  
  /** used to return variable name*/
  public String toString() {
    return name;
  }
}