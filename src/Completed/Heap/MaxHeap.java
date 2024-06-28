package Completed.Heap;

public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapIF<T>{
    private static final int INIT_SIZE = 16;
    private T[] array;
    private int lastIndex;

    public MaxHeap(){
        this(INIT_SIZE);
    }
    public MaxHeap(int size){
        array = (T[]) new Comparable[INIT_SIZE];

    }
    public MaxHeap(T[] eles){
        array  = (T[]) new Comparable[eles.length+1];
        for(int i =0;i<eles.length;i++){
            array[i+1] = eles[i];
        }
        lastIndex  = eles.length;
        for (int i = lastIndex/2;i>=1;i--){
            reHeap(array,i,lastIndex);
        }
    }
    @Override
    public T getMax() {
        if (lastIndex<1)
            return null;
        return array[1];
    }

    @Override
    public T removeMax() {
        if(lastIndex == 0)
            return null;
        if(lastIndex == 1){
            lastIndex =0;
            return array[1];
        }
        T res = array[1];
        array[1] = array[lastIndex];
        lastIndex--;
        reHeap(array,1,lastIndex);
        return res;
    }
    private void  reHeap(T[] arr, int rootIndex,int lastIndex){
        int largest = rootIndex;
        while (true){
            int left = largest*2;
            int rig = largest*2+1;
            int newLargest = largest;

            if(left<=lastIndex && arr[left].compareTo(arr[newLargest])>0)
                newLargest = left;
            if(rig<=lastIndex && arr[rig].compareTo(arr[newLargest])>0)
                newLargest = rig;
            if(newLargest == largest)
                break;
            swap(arr,largest,newLargest);
            largest = newLargest;
        }
    }
    private void swap(T[] arr,int i,int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public void add(T ele) {
        if(lastIndex == array.length -1)
            enLarge();
        lastIndex++;
        array[lastIndex] = ele;
        percolateUp();
    }
    private void percolateUp(){
        if(lastIndex == 1)
            return;
        int newInd = lastIndex;
        int parInd = newInd/2;
        while (newInd>1 && array[newInd].compareTo(array[parInd]) > 0){
            T temp = array[parInd];
            array[parInd] = array[newInd];
            array[newInd] = temp;
            newInd = parInd;
            parInd = newInd/2;
        }
    }
    private void enLarge(){
        T[] newArr = (T[]) new Comparable[array.length*2];
        for(int i=1;i<array.length;i++){
            newArr[i] = array[i];
        }
        array = newArr;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex<1;
    }

    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public void clear() {
        lastIndex = 0;
    }

    public java.util.Iterator<T> getIterator(){
        return new Iterator();
    }
    private class Iterator implements java.util.Iterator<T>{
        int curr = 1;

        @Override
        public boolean hasNext() {
            return curr<=lastIndex;
        }

        @Override
        public T next() {
            return array[curr++];
        }
    }
}

