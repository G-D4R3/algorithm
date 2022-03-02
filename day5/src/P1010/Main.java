package P1010;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int[][] dp = new int[30][30];
        dp[0][0] = 1;
        for(int i=1; i<=29; i++){
            for(int j=0; j<=29; j++){
                if(j==0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j]+ dp[i-1][j-1];
            }
        }

        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            System.out.println(dp[M][M-N]);
        }

    }
}