package Completed.Queue;

public class ArrayQueue<T> implements QueueInterface<T>{
    private final int INIT_SIZE = 16;
    private T[] array = (T[]) new Object[INIT_SIZE];
    private int front;
    private int back;
    private int size;

    @Override
    public void enqueue(T ele) {
        if((back+1)%array.length == front){
            enLarge();
        }
        array[back] = ele;
        back=(back+1)%array.length;
        size++;
    }
    private void  enLarge(){
        T[] newArray = (T[]) new Object[array.length*2];
        for(int i = 0;i<array.length-1;i++){
            newArray[i] = array[front];
            front = (front+1)% array.length;
        }
        front = 0;
        back = array.length-1;
        array = newArray;
    }

    @Override
    public T getFront() {
        if(front == back){
            return null;
        }
        return array[front];
    }

    @Override
    public T dequeue() {
        if(front == back)
            return  null;
        T res = array[front];
        front = (front+1)%array.length;
        size--;
        return res;
    }

    @Override
    public boolean isEmpty() {
        return front == back;
    }

    @Override
    public void clear() {
        back  = front;
    }
    public int size(){
        return size;
    }
}
