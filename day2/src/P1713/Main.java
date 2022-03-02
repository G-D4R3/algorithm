package P1713;

import java.util.*;

// Priority Queue를 사용할 수도 있지만, 충분히 간단한 정렬로 풀 수 있는 문제

class Person implements Comparable<Person>{
    int num;
    int count;
    int timeStamp;
    boolean isIn;

    public Person(int num, int count, int timeStamp, boolean isIn) {
        this.num = num;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    @Override
    public String toString() {
        return "Person{" +
                "num=" + num +
                ", count=" + count +
                ", timeStamp=" + timeStamp +
                ", isIn=" + isIn +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        int comp1 = Integer.compare(count, o.count);
        if(comp1==0) {
            return Integer.compare(timeStamp, o.timeStamp);
        }
        else {
            return -1;
        }
    }
}

public class Main {
    static int N, K;
    static int[] inputs;
    static Person[] people;

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        inputs = new int[K];
        people = new Person[101];

        List<Person> list = new ArrayList<>();
        for(int k=0; k<K; k++){
            int num = sc.nextInt();
            if(people[num] == null){
                people[num] = new Person(num, 0, 0, false);
            }
            // 사진판에 있는 경우 -> count ++;
            if(people[num].isIn){
                people[num].count++;
                people[num].isIn = true;
            }
            else { // 사진판에 없는 경우 -> 하나 골라서 제거 후 새 후보 추가
                if(list.size()==N){
                    Collections.sort(list);
                    Person p = list.remove(0);
                    p.isIn = false; // || p.count = 0;
                }
                people[num].count = 1;
                people[num].isIn = true;
                people[num].timeStamp = k;
                list.add(people[num]);
            }
        }

        // 익명 클래스 comparator는 lambda로 대체할 수 있다.
        // 현업에서 lambda를 사용하는 경우가 많음
                                                          Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });

        // Comparator.comparingInt(Person::getNum);
        // !! Collections.sort(list, (o1, o2) -> Integer.compare(o1.num, o2.num));

        for(Person p:list) System.out.print(p.num+" ");

    }
}
