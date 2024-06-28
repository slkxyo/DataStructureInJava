package Completed.Stack;

public class LinkedStack<T>  implements StackIF<T>{
    private Node<T>  top;

    @Override
    public void push(T element) {
        Node<T> node = new Node<>(element);
        node.next = top;
        top = node;
    }

    @Override
    public T pop() {
        if(top == null){
            return null;
        }
        T ele = top.getElement();
        top = top.next;
        return ele;

    }

    @Override
    public T peek() {
        if(top == null){
            return null;
        }
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void clear() {
        top = null;
    }
}
