package Completed.Stack;

import java.util.Stack;

public class Factorial {

    public static int getValue(int nu){
        if(nu == 0)
            return 1;
        return nu*getValue(nu-1);
    }

    public  int getValueUseStack(int nu,int total){
        Stack<Record> st = new Stack<>();
        st.push(new Record(nu,total));

        while (!st.isEmpty()){
            Record re = st.pop();
            nu = re.getNu();
            total = re.getTotal();
            if(nu == 0){
                return  total;
            }
            st.push(new Record(nu-1,nu*total));
        }
        return -1;
    }

    private class Record{
        private int nu;
        private int total;

        public Record(int nu,int total){
            this.nu = nu;
            this.total = total;
        }
        public int getNu(){
            return nu;
        }
        public int getTotal(){
            return total;
        }
    }
}

