package P1932;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<=i; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0];
        for(int i=1; i<N; i++){
            for(int j=0; j<=i; j++){
                if(j==0) dp[i][j] = dp[i-1][j] + arr[i][j];
                else dp[i][j] = Math.max(dp[i-1][j-1] + arr[i][j],  dp[i-1][j] + arr[i][j]);
            }
        }
        int[] res = dp[N-1].clone();
        Arrays.sort(res);
        System.out.println(res[N-1]);
    }
}
