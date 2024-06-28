package Completed.Queue;

interface QueueInterface <T>{

    void enqueue(T ele);
    T getFront();
    T dequeue();
    boolean isEmpty();
    void  clear();
}
