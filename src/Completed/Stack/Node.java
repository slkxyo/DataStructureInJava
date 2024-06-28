package Completed.Stack;

class Node<T> {
    private T element;
    public Node<T>  next;

    public Node(){
        this(null);
    }
    public Node(T element){
        this.element = element;
    }
    public T getElement(){
        return element;
    }
}
