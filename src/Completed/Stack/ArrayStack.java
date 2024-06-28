package Completed.Stack;

public class ArrayStack<T> implements StackIF<T>{
    private int INIT_SIZE = 16;
    private Node<T>[] array  = new Node[INIT_SIZE];
    private int topIndex = -1;

    @Override
    public void push(T element) {
        if(topIndex == array.length-1)
            larger();
        Node<T> node = new Node<>(element);
        topIndex++;
        array[topIndex] = node;
    }
    private void larger(){
        Node<T>[] newArray = new Node[array.length*2];
        for(int i=0;i<array.length;i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public T pop() {
        if(topIndex == -1){
            return null;
        }
        T res = array[topIndex].getElement();
        topIndex--;
        return res;
    }

    @Override
    public T peek() {
        return array[topIndex].getElement();
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public void clear() {
        topIndex = -1;
    }
}
