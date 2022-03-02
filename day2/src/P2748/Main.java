package P2748;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N+1];
        if(N==0) {
            System.out.println(0);
            return;
        }
        else if(N==1){
            System.out.println(1);
            return;
        }
        arr[0] = 0;
        arr[1] = 1;
        for(int i=2; i<=N; i++){
            arr[i] = arr[i-1]+arr[i-2];
        }
        System.out.println(arr[N]);

    }
}
