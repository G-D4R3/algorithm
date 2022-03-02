package P1837;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        String P = st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        boolean[] isNotPrime = new boolean[K+1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;


        for(int i=2; i<=K; i++){
            for(int j=i+i; j<=K; j+=i){
                isNotPrime[j] = true;
            }
        }


        boolean flag = false;
        for(int i=2; i<K; i++){
            if(!isNotPrime[i] && div(P, i)) {
                System.out.println("BAD "+i);
                return;
            }
        }

        System.out.println("GOOD");
    }

    static boolean div(String P, int K){
        String s = "";
        int index = 0;

        while(index<P.length()){
            s += P.charAt(index);
            int temp = Integer.parseInt(s);
            if(temp<K){
                index++;
                continue;
            }
            else {
                temp %= K;
                if(temp==0) s = "";
                else s = String.valueOf(temp);
            }
            index++;
        }

        if(s.length()<1) return true;
        else return false;
    }
}
