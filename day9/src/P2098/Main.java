package P2098;

import java.io.*;
import java.util.*;


// 비트 마스크를 사용하면 재귀의 finish 조건을 알기 편해진다.

public class Main {
    static int N, visit, end, answer;
    static int[][] W, D;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        end = (1<<N)-1;
        W = new int[N+1][N+1];
        D = new int[N+1][end+1];

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) W[i][j] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++){
            for(int j=0; j<=end; j++){
                D[i][j] = Integer.MAX_VALUE;
            }
        }


        visit = 1;
        D[1][1] = 0;
        TSP(1, 1, visit);

        System.out.println(answer);
    }

    static void TSP (int start, int node, int visit){

        // visit == end 이고 start로 가는 길이 있다면 최소값 갱신
        if(visit==end) {
            if(W[node][start]==0) return;
            answer = Math.min(answer, D[node][visit]+W[node][start]);
        }

        // node와 연결된 다음 노드들 중에서
        for(int i=1; i<=N; i++){
            int next = (1<<(i-1));
            int nextCheck = next | visit ;

            // 이미 방문한 곳
            if(nextCheck==visit) continue;

            // 갈 수 없는 곳
            if(W[node][i] == 0) continue;

            // 다음 도시 계산
            // 이게 있어서 완전탐색이 아니다
            if(D[i][nextCheck] > D[node][visit] + W[node][i]){
                D[i][nextCheck] = D[node][visit] + W[node][i];
                TSP(start, i, nextCheck);
            }

        }




    }

}
