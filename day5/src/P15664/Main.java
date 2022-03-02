package P15664;
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static int[] cards;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        cards = new int[N];
        for(int i=0; i<N; i++) cards[i] = Integer.parseInt(st.nextToken());
        visit = new boolean[N];


        Arrays.sort(cards);

        combination("", 0);

        for(String s:set){
            System.out.println(s);
        }

    }

    static void combination(String s, int r){
        if(r==K){
            s = s.substring(0, s.length()-1);
            set.add(s);
            return;
        }
        else {
            for(int i=0; i<N; i++){
                if(!visit[i]){
                    String[] str = s.split(" ");

                    if(str[0].length()>0){
                        int last = Integer.parseInt(str[str.length-1]);

                        if(last <= cards[i]){
                            visit[i] = true;
                            combination(s+cards[i]+" ", r+1);
                            visit[i] = false;
                        }
                    }
                    else {
                        visit[i] = true;
                        combination(s+cards[i]+" ", r+1);
                        visit[i] = false;
                    }
                }
            }
        }
    }
}
