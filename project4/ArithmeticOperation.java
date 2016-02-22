//David Lance
/** class used for Arithemtic Operations in functions */
public class ArithmeticOperation implements ExpressionInt{
  
  /** options for Arithmetic operations */
  public enum Operator { Add, Sub, Mult, Div, Rem; }
  
  /** used to store which operator for action */
  private Operator operator;
  /** used to store Expression */
  private ExpressionInt e1;
  /** used to store Expression */
  private ExpressionInt e2;
  
  /** used to construct Operation with 2 expressions and an operation between the 2
    * @param operator Operator used on the 2 Expressions
    * @param e1 Expression that operator will be used on in combiniation with e2
    * @param e2 Expression that operator will be used on in combiniation with e1 
    */
  public ArithmeticOperation(Operator operator, ExpressionInt e1, ExpressionInt e2) {
    this.e1 = e1;
    this.e2 = e2;
    this.operator = operator;
  }
  
  /** used to return result of Operator on 2 Expressions
   * @param s State for referencing variables */
  public int value(State s) {
    int e1Value = e1.value(s);
    int e2Value = e2.value(s);
    
    switch (operator) {
      case Add : return e1Value + e2Value;
      case Sub : return e1Value - e2Value;
      case Mult: return e1Value * e2Value;
      case Div : return e1Value / e2Value;
      case Rem : return e1Value % e2Value;
      default:   break;
    }
    return 0;
  }
  
  /** used to return String of e1 + Operator + e2 */
  public String toString() {
    String operatorString = operatorToString();
    return e1.toString() + " " + operatorString + " " + e2.toString();
  }
  
  /** used to convert Operator to a String for toString method */
  private String operatorToString() {
    switch (operator) {
      case Add : return "+";
      case Sub : return "-";
      case Mult: return "*";
      case Div : return "/";
      case Rem : return "%";
      default:   break;
    }
    return "";
  }
}