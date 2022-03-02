package P11049;

// 진짜 존나 모르겠음

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] rc, D;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rc = new int[N+1][2];
        D = new int[N+1+1][N+1+1];

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            rc[i][0] = Integer.parseInt(st.nextToken());
            rc[i][1] = Integer.parseInt(st.nextToken());
        }


        for(int i=N-1; i>=0; i--){
            for(int j=i+1; j<=N; j++){
                D[i][j] = Integer.MAX_VALUE;
                for(int k=i; k<=j; k++){
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k+1][j] + rc[i][0]*rc[k][1]*rc[j][1]);
                }
            }
        }
        System.out.println(D[1][N]);
    }
}
