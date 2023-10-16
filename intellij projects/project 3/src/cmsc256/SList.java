package cmsc256;

class SLink<E> {         // Singly linked list node class
  private E e;          // Value for this node
  private SLink<E> n;    // Point to next node in list
  
  // Constructors
  SLink(E it, SLink<E> inn) { e = it; n = inn; }
  SLink(SLink<E> inn) { e = null; n = inn; }
  
  E element() { return e; }                        // Return the value
  E setElement(E it) { return e = it; }            // Set element value
  SLink<E> next() { return n; }                     // Return next link
  SLink<E> setNext(SLink<E> inn) { return n = inn; } // Set next link
}