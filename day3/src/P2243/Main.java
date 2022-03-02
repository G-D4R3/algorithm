package P2243;

import java.io.*;
import java.util.*;

public class Main {
    static long[] tree; // 예제는 크기를 줄여서 해보고 제출할 때 크게 해서 제출
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int S = 1;
        while(S<1000000) S*=2;
        tree = new long[2*S+1];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(A==1){ // B번째 순위의 사탕을 꺼내는 경우
                // B맛의 사탕의 index를 구함
                int index = query(1, S, 1, B)-S+1;
                System.out.println(index);
                // B맛의 사탕을 하나 꺼냄
                update(1, S, 1, index, -1);
            }
            if(A==2){ // B정도의 맛의 사탕을 넣거나 빼는 경우
                int C = Integer.parseInt(st.nextToken());
                // B맛의 사탕을 C개 넣음
                update(1, S, 1, B, C);

            }


        }

    }

    static void update(int left, int right, int node, int target, int diff){
        if(target < left || target > right) return;
        else {
            tree[node] += diff;
            if(left!=right){
                int mid = (left+right)/2;
                update(left, mid, 2*node, target, diff);
                update(mid+1, right, 2*node+1, target, diff);
            }

        }
    }

    static int query(int left, int right, int node, long candy){
        // leafnode라면 node값과 비교
        if(left==right && tree[node]>=candy){
            return node;
        }
        else {
            int mid = (left+right)/2;
            long leftValue = tree[2*node];
            // 왼쪽에 있음
            if(leftValue >= candy){
                return query(left, mid, 2*node, candy);
            }
            // 오른쪽에 있음
            else {
                return query(mid+1, right, 2*node+1, candy-leftValue);
            }
        }
    }
}

