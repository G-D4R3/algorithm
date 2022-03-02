package P3425;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    static Queue<Integer> nums;
    static Queue<String> operations;
    static Stack<Long> stack;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));


        String input = br.readLine();
        nums = new LinkedList<>();
        operations = new LinkedList<>();
        stack = new Stack<>();

        while(!input.equals("QUIT")) {

            // 다음
            if(input.length()==0){
                // 프로그램 수행
                // N번 수행
                N = nums.poll();
                int count = 0;

                while(count<N){

                    int num = nums.poll();
                    Queue<String> oper = new LinkedList<>(operations);
                    boolean errorFlag = program(num, oper);

                    count++;
                    if(!errorFlag && stack.size()==1) System.out.println(stack.pop());
                    else System.out.println("ERROR");
                    stack.clear();
                }


                // 새로운 입력을 위한 초기화
                nums.clear();
                operations.clear();

            }
            // input이 숫자
            else if(input.matches("[+-]?\\d*(\\.\\d+)?")){
                nums.offer(Integer.parseInt(input));
            }
            // input이 operation
            else {
                operations.offer(input);
            }
            input = br.readLine();
        }
    }

    static boolean program(long num, Queue<String> operations){
        String operation = operations.poll();
        stack.push(num);

        // 숫자가 부족해서 연산을 수행할 수 없을 때, 0으로 나눴을 때 (DIV, MOD), 연산 결과의 절댓값이 109를 넘어갈 때는 모두 프로그램 에러이다.


        while(!operation.equals("END")) {
            //NUM X: X를 스택의 가장 위에 저장한다. (0 ≤ X ≤ 109
            if(operation.split(" ")[0].equals("NUM")){
                long n = Long.parseLong(operation.split(" ")[1]);
                if(Math.abs(n)>1e9) {
                    return true;
                }
                stack.push(n);
            }
            //POP: 스택 가장 위의 숫자를 제거한다.
            else if(operation.equals("POP")){
                if(stack.size()==0) {
                    return true;
                }
                stack.pop();
            }
            //INV: 첫 번째 수의 부호를 바꾼다. (42 -> -42)
            else if(operation.equals("INV")){
                if(stack.size()==0) {
                    return true;
                }
                stack.push(-1*stack.pop());
            }
            //DUP: 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
            else if(operation.equals("DUP")){
                if(stack.size()==0) {
                    return true;
                }
                stack.push(stack.peek());
            }
            //SWP: 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
            else if(operation.equals("SWP")){
                if(stack.size()<2) {
                    return true;
                }
                long a = stack.pop();
                long b = stack.pop();
                stack.push(a);
                stack.push(b);
            }
            //ADD: 첫 번째 숫자와 두 번째 숫자를 더한다.
            else if(operation.equals("ADD")){
                if(stack.size()<2) {
                    return true;
                }
                long a = stack.pop();
                long b = stack.pop();
                if(Math.abs(a+b)>1e9){
                    return true;
                }
                stack.push(a+b);
            }
            //SUB: 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
            else if(operation.equals("SUB")){
                if(stack.size()<2) {
                    return true;
                }
                long a = stack.pop();
                long b = stack.pop();
                if(Math.abs(b-a)>1e9){
                    return true;
                }
                stack.push(b-a);
            }
            //MUL: 첫 번째 숫자와 두 번째 숫자를 곱한다.
            else if(operation.equals("MUL")){
                if(stack.size()<2) {
                    return true;
                }
                long a = stack.pop();
                long b = stack.pop();


                if(Math.abs(a*b)>1e9){
                    return true;
                }
                stack.push(a*b);
            }
            //DIV: 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
            else if(operation.equals("DIV")){
                if(stack.size()<2) {
                    return true;
                }
                long a = stack.pop();
                long b = stack.pop();

                if((a>0 && b>0) || (a<0 && b<0)){
                    stack.push(b/a);
                }
                else if(a==0){
                    return true;
                }
                else {
                    stack.push(-1*Math.abs(b)/Math.abs(a));
                }
            }
            //MOD: 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
            else if(operation.equals("MOD")){
                if(stack.size()<2) {
                    return true;
                }
                long a = stack.pop();
                long b = stack.pop();
                if(a>0 && b>0){
                    stack.push(b%a);
                }
                else if(a==0){
                    return true;
                }
                else if(a<0 || b<0){
                    stack.push(-1*Math.abs(b)%Math.abs(a));
                }
            }
            operation = operations.poll();
        }
        return false;
    }
}
