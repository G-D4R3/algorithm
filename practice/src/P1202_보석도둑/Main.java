package P1202_보석도둑;

import java.io.*;
import java.util.*;

class Jewel implements Comparable<Jewel>{
    int m;
    int v;

    public Jewel(int m, int v) {
        this.m = m;
        this.v = v;
    }

    @Override
    public int compareTo(Jewel o) {
        return Integer.compare(this.m, o.m);
    }
}

public class Main {
    static int N, K;
    static ArrayList<Jewel> jewels;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new ArrayList<>();
        ArrayList<Integer> bags = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewels.add(new Jewel(m, v));
        }

        for(int i=0; i<K; i++) bags.add(Integer.parseInt(br.readLine()));

        Collections.sort(bags);
        Collections.sort(jewels);

        long res = 0;
        int p1 = 0;
        int p2 = 0;

        PriorityQueue<Jewel> jw = new PriorityQueue<>(new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return Integer.compare(o2.v, o1.v);
            }
        });

        while(p1<K){
            while(p2<N){
                if(bags.get(p1)>=jewels.get(p2).m){
                    jw.offer(jewels.get(p2));
                    p2++;
                }
                else break;
            }
            if(!jw.isEmpty()) res += jw.poll().v;
            p1++;
        }

        System.out.println(res);
    }
}