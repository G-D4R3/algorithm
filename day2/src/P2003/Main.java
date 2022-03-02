package P2003;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //for(int i=0; i<N; i++) System.out.println(arr[i]);

        int sum = 0, count = 0;
        int low = 0, high = 0;
        while(high<=N){
            if(sum>=M){
                sum-=arr[low++];
            }
            else if(high>=N) break;
            else {
                sum += arr[high++];
            }
        }
        System.out.println(count);
    }
}
