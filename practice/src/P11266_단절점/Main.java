package P11266_단절점;

import java.io.*;
import java.util.*;

public class Main {
    static int V, E, order;
    static int[] orders;
    static boolean[] isCutVertex;
    static ArrayList<Integer>[] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        orders = new int[V+1];
        isCutVertex = new boolean[V+1];
        map = new ArrayList[V+1];
        for(int i=1; i<=V; i++) map[i] = new ArrayList<>();

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            map[b].add(a);
        }

        order = 0;
        for(int i=1; i<=V; i++){
            if(orders[i]==0){
                dfs(i, true);
            }
        }

        int answer = 0;
        ArrayList<Integer> answerList = new ArrayList<>();
        for(int i=1; i<=V; i++) {
            if(isCutVertex[i]){
                answer ++;
                answerList.add(i);
            }
        }
        System.out.println(answer);
        if(answer>0){
            for(int a:answerList) System.out.print(a+" ");
            System.out.println("");
        }

    }

    static int dfs(int node, boolean isRoot){
        orders[node] = ++order;
        int rtn = order;
        int child = 0;

        // 연결된 곳
        for(int next:map[node]){

            // 갈 수 있는 곳
            if(orders[next]==0){
                child++;
                int low = dfs(next, false);

                if(!isRoot && low >= orders[node]){
                    isCutVertex[node] = true;
                }
                rtn = Math.min(low, rtn);
            }
            else {
                rtn = Math.min(rtn, orders[next]);

            }
        }

        if(isRoot && child>=2) isCutVertex[node] = true;

        return rtn;
    }
}
