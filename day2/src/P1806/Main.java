package P1806;

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


        int sum = 0, len = Integer.MAX_VALUE;
        int low = 0, high = 0;
        while(high<=N){
            if(sum>=M){
                sum-=arr[low];
                len = Math.min(len, high-low);
                low++;
            }
            else if(high>=N) break;
            else {
                sum += arr[high++];
            }
        }
        System.out.println(len<Integer.MAX_VALUE? len:0);
    }
}