package P11438;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] map;
    static int[] depth;
    static int[][] parents;
    static int N, M, LogN;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new ArrayList[N+1];
        depth = new int[N+1];
        getLogN();
        parents = new int[LogN+1][N+1];

        for(int i=0; i<=N; i++) map[i] = new ArrayList<>();

        for(int i=1; i<=N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        bfs(1);

        // make sparse table
        for(int i=1; i<=LogN; i++){
            for(int j=1; j<=N; j++){
                parents[i][j] = parents[i-1][parents[i-1][j]];
            }
        }



        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(getLCA(a, b));

        }
    }

    static void bfs(int start){

        ArrayDeque<Integer> que = new ArrayDeque<>();
        depth[start] = 1;
        que.add(start);

        while(!que.isEmpty()){
            int now = que.poll();
            for(int next:map[now]){
                if(depth[next]==0){
                    depth[next] = depth[now]+1;
                    parents[0][next] = now;
                    que.add(next);
                }
            }
        }
    }

    static int getLCA(int a, int b){
        if(depth[a]<depth[b]) {
            return getLCA(b, a);
        }

        for(int i=0; i<=LogN; i++){
            int k = 1<<i;
            if(((depth[a]-depth[b]) & (1 << i)) >= 1){
                a = parents[i][a];
            }
        }

        if(a==b) return a;

        for(int i=LogN; i>=0; i--){
            if(parents[i][a]!=parents[i][b]){
                a = parents[i][a];
                b = parents[i][b];
            }
        }

        return parents[0][a];

    }

    static void getLogN(){
        LogN = 0;
        for(int k=1; k<N; k*=2){
            LogN++;
        }
    }
}
