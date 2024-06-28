package Completed.Queue;

public class DoubleArrayQueue<T> implements DoubleQueueInterface<T>{
    private final int INIT_SIZE = 16;
    private T[] array ;
    private int front;
    private int back;


    public DoubleArrayQueue() throws Exception {
        this(16);
    }
    public DoubleArrayQueue(int size) throws Exception{
        if(size<= 0)
            throw new Exception("Size must bigger than zero");
        array = (T[]) new Object[size];
    }
    @Override
    public void addFront(T ele) {
        if((back+1)%array.length== front){
            enLarge();
        }
        front=front-1<0?(front-1+array.length)%array.length:(front-1)%array.length;
        array[front] = ele;
    }
    private void enLarge(){
        T[] newArray = (T[]) new Object[array.length*2];
        for(int i=0;i<= array.length-2;i++){
            newArray[i] = array[front];
            front = (front+1)%array.length;
        }
        front=0;
        back = array.length-1;
        array = newArray;
    }

    @Override
    public T removeFront() {
        if(front == back)
            return null;
        T res = array[front];
        front = (front+1)% array.length;
        return res;
    }

    @Override
    public T getFront() {
        if(front == back)
            return null;
        return array[front];
    }

    @Override
    public void addBack(T ele) {
        if((back+1)% array.length == front){
            enLarge();
        }
        array[back] = ele;
        back = (back+1)% array.length;
    }

    @Override
    public T removeBack() {
        if(front == back){
            return null ;
        }
        back = back-1<0?(back-1+array.length)%array.length:(back -1)%array.length;
        return array[back];
    }

    @Override
    public T getBack() {
        if(front == back)
            return null ;
        return array[back-1<0?(back-1+array.length)%array.length:back-1];
    }

    @Override
    public boolean isEmpty() {
        return front == back;
    }

    @Override
    public void clear() {
            front = back;
    }
}
