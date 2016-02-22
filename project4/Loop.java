//David Lance
/** class used to represent while loops */
public class Loop extends StatementType implements Statement{
  
  
  /** boolean conditional for statement*/
  private ExpressionBoolean boolCond;
  /** statement ran in each loop*/
  private Statement loopBody;
  
  /** constructs a loop based on boolean condition and body of loop
    * @param boolCond used to check if loop runs again
    * @param loopBody is executed for every loop ran */
  public Loop(ExpressionBoolean boolCond, Statement loopBody) {
    this.boolCond = boolCond;
    this.loopBody = loopBody;
  }
  
  /** runs the loop
    * @param s State used for variable referencing */
  public void execute(State s) {
    while(boolCond.value(s)) {
      loopBody.execute(s);
    }
  }
  
  /** returns the conditional in String form */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("while (" +
              boolCond.toString() +
              ")\n" +
              loopBody.toStringTabbed(1));
    return sb.toString();
  }
}