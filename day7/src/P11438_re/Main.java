package P11438_re;

import java.io.*;
import java.util.*;

public class Main {
    static int N, LogN, M;
    static ArrayList<Integer>[] map;
    static int[] depth;
    static int[][] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        getK();

        map = new ArrayList[N+1];
        depth = new int[N+1];
        parents = new int[LogN+1][N+1];

        for(int i=1; i<=N; i++) map[i] = new ArrayList<>();

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }


        bfs(1);
        makeParents();

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(getLCA(a, b));
        }
    }

    static void getK(){
        LogN = 0;
        for(int i=1; i<N; i*=2) LogN++;
    }

    static void bfs(int start){

        Queue<Integer> que = new LinkedList<>();
        depth[start] = 1;
        que.add(start);
        while(!que.isEmpty()){
            int now = que.poll();
            for(int next:map[now]){
                if(depth[next]==0) {
                    parents[0][next] = now;
                    depth[next] = depth[now]+1;
                    que.add(next);
                }
            }
            // 아직 방문하지 않은 노드

        }
    }

    static void makeParents(){
        for(int i=1; i<=LogN; i++){
            for(int j=1; j<=N; j++){
                parents[i][j] = parents[i-1][parents[i-1][j]];
            }
        }
    }

    static int getLCA(int a, int b){
        if(depth[a] < depth[b]) return getLCA(b, a); // depth가 깊은 쪽을 a로

        // 깊이 맞추기
        for(int i=0; i<=LogN; i++){
            // depth차이가 1이상일 때까지 올림.
            // depth 차이가 1이라면 마지막으로 올리게 되므로 depth차이가 0이 됨
            if(((depth[a]-depth[b]) & (1<<i)) >=1) {
                a = parents[i][a];
            }
        }

        if(a==b) return a;

        for(int i=LogN; i>=0; i--){
            // 올려서 같다(root일 확률 다분) -> 넘김.
            // 올려서 다르다 -> 같을 때까지 올림
            if(parents[i][a]!=parents[i][b]){
                a = parents[i][a];
                b = parents[i][b];
            }
        }


        return parents[0][a];

    }
}
