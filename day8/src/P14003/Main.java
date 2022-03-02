package P14003;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A, D, parents;
    static long max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        D = new int[N+1];
        parents = new int[N+1];
        int maxLength = 0;
        int lastIndex = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) A[i] = Integer.parseInt(st.nextToken());

        maxLength = lastIndex = 1;

        for(int i=1; i<=N; i++){
            if(D[i]==0) D[i] = 1;
            for(int j=1; j<i; j++){
                if(A[i] > A[j]) {
                    if(D[i] < D[j] + 1){
                        lastIndex = i;
                        D[i] = D[j] + 1;
                        parents[i] = j;
                    }
                }
            }
        }

        Stack<Integer> res = new Stack<>();
        while(lastIndex!=0){
            res.push(A[lastIndex]);
            lastIndex = parents[lastIndex];
        }


        System.out.println(res.size());
        while(!res.isEmpty()) System.out.print(res.pop()+" ");

        /*for(int p:parents) System.out.print(p+" ");
        System.out.println();
        for(int d:D) System.out.print(d+" ");*/
    }


}
