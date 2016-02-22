//David Lance
import java.util.*;

/** interface used for dictating types that are databases */
public interface DatabaseType<T> {
 
  /** returns comparater based on trait
    * @param trait String used to find which comparater
    * @return comparater based on string */
  public Comparator<T> getComparatorByTrait(String trait);
}