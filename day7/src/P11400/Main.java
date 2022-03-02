package P11400;

import java.io.*;
import java.util.*;

class Edge {
    int a;
    int b;

    public Edge(int a, int b) {
        this.a = Math.min(a, b);
        this.b = Math.max(a, b);
    }
}
public class Main {
    static int V, E;
    static ArrayList<Integer>[] map;
    static ArrayList<Edge> bridges;
    static int order, answer;
    static int[] low, entry_time, parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList[V+1];
        for(int i=1; i<=V; i++) map[i] = new ArrayList<>();

        bridges = new ArrayList<>();
        low = new int[V+1];
        entry_time = new int[V+1];
        parents = new int[V+1];
        order = answer = 0;

        for(int e=0; e<E; e++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        dfs(1, 0);

        Collections.sort(bridges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.a==o2.a) return Integer.compare(o1.b, o2.b);
                return Integer.compare(o1.a, o2.a);
            }
        });

        System.out.println(bridges.size());
        for(Edge e: bridges) System.out.println(e.a+" "+e.b);

    }

    static int dfs(int start, int parent){
        entry_time[start] = ++order;
        int rtn = entry_time[start];

        Queue<Integer> que = new LinkedList<>(map[start]);

        while(!que.isEmpty()){
            int next = que.poll();
            if(next==parent) continue;

            // next가 start의 child
            if(entry_time[next]==0) {

                int low = dfs(next, start);

                // next가 start를 거치지 않고서 그 위의 조상으로 향할 수 없다
                // 현재 low는 start를 거치지 않고 갈 수 있는 노드의 최소 order
                if(entry_time[start] < low){
                    bridges.add(new Edge(start, next));
                }
                rtn = Math.min(rtn, low);
            }
            // next가 start의 child가 아니고 조상
            // 그렇다면 start의 low는 order과 entry_time[next] 중 최솟값
            else {
                rtn = Math.min(rtn, entry_time[next]);
            }

        }

        return rtn;

    }
}
