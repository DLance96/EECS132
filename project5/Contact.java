//David Lance
import java.util.*;

/**class used to represent name number and email */
public class Contact implements DatabaseType<Contact>{
  
  /** String used to store name*/
  private String name;
  /** String used to store number*/
  private String number;
  /** String used to store email*/
  private String email;
  
  /** make a contact based input fields
    * @param name contact name
    * @param number contat number
    * @param email contact email 
    */
  public Contact(String name, String number, String email) {
    this.name = name;
    this.number = number;
    this.email = email;
  }
  
  /** gets name
    * @return String of the name */
  public String getName() {
    return name;
  }
  
  /** gets number
    * @return String of the number */
  public String getNumber() {
    return number;
  }
  
  /** gets email
    * @return String of the email */
  public String getEmail() {
    return email;
  }
  /** returns the contact as a String 
    * @return name number and email as a string together*/
  public String toString() {
    return name + ", " + number + ", " + email;
  }
  
  /** checks all fields against contact to see if they equal
    * @param c the contact to compare with
    * @return boolean based on if the contact equals this one */
  public boolean equals (Contact c) {
    if(c.getName().equals(name) && c.getNumber().equals(number) && c.getEmail().equals(email))
      return true;
    else
      return false;
  }
  
  /** generates a comparator based on input
    * @param trait String used to pick comparator style
    * @return Comparator to compare specific field */
  public Comparator<Contact> getComparatorByTrait(String trait) {

    if(trait.equals("name"))
      return getNameComparator();
    if(trait.equals("number"))
      return getNumberComparator();
    if(trait.equals("email"))
      return getEmailComparator();
    return null;
  }
  
  /** creates an anonymous comparator for name
    * @return comparator for contact names */
  private Comparator<Contact> getNameComparator() {
    return new Comparator<Contact>() {
      public int compare(Contact c1, Contact c2) {
        /** name string for contact 1 */
        String name1 = c1.getName();
        /** name string for contact 2 */
        String name2 = c2.getName();
        
        return name1.compareTo(name2);
      }
    };
  }
  
  /** creates an anonymous comparator for number
    * @return comparator for contact numbers */
  private Comparator<Contact> getNumberComparator() {
    return new Comparator<Contact>() {
      public int compare(Contact c1, Contact c2) {
        /** Number string for contact 1 */
        String number1 = c1.getNumber();
        /** Number string for contact 2 */
        String number2 = c2.getNumber();
        
        return number1.compareTo(number2);
      }
    };
  }
    
  private Comparator<Contact> getEmailComparator() {
    return new Comparator<Contact>() {
      public int compare(Contact c1, Contact c2) {
        /** Number string for contact 1 */
        String email1 = c1.getEmail();
        /** Number string for contact 2 */
        String email2 = c2.getEmail();
        
        return email1.compareTo(email2);
      }
    };
  }
}