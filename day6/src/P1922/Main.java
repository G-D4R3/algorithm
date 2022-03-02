package P1922;

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
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1; i<=N; i++) parent[i] = i;

        ArrayList<Edge> edges = new ArrayList<>();

        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }

        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        int res = 0;

        while(!edges.isEmpty()){
            Edge edge = edges.remove(0);
            if(find(edge.a)!=find(edge.b)){
                union(edge.a, edge.b);
                res += edge.weight;
            }
        }

        System.out.println(res);



    }

    static int find(int n){
        if(parent[n] == n) return n;
        else return parent[n] = find(parent[n]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        parent[aRoot] = bRoot;
    }

}