//David Lance

import junit.framework.TestCase;

public class SliderMoveTester extends TestCase {
  
  public void testSlideLeft() {
    int[][] a1 = {{}};
    int[][] a2 = {{1,0,1,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}};
    int[][] a3 = {{1,1,2,4,1,1,4,2,2,8,1,1,0,0,1,1,0,2,0,4,1,1,0}};
    int[][] a4 = {{1,0,0},{0,1,0},{0,0,1}};
    //Test 0
    assertFalse("There is not supposed to be a change in the array",SliderMove.slideLeft(a1,0));
    
    //Test 1
    assertTrue("There is supposed to be a change in the array",SliderMove.slideLeft(a2,0));
    assertEquals("Incorrect slide","{{2,0,0,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}}",SliderMove.printArray(a2));
    
    //Test Many
    assertTrue("There is supposed to be a change in the array",SliderMove.slideLeft(a3,0));
    assertEquals("Incorrect slide","{{8,2,4,4,8,2,8,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}}",SliderMove.printArray(a3));
    
    //Test First
    assertTrue("There is supposed to be a change in the array",SliderMove.slideLeft(a4,2));
    assertEquals("Incorrect slide","{{1,0,0},{0,1,0},{1,0,0}}",SliderMove.printArray(a4));
    //Test Middle
    assertTrue("There is supposed to be a change in the array",SliderMove.slideLeft(a4,1));
    assertEquals("Incorrect slide","{{1,0,0},{1,0,0},{1,0,0}}",SliderMove.printArray(a4));
    //Test Last
    assertFalse("There is supposed to be a change in the array",SliderMove.slideLeft(a4,0));
    assertEquals("Incorrect slide","{{1,0,0},{1,0,0},{1,0,0}}",SliderMove.printArray(a4));
  }
  
  public void testSlideRight() {
    int[][] a1 = {{}};
    int[][] a2 = {{1,0,1,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}};
    int[][] a3 = {{1,1,2,4,1,1,4,2,2,8,1,1,0,0,1,1,0,2,0,4,1,1,0}};
    int[][] a4 = {{1,0,0},{0,1,0},{0,0,1}};
    
    //Test 0
    assertFalse("There is not supposed to be a change in the array",SliderMove.slideRight(a1,0));
    
    //Test 1
    assertTrue("There is supposed to be a change in the array",SliderMove.slideRight(a2,0));
    assertEquals("Incorrect slide","{{0,0,0,2},{1,2,1,2},{2,1,1,1},{4,4,2,1}}",SliderMove.printArray(a2));
    
    //Test Many
    assertTrue("There is supposed to be a change in the array",SliderMove.slideRight(a3,0));
    assertEquals("Incorrect slide","{{0,0,0,0,0,0,0,0,0,0,0,0,2,2,4,2,8,8,2,2,2,4,2}}",SliderMove.printArray(a3));
    
    //Test First
    assertTrue("There is supposed to be a change in the array",SliderMove.slideRight(a4,0));
    assertEquals("Incorrect slide","{{0,0,1},{0,1,0},{0,0,1}}",SliderMove.printArray(a4));
    //Test Middle
    assertTrue("There is supposed to be a change in the array",SliderMove.slideRight(a4,1));
    assertEquals("Incorrect slide","{{0,0,1},{0,0,1},{0,0,1}}",SliderMove.printArray(a4));
    //Test Last
    assertFalse("There is supposed to be a change in the array",SliderMove.slideRight(a4,2));
    assertEquals("Incorrect slide","{{0,0,1},{0,0,1},{0,0,1}}",SliderMove.printArray(a4));
  }
  
  public void testSlideUp() {
    int[][] a1 = {{}};
    int[][] a2 = {{1,0,1,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}};
    int[][] a3 = {{1},{1},{4},{2},{2},{1},{1},{0},{1}};
    int[][] a4 = {{1,0,0},{0,1,0},{0,0,1}};
    
    //Test 0
    assertFalse("There is not supposed to be a change in the array",SliderMove.slideUp(a1,0));
    
    //Test 1
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUp(a2,0));
    assertEquals("Incorrect slide","{{8,0,1,0},{0,2,1,2},{0,1,1,1},{0,4,2,1}}",SliderMove.printArray(a2));
    
    //Test Many
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUp(a3,0));
    assertEquals("Incorrect slide","{{2},{4},{4},{2},{1},{0},{0},{0},{0}}",SliderMove.printArray(a3));
  
    //Test Last
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUp(a4,2));
    assertEquals("Incorrect slide","{{1,0,1},{0,1,0},{0,0,0}}",SliderMove.printArray(a4));
    //Test Middle
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUp(a4,1));
    assertEquals("Incorrect slide","{{1,1,1},{0,0,0},{0,0,0}}",SliderMove.printArray(a4));
    //Test First
    assertFalse("There is supposed to be a change in the array",SliderMove.slideUp(a4,0));
    assertEquals("Incorrect slide","{{1,1,1},{0,0,0},{0,0,0}}",SliderMove.printArray(a4));
  }
  
  public void testSlideDown() {
    int[][] a1 = {{}};
    int[][] a2 = {{1,0,1,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}};
    int[][] a3 = {{1},{1},{4},{2},{2},{1},{1},{0},{1}};
    int[][] a4 = {{1,0,0},{0,1,0},{0,0,1}};
    
    //Test 0
    assertFalse("There is not supposed to be a change in the array",SliderMove.slideDown(a1,0));
    
    //Test 1
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDown(a2,0));
    assertEquals("Incorrect slide","{{0,0,1,0},{2,2,1,2},{2,1,1,1},{4,4,2,1}}",SliderMove.printArray(a2));
    
    //Test Many
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDown(a3,0));
    assertEquals("Incorrect slide","{{0},{0},{0},{0},{0},{2},{8},{1},{2}}",SliderMove.printArray(a3));
  
    //Test First
    assertFalse("There is supposed to be a change in the array",SliderMove.slideDown(a4,2));
    assertEquals("Incorrect slide","{{1,0,0},{0,1,0},{0,0,1}}",SliderMove.printArray(a4));
    //Test Middle
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDown(a4,1));
    assertEquals("Incorrect slide","{{1,0,0},{0,0,0},{0,1,1}}",SliderMove.printArray(a4));
    //Test Last
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDown(a4,0));
    assertEquals("Incorrect slide","{{0,0,0},{0,0,0},{1,1,1}}",SliderMove.printArray(a4));
  }
  
  public void testSlideUpLeft() {
    int[][] a1 = {{}};
    int[][] a2 = {{1,0,0,0},{0,1,0,0},{0,0,2,0},{0,0,0,0}};
    int[][] a3 = {{1,0,1,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}};
    int[][] a4 = {{1,0,0},{0,0,0},{0,0,0}};
    int[][] a5 = {{0,0,0},{0,1,0},{0,0,0}};
    int[][] a6 = {{0,0,0},{0,0,0},{0,0,1}};
    
    //Test 0
    assertFalse("There is not supposed to be a change in the array",SliderMove.slideUpLeft(a1,0,0));
    
    //Test 1
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUpLeft(a2,0,0));
    assertEquals("Incorrect slide","{{4,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}",SliderMove.printArray(a2));
    
    //Test Many
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUpLeft(a3,0,0));
    assertEquals("Incorrect slide","{{1,0,1,0},{1,2,1,2},{2,1,2,1},{4,4,2,0}}",SliderMove.printArray(a3));
  
    //Test First
    assertFalse("There is supposed to be a change in the array",SliderMove.slideUpLeft(a4,0,0));
    assertEquals("Incorrect slide","{{1,0,0},{0,0,0},{0,0,0}}",SliderMove.printArray(a4));
    //Test Middle
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUpLeft(a5,0,0));
    assertEquals("Incorrect slide","{{1,0,0},{0,0,0},{0,0,0}}",SliderMove.printArray(a5));
    //Test Last
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUpLeft(a6,0,0));
    assertEquals("Incorrect slide","{{1,0,0},{0,0,0},{0,0,0}}",SliderMove.printArray(a6));
  }
  
  public void testSlideDownRight() {
    int[][] a1 = {{}};
    int[][] a2 = {{1,0,0,0},{0,1,0,0},{0,0,2,0},{0,0,0,0}};
    int[][] a3 = {{1,0,1,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}};
    int[][] a4 = {{1,0,0},{0,0,0},{0,0,0}};
    int[][] a5 = {{0,0,0},{0,1,0},{0,0,0}};
    int[][] a6 = {{0,0,0},{0,0,0},{0,0,1}};
    
    //Test 0
    assertFalse("There is not supposed to be a change in the array",SliderMove.slideDownRight(a1,0,0));
    
    //Test 1
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDownRight(a2,0,0));
    assertEquals("Incorrect slide","{{0,0,0,0},{0,0,0,0},{0,0,2,0},{0,0,0,2}}",SliderMove.printArray(a2));
    
    //Test Many
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDownRight(a3,0,0));
    assertEquals("Incorrect slide","{{0,0,1,0},{1,0,1,2},{2,1,1,1},{4,4,2,4}}",SliderMove.printArray(a3));
  
    //Test Last
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDownRight(a4,0,0));
    assertEquals("Incorrect slide","{{0,0,0},{0,0,0},{0,0,1}}",SliderMove.printArray(a4));
    //Test Middle
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDownRight(a5,0,0));
    assertEquals("Incorrect slide","{{0,0,0},{0,0,0},{0,0,1}}",SliderMove.printArray(a5));
    //Test First
    assertFalse("There is supposed to be a change in the array",SliderMove.slideDownRight(a6,0,0));
    assertEquals("Incorrect slide","{{0,0,0},{0,0,0},{0,0,1}}",SliderMove.printArray(a6));
  }
  
  public void testSlideUpRight() {
    int[][] a1 = {{}};
    int[][] a2 = {{0,0,0,1},{0,0,1,0},{0,2,0,0},{0,0,0,0}};
    int[][] a3 = {{1,0,1,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}};
    int[][] a4 = {{0,0,1},{0,0,0},{0,0,0}};
    int[][] a5 = {{0,0,0},{0,1,0},{0,0,0}};
    int[][] a6 = {{0,0,0},{0,0,0},{1,0,0}};
    
    //Test 0
    assertFalse("There is not supposed to be a change in the array",SliderMove.slideUpRight(a1,0,0));
    
    //Test 1
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUpRight(a2,0,3));
    assertEquals("Incorrect slide","{{0,0,0,4},{0,0,0,0},{0,0,0,0},{0,0,0,0}}",SliderMove.printArray(a2));
    
    //Test Many
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUpRight(a3,0,3));
    assertEquals("Incorrect slide","{{1,0,1,2},{1,2,4,2},{2,0,1,1},{0,4,2,1}}",SliderMove.printArray(a3));
  
    //Test First
    assertFalse("There is supposed to be a change in the array",SliderMove.slideUpRight(a4,0,2));
    assertEquals("Incorrect slide","{{0,0,1},{0,0,0},{0,0,0}}",SliderMove.printArray(a4));
    //Test Middle
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUpRight(a5,0,2));
    assertEquals("Incorrect slide","{{0,0,1},{0,0,0},{0,0,0}}",SliderMove.printArray(a5));
    //Test Last
    assertTrue("There is supposed to be a change in the array",SliderMove.slideUpRight(a6,0,2));
    assertEquals("Incorrect slide","{{0,0,1},{0,0,0},{0,0,0}}",SliderMove.printArray(a6));
  }
  
  public void testSlideDownLeft() {
    int[][] a1 = {{}};
    int[][] a2 = {{0,0,0,1},{0,0,1,0},{0,2,0,0},{0,0,0,0}};
    int[][] a3 = {{1,0,1,0},{1,2,1,2},{2,1,1,1},{4,4,2,1}};
    int[][] a4 = {{0,0,1},{0,0,0},{0,0,0}};
    int[][] a5 = {{0,0,0},{0,1,0},{0,0,0}};
    int[][] a6 = {{0,0,0},{0,0,0},{1,0,0}};
    
    //Test 0
    assertFalse("There is not supposed to be a change in the array",SliderMove.slideDownLeft(a1,0,0));
    
    //Test 1
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDownLeft(a2,0,3));
    assertEquals("Incorrect slide","{{0,0,0,0},{0,0,0,0},{0,2,0,0},{2,0,0,0}}",SliderMove.printArray(a2));
    
    //Test Many
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDownLeft(a3,0,3));
    assertEquals("Incorrect slide","{{1,0,1,0},{1,2,0,2},{2,2,1,1},{4,4,2,1}}",SliderMove.printArray(a3));
  
    //Test Last
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDownLeft(a4,0,2));
    assertEquals("Incorrect slide","{{0,0,0},{0,0,0},{1,0,0}}",SliderMove.printArray(a4));
    //Test Middle
    assertTrue("There is supposed to be a change in the array",SliderMove.slideDownLeft(a5,0,2));
    assertEquals("Incorrect slide","{{0,0,0},{0,0,0},{1,0,0}}",SliderMove.printArray(a5));
    //Test First
    assertFalse("There is supposed to be a change in the array",SliderMove.slideDownLeft(a6,0,2));
    assertEquals("Incorrect slide","{{0,0,0},{0,0,0},{1,0,0}}",SliderMove.printArray(a6));
  }
}
