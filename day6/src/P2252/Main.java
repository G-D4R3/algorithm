package P2252;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegrees = new int[N+1];
        ArrayList<Integer>[] map = new ArrayList[N+1];
        boolean[] visit = new boolean[N+1];

        for(int i=0; i<=N; i++) map[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a].add(b);
            inDegrees[b]++;
        }

        Queue<Integer> que = new LinkedList<>();

        for(int i=1; i<=N; i++) {
            if(inDegrees[i]==0){
                que.offer(i);
                visit[i] = true;
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!que.isEmpty()) {
            int t = que.poll();
            sb.append(t+" ");
            for(int i=0; i<map[t].size(); i++) {
                if(visit[map[t].get(i)]==false){
                    inDegrees[map[t].get(i)] -- ;
                    if(inDegrees[map[t].get(i)]==0) {
                        que.offer(map[t].get(i));
                        visit[map[t].get(i)] = true;
                    }
                }
            }

        }
        System.out.println(sb.toString().substring(0, sb.length()-1));
    }
}
