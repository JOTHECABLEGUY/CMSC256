package cmsc256;

import bridges.base.DLelement;
import bridges.connect.Bridges;
import bridges.base.Element;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Jordan Dube, CMSC 256, Project 4, Section 2
 * @param <E> type of the DLelements stored in a BridgesDoublyLinkedList object
 */
public class BridgesDoublyLinkedList<E> implements List<E> {
  private DLelement<E> head;         // Pointer to list header
  private DLelement<E> tail;         // Pointer to last element
  private DLelement<E> curr;         // Access to current element
  private int size;      // Size of list;
  private int currIndex; // keeps track of the index of the current element
  public BridgesDoublyLinkedList(int size) { this(); }     // Constructor -- Ignore size
  public BridgesDoublyLinkedList() { clear(); }

  /**
   * Remove all contents from the list, so it is once again empty
   *
   */
  @Override
  public void clear() {
    curr = tail = new DLelement<E>(); // Create trailer
    head = new DLelement<E>(tail, null);        // Create header
    tail.setPrev(head);
    size = 0;
    currIndex = 0;
  }

  /**
   * Insert "it" at the current location
   *
   * @param it the element to be inserted
   * @return boolean true if it can be inserted and false if otherwise
   */
  @Override
  public boolean insert(E it) {
    curr.setNext(new DLelement<E>(curr.getValue(), curr, curr.getNext()));
    curr.setValue(it);
    if (tail == curr) tail = curr.getNext();  // New tail
    size++;
    return true;
  }

  /**
   * Append "it" at the end of the list
   *
   * @param it the DLelement to be appended to the end of the list
   * @return true if it can be appended and false if otherwise
   */
  @Override
  public boolean append(E it) {
    tail.setPrev(new DLelement<>(it, tail.getPrev(), tail));
    moveToEnd();
    moveToPos(currPos()-2);
    curr.setNext(tail.getPrev());
    if(curr==tail)
      curr = tail.getPrev();
    size++;
    return true;
  }

  /** Remove and return the current element
   *
   * @return E the DLelement that is being removed
   * @throws NoSuchElementException if there is nothing to remove, then throws exception and says that there is no element at the current location
   */
  @Override
  public E remove() throws NoSuchElementException {
    if (curr == tail) // Nothing to remove
      throw new NoSuchElementException("remove() in LList has current of " + curr + " and size of "
          + size + " that is not a a valid element");
    E it = curr.getValue();             // Remember value
    curr.setValue(curr.getNext().getValue()); // Pull forward the next element
    if (curr.getNext() == tail) tail = curr;   // Removed last, move tail
    curr.setNext(curr.getNext().getNext());       // Point around unneeded link
    size--;                             // Decrement element count
    return it;                              // Return value
  }

  /**
   * Set the current position to the start of the list
   *
   */
  @Override
  public void moveToStart() {
    curr = head.getNext();
    currIndex = 0;
  }

  /**
   * Set the current position to the end of the list
   *
   */
  @Override
  public void moveToEnd() {
    curr = tail;
    currIndex = size-1;
  }

  /**
   * Move the current position one step left, no change if already at beginning
   *
   */
  @Override
  public void prev() {
    if (head.getNext() == curr && head.getNext() != null) return; // No previous element
    DLelement<E> temp = head;
    // March down list until we find the previous element
    while (temp.getNext() != curr && temp.getNext()!= null) temp = temp.getNext();
    curr = temp;
    currIndex--;
  }

  /**
   * Move the current position one step right, no change if already at end
   *
   */
  @Override
  public void next() {
    if (curr != tail)
      curr = curr.getNext();
    currIndex++;
  }

  /**
   * Return the number of elements in the list
   *
   * @return int size of the list
   */
  @Override
  public int length() {
    return size;
  }

  /**
   * Return the position of the current element
   *
   * @return int location of the current element
   */
  @Override
  public int currPos() {
    DLelement<E> temp = head.getNext();
    int i;
    // loops through the list and stops when the loop reaches the current DLelement
    for (i=0; temp != curr; i++)
      temp = temp.getNext();
    return i;
  }

  /**
   * Set the current position to "pos"
   *
   * @return true if the method was able to set the current position to pos and false if the given parameter is less than 1 or greater than the size of the list
   */

  @Override
  public boolean moveToPos(int pos) {
    if ((pos < 1) || (pos > size)) return false;
    //loops through the list until it reaches the DLelement at pos
    curr = head.getNext();
    for(int i=0; i<pos; i++) curr = curr.getNext();
    return true;
  }

  /**
   * Return true if current position is at end of the list
   *
   * @return true if the current position is the same as the size of the list and false if the current position is not as the end of the list
   */
  @Override
  public boolean isAtEnd() {
    return ((currPos() == size));
  }

  /**
   * Return the current element
   *
   * @return the value stored in the current DLelement
   */
  @Override
  public E getValue() {
    return (E) String.valueOf(curr.toString());
  }
  public static void main(String[] args) throws Exception {
    Bridges bridges = new Bridges(4, "JOTHECABLEGUY", "856574985608");
    // set title
    bridges.setTitle("A doubly Linked List");
    // set description
    bridges.setDescription("This list has 20 nodes all linked to the nodes before and after them and illustrates visual attributes. ");
    // create some elements
    BridgesDoublyLinkedList<String> x = new BridgesDoublyLinkedList<>();
    DLelement<String> e1 = new DLelement<>("a", "a");
    DLelement<String> e2 = new DLelement<>("b", "b");
    DLelement<String> e3 = new DLelement<>("c", "c");
    DLelement<String> e4 = new DLelement<>("d", "d");
    DLelement<String> e5 = new DLelement<>("e", "e");
    DLelement<String> e6 = new DLelement<>("f", "f");
    DLelement<String> e7 = new DLelement<>("g", "g");
    DLelement<String> e8 = new DLelement<>("h", "h");
    DLelement<String> e9 = new DLelement<>("i", "i");
    DLelement<String> e10 = new DLelement<>("j", "j");
    DLelement<String> e11 = new DLelement<>("k", "k");
    DLelement<String> e12 = new DLelement<>("l", "l");
    DLelement<String> e13 = new DLelement<>("m", "m");
    DLelement<String> e14 = new DLelement<>("n", "n");
    DLelement<String> e15 = new DLelement<>("o", "o");
    DLelement<String> e16 = new DLelement<>("p", "p");
    DLelement<String> e17 = new DLelement<>("q", "q");
    DLelement<String> e18 = new DLelement<>("r", "r");
    DLelement<String> e19 = new DLelement<>("s", "s");
    DLelement<String> e20 = new DLelement<>("t", "t");
    // create the list
    e1.setNext(e2);
    e1.setPrev(e1);
    e2.setPrev(e1);
    e2.setNext(e3);
    e3.setPrev(e2);
    e3.setNext(e4);
    e4.setPrev(e3);
    e4.setNext(e5);
    e5.setPrev(e4);
    e5.setNext(e6);
    e6.setPrev(e5);
    e6.setNext(e7);
    e7.setPrev(e6);
    e7.setNext(e8);
    e8.setPrev(e7);
    e8.setNext(e9);
    e9.setPrev(e8);
    e9.setNext(e10);
    e10.setPrev(e9);
    e10.setNext(e11);
    e11.setPrev(e10);
    e11.setNext(e12);
    e12.setPrev(e11);
    e12.setNext(e13);
    e13.setPrev(e12);
    e13.setNext(e14);
    e14.setPrev(e13);
    e14.setNext(e15);
    e15.setPrev(e14);
    e15.setNext(e16);
    e16.setPrev(e15);
    e16.setNext(e17);
    e17.setPrev(e16);
    e17.setNext(e18);
    e18.setPrev(e17);
    e18.setNext(e19);
    e19.setPrev(e18);
    e19.setNext(e20);
    e20.setPrev(e19);
    x.head = e1;
    x.append("bruh");
    System.out.println(x.toString());

    // set colors for list elements - see the Color class for supported colors
    e1.setColor("red");
    // color the links - must specify a valid terminating element
    e1.getLinkVisualizer(e1).setColor("green");
    // color the reverse link
    e1.getLinkVisualizer(e1).setColor("magenta");
    // set link label
    e2.getLinkVisualizer(e3).setLabel("Link Label");
    // set node size
    e1.setSize (20);
    bridges.setDataStructure(e1);
    bridges.visualize();
  }

  /**
   * Returns a String representation of the BridgesDoublyLinkedList object
   *
   * @return String that contains the elements in the BridgesDoublyLinkedList object
   */
  @Override
  public String toString(){
    StringBuilder output = new StringBuilder();
    //Iterator loops through the elements and appends them to the Stringbuilder object
    Iterator<E> iter = head.iterator();
    while (iter.hasNext()) {
      output.append(iter.next());
      output.append(" ");
    }
    return output.toString();
  }
  /**
   * checks if the list is empty
   *
   * @return true if the list is empty and false otherwise
   */
  public boolean isEmpty(){
    return (size==0);
  }
}




