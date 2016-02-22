//David Lance
import java.util.*;

/** class used for contact based databse */
public class ContactDatabase extends Database<Contact>{
  
  /** holds the scanner for the class */
  private Scanner scan;
  
  /** creates the database and adds a scanner */
  public ContactDatabase() {
    scan = new Scanner(System.in);
    readLoop();
  }
  
  /** used to print list to console
    * @param list to print */
  public void printList(Iterable list) {
    /** counter for list elements */
    int counter = 1;
    
    if(list == null)
      System.out.println("Empty List");
    else {
      /** iterator for list */
      Iterator iter = list.iterator();
      
      /** loops through printing each element */
      while(iter.hasNext()) {
        System.out.println(counter + ". " + iter.next().toString());
        counter += 1;
      }
    }
  }
  
  /** runs loop once instance is intialized */
  private void readLoop() {
    /** used to store previous arraylist for listby*/
    ArrayList<Contact> arrayList = new ArrayList<Contact>();
    /** stores previous linkedlist for find */
    LinkedList<Contact> linkedList = new LinkedList<Contact>();
    /** string for whether ll or al was most recent */
    String recentListType = "";
    /** signals end of loop */
    boolean keepGoing = true;
    
    /** constantly checks scanner for commands */
    while(keepGoing) {
      /** used to store first inpyt */
      String command = scan.next();
      
      if(command.equals("quit"))
        keepGoing = false;
      else if(command.equals("add")) {
        this.add(new Contact(scan.next(), scan.next(), scan.next()));
      }
      else if(command.equals("listby")) {
        arrayList = getList(scan.next());
        recentListType = "array";
        printList(arrayList);
      }
      else if(command.equals("find")) {
        /** stores which trait */
        String trait = scan.next();
        if(trait.equals("name"))
          linkedList = lookup(trait, new Contact(scan.next(),null,null));
        if(trait.equals("number"))
          linkedList = lookup(trait, new Contact(null,scan.next(),null));   
        if(trait.equals("email"))
          linkedList = lookup(trait, new Contact(null,null,scan.next())); 
        recentListType = "linked";
        printList(linkedList);
      }
      else if(command.equals("delete")) {
        try {
          /** used to store which element to delete */
          int deleteIndex = scan.nextInt();
          try {
            if(recentListType == "array")
              remove(arrayList.get(deleteIndex - 1));
            else
              remove(linkedList.get(deleteIndex - 1));
          }
          catch (Exception e) {
            System.out.println("Please enter an accurate index or \nrun listby and or find beforehand");
          }
        }
        catch(Exception e) {
          System.out.println("Please enter and interger after delete");
        }
      }
      else if(command.equals("makeIndex")) {
        makeIndex(scan.next());
      }
    }
  }
  
  /** main method used to start database
    * @param args no use */
  public static void main(String[] args) {
    ContactDatabase cd = new ContactDatabase();
  }
  }