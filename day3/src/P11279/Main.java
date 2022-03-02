package P11279;

import java.io.*;
import java.util.*;

public class Main {
    static int size;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> que = new PriorityQueue<>((o1,o2)->o2-o1);

        for(int i=0; i<N; i++){
            int input = Integer.parseInt(br.readLine());
            if(input==0){
                if(!que.isEmpty()) System.out.println(que.poll());
                else System.out.println(0);
            }
            else que.offer(input);

        }

    }
}
