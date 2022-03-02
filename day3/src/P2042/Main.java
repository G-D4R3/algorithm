package P2042;

import java.io.*;
import java.util.*;

public class Main {
    static long[] data;
    static long[] tree;
    static long S;
    public static void main(String[] args) throws Exception {

        //System.setIn(new FileInputStream("src/P2042/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        data = new long[N];
        for(int i=0; i<N; i++) data[i] = Long.parseLong(br.readLine());

        S = 1;
        while(S<=N) S*=2;

        tree = new long[(int)(2*S+1)];
        init(1, (int)S, 1);

        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a==1){
                long diff = c-tree[(int)(b+S-1)];
                update(1, (int)S, 1, (int)b, diff);
            }
            else if(a==2){
                long res = query(1, (int)S, 1, (int)b, (int)c);
                System.out.println(res);
            }
        }


    }

    static void init(int left, int right, int node){
        // leaf 노드라면 데이터 저장
        if(left==right && (node-S)<data.length) tree[node] = data[(int)(node-S)];
        // 내부노드라면 자식의 합 저장
        else if(left<right){
            int mid = (left+right)/2;
            init(left, mid, node*2);
            init(mid+1, right, (node*2)+1);
            tree[node] = tree[2*node] + tree[2*node+1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){

        // 관련 없는 구간이라면 0 리턴
        if(queryLeft > right || queryRight < left) return 0;
        // 판단 가능한 구간
        else if( queryLeft <= left && right <= queryRight){
            return tree[node];
        }
        // 판단이 불가능해서 자식에게 넘기는 구간
        else {
            int mid = (left+right)/2;
            long leftRes = query(left, mid, node*2, queryLeft, queryRight);
            long rightRes = query(mid+1, right, 2*node+1, queryLeft, queryRight);
            return leftRes+rightRes;
        }

    }

    static void update(int left, int right, int node, int target, long diff){
        // 연관 없음
        if(target < left || target > right) return;
        // 연관 있음
        else {
            tree[node] += diff;
            if(left!=right) {
                int mid = (left+right)/2;
                update(left, mid, 2*node, target, diff);
                update(mid+1, right, 2*node+1, target, diff);
            }
        }
    }
}
