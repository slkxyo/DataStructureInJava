package Completed.DicByArrayOrLink;

import java.util.Iterator;

public class SortArrayDictionary<K extends Comparable<? super K>,V> implements Dictionary<K,V>{
    private int INIT_SIZE = 25;
    private Pair<K,V>[] list = new Pair[INIT_SIZE];
    private  int size;


    @Override
    public void add(K key, V value) {
        int pos=0;
        for(int i=0;i<size;i++){
            if(list[i].getKey().equals(key)){
                list[i].setValue(value);
                return;
            }
            if(key.compareTo(list[i].getKey())>0){
                pos = i+1;
            }
        }
        ensureSize();
        for(int i=size;i>pos;i--){
            list[i] = list[i-1];
        }
        list[pos] = new Pair<>(key,value);
        size++;
    }

    private void ensureSize(){
        if(size >= list.length){
            Pair<K,V>[] newList = new Pair[list.length*2];
            for(int i=0;i<size ;i++){
                newList[i] = list[i];
            }
            list = newList;
        }
    }
    @Override
    public void remove(K key) {
        for(int i =0;i<size;i++){
            if(list[i].getKey().equals(key)){
                for(int j=i;j<size-1;j++){
                    list[j] = list[j+1];
                }
                size--;
                return;
            }
        }
    }

    @Override
    public V getValue(K key) {
        int low=0;
        int high=size-1;
        int mid = (low+high)/2;
        while (low<=high){
            if(list[mid].getKey().equals(key)){
                return list[mid].getValue();
            }
            low = mid+1;
            mid = (low+high)/2;
        }
        return null;
    }

    @Override
    public boolean contain(K key) {
        for(int i=0;i<size;i++){
            if(list[i].getKey().equals(key))
                return true;
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

    private class dicIterator  implements Iterator<V>{
        private int curr;

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
