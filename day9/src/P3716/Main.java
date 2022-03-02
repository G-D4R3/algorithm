package P3716;

import java.io.*;
import java.util.*;

class Node {
    int node;
    int distance;

    public Node(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class Main {
    static int N, LogN, K, D, E;
    static int MaxAnswer, MinAnswer;
    static int[][] parents, max, min;
    static int[] depth;
    static ArrayList<Node>[] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        getLogN();
        parents = new int[LogN+1+1][N+1];
        max = new int[LogN+1+1][N+1];
        min = new int[LogN+1+1][N+1];
        map = new ArrayList[N+1];
        depth = new int[N+1];

        for(int i=1; i<=N; i++){
            map[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map[a].add(new Node(b, w));
            map[b].add(new Node(a, w));
        }

        bfs(1);



        // make sparse table
        for(int i=1; i<=LogN; i++){
            for(int j=1; j<=N; j++){
                parents[i][j] = parents[i-1][parents[i-1][j]];
                max[i][j] = Math.max(max[i-1][j], max[i-1][parents[i-1][j]]);
                min[i][j] = Math.min(min[i-1][j], min[i-1][parents[i-1][j]]);
            }
        }

        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            getLCA(D, E);
            System.out.println(MinAnswer+" "+MaxAnswer);
        }





    }

    static void getLogN(){
        LogN = 0;
        for(int s=1; s<N; s*=2) LogN++;
    }

    static void bfs(int start){
        depth[start] = 0;

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(start, 0));
        while(!que.isEmpty()){
            Node now = que.poll();

            for(Node next:map[now.node]){
                // 아직 방문하지 않은
                if(next.node!=1 && depth[next.node]==0){
                    depth[next.node] = depth[now.node]+1;
                    parents[0][next.node] = now.node;
                    max[0][next.node] = next.distance;
                    min[0][next.node] = next.distance;
                    que.offer(new Node(next.node, next.distance));
                }
            }
        }
    }

    static int getLCA(int a, int b){
        if(depth[a]<depth[b]) {
            return getLCA(b, a);
        }

        MinAnswer = Integer.MAX_VALUE;
        MaxAnswer = 0;

        for(int i=0; i<=LogN; i++){
            if(((depth[a]-depth[b]) & (1<<i)) >= 1){
                MinAnswer = Math.min(MinAnswer, min[i][a]);
                MaxAnswer = Math.max(MaxAnswer, max[i][a]);
                a = parents[i][a];
            }
        }

        if(a==b){
            return a;
        }

        for(int i=LogN; i>=0; i--){
            if(parents[i][a]!=parents[i][b]) {
                MinAnswer = Math.min(MinAnswer, Math.min(min[i][a], min[i][b]));
                MaxAnswer = Math.max(MaxAnswer, Math.max(max[i][a], max[i][b]));
                a = parents[i][a];
                b = parents[i][b];
            }
        }
        MinAnswer = Math.min(MinAnswer, Math.min(min[0][a], min[0][b]));
        MaxAnswer = Math.max(MaxAnswer, Math.max(max[0][a], max[0][b]));
        return parents[0][a];
    }

}
