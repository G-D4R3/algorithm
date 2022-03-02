package P14003;

import java.io.*;
import java.util.*;

public class Main {
    static int[] nums, indexOrder, D;
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N+1];
        indexOrder = new int[N+1];
        D = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) nums[i] = Integer.parseInt(st.nextToken());

        D[1] = nums[1];
        int length = 1;
        indexOrder[1] = 1;

        for(int i=2; i<=N; i++){
            int index = binarySearch(1, length, nums[i]);
            indexOrder[i] = index;
            if(index > length) {
                length++;
                D[length] = nums[i];
            }
            else {
                D[index] = nums[i];
            }

        }

        int[] res = new int[N+1];
        int answer = length;

        for(int i=N; i>=1; i--){
            if(length==indexOrder[i]){
                res[length] = nums[i];
                length--;
            }
        }


        System.out.println(answer);
        if(answer>0){
            for(int i=1; i<=answer; i++) System.out.print(res[i]+" ");
        }


    }

    static int binarySearch(int start, int end, int num){
        int low = start;
        int high = end;

        while(low<=high){
            int mid = (low+high)/2;
            if(D[mid]>num){
                high = mid-1;
            }
            else if(D[mid]<num){
                low = mid+1;
            }
            else return mid;
        }

        return low;

    }
}
