package P1202;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jw = new Jewel[N];
        int[] bags = new int[K];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            jw[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<K; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 가방 오름 차순 정렬
        Arrays.sort(bags);
        // 보석 무게 오름차순 정렬
        Arrays.sort(jw, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return o1.weight-o2.weight;
            }
        });

        // 보석 가격이 높은 값 기준 힙
        PriorityQueue<Jewel> pq = new PriorityQueue<>();

        long result = 0;
        int p1 = 0, p2 = 0;

        // 1. 남은 가방 중 제일 작은 가방 선택 <-
        while(p1<K) {
            // 2. 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택 <- 힙 사용
            while(p2<N) {
                if(bags[p1]>=jw[p2].weight){
                    pq.offer(jw[p2]);
                    p2++;
                }
                else break;
            }
            if(!pq.isEmpty()) result += pq.poll().price;
            p1++;

        }
        System.out.println(result);
    }
}

class Jewel implements Comparable<Jewel>{
    int weight;
    int price;

    public Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "weight=" + weight +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Jewel o) {
        return o.price-this.price;
    }
}
