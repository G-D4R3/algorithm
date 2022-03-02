package P6588;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] isNotPrime = new boolean[MAX+1];
        isNotPrime[0] = isNotPrime[1] = true;

        for(int i=2; i<=MAX; i++){
            if(!isNotPrime[i]){
                for(int j=2*i; j<=MAX; j+=i){
                    isNotPrime[j] = true;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());

        while(N!=0){
            boolean flag = false;
            for(int i=3; i<=N/2; i+=2){
                if((i%2!=0) && ((N-i)%2!=0) && !isNotPrime[N-i] && !isNotPrime[i]){
                    bw.write(String.format("%d = %d + %d\n", N, i, N-i));
                    flag = true;
                    break;
                }
            }
            if(!flag) bw.write("Goldbach's conjecture is wrong.\n");
            N = Integer.parseInt(br.readLine());
        }
        bw.flush();
    }
}
