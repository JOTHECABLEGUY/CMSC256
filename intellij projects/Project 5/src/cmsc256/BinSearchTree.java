package cmsc256;

import bridges.connect.Bridges;
import bridges.base.BinTreeElement;
import bridges.validation.RateLimitException;
import java.io.IOException;

/**
 * @Jordan Dube
 * CMSC256- Fung
 * Project 5
 * 11/11/2020
 * A class the represents a Binary Search Tree
 *
 */

public class BinSearchTree<E extends Comparable<E>> implements BinTreeInterface<E> {
  private BinTreeElement<E> root;
  private int size;
  //default constructor
  public BinSearchTree() {
    clear();
  }
  /** Returns the root of this tree
   * @return BinTreeElement the root of this tree
   */
  @Override
  public BinTreeElement<E> getRoot() {
    return root;
  }

  /**
   * Helper method to add an element to the tree
   * @param parent BinTreeElement that designates the parent of the new child to be added
   * @param newNode BinTreeElement that is to be added to the tree
   * @return boolean true if the newNode was successfully added
   */
  private boolean addToParent(BinTreeElement<E> parent, BinTreeElement<E> newNode) {
    //checks if the newNode should be added to left
    if (parent.getValue().compareTo(newNode.getValue()) > 0) {
      //checks if left of the parent node is empty
      if (parent.getLeft() == null) {
        parent.setLeft(newNode);
        size++;
        return true;
      }
      else
        addToParent(parent.getLeft(), newNode);
    }
    //checks if the newNode should be added to the right
    else if (parent.getValue().compareTo(newNode.getValue()) < 0) {
      //checks if right of the parent node is empty
      if (parent.getRight() == null) {
        parent.setRight(newNode);
        size++;
        return true;
      }
      else
        addToParent(parent.getRight(), newNode);
    }
    return false;
  }
  /** Insert element into the binary tree
   * @param element E element to be inserted
   * @return boolean true if the element is inserted successfully
   */
  @Override
  public boolean add(E element) {
    //sets the root to the new element if the tree is empty
    if (root == null || size() == 0) {
      root = new BinTreeElement<>((String) element, element);
      size++;
    }
    //calls the helper function to add the element
    return addToParent(root, new BinTreeElement<>((String) element, element));
  }
  /** Deletes the specified element from the tree
   * @param element E the element to be removed
   * @return true if the element is deleted successfully
   */
  @Override
  public boolean remove(E element) {
    //searches for the desired element
    if (root == null)
      return true;
    if (root.getValue().compareTo(element) > 0) {
      //iterates left if the target has a lesser value than the root
      root = root.getLeft();
      remove(element);
    } else if (root.getValue().compareTo(element) < 0) {
      //iterates right if the target has a greater value than the root
      root = root.getRight();
      remove(element);
    } else {
      //checks if the root has 2 children
      if (root.getLeft() != null && root.getRight() != null) {
        BinTreeElement<E> rightMin = minElement(root.getRight());
        //sets the root to the value of the minElement on the right
        root.setValue(rightMin.getValue());
        //deletes the element that was used to replace the current root
        remove(root.getRight().getValue());
      }
      //checks if the current root only has a left child
      else if (root.getLeft() != null)
        root = root.getLeft();
      //checks if the current root only has a right child
      else if (root.getRight() != null)
        root = root.getRight();
      else
        root = null;
    }
    size--;
    return true;

  }

  /**
   * Helper method to keep track of and find the lowest element from the starting point
   * @param root BinTreeElement that designates the starting point for further iterations
   * @return BinTreeElement the element with the lowest value in the tree
   */
  private BinTreeElement<E> minElement(BinTreeElement<E> root) {
    //if no further left elements, then it has found the minimum element
    if (root.getLeft() == null)
      return root;
    else {
      return minElement(root.getLeft());
    }
  }
  /**
   * Returns the number of nodes in the tree
   * @return int size number of nodes in the tree
   */
  @Override
  public int size() {
    return size;
  }
  /**
   * Return true if the tree is empty
   * @return boolean true if tree is empty
   */
  @Override
  public boolean isEmpty() {
    return (this.root == null);
  }
  /**
   * Removes all nodes from the tree
   */
  @Override
  public void clear() {
    root = null;
    size = 0;
  }

  /**
   * Helper method to search recursively through the tree and stops when it reaches the desired element
   * @param target BinTreeElement desired element to search for
   * @param root E reference to the starting point for the recursive method
   * @return boolean true if the target is in the tree
   */
  private boolean recursiveSearch(E target, BinTreeElement<E> root) {
    if (root == null)
      return false;
    if (root.getValue().compareTo(target) == 0)
      return true;
    else if (root.getValue().compareTo(target) > 0) {
      recursiveSearch(target, root.getLeft());
    } else if (root.getValue().compareTo(target) < 0) {
      recursiveSearch(target, root.getRight());
    }
    return false;
  }
  /**
   * Return true if the element is in the tree
   * @return boolean true if the element is in the tree
   */
  @Override
  public boolean search(E target) {
    return recursiveSearch(target, root);
  }

  /** Inorder traversal from the root
   *  @return a String representation of the traversal
   *           with two spaces between the String representation
   *           of each element
   */
  @Override
  public String inorder() {
    return inorderHelper(root);
  }
  /**
   * Helper method to put the tree into preorder
   * @param root BinTreeElement that references the starting point of the tree
   * @return String result the representation of the tree in preorder
   */
  private String inorderHelper(BinTreeElement<E> root) {
    //field to hold the tree in the desired order
    String result = "";
    //checks if root has children
    if(root !=  null) {
      result += inorderHelper(root.getLeft());
      result += root.getValue() + "  ";
      result += inorderHelper(root.getRight());
    }
    return result;
  }

  /** Postorder traversal from the root
   *  @return a String representation of the traversal
   *           with two spaces between the String representation
   *           of each element
   */
  @Override
  public String postorder() {
    return postorderHelper(root);
  }

  /**
   * Helper method to put the tree into postorder
   * @param root BinTreeElement that references the starting point of the tree
   * @return String result the representation of the tree in postorder
   */
  private String postorderHelper(BinTreeElement<E> root) {
    //field to hold the tree in the desired order
    String result = "";
    if (root != null) {
      postorderHelper(root.getLeft());
      postorderHelper(root.getRight());
      result += root.getValue() + "  ";
    }
    return result;
  }

  /** Preorder traversal from the root
     @return a String representation of the traversal
   *           with two spaces between the String representation
   *           of each element
   */
  @Override
  public String preorder() {
    return preorderHelper(root);
  }

  /**
   * Helper method to put the tree into preorder
   * @param root BinTreeElement that references the starting point of the tree
   * @return String result the representation of the tree in inorder
   */
  private String preorderHelper(BinTreeElement<E> root) {
    //field to hold the tree in the desired order
    String result = "";
    //checks if root has children
    if(root !=  null) {
      result += root.getValue() + "  ";
      result += preorderHelper(root.getLeft());
      result += preorderHelper(root.getRight());
  }
    return result;
  }

  /**
   * Returns the height of this binary tree
   * @return int the height of this binary tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Helper method to find the height of a binary search tree
   * @param root the reference point to the beginning of the tree
   * @return int height of the tree
   */
  private int heightHelper(BinTreeElement<E> root) {
      if(root == null) {
        return -1;
      }
      return Math.max(heightHelper(root.getLeft()), heightHelper(root.getRight()))+1;
    }


  private BinTreeElement<E> node = getRoot(); //field holds the original root value
  /**
   * Returns true if the tree is a full binary tree
   * @return boolean true if the tree is a full binary tree
   */

  public boolean isFullBST() {
    //checks if tree is empty
    if (root == null) {
      return true;
    }
    //checks if the current BinTreeElement is a leaf
    if (root.getLeft() == null && root.getRight() == null)
      return true;
    //checks if the current BinTreeElement has 2 children
    if (root.getLeft() != null && root.getRight() != null) {
      //sets root to the original root's left side
      root = node.getLeft();
      //checks if there are more elements
      if (isFullBST()) {
        //sets root to right side of the tree
        root = node.getRight();
        return (isFullBST());
      }
    }
    return false;
  }

  /**
   * Returns the number of leaf nodes
   * @return int the number of leaf nodes
   */
  public int getNumberOfLeaves() {
    return numberOfLeavesHelper(root);
  }

  /**
   * Helper method to more easily find the umber of leaves in a BinSearchTree
   * @param root BinTreeElement that references the starting point, updates with each iteration of the method
   * @return int count the number of leaves
   */
  private int numberOfLeavesHelper(BinTreeElement<E> root){
    if (root == null) {
      return 0;
    }
    if (root.getRight() == null && root.getLeft() == null) {
      return 1;
    }
    int count = 0;
    count += numberOfLeavesHelper(root.getLeft());
    count += numberOfLeavesHelper(root.getRight());
    return count;
  }

  /**
   * Returns the number of non-leaf nodes
   * @return int the number of non-leaf nodes
   */
  public int getNumberOfNonLeaves() {
    return size() - getNumberOfLeaves();
  }





  public static void main(String[] args){
    Bridges bridges = new Bridges(5, "JOTHECABLEGUY", "856574985608");
    bridges.setTitle("Binary Search Tree");
    bridges.setDescription("A representation of a binary search tree with names");
    BinSearchTree<String> names = new BinSearchTree<String>();
    names.add("Frodo");
    names.add("Dori");
    names.add("Bilbo");
    names.add("Kili");
    names.add("Gandalf");
    names.add("Fili");
    names.add("Thorin");
    names.add("Nori ");
    bridges.setDataStructure(names.getRoot());
    try {
      bridges.visualize();
    } catch (IOException | RateLimitException e){
    e.printStackTrace();
    }}}



