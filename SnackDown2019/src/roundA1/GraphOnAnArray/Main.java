package roundA1.GraphOnAnArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static int __gcd(int a, int b) {
        // Everything divides 0
        if (a == 0 || b == 0) {
            return 0;
        }

        // base case
        if (a == b) {
            return a;
        }

        // a is greater
        if (a > b) {
            return __gcd(a - b, b);
        }

        return __gcd(a, b - a);
    }

    // function to check and print if
    // two numbers are co-prime or not
    static boolean isCoprime(int a, int b) {
        return __gcd(a, b) == 1;
    }

    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            // Reading input
            int numOfTestcases = Integer.valueOf(r.readLine());
            List<List<Integer>> tcs = new ArrayList<>();
            while (numOfTestcases > 0) {
                int arraySize = Integer.valueOf(r.readLine());
                String[] line3 = r.readLine().split(" ");
                List<Integer> data = Arrays.stream(line3).map(Integer::valueOf).collect(Collectors.toList());
                tcs.add(data);
                numOfTestcases--;
            }

            //
            for (List<Integer> test : tcs) {
                processing(test);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void processing(List<Integer> test) {

    }
}
