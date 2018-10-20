import java.io.IOException;
import java.util.Arrays;


public class RoundAMain2 {

    public static boolean isNonDecreaseArray(Integer[] arr) {
        Integer tmp = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                if (arr[arr.length - 1] > tmp) {
                    return false;
                } else {
                    for(int j = i + 1; j < arr.length -1; j++){
                        if(arr[j] > arr[j + 1]){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            //Line 1
            int numOfTestcases = Integer.valueOf(r.readLine());
            while (numOfTestcases > 0) {
                int arraySize = Integer.valueOf(r.readLine());

                String[] line3 = r.readLine().split(" ");
                Integer[] data = Arrays.stream(line3).map(Integer::valueOf).toArray(value -> new Integer[line3.length]);
                if (isNonDecreaseArray(data)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
                numOfTestcases--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
