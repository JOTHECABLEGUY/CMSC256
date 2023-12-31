package cmsc256;

/**
 *   Debra Duke
 *   Computer Science Department
 *   School of Engineering
 *   Virginia Commonwealth University
 */


//List class ADT. Generalize the element type using Java Generics.
public interface List<E> { // List class ADT
  // Remove all contents from the list, so it is once again empty
  void clear();
  
  // Insert "it" at the current location
  // The client must ensure that the list's capacity is not exceeded
  boolean insert(E it);
  
  // Append "it" at the end of the list
  // The client must ensure that the list's capacity is not exceeded
  boolean append(E it);
  
  // Remove and return the current element
  E remove();
  
  // Set the current position to the start of the list
  void moveToStart();
  
  // Set the current position to the end of the list
  void moveToEnd();
  
  // Move the current position one step left, no change if already at beginning
  void prev();
  
  // Move the current position one step right, no change if already at end
  void next();
  
  // Return the number of elements in the list
  int length();
  
  // Return the position of the current element
  int currPos();
  
  // Set the current position to "pos"
  boolean moveToPos(int pos);
  
  // Return true if current position is at end of the list
  boolean isAtEnd();
  
  // Return the current element
  E getValue();
}