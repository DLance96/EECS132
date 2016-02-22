//David Lance
/** method creation in the language class */
public class Function implements Expression{
  
  /** stores name of function */
  private String name;
  /** used to store body of function */
  private Statement functionBody;
  /** stores all params of function */
  private Variable[] variableArray = {};
  
  /** constucts a function based on inputs 
    * @param name wht the function is named
    * @param functionBody what the function does
    * @param variableArray list of all paramters for the function*/
  public Function(String name, Statement functionBody, Variable... variableArray) {
    this.name = name;
    this.functionBody = functionBody;
    this.variableArray = variableArray;
  }
  
  /** constructor for 0 variables
    * @param name wht the function is named
    * @param functionBody what the function does */
  public Function(String name, Statement functionBody) {
    this.name = name;
    this.functionBody = functionBody;
  }
  
  /** returns function in String form */
  public String toString() {
    StringBuilder bFunction = new StringBuilder();
    bFunction.append("function " +
              name +
              "(");
    for (int i = 0; i < variableArray.length; i++) {
      bFunction.append(variableArray[i].toString());
      if(i != variableArray.length - 1)
        bFunction.append(", ");
    }
    bFunction.append(")\n");
    bFunction.append(functionBody.toString());
    return bFunction.toString();
  }
  
  /**used to retrieve the name of the function 
    * @return name of function*/
  public String getName() {
    return name;
  }
  
  /**used to retrieve the body of the function 
    * @return Statement of function*/
  public Statement getFunctionBody() {
    return functionBody;
  }
  
  /**used to retrieve the variables of the function 
    * @return variable array of the function*/
  public Variable[] getVariables() {
    return variableArray;
  }
  
}