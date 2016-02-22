//David Lance
/** interface for Expressions that will produce booleans */
public interface ExpressionBoolean extends Expression {
 
  /** method stub designation for value needing to be implemented
    * @param s used for variable referencing
    * @return boolean based on what operator dictates*/
  boolean value(State s);
}