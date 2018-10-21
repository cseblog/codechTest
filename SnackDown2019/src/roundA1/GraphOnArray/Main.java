package roundA1.GraphOnArray;

import java.io.IOException;
import java.util.*;

public class Main {

    private static int gcd(int a, int b) {
        int t;
        while (b != 0) {
            t = a;
            a = b;
            b = t % b;
        }
        return a;
    }

    private static Integer getPrime(Integer n){
        Integer[] primes = {29, 31, 37, 41, 43, 47};
        for(Integer i: primes){
            if(n != i){
                return i;
            }
        }
        return 47;
    }
    /**
     * Ex:
     * tc1
     * 2 -> [5, 7]
     * 5 ->[3, 7]
     * 10 ->[7]
     * 7 ->[3, 5, 10]
     * <p>
     * tc2
     * 2 -> []
     * 4 ->[]
     * 10 ->[]
     * 24 ->[]
     *
     * tc3:
     * 2 ->[5]
     * 5 ->[2, 8]
     * 8 ->[5]
     * 80 ->[]
     */
    public static void processing(int size, Integer[] numbers) {
        int count = 0;
        StringBuilder tmp = new StringBuilder();
        Integer[] primes = {29, 31, 37, 41, 43, 47};
        int i = 0;

        for (int j = 0; j < numbers.length; j++) {
            boolean isCoPrime = false;
            for (int k = 0; k < size; k++) {
                if (j == k) {
                    continue;
                }

                Integer a = numbers[j];
                Integer b = numbers[k];

                if(gcd(a, b) == 1){
                    isCoPrime = true;
                    break;
                }
            }

            //
            if (!isCoPrime) {
                count++;
                numbers[j] = getPrime(numbers[j]);


            }


            if(j > 0)
                tmp.append(" ");
            tmp.append(numbers[j]);


        }


        System.out.println(count);
        System.out.println(tmp);

    }


    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            // Reading input
            int numOfTestcases = Integer.valueOf(r.readLine());
            LinkedList<Integer[]> test = new LinkedList<>();
            while (numOfTestcases > 0) {
                int size = Integer.valueOf(r.readLine());
                String[] line3 = r.readLine().split(" ");
                Integer[] data = Arrays.stream(line3).map(Integer::valueOf).toArray(value -> new Integer[line3.length]);
                test.add(data);
                numOfTestcases--;
            }


            for (Integer[] data : test) {
                processing(data.length, data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
