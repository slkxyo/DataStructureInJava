package Completed.Queue;

public class CLQueue<T> implements QueueInterface<T>{
    private Node<T> head;
    private Node<T> tail;


    public CLQueue(){
        head = tail = new Node<>();
    }

    @Override
    public void enqueue(T ele) {
        if(head.next == null){
            Node<T> node = new Node<>(ele);
            head = node;
            node.next = tail;
            tail.next = head;
            return;
        }
        if(tail.next == head){
            tail.setInfo(ele);
            Node<T> node = new Node<>();
            tail.next = node;
            node.next = head;
            tail = node;
            return;
        }
        tail.setInfo(ele);
        tail = tail.next;
    }

    @Override
    public T getFront() {
        if(head == tail)
            return null;
        return head.getInfo();
    }

    @Override
    public T dequeue() {
        if(tail == head){
            return null;
        }
        T res = head.getInfo();
        head = head.next;
        return res;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public void clear() {
        head = tail;
    }
}
