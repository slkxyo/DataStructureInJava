package Completed.Heap;

interface MaxHeapIF<T extends Comparable<? super T>>{
    T getMax();
    T removeMax();
    void add(T ele);
    boolean isEmpty();
    int size();
    void clear();
}
