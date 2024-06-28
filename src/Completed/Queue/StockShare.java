package Completed.Queue;

import java.util.ArrayDeque;

public class StockShare {
    private ArrayDeque<Share> queue = new ArrayDeque<>();

    public void buy(int number,int price){
        queue.add(new Share(number,price));
    }
    public int sell(int number,int price){
        int gathered =0;
        int totalMoney =0;
        while (!queue.isEmpty()){
            Share share = queue.removeFirst();
            int thisNumber = share.getNumber();
            int thisPrice = share.getPrice();
            if(gathered+thisNumber<number){
                gathered += thisNumber;
                totalMoney+=thisNumber*thisPrice;
                if(queue.isEmpty()){
                    System.out.println("Number invalid");
                    break;
                }
            }
            else if(gathered+thisNumber==number){
                System.out.println("Number valid");
                totalMoney+=thisNumber*thisPrice;
                break;
            }
            else {
                System.out.println("Number valid");
                int remain = gathered+thisNumber-number;
                queue.addFirst(new Share(remain,thisPrice));
                totalMoney+=thisPrice*(thisNumber - remain);
                break;
            }
        }
        int sellMoney  = number*price;
        return sellMoney - totalMoney;
    }





}
class Share{
    private int price;
    private int number;

    public Share(int number,int price){
        this.price = price;
        this.number = number;
    }
    public int getPrice() {
        return price;
    }
    public int getNumber(){
        return number;
    }
}
