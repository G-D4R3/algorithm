package P3055_탈출;

import java.io.*;
import java.util.*;

class Point{
    int r;
    int c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] times;
    static boolean[][] visit;
    static Point S, D;
    static final int[] mc = {0, 0, 1, -1};
    static final int[] mr = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visit = new boolean[R][C];
        times = new int[R][C];
        int startR = 0;
        int startC = 0;
        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j]=='S') {
                    S = new Point(i, j);
                }
                if(map[i][j]=='D'){
                    D = new Point(i, j);
                }
            }
        }


        bfs();
        if(times[D.r][D.c]>0) System.out.println(times[D.r][D.c]-1);
        else System.out.println("KAKTUS");

    }

    static void bfs(){
        // 체크인
        times[S.r][S.c] = 1;


        // water 먼저 넣기
        Queue<Point> queue = new LinkedList<>();
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j]=='*'){
                    queue.offer(new Point(i, j));
                }
            }
        }
        int waterSize = queue.size();
                // 고슴도치 시작점
        queue.offer(S);

        int hedgeHogSize = 1;


        while(!queue.isEmpty()){
            int temp = 0;
            for(int w=0; w<waterSize; w++){
                Point now = queue.poll();
                for(int i=0; i<4; i++){
                    if((now.r+mr[i] >= 0) && (now.r+mr[i]<R) && (now.c+mc[i]>=0) && (now.c+mc[i]<C) && map[now.r+mr[i]][now.c+mc[i]]=='.'){
                        map[now.r+mr[i]][now.c+mc[i]] = '*';
                        queue.offer(new Point(now.r+mr[i], now.c+mc[i]));
                        temp++;
                    }
                }
            }
            waterSize = temp;

            temp = 0;
            for(int h=0; h<hedgeHogSize; h++){
                Point now = queue.poll();
                if(now.r == D.r && now.c == D.c) return;
                for(int i=0; i<4; i++){
                    if((now.r+mr[i] >= 0) && (now.r+mr[i]<R) && (now.c+mc[i]>=0) && (now.c+mc[i]<C) && (map[now.r+mr[i]][now.c+mc[i]]=='.' || map[now.r+mr[i]][now.c+mc[i]]=='D')&& times[now.r+mr[i]][now.c+mc[i]]==0){
                        times[now.r+mr[i]][now.c+mc[i]] = times[now.r][now.c]+1;
                        queue.offer(new Point(now.r+mr[i], now.c+mc[i]));
                        temp++;
                    }
                }
            }

            hedgeHogSize = temp;
        }


    }
}
