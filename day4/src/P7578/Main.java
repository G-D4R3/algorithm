package P7578;

import java.io.*;
import java.util.*;

public class Main {
    static int[] A;
    static HashMap<Integer, Integer> B;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new HashMap<>();
        S = 1;
        while(S<N) S*=2;
        tree = new long[2*S+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            B.put(Integer.parseInt(st.nextToken()), i);
        }

        long res = 0;
        for(int i=0; i<N; i++){
            int b = B.get(A[i]);
            // index b update
            update(1, S, 1, b+1, 1);
            // j+1 ~ S 까지 query
            res += query(1, S, 1, b+2, S);
        }
        System.out.println(res);
    }

    static void update(int left, int right, int node, int target, int diff){
        if(target < left || target > right) return;
        else{
            tree[node] += diff;
            if(left!=right){
                int mid = (left+right)/2;
                update(left, mid, 2*node, target, diff);
                update(mid+1, right, 2*node+1, target, diff);
            }
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
        if(queryLeft > right || queryRight < left) return 0;
        else if(queryLeft <= left && queryRight >= right) return tree[node];
        else {
            int mid = (left+right)/2;
            long leftRes = query(left, mid, 2*node, queryLeft, queryRight);
            long rightRes = query(mid+1, right, 2*node+1, queryLeft, queryRight);
            return leftRes+rightRes;
        }
    }
}
