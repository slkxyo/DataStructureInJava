package Completed.Queue;

public class Node<T> {
    private T info;
    public Node<T> next;
    public Node<T> pre;

    public Node(){
    }
    public Node(T ele){
        this.info = ele;
    }
    public T getInfo(){
        return info;
    }
    public void setInfo(T ele){
        this.info = ele;
    }
}
