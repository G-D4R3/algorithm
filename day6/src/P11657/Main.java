package P11657;

import java.io.*;
import java.util.*;

class Edge {
    int a;
    int b;
    int weight;

    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
}

public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[M];
        int[] distances = new int[N+1];

        for(int i=1; i<=N; i++) distances[i] = Integer.MAX_VALUE;

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[m] = new Edge(a, b, w);
        }

        distances[1] = 0;

        // V-1번 반복
        for(int i=0; i<N; i++){
            // M개의 간선 모두 확인
            for(int j=0; j<M; j++){
                if(distances[edges[j].a]!=Integer.MAX_VALUE){
                    if(distances[edges[j].b] > distances[edges[j].a]+edges[j].weight) {
                        if(i==N-1) {
                            System.out.println("-1");
                            return;
                        }
                        distances[edges[j].b] = distances[edges[j].a]+edges[j].weight;
                    }
                }
            }
        }

        for(int i=2; i<=N; i++){
            if(distances[i]!=Integer.MAX_VALUE){
                System.out.println(distances[i]);
            }
            else System.out.println("-1");
        }

    }
}
