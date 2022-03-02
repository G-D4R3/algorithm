package P2096;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        short[] map = new short[3];
        int[] Dmax = new int[3];
        int[] Dmin = new int[3];
        int[] tmp1 = new int[3];
        int[] tmp2 = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++){
            Dmax[i] = Dmin[i] = Short.parseShort(st.nextToken());
        }
        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                map[j] = Short.parseShort(st.nextToken());
            }
            tmp1[0] = map[0] + Math.max(Dmax[0], Dmax[1]);
            tmp1[1] = map[1] + Math.max(Math.max(Dmax[0], Dmax[1]), Dmax[2]);
            tmp1[2] = map[2] + Math.max(Dmax[1], Dmax[2]);
            tmp2[0] = map[0] + Math.min(Dmin[0], Dmin[1]);
            tmp2[1] = map[1] + Math.min(Math.min(Dmin[0], Dmin[1]), Dmin[2]);
            tmp2[2] = map[2] + Math.min(Dmin[1], Dmin[2]);
            for(int j=0; j<3; j++){
                Dmax[j] = tmp1[j];
                Dmin[j] = tmp2[j];
            }
        }

        int max = -1, min = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            max = Math.max(max, Dmax[i]);
            min = Math.min(min, Dmin[i]);
        }

        System.out.println(max+" "+min);
    }
}
