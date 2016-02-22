/**
 * Used as the way for GUI to slide numbers in the gameArray
 * @author David Lance
 */ 
public class SliderMove{
  
  /**
   * slides the row left combining same number
   * @param array to be changed
   * @param row to be slid
   * @return boolean based on if the array was changed or not
   */
  public static boolean slideLeft(int[][] array, int row) {
    
    int slideSpot = 0;
    int[] selectedRow = array[row];
    boolean checkChange = false;
    
    //adds together indexes that are the same and precede eachother chronoclogically
    //and starts adding stuff to the next index once numbers dont match
    for(int i = slideSpot + 1; i < selectedRow.length; i++) {
      if (selectedRow[i] !=0)
      {           
        if (selectedRow[slideSpot] != selectedRow[i] && selectedRow[slideSpot] != 0)  
          slideSpot += 1;
        
        if(slideSpot != i) {
          selectedRow[slideSpot] += selectedRow[i];
          selectedRow[i] = 0;
          checkChange = true;
        }
      }
    }    
    return checkChange;
  }
  
  
  /**
   * slides the row right combining same number
   * @param array to be changed
   * @param row to be slid
   * @return boolean based on if the array was changed or not
   */
  public static boolean slideRight(int[][] array, int row) {
    
    int[] selectedRow = array[row];
    int slideSpot = selectedRow.length - 1;
    boolean checkChange = false;
    
    //adds together indexes that are the same and precede eachother chronoclogic
    //and starts adding stuff to the next index once numbers dont match
    for(int i = slideSpot - 1; i >= 0; i--) {
      if (selectedRow[i] !=0)
      { 
        
        if (selectedRow[slideSpot] != selectedRow[i] && selectedRow[slideSpot] != 0)  
          slideSpot -= 1;
        
        if(slideSpot != i) {
          selectedRow[slideSpot] += selectedRow[i];
          selectedRow[i] = 0;
          checkChange = true;
        }
 
      }
    }    
    
    return checkChange;
  }
  
  /**
   * slides the column up combining same number
   * @param array to be changed
   * @param col column to be slid
   * @return boolean based on if the array was changed or not
   */
  public static boolean slideUp(int[][] array, int col) {
    
    int slideSpot = 0;
    boolean checkChange = false;
    
    //catching null arrays
    try { if(array[0][0] == 1) {}} 
    catch (Exception e) {return false;}
    
    for(int i = slideSpot + 1; i < array.length; i++) {
      if (array[i][col] !=0)
      { 
        
        if (array[slideSpot][col] != array[i][col] && array[slideSpot][col] != 0)  
          slideSpot += 1;
        
        if(slideSpot != i) {
          array[slideSpot][col] += array[i][col];
          array[i][col] = 0; 
          checkChange = true;
        }
      }
    }    
    return checkChange;
  }
  
  /**
   * slides the column down combining same number
   * @param array to be changed
   * @param col column to be slid
   * @return boolean based on if the array was changed or not
   */
  public static boolean slideDown(int[][] array, int col) {
    
    int slideSpot = array.length - 1;
    boolean checkChange = false;
    
    //catching null arrays
    try { if(array[0][0] == 1) {}} 
    catch (Exception e) {return false;}
    
    for(int i = slideSpot - 1; i >= 0; i--) {
      if (array[i][col] !=0)
      { 

        if (array[slideSpot][col] != array[i][col] && array[slideSpot][col] != 0)  
          slideSpot -= 1;
        
        if(slideSpot != i) {
          array[slideSpot][col] += array[i][col];
          array[i][col] = 0;
          checkChange = true;
        }
      }
    }    
    return checkChange;
  }
  
  /**
   * slides the diagnol up combining same number
   * @param array to be changed
   * @param row defining the diagnol for sliding
   * @param col column defining the diagnol for sliding
   * @return boolean based on if the array was changed or not
   */
  public static boolean slideUpLeft(int[][] array, int row, int col) {
    
    if (row >= col) {
      row = row - col;
      col = 0;
    }
    else {
      col = col - row;
      row = 0;
    }
    int slideSpotRow = row;
    int slideSpotCol = col;
    boolean checkChange = false;
    
    //catching null arrays
    try { if(array[0][0] == 1) {}} 
    catch (Exception e) {return false;}
    
    for(int i = row, j = col; i < array.length && j < array[i].length ; i++,j++) {
      if (array[i][j] != 0 && i != row)
      { 
        
        if (array[slideSpotRow][slideSpotCol] != array[i][j] && array[slideSpotRow][slideSpotCol] != 0)  {
          slideSpotRow += 1;
          slideSpotCol += 1;
        }
        if(slideSpotRow != i) {
          array[slideSpotRow][slideSpotCol] += array[i][j];
          array[i][j] = 0;
          checkChange = true;
        }
      }
    }    
    return checkChange;
  }
  
  /**
   * slides the diagnol up combining same number
   * @param array to be changed
   * @param row defining the diagnol for sliding
   * @param col column defining the diagnol for sliding
   * @return boolean based on if the array was changed or not
   */
  public static boolean slideDownRight(int[][] array, int row, int col) {
    
    if (row >= col) {
      row = row - col;
      col = 0;
    }
    else {
      col = col - row;
      row = 0;
    }
    
    int slideSpotRow = array.length - col - 1;
    int slideSpotCol = array[row].length - row - 1;
    boolean checkChange = false;
    
    //catching null arrays
    try { if(array[0][0] == 1) {}} 
    catch (Exception e) {return false;}
    
    for(int i = slideSpotRow, j = slideSpotCol; i >= 0 && j >= 0; i--,j--) {
      if (array[i][j] !=0 && i != array.length - 1)
      { 
        
        if (array[slideSpotRow][slideSpotCol] != array[i][j] && array[slideSpotRow][slideSpotCol] != 0)  {
          slideSpotRow -= 1;
          slideSpotCol -= 1;
        }
        
        if(slideSpotRow != i) {
          array[slideSpotRow][slideSpotCol] += array[i][j];
          array[i][j] = 0;
          checkChange = true;
        }
      }
    }    
    return checkChange;
  }
  
  /**
   * slides the diagnol up combining same number
   * @param array to be changed
   * @param row defining the diagnol for sliding
   * @param col column defining the diagnol for sliding
   * @return boolean based on if the array was changed or not
   */
  public static boolean slideUpRight(int[][] array, int row, int col) {
    
    col = col + row;
    row = 0;
    
    //catching null arrays
    try { if(array[0][0] == 1) {}} 
    catch (Exception e) {return false;}
    
    if(col > (array[0].length - 1)) {
      row = col -(array[0].length - 1);
      col = (array[0].length - 1);
    }

    int slideSpotRow = row;
    int slideSpotCol = col;
    boolean checkChange = false;
    
    for(int i = slideSpotRow, j = slideSpotCol; i < array.length && j >= 0; i++,j--) {
      if (array[i][j] !=0 && i != 0)
      { 
        
        if (array[slideSpotRow][slideSpotCol] != array[i][j] && array[slideSpotRow][slideSpotCol] != 0)  {
          slideSpotRow += 1;
          slideSpotCol -= 1;
        }
        
        if(slideSpotRow != i) {
          array[slideSpotRow][slideSpotCol] += array[i][j];
          array[i][j] = 0;
          checkChange = true;
        }
      }
    }    
    return checkChange;
  }
  
  /**
   * slides the diagnol up combining same number
   * @param array to be changed
   * @param row defining the diagnol for sliding
   * @param col column defining the diagnol for sliding
   * @return boolean based on if the array was changed or not
   */
  public static boolean slideDownLeft(int[][] array, int row, int col) {
    
    row = col + row;
    col = 0;
    
    if(row > (array.length - 1)) {
      col = row - (array[0].length - 1);
      row = array.length - 1;
    }

    int slideSpotRow = row;
    int slideSpotCol = col;
    boolean checkChange = false;
    
    //catching null arrays
    try { if(array[0][0] == 1) {}} 
    catch (Exception e) {return false;}
    
    for(int i = slideSpotRow, j = slideSpotCol; j < array[row].length && i >= 0; i--,j++) {
      if (array[i][j] !=0 && i != array.length - 1)
      {
    
        if (array[slideSpotRow][slideSpotCol] != array[i][j] && array[slideSpotRow][slideSpotCol] != 0)  {
          slideSpotRow -= 1;
          slideSpotCol += 1;
        }
        
        if(slideSpotRow != i) {
          array[slideSpotRow][slideSpotCol] += array[i][j];
          array[i][j] = 0;
          checkChange = true;
        }
      }
    }    
    return checkChange;
  }
  
  /**
   * changes arrays into Strings for JUnit tester
   * @param a array to be written out
   * @return String in the format for JUnit tester
   */ 
  public static String printArray(int[][] a) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    
    for (int i = 0; i < a.length; i++) {
      if (i != 0)
        sb.append(",");
      sb.append("{");
      
      for (int j = 0; j < a[i].length; j++) {
        if (j != 0)
          sb.append(",");
        sb.append(a[i][j]);
      }
      sb.append("}");
    }
    
    sb.append("}");
    return sb.toString();
  }
 
  
}










