//David Lance
/** interface for Expressions that will produce ints from value() */
public interface ExpressionInt extends Expression {
 
  /** method stub designation for value needing to be implemented
    * @param s used for variable referencing
    * @return int based on operator*/
  int value(State s);
}