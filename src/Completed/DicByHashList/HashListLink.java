package Completed.DicByHashList;

import java.util.ArrayList;
import java.util.Iterator;

public class HashListLink<K,V> implements HashListIf<K,V>{
    private final int INIT_SIZE = 101;
    private Element<K,V>[] list = new Element[INIT_SIZE];
    private int size;


    @Override
    public void add(K key, V value) {
        ensureLamda();
        Element<K, V> newEle = new Element<K, V>(key, value, true);
        int index = locateEle(key, list);
        Element<K, V> ele = list[index];
        if(ele == null){
            list[index] = newEle;
            size++;
            return;
        }
        while (ele.next != null){
            if(ele.getKey().equals(key))
                break;
            ele = ele.next;
        }
        if (ele.getKey().equals(key)) {
            ele.setValue(value);
            return;
        }
        newEle.pre = ele;
        ele.next = newEle;
        size++;
    }
    public int size(){
        return size;
    }
    private int locateEle(K key,Element<K,V>[] target){
        int hashCode = key.hashCode();
        return hashCode<0?hashCode%target.length+ target.length:hashCode%target.length;
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
        if(ele == null){
            return;
        }
        while (ele.next != null){
            if(ele.getKey().equals(key))
                break;
            ele = ele.next;
        }
        if(!ele.getKey().equals(key)){
            return;
        }
        if(ele.pre == null){
            list[index] = null;
            size--;
            return;
        }
        if(ele.next == null){
            ele.pre.next = null;
            size--;
            return;
        }
        ele.next.pre = ele.pre;
        ele.pre.next = ele.next;
        size--;
    }

    @Override
    public V getValue(K key) {
        int index = locateEle(key,list);
        Element<K,V> ele = list[index];
        if(ele == null)
            return null;
        while (ele.next != null){
            if(ele.getKey().equals(key))
                break;
            ele = ele.next;
        }
        if(ele.getKey().equals(key)){
            return ele.getValue();
        }
        return null;
    }

    @Override
    public int getHashIndex(K key, Element<K, V>[] target) {
        return 0;
    }


    @Override
    public void ensureLamda() {
        double lamda = (double)size/list.length;
        if(Double.compare(lamda,1) <= 0){
            return;
        }
        Element<K,V>[] newList = new Element[getPrime(list.length*2)];
        for(int i=0;i<list.length;i++){
            Element<K,V> ele = list[i];
            if(ele == null)
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
        private ArrayList<Element<K,V>> elements = getELements();

        @Override
        public boolean hasNext() {
            if(curr<elements.size())
                return  true;
            return false;
        }

        @Override
        public V next() {
            V res = elements.get(curr).getValue();
            curr++;
            return res;
        }

        private ArrayList<Element<K,V>> getELements(){
            ArrayList<Element<K,V>> res = new ArrayList<>();
            for(int i=0;i<list.length;i++){
                if(list[i] == null)
                    continue;
                Element<K,V> ele = list[i];
                while (ele != null){
                    res.add(ele);
                    ele = ele.next;
                }
            }
            return res;
        }

    }
}

