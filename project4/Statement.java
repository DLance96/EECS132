//David Lance
/** setting the base Statement definition for the language */
public interface Statement extends Expression{
  
  /** method stub designation for toStringTabbed needing to be implemented used to get the String Tabbed
    * @param tabCount number of tabs
    * @return String properly tabbed */
  String toStringTabbed(int tabCount);
  /** method stub designation for execute needing to be implemented used to run a statement
    * @param s state for variable referencing*/
  void execute(State s);
}