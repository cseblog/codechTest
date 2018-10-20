import java.io.IOException;
import java.util.*;

public class SumSemiPrime {

    //bigO(n^1/2)
    public static boolean isPrime(int num) {
        for (int divisor = 2; divisor <= Math.sqrt(num); divisor++) {
            if (num % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    // bigO(logn) * bigO(n) ~ bigO(nlogn)
    public static boolean isSemiPrime(int num) {
        int cnt = 0;
        for (int a = 2; a * a <= num; ++a) { //bigO(logn)
            if (num % a == 0) {
                int b = num / a;
                if (isPrime(a) && isPrime(b) && a != b) { //bigO(n) + bigO(n) ~bigO(n)
                    cnt++;
                }
            }
        }
        return cnt == 1;
    }

    public static String isSumOfSemiPrimes(Integer num) {
        for (int a = 2; a <= num / 2; a++) { //bigO(n)
            int b = num - a;
            if (isSemiPrime(a) && isSemiPrime(b)) {
                return "YES";
            }
        }
        return "NO";
    }


    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            //Line 1
            int numOfTestcases = Integer.valueOf(r.readLine());
            List<Integer> testCases = new ArrayList<>();

            //Read all numbers to verify sum of semi primes
            while (numOfTestcases > 0) {
                String line = r.readLine();
                testCases.add(Integer.valueOf(line));
                numOfTestcases--;
            }

//            for (Integer tc : testCases) {
//                System.out.println(isSumOfSemiPrimes(tc));
//            }
            System.out.println(isSumOfSemiPrimes2(testCases));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * num <200 = a + b (a,b <200); a = x*y
     * //https://math.stackexchange.com/questions/422559/what-is-sum-limits-i-1n-sqrt-i
     * //O(n) = fixed at 671 + 25^2 + 25^2 ^4 + T (tests).
     * @param testCases
     * @return
     */
    public static String isSumOfSemiPrimes2(List<Integer> testCases) {
        List<Integer> set = primes(100);
        HashSet<Integer> semiPrise = new HashSet<>();
        for (Integer x : set) { //25*25
            for (Integer y : set) {
                if (!Objects.equals(x, y)) {
                    semiPrise.add(x * y);
                }
            }
        }


        HashSet<Integer> sumsOfSemiPrise = new HashSet<>();//sumsOfSemiPrise size only 97
        for (Integer x : semiPrise) { //36*36= 1892
            for (Integer y : semiPrise) {
                sumsOfSemiPrise.add(x + y);
            }
        }

        for (Integer test : testCases) { //cost T
            System.out.println(sumsOfSemiPrise.contains(test) ? "YES" : "NO");
        }

        return "";
    }

    static List<Integer> primes(int max) {//max is exclude
        List<Integer> result = new LinkedList<>();
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }
}

