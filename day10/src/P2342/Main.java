package P2342;

import java.io.*;
import java.util.*;

public class Main {
    static int[][][] D;
    static int[] direction = new int[100001];
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;;i++){
            int n = Integer.parseInt(st.nextToken());
            if(n==0) {
                N = i-1;
                break;
            }
            direction[i] = n;
        }


        if(N==0) {
            System.out.println(0);
            return;
        }

        D = new int[N+1][5][5];
        for(int i=1; i<=N; i++){
            for(int j=0; j<=4; j++){
                for(int k=0; k<=4; k++){
                    D[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        D[1][0][direction[1]] = 2;
        D[1][direction[1]][0] = 2;

        for(int i=1; i<=N-1; i++){
            for(int j=0; j<=4; j++){
                for(int k=0; k<=4; k++){
                    if(D[i][j][k]!=Integer.MAX_VALUE){
                        int next = direction[i+1];
                        if(next!=k){
                            D[i+1][next][k] = Math.min(D[i+1][next][k], D[i][j][k]+cost(j, next));
                        }
                        if(next!=j){
                            D[i+1][j][next] = Math.min(D[i+1][j][next], D[i][j][k]+cost(k, next));
                        }
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<=4; i++){
            for(int j=0; j<=4; j++){
                answer = Math.min(D[N][i][j], answer);
            }
        }

        System.out.println(answer);
    }


    static int cost(int from, int to){
        if(from==to) return 1;
        else if(from==0) return 2;
        else if(Math.abs(from-to)==2) return 4;
        else return 3;
    }
}
