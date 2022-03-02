package P1927;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        MinHeap heap = new MinHeap(new ArrayList<>());

        for(int i=0; i<N; i++){
            int input = Integer.parseInt(br.readLine());
            if(input==0){
                System.out.println(heap.delete());
            }
            else heap.insert(input);

        }
    }



}

class MinHeap {
    List<Integer> list;

    public MinHeap(List<Integer> list) {
        this.list = list;
        list.add(0);
    }

    public void insert(int val){
        // 1. leaf 마지막에 삽입
        list.add(val);

        int cur = list.size()-1;
        int par = cur/2;

        // 2. 부모와 비교 후 조건에 맞지 않으면 swap
        // 3. 조건이 만족되거나 root 까지 반복
        while(true){
            if( par == 0 || list.get(par) <= list.get(cur)) break;

            int temp = list.get(par);
            list.set(par, list.get(cur));
            list.set(cur, temp);

            cur = par;
            par = cur/2;
        }
    }


    public int delete() {
        if(list.size () ==1) return 0;

        int top = list.get(1);
        // 1. Root에 leaf 마지막 데이터를 가져옴
        list.set(1, list.get(list.size()-1));
        list.remove(list.size()-1);

        // 2. 자식과 비교 후 조건이 맞지 않으면 swap
        // 3. 조건이 만족되거나 leaf까지 반복

        int curPos = 1;
        while(true){
            //left가 없으면 right도 없는 것. left가 없으면 leaf인것

            int leftPos = curPos*2;
            int rightPos = curPos*2 + 1;
            if(leftPos >= list.size()) {//left가 없으면 right도 없는 것. left가 없으면 leaf인것
                break;
            }
            // left만 있는 case, right도 있는 case
            // 위의 if 구문을 통과 했기 때문에 left는 무조건 있음

            // 왼쪽 자식 확인
            int minValue = list.get(leftPos);
            int minPos = leftPos;

            // 오른쪽 자식 확인
            if(rightPos < list.size() && list.get(rightPos) < minValue){ // rightPos가 있고, rightPos가 더 작음.
                // min heap이기 때문에 작은 쪽을 위로 올림
                minValue = list.get(rightPos);
                minPos = rightPos;
            }

            if(list.get(curPos) > minValue){
                int temp = list.get(curPos);
                list.set(curPos, list.get(minPos));
                list.set(minPos, temp);
                curPos = minPos;
            } else { // swap할 필요가 없다면 탈출
                break;
            }
        }
        return top;
    }


}
