package Completed.Stack;

import java.util.Stack;

public class BiS {
    private int[] ints;
    private int key;
    private java.util.Stack<Record> records = new Stack<>();

    public BiS(int[] array,int key){
        this.ints = array;
        this.key = key;
    }
    public int getIndex(){
        int low = 0;
        int high =ints.length-1;
        int mid = 0;
        records.push(new Record(low,high));
        while (!records.isEmpty()){
            Record re = records.pop();
            low = re.getLow();
            high = re.getHigh();
            mid = (low+high)/2;
            if(low >high){
                return -1;
            }
            if(ints[mid] == key){
                return mid;
            }
            else if(ints[mid] > key){
                records.push(new Record(low,mid-1));
            }
            else {
                records.push(new Record(mid+1,high));
            }
        }
        return -1;
    }

}
class Record{
    private int low;
    private int high;

    public Record(int low,int high){
        this.low = low;
        this.high = high;
    }
    public int getLow(){
        return low;
    }
    public int getHigh(){
        return high;
    }
}
