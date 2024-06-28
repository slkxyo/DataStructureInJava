package Completed.Stack;

interface StackIF<T> {
    void push(T element);
    T pop();
    T peek();
    boolean isEmpty();
    void clear();
}
