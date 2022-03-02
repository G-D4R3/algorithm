package P2143;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[n];
        for(int i=0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] B = new int[m];
        for(int i=0; i<m; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> subA = new ArrayList<>();
        ArrayList<Integer> subB = new ArrayList<>();

        for(int i=0; i<n; i++){
            int sum = 0;
            for(int j=i; j<n; j++){
                sum += A[j];
                subA.add(sum);
            }
        }
        for(int i=0; i<m; i++){
            int sum = 0;
            for(int j=i; j<m; j++){
                sum += B[j];
                subB.add(sum);
            }
        }
        Collections.sort(subA);
        Collections.sort(subB);

        int p1 = 0, p2 = subB.size()-1;
        long count = 0;

        while(p1<subA.size() && p2>=0){
            int sum = subA.get(p1)+subB.get(p2);
            if(sum==T){
                int a = subA.get(p1);
                int b = subB.get(p2);
                long count1 = 0, count2 = 0;
                while(p1<subA.size() && subA.get(p1)==a){
                    count1++;
                    p1++;
                }
                while(p2>=0 && subB.get(p2)==b){
                    count2++;
                    p2--;
                }
                count += count1*count2;
            }
            else if(sum<T){
                p1++;
            }
            else if(sum>T){
                p2--;
            }
        }
        System.out.println(count);

    }
}
