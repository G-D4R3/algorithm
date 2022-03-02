package P14476;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        int[] data;
        int[] LR;
        int[] RL;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        LR = new int[N];
        RL = new int[N];
        data = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        LR[0] = data[0];
        RL[N-1] = data[N-1];

        for(int i=1; i<N; i++){
            LR[i] = GCD(LR[i-1], data[i]);
        }

        for(int i=N-2; i>=0; i--){
            RL[i] = GCD(RL[i+1], data[i]);
        }


        int max = 0;
        int maxIndex = 0;
        for(int i=0; i<N; i++){
            int gcd = 0;
            if(i==0){
                gcd = RL[1];
            }
            else if(i==N-1){
                gcd = LR[N-2];
            }
            else {
                gcd = GCD(LR[i-1], RL[i+1]);
            }

            if(data[i]%gcd!=0 && gcd > max){
                max = gcd;
                maxIndex = i;
            }
        }

        if(max!=0) System.out.println(max+" "+data[maxIndex]);
        else System.out.println("-1");

    }

    static int GCD(int a, int b){
        while(b!=0){
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}
