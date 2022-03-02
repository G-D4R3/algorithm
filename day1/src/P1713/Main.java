package P1713;

import java.io.*;
import java.util.*;

class Photo{
    int candidate;
    int date;
    int recommand;
    public Photo(int can, int dat, int rec){
        this.candidate = can;
        this.date = dat;
        this.recommand = rec;
    }

    @Override
    public String toString() {
        return "{" +
                "candidate=" + candidate +
                ", date=" + date +
                ", recommand=" + recommand +
                '}';
    }

}

public class Main {
    static ArrayList<Photo> arr;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/P1713/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int date = 0;

        for(int i=0; i<K; i++){
            int input = Integer.parseInt(st.nextToken());
            boolean check = true;
            for(Photo p:arr) {
                if(p.candidate==input){
                    p.recommand++;
                    check = false;
                    break;
                }
            }
            if(check){
                if(arr.size()==N){
                    Collections.sort(arr, new Comparator<Photo>() {
                        @Override
                        public int compare(Photo o1, Photo o2) {
                            if(o1.recommand==o2.recommand){
                                return o1.date-o2.date;
                            }
                            return o1.recommand-o2.recommand;
                        }
                    });
                    arr.remove(0);
                    arr.add(new Photo(input, date++, 1));
                }
                else {
                    arr.add(new Photo(input, date++, 1));
                }
            }
        }
        Collections.sort(arr, new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                return o1.candidate-o2.candidate;
            }
        });



        for(int i=0; i<arr.size()-1; i++) System.out.print(arr.get(i).candidate+" ");
        System.out.println(arr.get(arr.size()-1).candidate);
    }

}
