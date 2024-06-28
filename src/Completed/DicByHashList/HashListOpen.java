package Completed.DicByHashList;

import java.util.Iterator;

public class HashListOpen<K,V> implements HashListIf<K,V>{
    private final int INIT_SIZE = 101;
    private Element<K,V>[] list = new Element[INIT_SIZE];
    private int size;


    @Override
    public void add(K key, V value) {
        ensureLamda();
        Element<K,V> newEle = new Element<K, V>(key,value,true);
        int index = locateEle(key,list);
        Element<K,V> ele = list[index];
        list[index] = newEle;
        if(ele== null || !ele.getKey().equals(key)){
            size++;
        }
    }
    public int size(){
        return size;
    }
    private int locateEle(K key,Element<K,V>[] target){
        int pos = getHashIndex(key,target);
        for(int i=0;i<target.length;i++){
            if(pos == target.length-1){
                pos = 0;
            }
            Element<K,V> curr=target[pos];
            if(curr == null)
                return pos;
            if(!curr.getStatu()){
                return pos;
            }
            if(curr.getKey().equals(key)){
                return pos;
            }
            pos++;
        }
        return -1;
    }
    private int getPrime(){
        return getPrime(1);
    }
    private int getPrime(int min){
        int nu = min+1;
        while (!isPrime(nu)){
            nu++;
        }
        return nu;

    }
    private boolean isPrime(int num){
        if(num%2 == 0)
            return num==2;
        for(int i=3;i<num/2;i=i+2){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void remove(K key) {
        int index = locateEle(key,list);
        Element<K,V> ele = list[index];
        if(ele == null) {
            return;
        }
        if(ele.getStatu()){
            ele.setStatu(false);
            size--;
        }
    }

    @Override
    public V getValue(K key) {
        int index = locateEle(key,list);
        if(index == -1)
            return null;
        Element<K,V> ele = list[index];
        if(ele == null){
            return null;
        }
        if(ele.getKey().equals(key)){
            return ele.getValue();
        }
        return null;
    }

    @Override
    public int getHashIndex(K key,Element<K,V>[] target) {
        int hashCode = key.hashCode();
        return hashCode<0?hashCode%target.length+ target.length:hashCode%target.length;
    }

    @Override
    public void ensureLamda() {
        double lamda = (double)size/list.length;
        if(Double.compare(lamda,0.5) <= 0){
            return;
        }
        Element<K,V>[] newList = new Element[getPrime(list.length*3)];
        for(int i=0;i<list.length;i++){
            Element<K,V> ele = list[i];
            if(ele == null || ele.getStatu()==false)
                continue;
            addToNew(ele,newList);
        }
        list = newList;
    }
    private void addToNew(Element<K,V> ele,Element<K,V>[] target){
        int index = locateEle(ele.getKey(),target);
        if(index == -1){
            return;
        }
        target[index] = ele;
    }

    @Override
    public Iterator<V> iterator() {
        return new hashListIterator();
    }

    private class hashListIterator implements Iterator<V>{
        private int curr;

        @Override
        public boolean hasNext() {
            for(int i=curr;i<list.length;i++){
                Element<K,V> ele = list[i];
                if(ele != null&& ele.getStatu()!= false){
                    curr=i;
                    return true;
                }
            }
            return false;

        }

        @Override
        public V next() {
            V res = list[curr].getValue();
            curr++;
            return res;
        }
    }
}
