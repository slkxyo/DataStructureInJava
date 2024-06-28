package Completed.DicByArrayOrLink;

class Node<K,V> {
    private K key;
    private V value;

    public Node<K,V> next;
    public Node<K,V> pre;

    public Node(K key,V value){
        this.key = key;
        this.value = value;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
    public void setValue(V value){
        this.value = value;
    }
}
