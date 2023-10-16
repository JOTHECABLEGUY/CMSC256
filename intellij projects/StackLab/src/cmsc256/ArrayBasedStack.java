package cmsc256;

import java.util.Arrays;

public class ArrayBasedStack<T> implements StackInterface<T> {
  private T[] data;
  private int topOfStack;
  private static final int INITIAL_CAPACITY = 5;
  public ArrayBasedStack(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Array initial size error.");
    }
    @SuppressWarnings("unchecked")
    T[] tempStack = (T[]) new Object[capacity];
    data = tempStack;
    topOfStack = -1;
  }
  public ArrayBasedStack() {
    clear();
  }
  private T[] expandArray() {
    return Arrays.copyOf(data, data.length * 2);
  }
  private boolean isArrayFull() {
    return (topOfStack > data.length - 1);
  }
  @Override
  public void push(T newEntry) {
    topOfStack++;
    if (isArrayFull()) {
      data = expandArray();
    }
    data[topOfStack] = newEntry;
  }
  @Override
  public T pop() {
    if (topOfStack <= -1)
      throw new EmptyStackException();
    else {
      T t = data[topOfStack];
      data[topOfStack] = null;
      topOfStack--;
      return t;
    }
  }
  @Override
  public T peek() {
    if (topOfStack <= -1)
      throw new EmptyStackException();
    return data[topOfStack];
  }
  @Override
  public boolean isEmpty() {
    return topOfStack == -1;
  }
  @Override
  public void clear() {
    @SuppressWarnings("unchecked")
    T[] tempStack = (T[]) new Object[INITIAL_CAPACITY];
    data = tempStack;
    topOfStack = -1;
  }
}
