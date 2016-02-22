//David Lance
public class BooleanOperation{
  
  /** options for Boolean operations */
  public enum Operator { And, Or; };
  
  /** used to store which operator for action */
  private Operator operator;
  /** used to store Expression */
  private ExpressionBoolean e1;
  /** used to store Expression */
  private ExpressionBoolean e2;
  
  /** used to construct Operation with 2 expressions and an operation between the 2
    * @param operator Operator used on the 2 Expressions
    * @param e1 Expression that operator will be used on in combiniation with e2
    * @param e2 Expression that operator will be used on in combiniation with e1 
    */
  public BooleanOperation(Operator operator, ExpressionBoolean e1, ExpressionBoolean e2) {
    this.e1 = e1;
    this.e2 = e2;
    this.operator = operator;
  }
  
  /** used to return result of Operator on 2 Expressions
    * @param s State for referencing variables
    * @return boolean based on operator*/
  public boolean value(State s) {
    boolean e1Value = e1.value(s);
    boolean e2Value = e2.value(s);
    
    switch (operator) {
      case And  : return e1Value && e2Value;
      case Or : return e1Value || e2Value;
      default:   break;
    }
    return false;
  }
  
  /** used to return String of e1 + Operator + e2 */
  public String toString() {
    String operatorString = operatorToString();
    return e1.toString() + " " + operatorString + " " + e2.toString();
  }
  
  /** used to convert Operator to a String for toString method */
  private String operatorToString() {
    switch (operator) {
      case And  : return  "&&" ;
      case Or : return  "||" ;
      default:   break;
    }
    return "";
  }
}