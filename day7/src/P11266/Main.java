package P11266;

import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static boolean[] discovered, isCutVertex;
    static int[] low, entry_time;
    static int order, answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        Queue<Integer>[] map = new Queue[V+1];

        for(int i=1; i<=V; i++) map[i] = new LinkedList<>();

        discovered = new boolean[V+1];
        isCutVertex = new boolean[V+1];
        low = new int[V+1];
        entry_time = new int[V+1];

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a].offer(b);
            map[b].offer(a);
        }
        order = 0;
        answer = 0;

        for(int i=1; i<=V; i++){
            if(entry_time[i]==0){
                dfs(map, i, true);
            }
        }


        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=V; i++){
            if(isCutVertex[i]) {
                answer++;
                sb.append(i+" ");
            }
        }
        bw.write(answer+"\n");
        if(answer>0) bw.write(sb.toString()+"\n");
        bw.flush();
        bw.close();
    }

    public static int dfs(Queue<Integer>[] map, int start, boolean isRoot){

        discovered[start] = true;
        entry_time[start] = ++order;
        int rtn = order;
        int child = 0;


        Queue<Integer> adj = new LinkedList<>(map[start]);
        while(!adj.isEmpty()){
            int next = adj.poll();

            if(entry_time[next]==0){
                child++;

                int low = dfs(map, next, false);

                if(isRoot == false && entry_time[start] <= low){
                    isCutVertex[start] = true;
                }
                rtn = Math.min(rtn, low);
            }
            else {
                rtn = Math.min(rtn, entry_time[next]);
            }
        }

        if(isRoot == true && child>=2){
            isCutVertex[start] = true;
        }
        return rtn;
    }
}
