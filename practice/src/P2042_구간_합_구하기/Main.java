package P2042_구간_합_구하기;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, S;
    static long[] indexTree, nums;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        getS();
        indexTree = new long[2*S+1];


        nums = new long[N+1];
        for(int i=1; i<=N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        initIndexTree(1, S, 1);

        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a==1) { // update
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                long diff = c - indexTree[b+S-1];

                update(1, S, 1, b, diff);

            }
            else if(a==2) { // query
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                long answer = query(1, S, 1, b, c);
                System.out.println(answer);
            }
        }

    }

    static void getS(){
        S = 1;
        while(S<=N) S*=2;
    }

    static void initIndexTree(int left, int right, int node){
        if(left==right & (node-S+1)<nums.length) indexTree[node] = nums[node-S+1];
        else if(left<right){ // 이분 탐색 할 때도 중요함
            int mid = (left+right)/2;
            initIndexTree(left, mid, 2*node);
            initIndexTree(mid+1, right, 2*node+1);
            indexTree[node] = indexTree[2*node]+indexTree[2*node+1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight){
        // 완전히 벗어난 구역
        if(queryRight<left || queryLeft > right) return 0;

        // 판단 가능한 구역
        else if(queryLeft <= left && queryRight >= right) {
            return indexTree[node];
        }
        // 판단 불가능한 구역
        else {
            int mid = (left+right)/2;
            long leftQuery = query(left, mid, 2*node, queryLeft, queryRight);
            long rightQuery = query(mid+1, right, 2*node+1, queryLeft, queryRight);
            return leftQuery+rightQuery;
        }

    }

    static void update(int left, int right, int node, int target, long diff){
        if(left>target || right<target) return;
        else {
            indexTree[node] += diff;
            if(left!=right){
                int mid = (left+right)/2;
                update(left, mid, 2*node, target, diff);
                update(mid+1, right, 2*node+1, target, diff);
            }
        }
    }
}