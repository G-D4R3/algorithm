package P11050;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][N+1];
        dp[0][0] = 1;
        for(int i=1; i<=N; i++){
            for(int j=0; j<=N; j++){
                if(j==0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j]+ dp[i-1][j-1];
            }
        }

        System.out.println(dp[N][N-K]);
    }
}
