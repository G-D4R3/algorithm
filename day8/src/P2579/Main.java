package P2579;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] D;
    static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N+1];
        D = new int[N+1][2+1]; // 0: 안밝음. 1 : 직전 밟지않음. 2 : 직전 밟음
        for(int i=1; i<=N; i++) nums[i] = Integer.parseInt(br.readLine());
        for(int i=1; i<=N; i++){
            D[i][0] = Math.max(D[i-1][1], D[i-1][2]);
            D[i][1] = D[i-1][0] + nums[i];
            D[i][2] = D[i-1][1] + nums[i];
        }
        System.out.println(Math.max(D[N][1], D[N][2]));
    }
}
