package Completed.Sort;

public class Sort<T extends Comparable<? super T>> {
    private T[] array;

    public Sort(Comparable[] array){
        this.array = (T[]) array;
    }
    public void show(){
        for(int i=0;i<array.length;i++)
            System.out.print(array[i]+"  ");
    }
    public void insertSort(){
        if(array.length < 2)
            return;
        for(int i=1;i<array.length;i++){
            int curr = i;
            T temp = array[curr];
            while (true){
                if(curr == 0){
                    array[0] = temp;
                    break;
                }
                if(temp.compareTo(array[curr-1])>=0){
                    array[curr] = temp;
                    break;
                }

                array[curr] = array[curr-1];
                curr--;
            }
        }
    }

    public void  mergeSort(){

    }
    private void mergeSort(int first,int mid ,int last){

    }

}
