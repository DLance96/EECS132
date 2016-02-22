//David Lance
public class Assignment extends StatementType implements Statement{
   /** var where expression will be stored */
  private Variable var;
  /** expression what is stored in var */
  private ExpressionInt expression;
  
  /** creates an assignment instance
    * @param var Variable for where the int will be assigned
    * @param e epression to be assigned to Variable
    */
  public Assignment(Variable var, ExpressionInt e) {
    this.var = var;
    this.expression = e;
  }
  
  /** executes the Aissgnment by updating the variable with the value of the expression
    * @param s the State used for variable updating */
  public void execute(State s) {
    s.update(var.toString(), expression.value(s));
  }
  
  /** returns the Assignment expression in String form */
  public String toString() {
    return var.toString() + " := " + expression.toString() + ";\n";
  }
}