//David Lance
/** class used to call a function in the language */
public class FunctionCall implements ExpressionInt{
  
  /** stores the functions used */
  private Function function;
  /** stores all varibale inputs */
  private ExpressionInt[] variableInputs;
  /** stores all variables params */
  private Variable[] variableArray = {};
  /** stores functions processes */
  private Statement functionBody;
  /** stores state for varibale referening */
  private State state;
  
  /** constructs function call
    * @param function used to produce value
    * @param variableInputs fills functions params */
  public FunctionCall(Function function,ExpressionInt... variableInputs) {
    this.function = function;
    this.variableInputs = variableInputs;
    this.variableArray = function.getVariables();
    this.functionBody = function.getFunctionBody();
  }
  
  /** constructs function call when no params for function
    * @param function used to produce value */
  public FunctionCall(Function function) {
    this.function = function;
    this.variableArray = function.getVariables();
    this.functionBody = function.getFunctionBody();
  }
  
  /** returns value of return after running  function
    * @param s State used for varibale referencing */
  public int value(State s) {
    state = new State();
    
    for(int i = 0; i < variableArray.length; i++) {
      s.update(variableArray[i].toString(), variableInputs[i].value(state));
    }
    
    functionBody.execute(state);
    
    return state.lookup("return");
  }
  
  /** returns function call in String form */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(function.getName() +
              "(");
    for (int i = 0; i < variableArray.length; i++) {
      sb.append(variableInputs[i].toString());
      if(i != variableArray.length - 1)
        sb.append(", ");
    }
    sb.append(")");
    return sb.toString();
  }
}