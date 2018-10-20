package roundA1.ChefAndPeriodicSequence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try

        {
            Integer testCases = Integer.valueOf(r.readLine());
            ArrayList<Integer[]> tests = new ArrayList<>(testCases);
            for (int i = 0; i < testCases; i++) {
                String[] split = r.readLine().split(" ");
                Integer[] test = Arrays.stream(split).map(Integer::valueOf).toArray(value -> new Integer[split.length]);
                tests.add(test);
            }
            for (Integer[] test : tests) {
                System.out.println(calculate(test));
            }
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
    }

    private static String calculate(Integer[] test) {
        if (test.length == 1) {
            return "inf";
        }

        int max = test[0];
        int iMax = 0;
        for (int i = 1; i < test.length; i++) {
            if (test[i] > max) {
                max = test[i];
                iMax = i;
            }
        }
        if (max == -1) {//case all -1
            return "inf";
        }
        test = goUp(test, iMax);
        test = goDown(test, iMax);
        return "";
    }

    private static Integer[] goDown(Integer[] test, int iMax) {

        return test;
    }

    private static Integer[] goUp(Integer[] test, int iMax) {

        return test;
    }


}
