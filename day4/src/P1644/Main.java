package P1644;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> primes = new ArrayList<>();

        boolean[] isNotPrime = new boolean[N+1];
        isNotPrime[0] = isNotPrime[1] = true;
        for(int i=2; i<=N; i++){
            if(!isNotPrime[i]){
                primes.add(i);
                for(int j=i*2; j<=N; j+=i){
                    isNotPrime[j] = true;
                }
            }
        }
        int count = 0;
        int p1 = 0, p2 = 0;
        while(p1<primes.size()){
            int sum = 0;
            while(p2<primes.size()){
                sum += primes.get(p2);
                if(sum > N) {
                    p1++;
                    p2 = p1;
                    break;
                }
                else if(sum ==  N){
                    count ++ ;
                    p1 ++;
                    p2 = p1;
                    break;
                }
                else {
                    p2++;
                }
            }
            if((p1+1)==primes.size()&&p2==primes.size()) {
                break;
            }
        }

        System.out.println(count);

    }
}
