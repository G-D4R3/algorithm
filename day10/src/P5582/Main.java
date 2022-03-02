package P5582;

import java.io.*;
import java.util.*;


public class Main {
    static int[][] M;
    static int max;

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

        for(int i=1; i<n; i++){
            M[i][0] = 0;
            for(int j=1; j<m; j++){
                if(a[i]==b[j]) {
                    if(M[i][j] < M[i-1][j-1]+1){
                        M[i][j] = M[i-1][j-1] + 1;
                        max = Math.max(max, M[i][j]);
                    }

                }
            }
        }

        System.out.println(max);

    }
}

