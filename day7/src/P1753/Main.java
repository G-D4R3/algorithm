package P1753;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int node;
    int weight;

    public Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Main {
    static int V, E;
    static int[] distances;
    static ArrayList<Node>[] map;

    public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList[V+1];
        distances = new int[V+1];

        for(int i=1; i<=V; i++){
            map[i] = new ArrayList<>();
            distances[i] = Integer.MAX_VALUE;
        }
        int start = Integer.parseInt(br.readLine());
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[a].add(new Node(b, w));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.offer(new Node(start, 0));
        distances[start] = 0;

        while(!queue.isEmpty()){
            Node n = queue.poll();

            // 현재 최단 경로보다 현재 노드에서 n으로 가는 거리가 더 길다면 계산할 필요가 없다
            if(n.weight > distances[n.node]) continue;

            // 인접 노드 찾기
            for(Node v:map[n.node]){
                // 현재 v까지의 최단 경로보다 n을 거쳐서 가는 것이 빠르다면
                // 거리를 갱신하고 queue에 넣는다
                if(distances[v.node] > distances[n.node]+v.weight) {
                    distances[v.node] = distances[n.node]+v.weight;
                    queue.offer(new Node(v.node, distances[v.node]));
                }
            }
        }

        for(int i=1; i<=V; i++){
            if(distances[i]==Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(distances[i]);
        }

    }
}
