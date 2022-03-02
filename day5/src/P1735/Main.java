package P1735;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());


        EGResult egr = extendedGcd(B, D);
        int lcm = B * D / egr.r;
        int numerator = A*D/ egr.r + C*B/ egr.r;
        int denumerator = lcm;
        egr = extendedGcd(numerator, denumerator);

        numerator /= egr.r;
        denumerator /= egr.r;

        System.out.println(numerator+" "+denumerator);


    }
    static EGResult extendedGcd(int a, int b){
        int s0 = 1, t0 = 0, r0 = a;
        int s1 = 0, t1 = 1, r1 = b;

        int temp;
        while(r1!=0){
            int q = r0 / r1;
            temp = r0 - q * r1; // next r1 / 여기는 %연산자 사용할 수 있음. a, b가 양수인 이상 음수가 나오지 않기 때문
            r0 = r1;
            r1 = temp;

            temp = s0 - q * s1; // %연산자 사용할 수 없음. 결과값이 음수가 될 수 있기 때문
            s0 = s1;
            s1 = temp;

            temp = t0 - q * t1; // %연산자 사용할 수 없음. 결과값이 음수가 될 수 있기 때문
            t0 = t1;
            t1 = temp;
        }

        return new EGResult(s0, t0, r0);
    }
}

class EGResult{
    int s;
    int t;
    int r;

    public EGResult(int s, int t, int r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
