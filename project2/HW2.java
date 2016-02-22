//David Lance
public class HW2 {
  
  //inputs a string that is looped through checking if each letter is alphabetically in order
  public static boolean isAlphabeticalOrder(String s)
  {
    char prevChar = 'A';
    
    //steps through the string checking to make sure each letter is greater than or equal to the previous
    for (int i = 0; i < s.length(); i++)
    {
      if (isLetter(s.charAt(i)))
      {
        //checks if the letter comes after the previous letter
        if (prevChar <= returnCapitalChar(s.charAt(i)))
          prevChar = returnCapitalChar(s.charAt(i)); //changes to the current letter for comparison next time
        else
          return false;
      }
    }
    return true;
  }
  
  /*** Personal Method ***/
  //used to check if a char is a letter
  private static boolean isLetter(char c)
  {
    if (c >= 'a' && c <= 'z')
      return true;
    if (c >= 'A' && c <= 'Z')
      return true;
    return false;
  }

  /*** Personal Method ***/
  //takes in a char and returns the capital form of it
  private static char returnCapitalChar(char c)
  {
    if (c >= 'a' && c <= 'z')
      return (char)('A'-'a' + c);
    else
      return c;
  }
  
  //takes in a string an int and char and reomves the char a maximize of the int times from the string
  public static String removeNchars(String s, int removeCount, char filterChar)
  {
    StringBuilder sb = new StringBuilder();
    
    //steps through the string removing each filter char until removecount is 0
    for (int i = 0; i < s.length(); i++)
    {
      if (filterChar == s.charAt(i) && removeCount > 0)
        removeCount--;
      else
        sb.append(s.charAt(i));
    }
    
    return sb.toString();
  }
  
  //takes in a char and a string and moves that char one to the right if possible in the string
  public static String moveXright(char X, String s)
  {
    StringBuilder sb = new StringBuilder();
    
    //steps through the string replacing X with the char next in line when found
    for (int i = 0; i < s.length(); i++)
    {
      if (s.charAt(i) == X)
      {
        try {
          sb.append(s.charAt(i + 1));
        }
        catch(StringIndexOutOfBoundsException e) {
          sb.append(X);
        }
        i++;
        X = (char)0;
      }
      else 
        sb.append(s.charAt(i)); 
    } 
    
    return sb.toString();
  }

  //takes in a string and another string that if it exsists in the first string will be bracketed in the return
  public static String bracketString(String s, String part)
  {
    StringBuilder sb = new StringBuilder();
    
    //steps through the string finding matching parts and adding brackets before and after when found
    for(int i = 0; i < s.length(); i++)
    {
      try{
        if(partStringMatch(s, part, i))
        {
          sb.append('[');
          sb.append(part);
          sb.append(']');
          i += part.length() - 1;
        }
        else
          sb.append(s.charAt(i));
      }
      catch(StringIndexOutOfBoundsException e) {
        sb.append(s.charAt(i));
      }
    }
    return sb.toString();
  }
  
  
  /*** Personal Method ***/
  //tests if the part string matches the main string starting from an index
  private static boolean partStringMatch(String s, String part, int startIndex)
  {  
    int partIndex = 0;
    
    for (int i = startIndex; partIndex < part.length(); i++)
    {
      if (part.charAt(partIndex) != s.charAt(i))
        return false;
      else
        partIndex++;
    }
    return true;
  }
  
  
  //takes in a char and a string and moves that char one to the right if possible in the string
  public static String moveAllXsRight(char X, String s)
  {
    StringBuilder sb = new StringBuilder();
    String xString = "";
    
    //steps through the string tracking how many x found in a row and replacing them with the next non X char moving them to the right
    for (int i = 0; i < s.length(); i++)
    {
      if(s.charAt(i) == X)
        xString += X;
      else
      {
        sb.append(s.charAt(i));
        sb.append(xString);
        xString = "";
      }
      if(i == s.length() - 1)
      {
        sb.append(xString);
      }
    } 
    
    return sb.toString();
  }
  
  //takes in a char and a string and moves the char 1 line down on the string when possible
  public static String moveXdown(char X, String stringLines)
  {
    //varibales used in first loop
    int indexX = -1; //tracks index of X
    int indexFromLine = 1; //tracks how far away X if from the line start it appears on
    
    //variables used in second loop
    int indexForSwitch = 0; //index for where the X char will be moved to 
    int lineCounter = 0; //tracks once the second loop has progressed to another line
    int lineStartIndex = 0; //tracks the index of where a new line starts
    boolean lineLengthMatch = false; //tracks if proper situation occurs to allow for the X char ot move
    
    //finding the location of the X
    for(int i = 0; i < stringLines.length(); i++)
    {
      if(partStringMatch(stringLines, "\n", i))
      {
        i++;                 //adds one to compensate for the n in \n
        if (indexX == -1)
          indexFromLine = 1; //resets distance from newline if index wasnt set
      }
      if (indexX == -1)                    //if index hasnt been set 
      {
        if(stringLines.charAt(i) == X)     //set index if the char
          indexX = i;
        else
          indexFromLine++;                //count distance from newline if not
      }
    }
    
    //moves foward from X index then replaces the two chars if proper situation
    for(int i = indexX + 1; i < stringLines.length() && lineCounter < 2 && lineLengthMatch == false; i++)
    {
      if(partStringMatch(stringLines, "\n", i))
      {
        lineCounter += 1;
        lineStartIndex = i;
        i++;
        indexForSwitch = lineStartIndex + indexFromLine;
      }
      else if(indexForSwitch == i)
          lineLengthMatch = true;

    }

    if (indexX == -1) //X was never found in the String
      return stringLines;
    if (lineLengthMatch)
      return charReplace (stringLines, X, indexX, stringLines.charAt(indexForSwitch), indexForSwitch);
    return stringLines;
  }
  
  /*** Personal Method ***/
  //changes of the char at the two locations with the opposite chars as inputed in the given string
  public static String charReplace(String s, char char1, int indexChar1, char char2, int indexChar2)
  {
    StringBuilder sb = new StringBuilder(s);
    sb.setCharAt(indexChar2, char1);
    sb.setCharAt(indexChar1, char2);
    
    return sb.toString();
  }
}

