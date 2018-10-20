package roundA1.GraphAndArray;

import java.io.IOException;
import java.util.*;

/**
 * Created by trunghuynh on 10/20/18.
 */
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

    private static Integer getCoprime(Integer k) {
        for (int i = 2; i < k; i++) {
            if (gcd(k, i) == 1) {
                return i;
            }
        }
        return 3;
    }


    // create a map for the array.
    public static boolean isCoprime(Integer a, Integer b) {
        return gcd(a, b) == 1;
    }


    public static Integer getNewCoprime(Integer a, Integer b) {

        return getCoprime(b);
    }

    public static void processing(Data data) {
        Map<Integer, Boolean> mapData = new HashMap<>();
        int count = 0;


        // build a map with key is numbers of input
        for (int i = 0; i < data.numberList.length; i++) {
            mapData.put(data.numberList[i], false);
        }

        /**
         * Build a map of coprimes
         * Ex:
         * tc1
         *     2 -> [5, 7]
         *     5 ->[3, 7]
         *     10 ->[7]
         *     7 ->[3, 5, 10]
         *
         * tc2
         *     2 -> []
         *     4 ->[]
         *     10 ->[]
         *     24 ->[]
         * tc3:
         *     2 -> [5]
         *     5 ->[2, 8]
         *     8 ->[5]
         *     80 ->[]
         *
         */
        Integer[] numbers = data.numberList;
        for (int j = 0; j < numbers.length; j++) {
            for (int k = j + 1; k < numbers.length; k++) {
                if (mapData.get(numbers[k]) == false) {
                    Integer a = numbers[j];
                    Integer b = numbers[k];
                    if (isCoprime(a, b)) {
                        mapData.put(a, true);
                        mapData.put(b, true);

                    }
                }
            }
        }

        //Starting from second number
        for (int i = 1; i < numbers.length; i++) {
            Boolean status = mapData.get(numbers[i]);
            if (status == false) {
                count++;
                numbers[i] = getNewCoprime(numbers[i], numbers[i - 1]);
            }
        }

        System.out.println(count);
        printArray(numbers);
    }

    public static class Data {
        public int count;
        public Integer[] numberList;

        public Data(int s, Integer[] list) {
            count = s;
            numberList = list;
        }
    }

    private static void printArray(Integer[] anArray) {
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(anArray[i]);
        }
    }

    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            // Reading input
            int numOfTestcases = Integer.valueOf(r.readLine());
            List<Data> tcs = new ArrayList<>();
            while (numOfTestcases > 0) {
                int arraySize = Integer.valueOf(r.readLine());

                String[] line3 = r.readLine().split(" ");
                Integer[] data = Arrays.stream(line3).map(Integer::valueOf).toArray(value -> new Integer[line3.length]);
                Data inputData = new Data(arraySize, data);
                tcs.add(inputData);
                numOfTestcases--;
            }

            //
            for (Data d : tcs) {
                processing(d);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
