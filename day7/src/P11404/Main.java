package P11404;

import java.util.*;
import java.io.*;

public class Main {
    static int[][] floyd;
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        floyd = new int[N+1][N+1];

        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(floyd[a][b] == 0 || floyd[a][b] > w)
                floyd[a][b] = w;
        }

        // i 노드 경유
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                for(int k=1; k<=N; k++){
                    if(j!=k && floyd[j][i] != 0 && floyd[i][k]!=0){
                        int tmp = floyd[j][i]+floyd[i][k];
                        if(floyd[j][k] == 0 || floyd[j][k] > tmp){
                            floyd[j][k] = tmp;
                        }
                    }

                }
            }
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<N; j++){
                System.out.print(floyd[i][j]+" ");
            }
            System.out.println(floyd[i][N]);
        }
    }
}
