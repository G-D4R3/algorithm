package P1722;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long K;
    static long[] factorial;
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static int[] cards;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        factorial = new long[N+1];

        factorial[0] = factorial[1] = 1;
        for(int i=2; i<=N; i++) factorial[i] = factorial[i-1]*i;

        ArrayList<Integer> nums = new ArrayList<>();
        for(int i=1; i<=N; i++) nums.add(i);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int command = Integer.parseInt(st.nextToken());

        // K번째 순열 구하기
        if(command==1){
            StringBuilder sb = new StringBuilder();
            K = Long.parseLong(st.nextToken());

            for(int i=0; i<N; i++){
                for(int j=0; j<nums.size(); j++){
                    long temp = factorial[N-i-1];
                    if(temp < K ) K-= temp;
                    else {
                        sb.append(nums.get(j)+" ");
                        nums.remove(j);
                        break;
                    }
                }
            }

            System.out.println(sb.toString());
        }
        // 순열의 순서 구하기
        else if(command==2){
            ArrayList<Integer> input = new ArrayList<>();
            for (int i=0; i<N; i++) input.add(Integer.parseInt(st.nextToken()));
            long result = 0;
            for(int i=0; i<N; i++){
                for(int j=1; j<input.get(0); j++){
                    if(nums.contains(j))
                        result += factorial[N-i-1];
                }
                nums.remove(input.get(0));
                input.remove(0);
            }


            System.out.println(result+1);
        }



    }

}
