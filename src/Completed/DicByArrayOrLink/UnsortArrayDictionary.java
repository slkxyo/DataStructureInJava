package Completed.DicByArrayOrLink;

import java.util.Iterator;

public class UnsortArrayDictionary<K,V> implements Dictionary<K,V>{
    private int INIT_SIZE =16;
    private Pair<K,V>[] list = new Pair[INIT_SIZE];
    private  int size;


    @Override
    public void add(K key, V value) {
        for(int i=0;i<size;i++){
            if (list[i].getKey().equals(key)){
                list[i].setValue(value);
                return;
            }
        }
        ensureSize();
        list[size] = new Pair<>(key,value);
        size++;
    }
    private void ensureSize(){
        if(size == list.length){
            Pair<K,V>[] newList = new Pair[list.length*2];
            for(int i=0 ;i<size;i++){
                newList[i] = list[i];
            }
            list = newList;
        }

    }

    @Override
    public void remove(K key) {
        if(!contain(key)){
            return;
        }
        int pos=0;
        for(int i=0;i<size;i++){
            if(list[i].getKey().equals(key)){
                pos = i;
                break;
            }
        }
        list[pos] = list[size-1];
        size--;
    }

    @Override
    public V getValue(K key) {
        for(int i=0;i<size;i++){
            if(list[i].getKey().equals(key))
                return list[i].getValue();
        }
        return null;
    }

    @Override
    public boolean contain(K key) {
        for(int i=0;i<size;i++){
            if(list[i].getKey().equals(key))
                return  true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new dicIterator();
    }
    private class dicIterator implements  Iterator<V>{
        private int curr=0;

        @Override
        public boolean hasNext() {
            return curr<=size-1;
        }

        @Override
        public V next() {
            return list[curr++].getValue();
        }
    }
}
