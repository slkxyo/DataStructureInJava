package Completed.Queue;

public class LinkedQueue<T> implements QueueInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void enqueue(T ele) {
        Node<T>  node = new Node<T>(ele);
        if(head == null){
            head = tail = node;
            size++;
            return;
        }
        tail.next = node;
        tail = node;
        size++;
    }

    @Override
    public T getFront() {
        if(head == null)
            return  null;
        return head.getInfo();
    }

    @Override
    public T dequeue() {
        if(head == null)
            return null;
        Node<T>  front = head;
        head = head.next;
        size--;
        return front.getInfo();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = tail = null;
        size=0;
    }
    public int size(){
        return size;
    }
}

