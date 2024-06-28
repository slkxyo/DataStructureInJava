package Completed.DicByHashList;

class Element<K,V> {
    private K key;
    private V value;
    private boolean statu = true;
    public Element<K,V> next;
    public Element<K,V> pre;

    public Element(K key, V value, boolean statu) {
        this.key = key;
        this.value = value;
        this.statu = statu;
    }

    public K getKey() {
        return key;
    }

    public boolean getStatu() {
        return statu;
    }

    public V getValue() {
        return value;
    }

    public void setStatu(boolean newStatu) {
        statu = false;
    }
    public void setValue(V value){
        this.value = value;
    }
}
