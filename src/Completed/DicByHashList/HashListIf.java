package Completed.DicByHashList;

interface HashListIf<K,V> extends Iterable<V>{
    void add(K key,V value);
    void remove(K key);
    V getValue(K key);

    int getHashIndex(K key,Element<K,V>[] target);

    void ensureLamda();
}
