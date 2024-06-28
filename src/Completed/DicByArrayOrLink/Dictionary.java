package Completed.DicByArrayOrLink;

interface Dictionary<K,V> extends  Iterable<V>{
    void add(K key,V value);
    void remove(K key);
    V getValue(K key);


    boolean contain(K key);
    int size();
}
