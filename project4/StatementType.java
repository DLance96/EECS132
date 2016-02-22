//David Lance
/** class used for extending purposes to make sure all statement classes get proper toStringTabbed */
public class StatementType{
    /** returns the conditional in string form tabbed accordiningly
    * @param tabCount amount of tabs used in front of conditional 
    * @return String properly tabbed*/
  public String toStringTabbed(int tabCount) {
    String tabs;
    //builder for tabs
    StringBuilder bTabs = new StringBuilder();
    //builder for output
    StringBuilder bOutput = new StringBuilder("\t");
    
    //creates a string for the tabs
    for (int i = 0; i < tabCount; i++) {
      bTabs.append("\t");
    }
    tabs = bTabs.toString();
    
    for(int i = 0; i < this.toString().length(); i++) {
      bOutput.append(this.toString().charAt(i));
      if (this.toString().charAt(i) == '\n' && i != this.toString().length() - 1) {
        bOutput.append(tabs);
      }
    }
        
    return bOutput.toString();
  }
}