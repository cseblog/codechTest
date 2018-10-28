package R1b2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static class Test {
        int N, M, K, L;
        int[] a;

        public Test(int n, int m, int k, int l, int[] a) {
            N = n;
            M = m;
            K = k;
            L = l;
            this.a = a;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTc = scanner.nextInt();
        List<Test> tcs = new ArrayList<>();
        for (int i = 0; i < numberOfTc; i++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int K = scanner.nextInt();
            int L = scanner.nextInt();
            int[] a = new int[N];
            for (int j = 0; j < N; j++) {
                a[j] = scanner.nextInt();
            }
            tcs.add(new Test(N, M, K, L, a));
        }
        for (Test test : tcs) {
            System.out.println(calculate(test));
        }
//        System.out.println(calculate(new Test(1,1,9,5,new int[]{8})));
    }

    private static int calculate(Test test) {
        List<Integer> list = Arrays.stream(test.a).boxed().sorted().collect(Collectors.toList());
        int time = Integer.MAX_VALUE;
        int lastOrder = 1;
        for (int i = 1; i <= test.K; i++) {
            int order = findOrder(lastOrder, i, list);
            lastOrder = order;
            int current = (test.M + order) * test.L - i;
            if (current < time) {
                time = current;
            }
        }
        return time;
    }

    private static int findOrder(int lastOrder, int i, List<Integer> list) {
        for (int j = lastOrder - 1; j < list.size(); j++) {
            if (i <= list.get(j)) {
                return j + 1;
            }
        }
        return list.size() + 1;
    }
}
