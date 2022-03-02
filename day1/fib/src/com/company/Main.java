package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(fib(7));
    }

    public static int fib(int n){ // 재귀함수 디버깅
        if(n==1||n==2) {
            return 1;
        }
        else {
            return fib(n-1)+fib(n-2);
        }
    }
}
