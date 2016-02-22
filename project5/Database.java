//David Lance
import java.util.*;
import java.util.Collections;

/** class for the database itself */
public class Database<T extends DatabaseType<T>>{

  /** used to signify start of node list */
  private LinkedList<T> list = new LinkedList<T>();
  /** used to reference based on String to find Comparator */
  private Hashtable<String,ArrayList<T>> indexHash = new Hashtable<String,ArrayList<T>>();

  /** adds an element to the Database linkedlist
    * @param element new DatabaseType to be added */
  public void add(T element) {
    list.add(element);
    indexHash.clear();
  }
  
  /** removes any elements in the database
    * @param element delete this any number of times from the database */
  public void remove(T element) {
    /** boolean used to track for if a node was deleted */
    boolean changeCheck = false;
    /** list for gathering element to be deleted */
    LinkedList<T> tempList = new LinkedList<T>();
    /** iterator for linked list */
    ListIterator<T> iter = list.listIterator();

    /** iterates comparing elements and not adding ones that match */
    while(iter.hasNext()) {
      /** generic for comparing */
      T tempElement = iter.next();
      if(tempElement.toString().equals(element.toString())) {
        changeCheck = true;
      }
      else {
        tempList.add(tempElement);
      }
    }
    
    if(changeCheck) {
      indexHash.clear();
      list = tempList;
    }
  }
  
  /** makes a list of elements in database matching the filter rules
    * @param element used as baseline to compare against
    * @param filter runs comparing between element and list elements
    * @return LinkedList with all fitting elements */
  public LinkedList<T> lookupInList(T element, Comparator<T> filter) {
    /** iterator for linked list */
    ListIterator<T> iter = list.listIterator();
    /** list with filtered elements */
    LinkedList<T> returnList = new LinkedList<T>();
    
    /** paces through linked list adding elements that fit the filter */
    while(iter.hasNext()) {
      /** generic for comparing */
      T tempElement = iter.next();
      if(filter.compare(element,tempElement) == 0) {
        returnList.add(tempElement);
      }
    }
    
    return returnList;
  }
  
  /** looks up certain element in an index for making it sorted a certain way
    * @param element looking up based on this element
    * @param index access String for HashTable
    * @param comparator what to base search on
    * @return LinkedList with fitting elements in sorted order from hashtable */
  public LinkedList<T> lookupInList(T element, String index, Comparator<T> comparator) {
    /** list with filtered elements from array list*/
    LinkedList<T> returnList = new LinkedList<T>();
    /** ArrayList from Hashtable */
    ArrayList<T> aList = new ArrayList<T>();
    
    return returnList;
  }
  
  /** goes through a array list and picks out mathing elements base on string
    * @param element base for matching elements
    * @param index String to refernce ArrayList in hashtable
    * @param comparator used to filter
    * @return LinkedList with matching elements */
  public LinkedList<T> lookupInIndex(T element, String index, Comparator<T> comparator) {
    /** ArrayList from Hashtable */
    ArrayList<T> aList = new ArrayList<T>();
    /** used in finding where other matches are based on order */
    int intialMatch = -1;
    /** used to input start into arrayListPart for multiple elements meeting conditions */
    int firstMatch = 0;
    /** used to input end into arrayListPart for multiple elements meeting conditions */
    int lastMatch = 0;
    
    makeIndex(index);
    aList = indexHash.get(index);
    
    //returns empty list if empty datbase
    if(aList.size() == 0)
      return new LinkedList<T>();
    
    intialMatch = binarySearch(element, aList, comparator);
    firstMatch = intialMatch;
    lastMatch = intialMatch;
    
    //returns empty list if no match was found
    if(intialMatch == -1)
      return new LinkedList<T>();
    
    /** loop looking for matches before intial */
    while(comparator.compare(element,aList.get(firstMatch - 1)) == 0 && firstMatch > 0) 
      firstMatch -= 1;
    /** loop looking for matches after intial */
    while(comparator.compare(element,aList.get(lastMatch + 1)) == 0 && lastMatch < aList.size()) 
      lastMatch += 1;
    
    return arrayListPart(aList,firstMatch,lastMatch);
  }
  
  /** used as helper fuction to find a spot where a match occurs in  array list with binary
    * @param element that needs to match according to compartor
    * @param aList where data is being used from to compare
    * @param dictates which elements match
    * @return int for location of a match*/
  private int binarySearch(T element, ArrayList<T> aList,Comparator<T> comparator) {
    /** beginning range lower bound for bainary search */
    int front = 0;
    /** beginning range upper bounf for bainary search */
    int end = aList.size() - 1;;
  
    /** finding intially where matching elements are */
    while(front <= end) {
      /** finding center for binary */
      int middle = (end + front)/2;
      /** compare value between middle and given element */
      int compareValue = comparator.compare(element,aList.get(middle));
      if(compareValue == 0) {
        return middle;
      }
      else if(compareValue > 0)
        front = middle + 1;
      else
        end = middle - 1;
    }
    
    return -1;
  }
  
  /** used to construct linked list of matches for lookupInIndex
    * @param aList where elements will be pulled from
    * @param start first matching element location
    * @param end last matching element location
    * @return Linked list with selected elements */
  private LinkedList<T> arrayListPart(ArrayList<T> aList, int start, int end) {
    /** list with filtered elements from array list*/
    LinkedList<T> returnList = new LinkedList<T>();
    
    for(int i = start; i <= end; i++) {
      returnList.add(aList.get(i));
    }
    return returnList;
  }
  
  
  /** creates an index in the hash based on a trait
    * @param trait String to uses a filter from element getComparatorByTrait(trait) */
  public void makeIndex(String trait) {
    /** ArrayList to be added to HashTable */
    ArrayList<T> aList = new ArrayList<T>();
    /** iterator for linked list */
    ListIterator<T> iter = list.listIterator();
    
    /** goes through copying addresses to ArrayList */
    while(iter.hasNext()) {
      aList.add(iter.next());
    }
    if(!list.isEmpty()) {
      Comparator<T> comparator = aList.get(0).getComparatorByTrait(trait);
      Collections.sort(aList,comparator);
    }
    indexHash.put(trait,aList); 
      
  }
  
  /** goes into hashtable finds trait and the finds matching traits and returns
    * @param trait what search is based on
    * @param element searh "keyword"
    * @return LinkedList with sorted  matching elements */
  public LinkedList<T> lookup(String trait, T element) {
    /** list with filtered elements from array list*/
    LinkedList<T> returnList = new LinkedList<T>();
    /** ArrayList from Hashtable */
    ArrayList<T> aList = new ArrayList<T>();
    
    try{
      return lookupInIndex(element, trait, element.getComparatorByTrait(trait));
    }
    catch (Exception e) {
      return lookupInList(element,element.getComparatorByTrait(trait));
    }
  }
  
  /** reads off HashTable if trait has already been established
    * @param trait which ArrayList to get
    * @return ArrayList from hashTable */
  public ArrayList<T> getList(String trait) {
    if(indexHash.get(trait) == null) {
      makeIndex(trait);
      return indexHash.get(trait);
    }
    else {
      return indexHash.get(trait);
    }
  }
  
  /** method made for early testing purposes
    * @return the header for the database */
  public LinkedList<T> getData() {
    return list;
  }
}