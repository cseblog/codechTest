package roundA1.ChefAndStrangeAddition;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
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
        long t = System.currentTimeMillis();
        int c = (int) Math.pow(10, 9);
        int a = (int) Math.pow(10, 5) + 1234;
        System.out.println(calculate(new String[]{"" + a, "" + (c - a), "" + c}));
        System.out.println(System.currentTimeMillis() - t);
    }

    private static long calculate(String[] test) {
        long count = 0;
        int a = Integer.valueOf(test[0]);
        int b = Integer.valueOf(test[1]);
        int c = Integer.valueOf(test[2]);
        int aCount = countSetBits(a);
        int bCount = countSetBits(b);
        for (int ai = 0; ai <= c / 2; ai++) {
            int bi = c - ai;
            if (countSetBits(ai) == aCount && countSetBits(bi) == bCount) {
                count++;
            }
        }

        return count * 2;
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
}
