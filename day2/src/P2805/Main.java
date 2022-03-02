package P2805;

import java.util.*;
import java.io.*;

public class Main {
    long[] tree;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        long max = Long.MIN_VALUE;
        long[] tree = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            tree[i] = Long.parseLong(st.nextToken());
            max = Math.max(tree[i], max);
        }

        long low = 0, high = max, mid = (low+high)/2;
        while(low<=high){
            long res = 0;
            mid = (low+high)/2;
            for(int i=0; i<N; i++) {
                if(tree[i]-mid>0) res+=(tree[i]-mid);
            }
            if(res<M){
                high = mid-1;
            }
            else if(res>=M){
                low = mid+1;
            }
        }
        System.out.println(high);


        // 풀이
        while(true){
            mid = (low+high)/2;
            long sum = calc(mid);

            // sum == M -> 정답, 탈출
            if(sum==M){
                result = mid;
                break;
            }
            // sum < M -> mid -> end
            else if(sum < M){
                high = mid-1;
            }
            // sum > M -> mid -> s, 정답 후보
            else {
                low = mid+1;
                result = mid; // result에 미리 기록
            }
            // 못 찾았을 때
            if(low>high) break;
        }
    }

    static long calc(long value){
        long result = 0;
        for(int i=0; i<trees.length; i++){
            int tree = trees[i];
            if(tree>value){
                result += tree-value;
            }
        }
        return result;
    }

}
