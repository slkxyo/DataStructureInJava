package Completed.Queue;

public class DoubleQueue<T> implements  DoubleQueueInterface<T>{
    private Node<T> head;
    private Node<T> tail;


    @Override
    public void addFront(T ele) {
        Node<T> node = new Node<>(ele);
        if (head == null){
            head = tail = node;
            return;
        }
        node.next = head;
        head.pre = node;
        head = node;
    }

    @Override
    public T removeFront() {
        if(head == null){
            return null;
        }
        if(head.next == null){
            T res = head.getInfo();
            head = tail = null;
            return res;
        }
        T res = head.getInfo();
        head = head.next;
        head.pre = null;
        return res;

    }

    @Override
    public T getFront() {
        if(head == null)
            return null;
        return head.getInfo();
    }

    @Override
    public void addBack(T ele) {
        Node<T> node = new Node<>(ele);
        if(head == null){
            head = tail = node;
            return;
        }
        node.pre = tail;
        tail.next = node;
        tail = node;
    }

    @Override
    public T removeBack() {
        if(tail == null){
            return null;
        }
        if(tail.pre == null){
            T res = tail.getInfo();
            head = tail = null;
            return res;
        }
        T res = tail.getInfo();
        tail  = tail.pre;
        tail.next = null;
        return res;
    }

    @Override
    public T getBack() {
        if(head == null)
            return null;
        return tail.getInfo();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head =tail = null;
    }
}