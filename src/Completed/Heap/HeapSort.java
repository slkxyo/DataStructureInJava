package Completed.Heap;

public class HeapSort<T extends Comparable<? super T>> {

    public void sort(T[] array,int number){
        if (number <= 1)
            return;
        if (number > array.length)
            number = array.length;
        int lastIndex = number - 1;

        for (int i = number / 2 - 1; i >= 0; i--) {
            reHeap(array, i, lastIndex);
        }

        for (int i = lastIndex; i > 0; i--) {
            swap(array, 0, i);
            reHeap(array, 0, i - 1);
        }
    }
    private void swap(T[] array,int i,int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private void reHeap(T[] array,int rootIndex,int lastIndex){
        int largest = rootIndex;
        while (true){
            int left = largest*2+1;
            int rig = largest*2+2;
            int newLargeset = largest;

            if(left<=lastIndex && array[left].compareTo(array[newLargeset])>0)
                newLargeset = left;
            if(rig<=lastIndex && array[rig].compareTo(array[newLargeset])>0)
                newLargeset = rig;
            if(newLargeset == largest)
                break;
            swap(array,largest,newLargeset);
            largest = newLargeset;
        }
    }
}
