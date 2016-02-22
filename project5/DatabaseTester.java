import junit.framework.TestCase;
import java.util.*;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class DatabaseTester extends TestCase {
  
  public void testContactClass() {
    Contact c1 = new Contact("Harold","368-5877", "hsc21@case.edu");
    Contact c2 = new Contact("Harold","368-5877", "hsc21@case.edu");
    Contact c3 = new Contact("David","781-3443", "dcl71@case.edu");
    
    //toString test
    assertEquals("print out contact properly","Harold, 368-5877, hsc21@case.edu",c1.toString());
    
    //equals test (true and false)
    assertTrue("check if contact fields are equal",c1.equals(c2));
    assertFalse("check if contact fields are equal",c1.equals(c3));
    
    //equals test (name and number and email)
    assertTrue("heck if contact fields are equal",c1.equals(c2));
    assertFalse("check if contact fields are equal",c1.equals(c3));
    
    //Comparator return testing
    //greater than zero because Harold comes after David
    assertTrue("return Comparator and use it on a Contact", 0 < c1.getComparatorByTrait("name").compare(c1,c3));
    //c3 number should come after so will return less than 0
    assertFalse("return Comparator and use it on a Contact", 0 < c2.getComparatorByTrait("number").compare(c1,c3));
    //same so 0
    assertTrue("return Comparator and use it on a Contact", 0 == c3.getComparatorByTrait("email").compare(c1,c2));
  }
  
  public void testAddRemove() {
    Contact c1 = new Contact("Harold","368-5877", "hsc21@case.edu");
    Contact c2 = new Contact("Harold","368-5877", "hsc21@case.edu");
    Contact c3 = new Contact("David","781-3443", "dcl71@case.edu");
    Database<Contact> d = new Database<Contact>();
    Database<Contact> d2 = new Database<Contact>();
    d.add(new Contact("name1","number1","email1"));
    d.add(new Contact("name2","number2","email2"));
    d.add(new Contact("name2","number2","email2"));
    d.add(new Contact("name3","number3","email3"));
    
    //--- public void add() ----
    //with elemenets
    assertEquals("checking name of first Contact","name1, number1, email1",d.getData().element().toString());
    //test 0
    assertEquals("checking empty database",0,d2.getData().size());
    
    //--- public void remove
    //test 0 changes
    d.remove(new Contact("name2","number2","email7"));
    assertEquals("should not change anything",4,d.getData().size());
    //test 0 elements
    d2.remove(new Contact("name2","number2","email7"));
    assertEquals("shouldreturn null",0,d2.getData().size());
    //test 1
    d.remove(new Contact("name1","number1","email1")); //database now has 3 elements
    assertEquals("should delete first addition","name2, number2, email2",d.getData().element().toString());
    //test many
    d.remove(new Contact("name2","number2","email2"));
    assertEquals("delete multiple instances",1,d.getData().size());
  }
  
  public void testLoopupInList() {
    //--- public LinkedList<T> loopupInList ---
    Database<Contact> d = new Database<Contact>();
    Contact c1 = new Contact("Harold","368-5877", "hsc21@case.edu");
    Database<Contact> d2 = new Database<Contact>();
    d.add(new Contact("Bob","1","email"));
    d.add(new Contact("Bob","5","email"));
    d.add(new Contact("Jim","5","email"));
    d.add(new Contact("Fred","7","email"));
    
    //test 0 (empty database d2)
    LinkedList<Contact> ls = d2.lookupInList(new Contact("Bob","5","email"),c1.getComparatorByTrait("name"));
    assertEquals("should have empty List",0,ls.size());
    //test 0 (no matches)
    ls = d.lookupInList(new Contact("Joe","5","email"),c1.getComparatorByTrait("name"));
    assertEquals("should have empty List",0,ls.size());
    //test 1
    ls = d.lookupInList(new Contact("Joe","1","notEmail"),c1.getComparatorByTrait("number"));
    assertEquals("should have first element",1,ls.size());
    //test many
    ls = d.lookupInList(new Contact("Joe","1","email"),c1.getComparatorByTrait("email"));
    assertEquals("should have all elements",4,ls.size());
    //test last
    ls = d.lookupInList(new Contact("Joe","7","email"),c1.getComparatorByTrait("number"));
    assertEquals("should have the last element","Fred",ls.element().getName());
    //test middle
    ls = d.lookupInList(new Contact("Joe","5","email"),c1.getComparatorByTrait("number"));
    assertEquals("should have middle element",2,ls.size());
  }
  
  public void testLookupInIndex() {
    //--- public LinkedList<T> lookupInIndex(T element, String index, Comparator<T> comparator) ---
    Database<Contact> d = new Database<Contact>();
    Database<Contact> d2 = new Database<Contact>();
    Contact c1 = new Contact("Bob","1","email");
    d.add(c1);
    d.add(new Contact("Bob","1","email"));
    d.add(new Contact("Bob","5","email"));
    d.add(new Contact("Jim","5","email"));
    d.add(new Contact("Fred","7","amail"));
    d.add(new Contact("Henry","10","gmail"));
    
    //Test 0 on empty database
    LinkedList<Contact> ls = d2.lookupInIndex(new Contact("Bob","9","gmail"),"number",c1.getComparatorByTrait("number")); 
    assertEquals("empty list from empty database","[]",ls.toString());
    //Test 0 on database with no matches
    ls = d.lookupInIndex(new Contact("Bob","9","gmail"),"number",c1.getComparatorByTrait("number"));
    assertEquals("empty list from empty database","[]",ls.toString());
    //test 1 getting 1 match
    ls = d.lookupInIndex(new Contact("Fred","9","gmail"),"name",c1.getComparatorByTrait("name"));
    assertEquals("1 element that matches","Fred, 7, amail",ls.element().toString());
    //test many getting 4
    ls = d.lookupInIndex(new Contact("Fred","9","email"),"email",c1.getComparatorByTrait("email"));
    assertEquals("1 element that matches",4,ls.size());
  }
  
  public void testMakeIndex() {
    //--- public void makeIndex(String trait) ---
    //also tests getList
    Database<Contact> d = new Database<Contact>();
    Database<Contact> d2 = new Database<Contact>();
    d.add(new Contact("Bob","1","email"));
    d.add(new Contact("Bob","5","email"));
    d.add(new Contact("Jim","5","email"));
    d.add(new Contact("Fred","7","amail"));
    
    //test 0 (no elements in database)
    d2.makeIndex("name");
    assertEquals("makes an empty list","[]",d2.getList("name").toString());
    //test making index
    d.makeIndex("email");
    assertEquals("create a list ordered by email","Fred, 7, amail",d.getList("email").get(0).toString());
    d.makeIndex("name");
    assertEquals("create a list ordered by name","Fred, 7, amail",d.getList("name").get(2).toString());
    //index are saved
    assertEquals("create a list ordered by email","Bob, 1, email",d.getList("email").get(1).toString());
    
  }
  
  public void testLookup() {
    // --- public LinkedList<T> lookup(T element, String, trait) ---
    Database<Contact> d = new Database<Contact>();
    Database<Contact> d2 = new Database<Contact>();
    d.add(new Contact("Bob","1","email"));
    d.add(new Contact("Bob","5","email"));
    d.add(new Contact("Jim","5","email"));
    d.add(new Contact("Fred","7","amail"));
    
    //test 0 (no elements in database
    assertEquals("makes an empty list","[]",d2.lookup("name", new Contact("Bob","8","email")).toString());
    //test 0 with no matches
    assertEquals("makes an empty list","[]",d.lookup("name", new Contact("Frank","8","email")).toString());
    //test 1
    assertEquals("makes an empty list","Jim, 5, email",d.lookup("name", new Contact("Jim","8","email")).element().toString());
    //test many
    assertEquals("makes an empty list",2,d.lookup("name", new Contact("Bob","8","email")).size());
  }
}
