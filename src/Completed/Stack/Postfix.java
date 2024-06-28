package Completed.Stack;

import java.util.Stack;

public class Postfix {
    private static StringBuilder res = new StringBuilder();
    private static java.util.Stack<Character> ops = new Stack<>();

    private static Stack<Double> nums = new Stack<>();
    private static Stack<Character> syms = new Stack<>();

    public static void cacuNormal(String str){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char onechar = str.charAt(i);
            if(isOps(onechar)){
                builder.append(" ").append(onechar).append(" ");
            }
            else {
                builder.append(onechar);
            }
        }
        String strs = builder.toString();
        String[] chars = strs.split(" ");
        for(int i=0;i<chars.length;i++){
            String st = chars[i].trim();
            if(!st.isEmpty() &&isOps(st.charAt(0))){
                inOpsStack(st.charAt(0));
            }
            if(!st.isEmpty() && !isOps((st.charAt(0)))) {
                nums.push(Double.parseDouble(st));
            }
        }
        while (!syms.isEmpty()){
            char peek = syms.peek();
            if(peek == '+'){
                add();
                continue;
            }
            if(peek =='-'){
                sub();
                continue;
            }
            if(peek == '*'){
                mut();
                continue;
            }
            if(peek =='/'){
                div();
                continue;
            }
            if(peek == '^'){
                tri();
                continue;
            }
        }

        System.out.println(nums.pop());
    }
    private static void  inOpsStack(char c){
        if(c == '('||c == '^'){
            syms.push(c);
            return;
        }
        if(c=='+'||c=='-'){
            while (!syms.isEmpty()){
                char peek = syms.peek();
                if(peek == '('){
                    syms.push(c);
                    return;
                }
                if(peek == '+'){
                    add();
                    continue;
                }
                if(peek == '-'){
                    sub();
                    continue;
                }
                if(peek == '*'){
                    mut();
                    continue;
                }
                if(peek == '/'){
                    div();
                    continue;
                }
                if(peek == '^'){
                    tri();
                }
            }
            syms.push(c);
            return;
        }
        if(c =='*'||c =='/'){
            while (!syms.isEmpty()){
                char peek = syms.peek();
                if(peek == '('){
                    syms.push(c);
                    return;
                }
                if(peek == '*'){
                    mut();
                    continue;
                }
                if(peek == '/'){
                    div();
                    continue;
                }
                if(peek == '^'){
                    tri();
                }
                if(peek =='+'||peek =='-'){
                    syms.push(c);
                    return;
                }
            }
            syms.push(c);
            return;
        }
        if(c ==')'){
            while (!syms.isEmpty()){
                char peek = syms.peek();
                if(peek == '('){
                    syms.pop();
                    return;
                }
                if(peek == '+'){
                    add();
                    continue;
                }
                if(peek == '-'){
                    sub();
                    continue;
                }
                if(peek == '*'){
                    mut();
                    continue;
                }
                if(peek == '/'){
                    div();
                    continue;
                }
                if(peek == '^'){
                    tri();
                }
            }
        }
    }
    private static void add(){
        syms.pop();
        double n1 = nums.pop();
        double n2 = nums.pop();
        nums.push(n1+n2);
    }
    private static void sub(){
        syms.pop();
        double n1 = nums.pop();
        double n2 = nums.pop();
        nums.push(n2-n1);
    }
    private static void mut(){
        syms.pop();
        double n1 = nums.pop();
        double n2 = nums.pop();
        nums.push(n1*n2);
    }
    private static void div(){
        syms.pop();
        double n1 = nums.pop();
        double n2 = nums.pop();
        nums.push(n2/n1);
    }
    private static void tri(){
        syms.pop();
        double n1 = nums.pop();
        double n2 = nums.pop();
        nums.push(Math.pow(n2,n1));
    }
    private static boolean isOps(char c){
        return c=='+'||c=='-'||c=='*'||c=='/'||c=='('||c==')'||c=='^';
    }
    public static String toPostfix(String str){
        for(int i =0;i<str.length();i++){
            char sym = str.charAt(i);
            switch (sym){
                case '(', '^':
                    ops.push(sym);
                break;
                case ')':
                    while (!ops.isEmpty()){
                        if(ops.peek()!='('){
                            res.append(ops.pop());
                        }
                        else {
                            ops.pop();
                            break;
                        }
                    }
                break;
                case '+': case '-':
                    if(ops.isEmpty()){
                        ops.push(sym);
                        continue;
                    }
                    while (!ops.isEmpty()){
                        if(ops.peek() == '+'||ops.peek() == '-'||ops.peek()=='*'||ops.peek()=='/'||ops.peek() == '^'){
                            res.append(ops.pop());
                        }
                        else {
                            break;
                        }
                    }
                    ops.push(sym);
                break;
                case '*': case '/':
                    if(ops.isEmpty()){
                        ops.push(sym);
                        continue;
                    }
                    while (!ops.isEmpty()) {
                        if (ops.peek() == '^'||ops.peek() =='*'||ops.peek()=='/') {
                            res.append(ops.pop());
                        }
                        else {
                            break;
                        }
                    }
                    ops.push(sym);
                break;

                default:
                    res.append(sym);
                break;
            }
        }
        while (!ops.isEmpty()){
            res.append(ops.pop());
        }
        return res.toString();
    }

    public static double cacuPostfix(String postfix){
        Stack<String> eles = new Stack<>();
        for(int i=0;i<postfix.length();i++){
            char ops = postfix.charAt(i);
            if(ops =='+'||ops == '-'||ops=='*'||ops=='/'||ops =='^'){
                double a = Double.parseDouble(eles.pop());
                double b = Double.parseDouble(eles.pop());
                if(ops == '+'){
                    eles.push(Double.toString(a+b));
                }
                else if(ops == '-'){
                    eles.push(Double.toString(b-a));
                }
                else if(ops == '*'){
                    eles.push(Double.toString(a*b));
                }
                else if(ops == '/'){
                    eles.push(Double.toString(b/a));
                }
                else{
                    eles.push(Double.toString(Math.pow(b,a)));
                }
            }
            else {
                eles.push(String.valueOf(ops));
            }
        }
        return Double.parseDouble(eles.pop());
    }

}
