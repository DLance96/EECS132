//David Lance
/** class used for combining mulitple statements*/
public class CompoundStatement extends StatementType implements Statement{
  
  /** used to store all Statment */
  private Statement[] statementArray;
  
  /** constructs a series of Statments
    * @param statementArray list of Statements for the compound statement */
  public CompoundStatement(Statement... statementArray) {
    this.statementArray = statementArray;
  }
  
  /** executes all statements in order
    * @param s used for variable reference when needed */
  public void execute(State s) {
    for (int i = 0; i < statementArray.length; i++) {
      statementArray[i].execute(s);
    }
  }
  
  /** returns the Compound Statement in String form */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    for(int i = 0; i < statementArray.length; i++) {
      sb.append(statementArray[i].toStringTabbed(1));
    }
    sb.append("}\n");
    return sb.toString();
  }
  
}