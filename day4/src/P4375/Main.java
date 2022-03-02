package P4375;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while(input!=null){
            int N = Integer.parseInt(input);
            int length = 1;
            String div = 1%N==0? "0":Integer.toString(1%N);
            while(!div.equals("0")){
                div += "1";
                div = Integer.parseInt(div)%N==0? "0":Integer.toString(Integer.parseInt(div)%N);
                length++;
            }
            System.out.println(length);
            input = br.readLine();
        }

    }
}