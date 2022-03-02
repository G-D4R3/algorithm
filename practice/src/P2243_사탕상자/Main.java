package P2243_사탕상자;

import java.io.*;
import java.util.*;

public class Main {
    static int S;
    static int[] tree;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        S = 1;
        while(S<=1000000) S*=2;

        tree = new int[2*S+1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            if(A==1){ // 꺼내는 경우
                int B = Integer.parseInt(st.nextToken());
                System.out.println(query(1, S, 1, B)-S+1);
                update(1, S, 1, B,-1);

            }
            else if(A==2) { // 넣는 경우. c가 음수면 꺼냄.
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                update(1, S, 1, B, C);
            }
        }

    }
    static int query(int left, int right, int node, int target){
        if(left==right && target<=tree[node]) return node;
        else {
            int mid = (left+right)/2;
            if(tree[2*node]>=target) {
                return query(left, mid, 2*node, target);
            }
            else {
                return query(mid+1, right, 2*node+1, target-tree[2*node]);
            }
        }
    }

    static void update(int left, int right, int node, int target, int diff){
        if(target < left || target > right ) return;
        else {
            tree[node] += diff;
            if(left!=right){
                int mid = (left+right)/2;
                update(left, mid, 2*node, target, diff);
                update(mid+1, right, 2*node+1, target, diff);
            }

        }
    }
}