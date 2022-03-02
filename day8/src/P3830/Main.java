package P3830;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parents;
    static long[] weights; // root와의 무게 차이 root는 나보다 weights[n]만큼 무겁다 (음수 가능)
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true){

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N==0 && M==0) break;

            parents = new int[N+1];
            weights = new long[N+1];

            for(int i=1; i<=N; i++) parents[i] =  i;

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                String query = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                // 교수님 질문
                if(query.equals("?")){

                    if(find(a)==find(b)){
                        // root와의 무게차이의 차이를 구한다
                        System.out.println(weights[b]-weights[a]);
                    }
                    else {
                        System.out.println("UNKNOWN");
                    }

                }

                // 무게 재기
                else if(query.equals("!")) {
                    int w = Integer.parseInt(st.nextToken());
                    // b가 a보다 w만큼 무겁다

                    union(a, b, w);
                }
            }
        }

    }

    static int find(int n){
        if(parents[n]==n) return n;
        else {
            int rootIndex = find(parents[n]);
            weights[n] += weights[parents[n]]; // 갱신하며 내려온다
            return parents[n] = rootIndex;
        }
    }

    static void union(int a, int b, long diff){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot==bRoot) return; // 불필요한 연산으로 값이 갱신되지 않도록 한다

        weights[bRoot] = weights[a] - weights[b] + diff; //
        parents[bRoot] = aRoot; // b를 a에 붙인다
    }
}
