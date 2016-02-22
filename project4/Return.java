//David Lance
/** class used as return experessions in functions */
public class Return extends StatementType implements Statement{
  
  private ExpressionInt expression;
  private Variable returnVar = new Variable("return");
  
  /** constructs Return by making a return variable and making an Assignment instance 
    * @param e Expression to be returned as return value*/
  public Return(ExpressionInt e) {
    expression = e; 
  }
  
  /** updates return value
    * @param s sued to store return value */
  public void execute(State s) {
    s.update("return",expression.value(s));
  }
  
  /** returns the Return expression in String form */
  public String toString() {
    return "return " + expression.toString() + ";\n";
  }
}