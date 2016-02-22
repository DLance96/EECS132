//David Lance
public class Comparison implements ExpressionBoolean{
  
  /** options for Conditional operations */
  public enum Operator { LT, LTE, GT, GTE, EQ, NEQ; };
  
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
  public Comparison(Operator operator, ExpressionInt e1, ExpressionInt e2) {
    this.e1 = e1;
    this.e2 = e2;
    this.operator = operator;
  }
  
  /** used to return result of Operator on 2 Expressions
   * @param s State for referencing variables */
  public boolean value(State s) {
    int e1Value = e1.value(s);
    int e2Value = e2.value(s);
    
    switch (operator) {
      case LT  : return e1Value < e2Value;
      case LTE : return e1Value <= e2Value;
      case GT  : return e1Value > e2Value;
      case GTE : return e1Value >= e2Value;
      case EQ  : return e1Value == e2Value;
      case NEQ : return e1Value != e2Value;
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
      case LT  : return  "<" ;
      case LTE : return  "<=" ;
      case GT  : return  ">" ;
      case GTE : return  ">=" ;
      case EQ  : return  "==" ;
      case NEQ : return  "!=" ;
      default:   break;
    }
    return "";
  }
}