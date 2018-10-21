import java.io.IOException;
import java.util.*;

/**
 * Created by trunghuynh on 10/20/18.
 */
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

    private static Integer getCoprime(Integer k){
        for(int i = 2; i < k; i++){
            if(gcd(k, i) == 1){
                return i;
            }
        }
        return 3;
    }


    // create a map for the array.
    public static boolean isCoprime(Integer a, Integer b) {
        return gcd(a,b) == 1;
    }


    public static Integer getNewCoprime(Integer a, Integer b){

        return getCoprime(b);
    }

    public static void processing(Data data) {
        Map<Integer, Boolean> mapData = new HashMap<>();
        int count = 0;
        StringBuilder tmp = new StringBuilder();

        // build a map with key is numbers of input
//        for (int i = 0; i < data.list.length; i++) {
//            mapData.put(data.list[i], false);
//        }

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
        tmp.append(numbers[0]);
        for (int j = 1; j < numbers.length; j++) {
            boolean isPrime = false;
          for (int k = 0; k < numbers.length; k++){
                  Integer a = numbers[j];
                  Integer b = numbers[k];
                  if(gcd(a, b) == 1){
                      isPrime = true;
                  }

          }
            if(!isPrime){
                count++;
                numbers[j] = getNewCoprime(numbers[j], numbers[j-1]);
            }

            tmp.append(" ");
            tmp.append(numbers[j]);
        }

        //Starting from second number
//
//        for (int i = 1; i < numbers.length; i++){
//            Boolean status = mapData.get(numbers[i]);
//            if(status == false){
//                size++;
//                numbers[i] = getNewCoprime(numbers[i], numbers[i-1]);
//            }
//            tmp.append(" ");
//            tmp.append(numbers[i]);
//        }

        System.out.println(count);
        System.out.println(tmp.toString());

    }

    public static class Data {
        public int count;
        public Integer[] numberList;
        public Data(int s, Integer[] list){
            count = s;
            numberList = list;
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
            for(Data d: tcs){
                processing(d);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
