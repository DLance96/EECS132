//David Lance
import java.util.Hashtable;

/** class used to store and retrieve variables */
public class State{
 
  /** HashTable that stores all variables are stored for the language */
  private static Hashtable<String,Integer> stateHash = new Hashtable<String,Integer>();
  
  /** adds a new variable to the HashTable for reference
    * @param varName name of the new variable
    * @param varValue value for the new variable */
  public void update(String varName, int varValue) {
    stateHash.put(varName, varValue);
  }
  
  /** Looks up values for variable in the language
   * @param varName name of Variable to look up 
   * @return int from HashTable*/ 
  public int lookup(String varName) {
    try{
      return stateHash.get(varName);
    }
    //catching for non stated variables
    catch (Exception e) {
      return 0;
    }
  }
}