package Completed.Stack;

import java.util.Stack;

public class Checker {
    private static Stack<Character> chars = new Stack<>();

    public static boolean isBanlanced(String str){
        boolean statu = true;
        for(int i=0;i<str.length();i++){
            char sym = str.charAt(i);
            switch (sym){
                case '(': case'[': case'{':{
                    chars.push(sym);}
                break;

                case ')': case']': case'}': {
                    if (chars.isEmpty()) {
                        statu = false;
                        break;
                    }
                    if (!isPair(sym, chars.pop())) {
                        statu = false;
                        break;
                    }
                }
                break;
            }
        }
        if(!chars.isEmpty()){
            statu = false;
        }
        return statu;
    }
    private static boolean isPair(char a,char b){
        return a == ')' && b =='(' ||
                a == ']' && b =='[' ||
                a == '}' && b =='{';
    }
}
