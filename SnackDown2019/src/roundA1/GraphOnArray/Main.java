package roundA1.GraphOnArray;

import java.io.IOException;
import java.util.*;
public class Main {

    private static int gcd(int a, int b) {
        int t;
        while(b != 0){
            t = a;
            a = b;
            b = t%b;
        }
        return a;
    }

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

    static List<Integer> printDivisors(int n)
    {
        List<Integer> listOfDivisors= new ArrayList<>();
        int maxD = (int)Math.sqrt(n);
        for (int i=1; i<=maxD; i++)
        {
            if (n%i==0)
            {
                // If divisors are equal, print only one
                if (n/i == i)
                    listOfDivisors.add(i);

                    // Otherwise print both
                else
                {
                    listOfDivisors.add(i);
                    listOfDivisors.add(n/i);
                }
            }
        }
        return listOfDivisors;
    }

    /**
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
    public static void processing(int size, Integer[] numbers) {
        int count = 0;
        StringBuilder tmp = new StringBuilder();


        Integer[] primes = {29, 31, 37, 41, 43, 47};
        int i = 0;

        for (int j = 0; j < numbers.length; j++) {
            boolean isCoPrime = false;
            for (int k = 0; k < numbers.length; k++){
                if(j == k)
                    continue;
                Integer a = numbers[j];
                Integer b = numbers[k];
//                List<Integer> l1 = printDivisors(a);
//                List<Integer> l2 = printDivisors(b);
//                l1.retainAll(l2);
//                if(l1.size() == 1 && l1.get(0) == 1){
//                    isCoPrime = true;
//                }

                if(gcd(a, b) == 1){
                    isCoPrime = true;
                    break;
                }
            }

            //
            if(!isCoPrime){
                count++;
                if( i >= 5){
                    i = 0;
                }
                numbers[j] = primes[i];
                i++;
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
            while (numOfTestcases > 0) {
                int size = Integer.valueOf(r.readLine());
                String[] line3 = r.readLine().split(" ");
                Integer[] data = Arrays.stream(line3).map(Integer::valueOf).toArray(value -> new Integer[line3.length]);
                processing(size, data);
                numOfTestcases--;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
