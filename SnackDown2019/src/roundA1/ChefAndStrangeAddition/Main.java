package roundA1.ChefAndStrangeAddition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

//    public static void main(String[] args) {
//        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
//        try {
//            Integer testCases = Integer.valueOf(r.readLine());
//            ArrayList<String[]> tests = new ArrayList<>(testCases);
//            for (int i = 0; i < testCases; i++) {
//                String[] test = r.readLine().split(" ");
//                tests.add(test);
//            }
//            for (String[] test : tests) {
//                System.out.println(calculate(test));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        long t = System.currentTimeMillis();
////        int c = (int) Math.pow(10, 9);
////        int a = (int) Math.pow(10, 5) + 1234;
////        System.out.println(calculate(new String[]{"2", "3", "5"}));
////        System.out.println(System.currentTimeMillis() - t);
//    }

    private static long calculate(String[] test) {
        long count = 0;
        int a = Integer.valueOf(test[0]);
        int b = Integer.valueOf(test[1]);
        int c = Integer.valueOf(test[2]);
        int aCount = countSetBits(a);
        int bCount = countSetBits(b);

        for (int ai = 0; ai <= c; ai++) {
            int bi = c - ai;
            if (countSetBits(ai) == aCount && countSetBits(bi) == bCount) {
                count++;
            }
        }

        return count;
    }

    static int countSetBits(int i) {

        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;

//        int size = 0;
//        while (n > 0) {
//            size += n & 1;
//            n >>= 1;
//        }
//        return size;
    }

    public static int count1(int x) {
        int count = 0;
        while (x > 0) {
            count += x & 1;
            x = x >> 1;
        }
        return count;
    }
    public static int countBits(int x) {
        int count = 0;
        while (x > 0) {
            count++;
            x = x >> 1;
        }
        return count;
    }
    static int[][][][] f;
    static boolean[][][][] vis;
    static int[] c;

    public static int find(int i, int o1, int o2, int u) {
        if (i == 0) {
            if (o1 == 0 && o2 == 0 && u == 0)
                return 1;
            return 0;
        }
        if (o1 < 0 || o2 < 0)
            return 0;
        if (vis[i][o1][o2][u])
            return f[i][o1][o2][u];

        if (c[i-1] == 0) {
            if (u == 0)
                f[i][o1][o2][u] = find(i-1, o1, o2, 0);
            else
                f[i][o1][o2][u] = find(i-1, o1-1, o2-1, 0)
                        + find(i-1, o1-1, o2, 1)
                        + find(i-1, o1, o2-1, 1);
        } else {
            if (u == 0)
                f[i][o1][o2][u] = find(i-1, o1-1, o2, 0)
                        + find(i-1, o1, o2-1, 0)
                        + find(i-1, o1, o2, 1);
            else
                f[i][o1][o2][u] = find(i-1, o1-1, o2-1, 1);
        }
        vis[i][o1][o2][u] = true;
        return f[i][o1][o2][u];
    }
    public static final int[][][][] m = new int[32][32][32][2];

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int t = in.nextInt();
//        for (int testcase=0; testcase<t; testcase++) {
//            int A = in.nextInt(), B = in.nextInt(), C = in.nextInt();
//            int a1 = count1(A);
//            int b1 = count1(B);
//            int n = countBits(C);
//            c = new int[n];
//            int CC = C;
//            for (int i=0; i<n; i++) {
//                c[i] = CC & 1;
//                CC = CC >> 1;
//            }
//
//            f = new int[n+1][a1+1][b1+1][2];
//            vis = new boolean[n+1][a1+1][b1+1][2];
//            System.out.println(find(n, a1, b1, 0));
//        }
        long t = System.currentTimeMillis();
        int c = (int) Math.pow(10, 9);
        int a = (int) Math.pow(10, 5) + 1234;
        int b = c-a;
        int count_a = Count_1(a);
        int count_b = Count_1(b);
        for(int i = 0; i < 32; i++){
            for(int j = 0; j < 32; j++){
                for(int k = 0; k < 32; k++){
                    for(int l = 0; l < 2; l++){
                        m[i][j][k][l] = -1;
                    }
                }
            }
        }

        System.out.println(generate(count_a, count_b, c, 0, 0, 0, 0));
        System.out.println(System.currentTimeMillis() - t);
    }

    public static int Count_1(long n) {
        int count = 0;
        while (n > 0) {
            n = (n & n - 1);
            count++;
        }

        return count;
    }

    public static int SB(int x, int i) {
        int bit_mask = (1 << i);
        x = x | bit_mask;
        return x;
    }

    public static boolean CB(int n, int x) {
        int bit_mask = 1 << x;
        n = n & bit_mask;
        return n != 0;
    }

    public static int generate(int count_a, int count_b, int c, int i, int update, int n1, int n2) {
        int bits_n1 = Count_1(n1);
        int bits_n2 = Count_1(n2);

        if (m[bits_n1][bits_n2][i][update] != -1) {
            return m[bits_n1][bits_n2][i][update];
        }
        if ((bits_n1 > count_a) || (bits_n2 > count_b) || (n1 + n2 > c)) {
            m[bits_n1][bits_n2][i][update] = 0;
            return 0;
        }

        if (n1 + n2 == c) {
            if (bits_n1 == count_a && bits_n2 == count_b) {
                m[bits_n1][bits_n2][i][update] = 1;
                return 1;
            }
            m[bits_n1][bits_n2][i][update] = 0;
            return 0;
        }

        boolean current_bit = CB(c, i);
        int ans = 0;

        if (current_bit) {
            if (update == 1) {
                ans += generate(count_a, count_b, c, i + 1, 0, n1, n2);
                ans += generate(count_a, count_b, c, i + 1, 1, SB(n1, i), SB(n2, i));
            } else {
                ans += generate(count_a, count_b, c, i + 1, 0, SB(n1, i), n2);
                ans += generate(count_a, count_b, c, i + 1, 0, n1, SB(n2, i));
            }
        } else {
            if (update == 1) {
                ans += generate(count_a, count_b, c, i + 1, 1, n1, SB(n2, i));
                ans += generate(count_a, count_b, c, i + 1, 1, SB(n1, i), n2);
            } else {
                ans += generate(count_a, count_b, c, i + 1, 0, n1, n2);
                ans += generate(count_a, count_b, c, i + 1, 1, SB(n1, i), SB(n2, i));
            }

        }
        m[bits_n1][bits_n2][i][update] = ans;
        return ans;

    }
}
