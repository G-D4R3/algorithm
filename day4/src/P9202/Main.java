package P9202;
import java.io.*;
import java.util.*;

class Node {
    String data;
    Node[] child;
    boolean isEnd;
    boolean isHit;

    public Node(String data, boolean isEnd, boolean isHit) {
        this.data = data;
        this.isEnd = isEnd;
        this.isHit = isHit;
        child = new Node[26];
    }

    @Override
    public String toString() {
        return "Node{" +
                "data='" + data + '\'' +
                ", child=" + Arrays.toString(child) +
                '}';
    }

    void clearHit(){
        isHit = false;
        for (Node node : child) {
            if(node != null) node.clearHit();
        }
    }
}

public class Main {
    static final int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static final int[] mx = { 0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] my = { -1, -1, 0, 1, 1, 1, 0, -1};
    static char[][] map;
    static boolean[][] visit;
    static Node trie;

    static String longestWord;
    static int maxScore;
    static int wordCount;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        // trie 입력
        trie = new Node(null, false, false);
        for(int i=0; i<N; i++){
            String input = br.readLine().trim();
            Node v = trie;
            StringBuilder data = new StringBuilder();
            for(int j=0; j<input.length(); j++){
                int c = input.charAt(j)-'A';
                Node next = v.child[c];
                data.append((char)('A'+c));
                if(next==null) {
                    next = new Node(data.toString(), false, false);
                    v.child[c] = next;
                }
                v = v.child[c];
            }
            v.isEnd = true;
        }
        br.readLine();

        int b = Integer.parseInt(br.readLine());
        for(int i=0; i<b; i++){
            visit = new boolean[4][4];
            map = new char[4][4];
            // Boggle 보드 입력
            for(int j=0; j<4; j++){
                String s = br.readLine();
                for(int k=0; k<4; k++){
                    map[j][k] = s.charAt(k);
                }
            }

            if(i<b-1) br.readLine();


            longestWord = "";
            maxScore = 0;
            wordCount = 0;
            for(int j=0; j<4; j++){
                for(int k=0; k<4; k++){
                    char c = map[j][k];
                    if(trie.child[c-'A']!=null){
                        StringBuilder sb = new StringBuilder();
                        sb.append(c);
                        DFS(0, j, k, sb.toString(), trie.child[c-'A']);
                        //System.out.println(longestWord+" "+maxScore+" "+wordCount);
                    }

                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(maxScore+" "+longestWord+" "+wordCount);
            System.out.println(sb.toString().replaceAll("\\s+", " "));

            trie.clearHit();
        }



    }

    static void DFS(int length, int row, int col, String word, Node wordNode){
        // 1. 체크인
        visit[row][col] = true;
        // 2. 목적지인가? ( isEnd = true, isHit = false)
        if(wordNode.isEnd && !wordNode.isHit){
            if(longestWord.length() < word.length()) longestWord = word;
            else if(longestWord.length() == word.length()) longestWord = longestWord.compareTo(word)<0? longestWord:word;
            maxScore += score[word.length()];
            wordCount++;
            wordNode.isHit = true;
            visit[row][col] = false;
            return;
        }
        // 3. 연결된 곳 : 8방향
        for(int i=0; i<8; i++){

            int nextRow = row + my[i];
            int nextCol = col + mx[i];

            // 4. 갈 수 있는가? ( 맵 내부, trie, 방문하지 않은 지점)
            if(nextRow >= 0 && nextRow < 4 && nextCol >= 0 && nextCol < 4
            && wordNode.child[map[nextRow][nextCol]-'A'] != null
            && visit[nextRow][nextCol]==false){
                // 5. 간다
                char c = map[nextRow][nextCol];

                // 다음 노드
                Node next = wordNode.child[c-'A'];
                StringBuilder sb = new StringBuilder(word);
                sb.append(c);

                DFS(length+1, nextRow, nextCol, sb.toString(), next);
            }
        }
        // 6. 체크 아웃
        visit[row][col] = false;

    }
}

