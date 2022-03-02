package P1256;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[][] pascal;
    static final int maxK = 1000000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        // pascal 구성
        pascal = new int[N+M+1][N+M+1];
        for(int i=0; i<N+M+1; i++) pascal[i][0] = 1;
        for(int i=0; i<N+M+1; i++){
            for(int j=1; j<=i; j++){
                if(i==0 || i==1) pascal[i][j] = 1;
                else {
                    int left = pascal[i-1][j-1];
                    int right = pascal[i-1][j];

                    // K가 넘는 값을 K의 max값으로 치환해도 로직에 아무 이상 없음
                    if(left+right>=maxK) pascal[i][j] = maxK;
                    else pascal[i][j] = left+right;
                }
            }
        }

        if(pascal[N+M][N]<K){
            System.out.println("-1");
            return;
        }


        // K번째 단어를 찾도록 쿼리
        String s = "";
        query(s, K, 0, 0);

    }

    static void query(String s, int count, int aCount, int zCount){
        if(aCount==N && zCount==M){
            System.out.println(s);
            return;
        }

        // 왼쪽
        aCount++;
        if(N-aCount>=0) {
            int l = pascal[N+M-aCount-zCount][N-aCount];
            if(count < l){
                s += "a";
                query(s, count, aCount, zCount);
            }
            else if(count > l){
                s += "z";
                aCount--;
                zCount++;
                count -= l;
                query(s, count, aCount, zCount);
            }
            else if( count == l ){
                if(N<aCount){
                    s+= "z";
                    zCount++;
                    query(s, count, aCount, zCount);
                }
                else {
                    s += "a";
                    query(s, count, aCount, zCount);
                }
            }
        }
        else if(M-zCount>=0){
            int l = pascal[N+M-aCount-zCount][0];
            s += "z";
            aCount = N;
            zCount++;
            count -= l;
            query(s, count, aCount, zCount);
        }

    }
}
