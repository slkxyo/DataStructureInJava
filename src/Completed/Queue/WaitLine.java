package Completed.Queue;

import java.util.ArrayDeque;

public class WaitLine {
    private int totalWaitTime;
    private int numberServiced;
    private int numberArr;
    private ArrayDeque<Customer> queue = new ArrayDeque<>();


    public WaitLine(int dur,double arrivalP,int maxP){
        emulate(dur,arrivalP,maxP);
    }
    private void emulate(int dur,double arrivalP,int maxP){
        int transTimeNow =0;
        for(int clock=0;clock<=dur;clock++){
            if(Double.compare(Math.random(),arrivalP)<=0){
                numberArr++;
                int transTime = (int)(Math.random()*maxP+1);
                System.out.printf("In \tid:%d\tarrivaltime:%d\ttranstime:%d\n",queue.size()+1,clock,transTime);
                queue.add(new Customer(clock,transTime,numberArr));
            }
            if(transTimeNow>0){
                transTimeNow--;
            }
            else if(!queue.isEmpty()){
                    Customer ser = queue.remove();
                    System.out.printf("ServerBegin\tid:%d\ttimewaited:%d\n",ser.getNumber(),clock - ser.getArrayTime());
                    numberServiced++;
                    totalWaitTime+=(clock -ser.getArrayTime());
                    transTimeNow = ser.getTransTime()-1;
                }
            }
        System.out.printf("NumberServerd:%d\tTotalWatiTime:%d\tAvWatiTime:%f",numberServiced,totalWaitTime,(double)totalWaitTime/numberServiced);
        }
    }
class Customer{
    private int number;
    private int arrayTime;
    private int transTime;

    public Customer(int arrayTime,int transTime,int number){
        this.arrayTime =arrayTime;
        this.transTime = transTime;
        this.number = number;
    }
    public int getArrayTime(){
        return arrayTime;
    }
    public int getTransTime(){
        return  transTime;
    }
    public int getNumber(){
        return number;
    }
}
