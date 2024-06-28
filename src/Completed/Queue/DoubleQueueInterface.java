package Completed.Queue;

interface DoubleQueueInterface<T> {
    void addFront(T ele);
    T removeFront();
    T getFront();

    void addBack(T ele);
    T removeBack();
    T getBack();

    boolean isEmpty();
    void clear();
}
