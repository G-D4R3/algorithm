package P7579;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, totalCost;
    static int[] m, c;
    static int[][] D;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        totalCost = 0;

        m = new int[N+1];
        c = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) m[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            c[i] = Integer.parseInt(st.nextToken());
            totalCost += c[i];
        }

        D = new int[N+1][totalCost+1];
        if(c[0]==0) D[1][0] = m[0];

        for(int i=1; i<=N; i++){
            for(int j=0; j<=totalCost; j++){
                D[i][j] = D[i-1][j];
                if(j-c[i] >=0 && D[i][j] < D[i-1][j-c[i]]+m[i]){
                    D[i][j] = D[i-1][j-c[i]]+m[i];
                }
            }
        }

        for(int i=0; i<=totalCost; i++){
            if(D[N][i]>=M) {
                System.out.println(i);
                return;
            }
        }

    }
}
