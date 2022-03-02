package P5568;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static HashSet<String> set = new HashSet<>();
    static String[] cards;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        cards = new String[N];
        for(int i=0; i<N; i++) cards[i] = br.readLine();
        visit = new boolean[N];
        combination("", 0);

        System.out.println(set.size());
    }

    static void combination(String s, int r){
        if(r==K){
            set.add(s);
            return;
        }
        else {
            for(int i=0; i<N; i++){
                if(!visit[i]){
                    visit[i] = true;
                    combination(s+cards[i], r+1);
                    visit[i] = false;
                }
            }
        }
    }
}
