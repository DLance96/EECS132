//David Lance
import junit.framework.TestCase;

public class LanguageTester extends TestCase {
  
  /** State used thoughout testing */
  public State s = new State();
  
  public void testState() {
    s.update("x",7);
    
    //returns 0 if no variable
    s.update("testVar",7);
    assertEquals("Non defined variable should be 0",0,s.lookup("None"));
    
    //returns "testVar" value
    assertEquals("testVar should return 7",7,s.lookup("testVar"));
    
    //returns "testVar" value after change
    s.update("testVar",8);
    assertEquals("testVar should return 8",8,s.lookup("testVar"));
  }
  
  public void testVariable() {
    s.update("x",7);
    
    //making x Varibale and checking value
    Variable x = new Variable("x");
    s.update("x",7);
    assertEquals("x should be 7",7,x.value(s));
    
    //return 0 for value of y since it isnt set
    Variable noValue = new Variable("noValue");
    assertEquals("noValue should be 0",0,noValue.value(s));
    
    //returning name of x
    assertEquals("x should return x","x",x.toString());
  }
  
  public void testNumber() {
    Number n = new Number(5);
    
    //basic value method
    assertEquals("n should be 5",5,n.value(s));
    
    //returning number in String form
    assertEquals("n should return \"5\"","5",n.toString());
    
  }
  
  public void testArithmeticOperation() {
    //commonly used for testing
    Number n1 = new Number(2);
    Number n2 = new Number(4);
    Number n3 = new Number(5);
    Variable x = new Variable("x");
    s.update("x",7);
    
    //adding 2 and 4
    ArithmeticOperation a1 = new ArithmeticOperation(ArithmeticOperation.Operator.Add, n1,n2);
    assertEquals("a1 should be 6",6,a1.value(s));
    
    //subtraction of a variable of 7 and a number 4
    ArithmeticOperation a2 = new ArithmeticOperation(ArithmeticOperation.Operator.Sub, x,n2);
    assertEquals("a2 should be 3",3,a2.value(s));
    
    //return String of multiplication of a variable of 7 and a number 5
    ArithmeticOperation a3 = new ArithmeticOperation(ArithmeticOperation.Operator.Mult, x,n3);
    assertEquals("should return \"x * 5\"","x * 5",a3.toString());
  }
  
  public void testComparison() {
    //commonly used for testing
    Number n1 = new Number(2);
    Number n2 = new Number(4);
    Number n3 = new Number(5);
    Variable x = new Variable("x");
    s.update("x",7);
    
    //returning true based on 4 > 2
    Comparison c1 =  new Comparison(Comparison.Operator.GT, n2,n1);
    assertEquals("c1 should be true",true,c1.value(s));
    
    //returning true based on 7 < 2
    Comparison c2 =  new Comparison(Comparison.Operator.LT, x,n1);
    assertEquals("c2 should be false",false,c2.value(s));
    
    //returning true based on 7 <= 5
    Comparison c3 =  new Comparison(Comparison.Operator.LTE, x,n3);
    assertEquals("c3 should be \"x <= 5\"","x <= 5",c3.toString());
  }
  
  public void testBooleanOperation() {
    //commonly used for testing
    Number n1 = new Number(2);
    Number n2 = new Number(4);
    Number n3 = new Number(5);
    Variable x = new Variable("x");
    s.update("x",7);
    
    Comparison c1 =  new Comparison(Comparison.Operator.GT, n2,n1);
    Comparison c2 =  new Comparison(Comparison.Operator.LT, x,n1);
    Comparison c3 =  new Comparison(Comparison.Operator.LTE, x,n3);
    
    //return true based on or between true false
    BooleanOperation b1 = new BooleanOperation(BooleanOperation.Operator.Or, c1, c2);
    assertEquals("b1 should return true",true,b1.value(s));
    
    //returning false on and between true false
    BooleanOperation b2 = new BooleanOperation(BooleanOperation.Operator.And, c1, c3);
    assertEquals("b2 should return false",false,b2.value(s));
    
    //returning toString 
    assertEquals("b2 should return \"4 > 2 && x <= 5\"", "4 > 2 && x <= 5", b2.toString());
  }
  
  public void testAssignment() {
    //commonly used for testing
    Number n1 = new Number(2);
    Number n2 = new Number(4);
    Number n3 = new Number(5);
    Variable x = new Variable("x");
    Variable var = new Variable("var");
    ArithmeticOperation arith = new ArithmeticOperation(ArithmeticOperation.Operator.Mult, x,n1);
    s.update("x",7);
    
    //setting a varibale with Assignment
    Assignment a1 = new Assignment(var,arith);
    a1.execute(s);
    assertEquals("var should equal 14",14,var.value(s));
    
    //returning to String
    assertEquals("should return var := x * 2","var := x * 2;\n",a1.toString());
    
    //returning toStringTabbed
    assertEquals("should return tab tab var := x * 2","\tvar := x * 2;\n",a1.toStringTabbed(1));
    
    //changing an active variable
    Assignment a2 = new Assignment(x,arith);
    a2.execute(s);  
    assertEquals("x should equal 14",14,x.value(s));
  }
  
  public void testReturn() {
    //commonly used for testing
    Number n1 = new Number(2);
    Number n2 = new Number(4);
    Number n3 = new Number(5);
    Variable x = new Variable("x");
    Variable returnVar = new Variable("returnVar");
    s.update("x",7);
    ArithmeticOperation arith = new ArithmeticOperation(ArithmeticOperation.Operator.Mult, x,n1);
    
    //return of expression
    Return r1 = new Return(arith);
    r1.execute(s);
    assertEquals("returnVar should equal 14",14,s.lookup("return"));
    
    //returning to String
    assertEquals("should return return: x * 2","return x * 2;\n",r1.toString());
    
    //returning to String Tabbed
    assertEquals("should return return: tab x * 2","\treturn x * 2;\n",r1.toStringTabbed(1));
  }
  
  public void testConditionalStatement() {
    //commonly used for testing
    Number n1 = new Number(2);
    Number n2 = new Number(4);
    Number n3 = new Number(5);
    Variable x = new Variable("x");
    Variable var = new Variable("var");
    Assignment a1 = new Assignment(var,new Number(1));
    Assignment a2 = new Assignment(var,new Number(2));
    
    //testing based on true
    Comparison c1 =  new Comparison(Comparison.Operator.GT, n2,n1);
    ConditionalStatement cs1 = new ConditionalStatement(c1,a1,a2);
    cs1.execute(s);
    assertEquals("should have var be 1",1,var.value(s));
    
    //testing based on false
    Comparison c2 =  new Comparison(Comparison.Operator.GT, n1,n2);
    ConditionalStatement cs2 = new ConditionalStatement(c2,a1,a2);
    cs2.execute(s);
    assertEquals("should have var be 2",2,var.value(s));
    
    //returns String of the statement
    assertEquals("should return string form",
                 "if (4 > 2)\n\tvar := 1;\nelse\n\tvar := 2;\n",
                 cs1.toString());
    
    //returns TabbedString of the statement
    assertEquals("should return string form tabbed",
                 "\tif (4 > 2)\n\t\tvar := 1;\n\telse\n\t\tvar := 2;\n",
                 cs1.toStringTabbed(1));
    
  }
  
  public void testLoop() {
    //commonly used for testing
    Number n1 = new Number(2);
    Number n2 = new Number(4);
    Number n3 = new Number(5);
    Variable x = new Variable("x");
    Variable var = new Variable("var");
    s.update("var",1);
    Assignment a1 = new Assignment(var, new ArithmeticOperation(ArithmeticOperation.Operator.Mult, var, new Number(2)));
    
    //testing  simple loop doubling until above 5
    Comparison c1 =  new Comparison(Comparison.Operator.LT, var, n3);
    Loop loop1 = new Loop(c1,a1);
    loop1.execute(s);
    assertEquals("should loop making var = 8",8,var.value(s));
    
    //returning loop in String form
    assertEquals("should make loop into a string","while (var < 5)\n\tvar := var * 2;\n",loop1.toString());
    
    //returning loop tabbed in String form
    assertEquals("should make loop into a string tabbed","\twhile (var < 5)\n\t\tvar := var * 2;\n",loop1.toStringTabbed(1));
  }
  
  public void testCompoundStatement() {
    //commonly used for testing
    Number n1 = new Number(2);
    Number n2 = new Number(4);
    Number n3 = new Number(5);
    Variable var = new Variable("var");
    s.update("var",1);
    Assignment a1 = new Assignment(var, new Number(1));
    Assignment a2 = new Assignment(var, new Number(2));
    Assignment a3 = new Assignment(var, new Number(3));
    
    //testing a basic compound statement
    CompoundStatement cp1 = new CompoundStatement(a1,a2);
    cp1.execute(s);
    assertEquals("should make var return 2 because a2 is last", 2, var.value(s));
                 
    //testing against first one for chrnology
    CompoundStatement cp2 = new CompoundStatement(a2,a3,a1);
    cp2.execute(s);
    assertEquals("should make var return 1 because a1 is last", 1, var.value(s));
    
    //retunring Compound statement in string form
    assertEquals("should convert to string","{\n\tvar := 1;\n\tvar := 2;\n}\n",cp1.toString());
    
    //returning string form with tabs
    assertEquals("should convert to string","\t{\n\t\tvar := 1;\n\t\tvar := 2;\n\t}\n",cp1.toStringTabbed(1));
  }
  
  public void testFunction() {
    Variable var = new Variable("var");
    Variable var2 = new Variable("var2");
    Assignment a1 = new Assignment(var, new Number(1));
    Assignment a2 = new Assignment(var2, new Number(2));
    CompoundStatement c1 = new CompoundStatement(a1,a2);
    Function f = new Function("test",c1,var,var2);
    
    //returns Function in a String form
    assertEquals("turns function into a Sring","function test(var, var2)\n{\n\tvar := 1;\n\tvar2 := 2;\n}\n",f.toString());
    
    //returns name
    assertEquals("returns the name of function","test",f.getName());
    
    //returns statement
    assertEquals("returns the Body of function",c1,f.getFunctionBody());
    
    //returns variables
    assertEquals("returns the first var of function",var,f.getVariables()[0]);
    
  }
  
  public void testFunctionCall() {
    Variable x = new Variable("x");
    Variable n = new Variable("n");
    Function f = new Function("sum", new CompoundStatement(new Assignment(x, new Number(0)), 
                                                           new Loop(new Comparison(Comparison.Operator.GT, n, new Number(0)), 
                                                                                                      new CompoundStatement(new Assignment(x, new ArithmeticOperation(ArithmeticOperation.Operator.Add, x, n)), 
                                                                                                                            new Assignment(n, new ArithmeticOperation(ArithmeticOperation.Operator.Sub, n, new Number(1))))), 
                                                           new Return(x)), n);
    Function m = new Function("main", new CompoundStatement(new Assignment(new Variable("y"), new Number(6)), new Return(new FunctionCall(f, new Variable("y")))));

    //string test
    assertEquals("converts to String","function sum(n)\n{\n\tx := 0;\n\twhile (n > 0)\n\t\t{\n\t\t\tx := x + n;\n\t\t\tn := n - 1;\n\t\t}\n\treturn x;\n}\n",f.toString());
    
    //call with 1
    assertEquals("using function call",1,new FunctionCall(f, new Number(1)).value(new State()));
    
    //call with 10
    assertEquals("using function call",55,new FunctionCall(f, new Number(10)).value(new State()));
    
    //additional String test
    assertEquals("converts to String","function main()\n{\n\ty := 6;\n\treturn sum(y);\n}\n",m.toString());
    
    //additional calling test
    assertEquals("using function call",21,new FunctionCall(m).value(new State()));
  }
}
