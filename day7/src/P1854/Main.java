package P1854;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
    int node;
    int distance;

    public Edge(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }


    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.distance, o.distance);
    }
}

public class Main {
    static int N, M, K;
    static ArrayList<Edge>[] map;
    static PriorityQueue<Integer>[] distances;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        distances = new PriorityQueue[N+1];
        map = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            distances[i] = new PriorityQueue<>(Collections.reverseOrder());
            map[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map[a].add(new Edge(b, w));
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(1, 0));
        distances[1].add(0);

        while(!queue.isEmpty()){
            Edge e = queue.poll();

            if(e.distance > distances[e.node].peek()) continue;

            for(Edge next: map[e.node]){
                if(distances[next.node].size() < K){
                    distances[next.node].offer(e.distance+next.distance);
                    queue.add(new Edge(next.node, e.distance+next.distance));
                }
                else {
                    if(distances[next.node].peek() > e.distance+next.distance){
                        distances[next.node].poll();
                        distances[next.node].add(e.distance+next.distance);
                        queue.add(new Edge(next.node, e.distance+next.distance));
                    }
                }
            }
        }

        for(int i=1; i<=N; i++){
            if(distances[i].size()==K) System.out.println(distances[i].peek());
            else System.out.println("-1");
        }


    }
}
