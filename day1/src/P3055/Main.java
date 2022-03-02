package P3055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
    int r;
    int c;
    public Point(int r, int c){
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int R, C;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Point S = null, D = null;
        char[][] map = new char[R][C];
        int[][] check = new int[R][C];
        ArrayList<Point> water = new ArrayList<>();

        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j]=='S'){
                    S = new Point(i, j);
                }
                if(map[i][j]=='D'){
                    D = new Point(i, j);
                }
                if(map[i][j]=='*'){
                    water.add(new Point(i,j));
                }
            }
        }
        bfs(S, D, map, check, water);

    }


    // 1. 큐에서 꺼내옴
    // 2. 목적지 : 비버굴 D
    // 3. 연결된 곳 순회 : 일단 좌 우 위 아래가 다 가능하다고 생각
    // 4. 갈 수 있는가? : 맵 영역을 넘어가는 지, 돌이나 물이 아닌지(.인데 물이 인접해있지 않은), 방문하지 않은 곳,
    // 5. 체크인 (T/F) : 근데 time을 결과로 원하기 때문에 배열에 시간을 기록해 둠. 지도와 같은 사이즈의 int 배열을 하나 만들어 체크인 용도로 사용
    // 6. 큐에 넣음

    // 고슴도치 뿐만 아니라 물도 이동. 물은 고슴도치와 다르게 목적지가 없음. 연결된 곳을 모두 감.
    // 갈 수 있는 곳 : 돌이 아닌 곳, 맵 영역 안, (.)
    // 체크인은 맵에 그냥 *로
    // 큐에 고슴도치 이동, 물 이동 같이
    // 근데 큐에 물을 먼저 넣으면 다음 물이 들어올 . 을 따로 체크하지 않아도 됨
    static void bfs(Point S, Point D, char[][] map, int[][] check, ArrayList<Point> water){

        int[] mc = {-1, 1, 0, 0};
        int[] mr = {0, 0, -1, 1};

        Queue<Point> que = new LinkedList<>();
        for(Point p:water) que.offer(p);
        que.offer(S);
        int time = 1;
        check[S.r][S.c] = time;

        int w = water.size();
        int s = 1;

        while(!que.isEmpty()){

            int wt = w;
            w = 0;
            for(int k=0; k<wt; k++){
                Point u = que.poll();

                for(int i=0; i<4; i++){
                    if(u.r+mr[i]>=0 && u.r+mr[i]<R
                            && u.c+mc[i]>=0 && u.c+mc[i]<C
                            && map[u.r+mr[i]][u.c+mc[i]]=='.'){
                        map[u.r+mr[i]][u.c+mc[i]] = '*';
                        que.offer(new Point(u.r+mr[i], u.c+mc[i]));
                        w++;
                    }
                }
            }

            time++;
            int st = s;
            s = 0;
            for(int k=0; k<st; k++){
                Point u = que.poll();
                for(int i=0; i<4; i++){
                    if(u.r+mr[i]>=0 && u.r+mr[i]<R
                            && u.c+mc[i]>=0 && u.c+mc[i]<C){
                        if(map[u.r+mr[i]][u.c+mc[i]]=='.' && check[u.r+mr[i]][u.c+mc[i]]==0){
                            check[u.r+mr[i]][u.c+mc[i]] = time;
                            que.offer(new Point(u.r+mr[i],u.c+mc[i]));
                            s++;
                        }
                        else if(map[u.r+mr[i]][u.c+mc[i]]=='D'){
                            System.out.println(time-1);
                            return;
                        }
                        else if(map[u.r+mr[i]][u.c+mc[i]]=='X') continue;
                    }
                }
            }
        }
        System.out.println("KAKTUS");
    }
}
