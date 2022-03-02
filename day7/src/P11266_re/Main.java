package P11266_re;

import java.io.*;
import java.util.*;


public class Main {
    static int V, E;
    static ArrayList<Integer>[] map;
    static boolean[] isAP;
    static int order, answer;
    static int[] low, entry_time;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new ArrayList[V+1];
        for(int i=1; i<=V; i++) map[i] = new ArrayList<>();

        isAP = new boolean[V+1];
        low = new int[V+1];
        entry_time = new int[V+1];
        order = answer = 0;

        for(int e=0; e<E; e++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        for(int i=1; i<=V; i++){
            if(entry_time[i]==0){
                dfs(i, true);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=V; i++){
            if(isAP[i]){
                answer++;
                sb.append(i+" ");
            }
        }
        System.out.println(answer);
        if(answer>0) System.out.println(sb.toString());

    }

    static int dfs(int start, boolean isRoot){
        entry_time[start] = ++order;
        int rtn = order;
        int child = 0;

        Queue<Integer> que = new LinkedList<>(map[start]);

        while(!que.isEmpty()){
            int next = que.poll();

            // next가 start의 child
            if(entry_time[next]==0) {
                child++;

                int low = dfs(next, false);

                // next가 start를 거치지 않고서 그 위의 조상으로 향할 수 없다
                // 현재 low는 start를 거치지 않고 갈 수 있는 노드의 최소 order
                if(isRoot == false && entry_time[start] <= low){
                    isAP[start] = true;
                }
                rtn = Math.min(rtn, low);
            }
            // next가 start의 child가 아니고 조상
            // 그렇다면 start의 low는 order과 entry_time[next] 중 최솟값
            else {
                rtn = Math.min(rtn, entry_time[next]);
            }

        }

        if(isRoot && child>=2){
            isAP[start] = true;
        }
        return rtn;

    }
}
