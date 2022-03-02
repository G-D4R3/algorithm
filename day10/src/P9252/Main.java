package P9252;

import java.io.*;
import java.util.*;

class Point {
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int[][] M;
    static Point[][] B;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        char[] a = new char[s1.length()+1];
        char[] b = new char[s2.length()+1];

        a[0] = b[0] = '/';
        for(int i=1; i<=s1.length(); i++){
            a[i] = s1.charAt(i-1);
        }
        for(int i=1; i<=s2.length(); i++){
            b[i] = s2.charAt(i-1);
        }

        LCS(a, b);
    }

    public static void LCS(char[] a, char[] b){
        int n = a.length;
        int m = b.length;

        M = new int[n][m];
        B = new Point[n][m];

        for(int j=0; j<m; j++){
            B[0][j] = new Point(0, 0);
        }

        for(int i=1; i<n; i++){
            M[i][0] = 0;
            B[i][0] = new Point(0, 0);
            for(int j=1; j<m; j++){

                if(M[i-1][j] > M[i][j-1]){
                    M[i][j] = M[i-1][j];
                    B[i][j] = new Point(i-1, j);
                }
                else {
                    M[i][j] = M[i][j-1];
                    B[i][j] = new Point(i, j-1);
                }
                if(a[i]==b[j]) {
                    M[i][j] = M[i-1][j-1] + 1;
                    B[i][j] = new Point(i, j);
                }
            }
        }

        Stack<Character> stack = new Stack<>();
        Point fin = B[n-1][m-1];
        int now_i=n-1, now_j=m-1;
        int mM = M[n-1][m-1];

        while(mM>0){

            for(int j=now_j; j>0 && M[now_i][j]>=mM; j--){
                now_j = j;
            }
            for(int i=now_i; i>0 && M[i][now_j]>=mM; i--){
                now_i = i;
            }

            stack.push(a[B[now_i][now_j].r]);
            mM--;
        }

        System.out.println(stack.size());
        if(stack.size()>0){
            while(!stack.isEmpty()){
                System.out.print(stack.pop());
            }
            System.out.println();
        }

    }
}
