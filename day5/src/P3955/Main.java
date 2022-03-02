package P3955;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long K = Long.parseLong(st.nextToken());
            long C = Long.parseLong(st.nextToken());

            // X : 인당 나눠줄 사탕의 수
            // Y : 사탕 봉지의 수
            // A * X + 1 = B * Y
            // Ax + By = C 의 형태로 변환
            // -Ax + By = 1
            // A(-x) + By = 1

            // K*X + 1 = C*Y
            // -KX + CY = 1
            // A = K, B = C, C = 1
            long A = K, B = C;
            EGResult egr = extendedGcd(A, B);

            // 1. 해 검증
            // D = gcd(A, B) = egcd ( A, B ).r
            // Ax + By = C 일 때 C % D == 0 이어야 해를 가질 수 있음 (배주항등식)
            // C % D != 0 -> 해가 없음
            // 어차피 C는 1이니까
            long D = egr.r;
            if(D!=1) {
                System.out.println("IMPOSSIBLE");
                continue;
            }

            // 2. 초기 해 구하기
            // x0 = s * C / D
            // y0 = t * C / D
            double x0 = egr.s;
            double y0 = egr.t;

            // 3. 일반해 구하기
            // x = x0 + B / D * k
            // y = y0 - A / D * k

            // k를 찾는 차선책 : while 문으로 k를 앞뒤로 움직이면서 만족하는 k를 찾음 -> 시간복잡도에는 안 맞을 수 있음

            // 4. k의 범위
            // x < 0
            // x0 + B * k < 0
            // k < -x0 / B

            // 0 < y <= 10^9
            // 0 < y0 - A / D * k <= 1e9
            // -y0 < -A * k <= 1e9 일단 D 뺴고( 1이니까 )
            // ( y0-1e9 ) / A <= k < y0 / A

            // ( y0-1e9 ) / A <= k < y0 / A
            //                   k < -x0 / B (Double)

            long kFromY = (long) Math.ceil((double)y0/(double)A)-1;
            long kFomrX = (long) Math.ceil((double)(-x0)/(double)(B))-1; // 한번에 Math.min으로 구하면 안됨... -1 꼭 각자 해줘야함

            long k = Math.min(kFomrX, kFromY);


            // Math.floor를 쓰면 원하지 않는 값을 씀. <= 일 때(등호가 있을 때)는 floor를 쓸 수 있음
            // Math.ceil - 1 을 하면 < 에 만족하는 값을 찾을 수 있음
            // max k를 찾아서 ( y0-1e9 ) / A <= k 를 만족하는지 확인

            long kLimitFromY = (long)Math.ceil((y0-1e9)/(double)A);

            // 5. 만족하는 k가 있는지 찾고, k를 통해 y를 구한다.
            if(kLimitFromY<=k){
                System.out.println((long)(y0-A*k));
            }
            else System.out.println("IMPOSSIBLE");




        }




    }
    static EGResult extendedGcd(long a, long b){
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;
        while(r1!=0){
            long q = r0 / r1;
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
    long s;
    long t;
    long r;

    public EGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
