package P2504;

import java.io.*;
import java.util.Stack;

public class Main {
    static int res;
    static Stack<Character> stack;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        stack = new Stack<>();
        res = 1;

        for(char c:s.toCharArray()){
            if(c=='(' || c=='[') stack.push(c);
            else if(c==')' || c==']') pop(c);
        }
        System.out.println(res);
    }

    static void pop(char c){

    }
}
