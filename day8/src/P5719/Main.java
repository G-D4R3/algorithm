package P5719;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int node;
    int distance;

    public Node(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.distance, o.distance);
    }
}

public class Main {

    static int N, M, S, D;
    static int[] distances;
    static ArrayList<Integer>[] shortestPath;
    static ArrayList<Node>[] map;
    static boolean[][] isShortest;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N==0 && M==0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            distances = new int[N];
            map = new ArrayList[N];
            shortestPath = new ArrayList[N];
            isShortest = new boolean[N][N];
            for(int i=0; i<N; i++) {
                shortestPath[i] = new ArrayList<>();
                map[i] = new ArrayList<>();
            }

            int a, b, w;
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                map[a].add(new Node(b, w));
            }


            dijkstra();
            checkPath(D);
            dijkstra();

            if(distances[D]!=Integer.MAX_VALUE) System.out.println(distances[D]);
            else System.out.println("-1");
        }
    }

    static void checkPath(int now){
        if(now==S) return;
        else {
            for(int next:shortestPath[now]){
                if(!isShortest[next][now]){
                    isShortest[next][now] = true;
                    checkPath(next);
                }
            }
        }
    }

    static void dijkstra() {


        for (int i = 0; i < N; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        // 최단거리, 경로 구하기
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));
        distances[S] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.distance > distances[now.node]) continue;

            for(Node next:map[now.node]){
                if(!isShortest[now.node][next.node]) {
                    if(distances[next.node] == distances[now.node] + next.distance){
                        shortestPath[next.node].add(now.node);
                    }
                    else if(distances[next.node] > distances[now.node] + next.distance){
                        distances[next.node] = distances[now.node] + next.distance;
                        shortestPath[next.node].clear();
                        shortestPath[next.node].add(now.node);
                        pq.offer(new Node(next.node, distances[next.node]));
                    }
                }
            }
        }
    }
}
