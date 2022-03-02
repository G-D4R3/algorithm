package P1806_부분합;

import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[] nums, D;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N+1];
        D = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            D[i] = D[i-1]+nums[i];
        }


        int p1 = 1;
        int p2 = 1;
        int length = N+1;

        while(p1<=N && p2<=N){
            int tmp = D[p2]-D[p1]+nums[p1];
            if(D[p2]-D[p1]+nums[p1]<S){
                p2++;
            }
            else {
                length = Math.min(p2-p1+1, length);
                p1++;
            }
        }

        if(length!=N+1) System.out.println(length);
        else System.out.println("0");
    }
}