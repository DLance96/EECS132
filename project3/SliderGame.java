
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.JOptionPane;

/**
 * Used as the the Game GUI and means of starting the game
 * @author David Lance
 */ 
public class SliderGame extends JFrame implements ActionListener{
  
  
  /**
   * values for each location stored here
   */ 
  private int[][] gameArray; 
  /**
   * Used to store all buttons
   */ 
  private JButton[][] buttonArray;
  /**
   * all buttons for sliding are here
   */ 
  private JPanel board; //panel that holds the buttons
  /**
   * goal that will trigger win Dialogue
   */ 
  private int goal = 2048;
  /**
   * Button for reseting the board
   */ 
  private JButton resetButton;
  /**
   * Where the score is displayed
   */ 
  private JLabel scoreLabel;
  
  /**
   * Called to intialize a game by generating the buttons and changing the JFrame of the game itself
   * @param row used for amount of rows in game
   * @param col used for amount of columns in game
   */
  public SliderGame(int row, int col) {
    
    gameArray = new int[row][col];
    buttonArray = new JButton[row][col];
    board = new JPanel(new GridLayout(row, col));
    
    //Error catching for Macs
    try {
      UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    //button generation
    for (int i = 0; i <= row * col - 1; i++) {
      JButton jB = new JButton();
      jB.setFont(new Font("Sans Serif", Font.BOLD, 20));
      jB.setForeground(Color.white);
      jB.setBorder(new LineBorder(Color.WHITE, 5));
      buttonArray[i / col][i % col] = jB;
      jB.addActionListener(this);
      board.add(jB);
    }
    
    //Score
    scoreLabel = new JLabel("Score: 1");
    scoreLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
    scoreLabel.setForeground(Color.white);
    scoreLabel.setBorder(new LineBorder(Color.WHITE, 5));
    scoreLabel.setBackground(Color.BLACK);
    scoreLabel.setOpaque(true);
    
    //reset button
    resetButton = new JButton("Reset Board");
    resetButton.setFont(new Font("Sans Serif", Font.BOLD, 20));
    resetButton.setForeground(Color.white);
    resetButton.setBorder(new LineBorder(Color.WHITE, 5));
    resetButton.setBackground(Color.BLACK);
    resetButton.addActionListener(this);
    
    //JFrame features
    this.getContentPane().add(board,"Center");
    this.getContentPane().add(resetButton,"South");
    this.getContentPane().add(scoreLabel,"North");
    this.setSize(col*100,row*100);
    this.setLocationRelativeTo(null);
    this.setTitle("2048 Parody by David Lance");
    updateBoard();
    this.setVisible(true);
  }
  
  /**
   * Action listener for when buttons are preessed used to slide board based on button location
   * @param e for ActionEvent when buttons are press
   */
  public void actionPerformed(ActionEvent e) {
    JButton b = (JButton)e.getSource();
    
    //reset button
    if(b == resetButton) {
      gameArray = new int[gameArray.length][gameArray[0].length];
      updateBoard();
    }
    
    //top left corner
    if(b == buttonArray[0][0]) {
      if (slideAllUpLeft()) {
        updateBoard();
      }
    }
    //bottom right corner
    else if(b == buttonArray[buttonArray.length - 1][buttonArray[0].length - 1]) {
      if (slideAllDownRight()) {
        updateBoard();
      }
    }
    //top right corner
    else if(b == buttonArray[0][buttonArray[0].length - 1]) {
      if (slideAllUpRight()) {
        updateBoard();
      }
    }
    //bottom left corner
    else if(b == buttonArray[buttonArray.length - 1][0]) {
      if (slideAllDownLeft()) {
        updateBoard();
      }
    }
    //sides
    for (int i = 1; i < buttonArray.length - 1; i++) {
      //left side
      if (b == buttonArray[i][0]) {
        if (slideAllLeft()) {
          updateBoard();
        }
      }
      //right side
      else if (b == buttonArray[i][gameArray[0].length - 1]) {
        if (slideAllRight()) {
          updateBoard();
        }
      }
    }
    //top and bottom edges
    for (int i = 1; i < buttonArray[0].length - 1; i++) {
      //top edge
      if (b == buttonArray[0][i]) {
        if (slideAllUp()) {
          updateBoard();
        }
      }
      //bottom edge
      else if (b == buttonArray[gameArray.length - 1][i]) {
        if (slideAllDown()) {
          updateBoard();
        }
      }
    }
  }
  
  /**
   * calls addNew1() then updates the board with all the current numbers on gameArray
   */
  public void updateBoard() {
    addNew1();
    
    scoreLabel.setText("Score: " + getBoardScore());
    
    for(int i = 0; i < gameArray.length; i++) {
      for(int j = 0; j < gameArray[i].length; j++) {
        //setting 0 values as empty buttons
        if (gameArray[i][j] == 0) {
          buttonArray[i][j].setText("");
          buttonArray[i][j].setBackground(new Color(0, 0, 255));
        }
        else
          buttonArray[i][j].setText(Integer.toString(gameArray[i][j]));
        
       
        //chnaging button backgrounds based on number values
        if (gameArray[i][j] == 1) {
          buttonArray[i][j].setBackground(new Color(21, 0, 231));
        }
        else if  (gameArray[i][j] == 2) {
          buttonArray[i][j].setBackground(new Color(42, 0, 210));
        }
        else if  (gameArray[i][j] == 4) {
          buttonArray[i][j].setBackground(new Color(63, 0, 189));
        }
        else if  (gameArray[i][j] == 8) {
          buttonArray[i][j].setBackground(new Color(84, 0, 168));
        }
        else if  (gameArray[i][j] == 16) {
          buttonArray[i][j].setBackground(new Color(105, 0, 147));
        }
        else if  (gameArray[i][j] == 32) {
          buttonArray[i][j].setBackground(new Color(126, 0, 126));
        }
        else if  (gameArray[i][j] == 64) {
          buttonArray[i][j].setBackground(new Color(147, 0, 105));
        }
        else if  (gameArray[i][j] == 128) {
          buttonArray[i][j].setBackground(new Color(168, 0, 84));
        }
        else if  (gameArray[i][j] == 256) {
          buttonArray[i][j].setBackground(new Color(189, 0, 63));
        }
        else if  (gameArray[i][j] == 512) {
          buttonArray[i][j].setBackground(new Color(210, 0, 42));
        }
        else if  (gameArray[i][j] == 1024) {
          buttonArray[i][j].setBackground(new Color(231, 0, 21));
        }
        else if  (gameArray[i][j] >= 2048) {
          buttonArray[i][j].setBackground(new Color(255, 0, 0));
        }
        
        
        //winning check here accouts for increased goals
        if (gameArray[i][j] == goal) {
          JOptionPane.showMessageDialog(new JFrame(),"Congradulations you made it to " + goal +
                                        "\n now try and get " + (goal * 2));
          goal = goal * 2;
        }
        
      }
    }
  }
  
  /**
   * counts empty button locations and the randoming selects a number based on how many
   * afterwards counts through again til reaching that number before changing that slot to 1 
   */
  public void addNew1() {
    int slotCount = 0;
    int randomInt;
    
    //counting number of slots with 0
    for (int i = 0; i < gameArray.length; i++) {
      for (int j = 0; j < gameArray[i].length; j++) {
       if (gameArray[i][j] == 0)
         slotCount += 1;
      }
    }
    
    //randomly deteriming slot for new 1
    randomInt = (int)(Math.random() * slotCount) + 1;
    slotCount = 0;
    
    //finding the randomly determined slot
    for (int i = 0; i < gameArray.length; i++) {
      for (int j = 0; j < gameArray[i].length; j++) {
        if (gameArray[i][j] == 0) {
         slotCount += 1;
         if (slotCount == randomInt) {
           gameArray[i][j] = 1;
           buttonArray[i][j].setBackground(new Color(100, 100, 200));
         }
        }
      }
    }      
  }
  
  /**
   * Slides all the rows to the left 
   * @return boolean true if a change was made to the board false if not
   */
  private boolean slideAllLeft() {
    boolean changeCheck = false;
    
    for (int i = 0; i < gameArray.length; i++) {
      if (SliderMove.slideLeft(gameArray,i))
        changeCheck = true;
    }
    
    return changeCheck;
  }
  
  /**
   * Slides all the rows to the right 
   * @return boolean true if a change was made to the board false if not
   */
  private boolean slideAllRight() {
    boolean changeCheck = false;
    
    for (int i = 0; i < gameArray.length; i++) {
      if (SliderMove.slideRight(gameArray,i))
        changeCheck = true;
    }
    
    return changeCheck;
  }
  
  /**
   * Slides all the columns to the up 
   * @return boolean true if a change was made to the board false if not
   */
  private boolean slideAllUp() {
    boolean changeCheck = false;
    
    for (int i = 0; i < gameArray[0].length; i++) {
      if (SliderMove.slideUp(gameArray,i))
        changeCheck = true;
    }
    
    return changeCheck;
  }
  
  /**
   * Slides all the columns down
   * @return boolean true if a change was made to the board false if not
   */
  private boolean slideAllDown() {
    boolean changeCheck = false;
    
    for (int i = 0; i < gameArray[0].length; i++) {
      if (SliderMove.slideDown(gameArray,i))
        changeCheck = true;
    }    
    
    return changeCheck;
  }
  
  /**
   * Slides all the diagales top left from bottom right 
   * @return boolean true if a change was made to the board false if not
   */
  private boolean slideAllUpLeft() {
    boolean changeCheck = false;
    
    for (int i = 0; i < gameArray.length; i++) {
      if (SliderMove.slideUpLeft(gameArray,i,0))
        changeCheck = true;
    }
    for (int i = 0; i < gameArray[0].length; i++) {
      if (SliderMove.slideUpLeft(gameArray,0,i))
        changeCheck = true;
    }
    
    return changeCheck;
  }
  
  /**
   * Slides all the diagales top left to bottom right 
   * @return boolean true if a change was made to the board false if not
   */
  private boolean slideAllDownRight() {
    boolean changeCheck = false;
    
    for (int i = 0; i < gameArray.length; i++) {
      if (SliderMove.slideDownRight(gameArray,i,0))
        changeCheck = true;
    }
    for (int i = 0; i < gameArray[0].length; i++) {
      if (SliderMove.slideDownRight(gameArray,0,i))
        changeCheck = true;
    }
    
    return changeCheck;
  }
  
  /**
   * Slides all the diagales top right from bottom left 
   * @return boolean true if a change was made to the board false if not
   */
  private boolean slideAllUpRight() {
    boolean changeCheck = false;
    
    for (int i = 0; i < gameArray.length; i++) {
      if (SliderMove.slideUpRight(gameArray,i,gameArray[0].length - 1))
        changeCheck = true;
    }
    for (int i = gameArray[0].length - 1; i >= 0; i--) {
      if (SliderMove.slideUpRight(gameArray,0,i))
        changeCheck = true;
    }
    
    return changeCheck;
  }
  
  /**
   * 
   * Slides all the diagales top right to bottom rightLeft 
   * @return boolean true if a change was made to the board false if not
   */
  private boolean slideAllDownLeft() {
    boolean changeCheck = false;
    
    for (int i = 0; i < gameArray.length; i++) {
      if (SliderMove.slideDownLeft(gameArray,i,gameArray[0].length - 1))
        changeCheck = true;
    }
    for (int i = gameArray[0].length - 1; i >= 0; i--) {
      if (SliderMove.slideDownLeft(gameArray,0,i))
        changeCheck = true;
    }    
    
    return changeCheck;
  }  

  /**
   * sums up the board numbers
   * @return int total of the gameArray
   */
  private int getBoardScore() {
    int score = 0;
    int temp = 0;
    
    for (int i = 0; i < gameArray.length; i++) {
      for (int j = 0; j < gameArray[i].length; j++) {
        score += gameArray[i][j];
        temp = gameArray[i][j] / 2;
        while(temp >= 1) {
          score += temp;
          temp = temp / 2;
        }
      }
    }
    
    return score;
  }
  
    /**
   * Runs off a call to SliderGame and generates a game based on inputs
   * @param args array of strings used to get the row and column for game
   */ 
  public static void main(String[] args) {
    if (args.length == 0) { //checking for no inputs
      SliderGame g = new SliderGame(4,4);
    }
    else if(args.length == 1) {//checking for single input
      try {//checking for parsing errors
        SliderGame g = new SliderGame(Integer.parseInt(args[0]),Integer.parseInt(args[0]));
      }
      catch (Exception e) {
        SliderGame g = new SliderGame(4,4);
      }
    }
    else { //checking for 2+ inputs
      try {//checking for parsing errors
        if ((Integer.parseInt(args[0]) < 3 || Integer.parseInt(args[1]) < 3) || (Integer.parseInt(args[0]) > 20 || Integer.parseInt(args[1]) > 20)) {
          //board would be too big or too small
          SliderGame g = new SliderGame(4,4);
        }
        else {
          SliderGame g = new SliderGame(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        }
      }
      catch(Exception e) {
        SliderGame g = new SliderGame(4,4);
      }
    }
  }
}