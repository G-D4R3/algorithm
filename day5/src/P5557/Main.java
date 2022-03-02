package P5557;

import java.io.*;
import java.util.*;

public class Main {
    static int[] nums;
    static String[] operator = {"+", "-"};
    static long count = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());
        combination(0, 0);
        System.out.println(count);
    }


    static void combination(int now, int index){
        if(index == nums.length-1) {
            if(now==nums[index]){
                count++;
            }
            return;
        }
        else {
            int plus = now + nums[index];
            int minus = now - nums[index];
            if( plus<=20 ) combination(plus, index+1);
            if( minus >= 0 ) combination(minus, index+1);
            return;
        }

    }

}