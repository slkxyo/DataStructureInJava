package Completed.DicByArrayOrLink;

import java.util.Iterator;

public class UnsortLinkedDictionary<K,V> implements Dictionary<K,V>{
    private Node<K,V> head;
    private Node<K,V> tail;
    private int size;



    @Override
    public void remove(K key) {
        if(!contain(key)){
            return;
        }
        Node<K,V> node = head;
        while (node!=null){
            if(node.getKey().equals(key)){
               if(node == head) {
                   head.next.pre = null;
                   head = head.next;
                   size--;
                   return;
               }
               else if(node == tail){
                   tail.pre.next = null;
                   tail = tail.pre;
                   size--;
                   return;
               }
               else {
                   node.pre.next = node.next;
                   node.next.pre = node.pre;
                   size--;
                   return;
               }
            }
            node = node.next;
        }
    }

    @Override
    public V getValue(K key) {
        Node<K,V> node = head;
        while (node != null){
            if(node.getKey().equals(key)){
                return node.getValue();
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean contain(Object key) {
        Node<K,V> node = head;
        while (node!=null){
            if(node.getKey().equals(key)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public void add(K key, V value) {
        Node<K,V> curr=head;
        while (curr!=null){
            if(curr.getKey().equals(key)){
                curr.setValue(value);
                return;
            }
            curr = curr.next;
        }
        Node<K,V> node = new Node<>(key,value);
        if(size ==0){
            head = tail = node;
            size++;
            return;
        }
        tail.next = node;
        node.pre =tail;
        tail = node;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new linkedDicIterator();
    }
    private class linkedDicIterator implements Iterator<V>{
        private Node<K,V> curr = head;

        @Override
        public boolean hasNext() {
            return curr!=null;
        }

        @Override
        public V next() {
            V res = curr.getValue();
            curr = curr.next;
            return res;
        }
    }
}
