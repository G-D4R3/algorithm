package P2960;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        boolean[] isNotPrime = new boolean[A+1];
        isNotPrime[0] = isNotPrime[1] = true;

        int count = 0;

        for(int i=2; i<=A; i++){
            if(isNotPrime[i]==false){
                count++;
                if(count==B){
                    System.out.println(i);
                    return;
                }
            }
            for(int j=2*i; j<=A; j+=i){
                if(isNotPrime[j]==false){
                    isNotPrime[j] = true;
                    count++;
                    if(count==B){
                        System.out.println(j);
                        return;
                    }
                }

            }
        }


    }
}
