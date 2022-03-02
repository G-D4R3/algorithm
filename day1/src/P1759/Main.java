package P1759;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int L;
    static int C;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/P1759/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ArrayList<Character> arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=C; i++){
            arr.add(st.nextToken().charAt(0));
        }
        Collections.sort(arr);

        for(int i=0; i<arr.size(); i++){
            StringBuilder sb = new StringBuilder();
            sb.append(arr.get(i));
            DFS(i, arr, sb);
        }
    }

    public static void DFS(int index, ArrayList<Character> arr, StringBuilder sb){
        if(sb.length()==L){
            int l = 0, r = 0;
            for(char c:sb.toString().toCharArray()){
                if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') l++;
                else r++;
            }

            if(l>=1 && r>=2) System.out.println(sb.toString());
            return;
        }
        for(int i=index+1; i<arr.size(); i++){
            sb.append(arr.get(i));
            DFS(i, arr, sb);
            sb.deleteCharAt(sb.length()-1);
        }

    }

    /*public static void dfs(int length, int ja, int mo, int cur, String pwd){
        if(length==L){
            if(ja>=2 && mo>=1) result.add(pwd);
        }
        else {
            for(int next = cur+1; next<data.length; next++){
                char c = data[next];
                if(모음){
                    dfs(length+1, ja, mo+1, next, pwd+c);
                }
                else {
                    dfs(length+1, ja+1, mo, next, pwd+c);
                }
            }
        }
    }*/
}
