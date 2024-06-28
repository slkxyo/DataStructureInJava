package Completed.Tree;

import java.util.Iterator;

public interface BSTreeIF<T extends Comparable<? super T>>{
    boolean contain(T ele);
    T get(T ele);
    void add(T ele);
    void remove(T ele);
    Iterator<T> iterator();
}



