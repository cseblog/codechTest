
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    //BigO(nlogn) + BigO(n) ~ BigO(nlogn)
    public static List<Integer> findBestTeam(int max, int limit, Integer[]s){
        Arrays.sort(s, Collections.reverseOrder()); //nlog(n)
        List<Integer> result = new ArrayList<>();
        int tmp = s[0];
        for (int i = 1; i <= max; i++){ //n
            if(i <= limit){
                result.add(s[i-1]);
            } else {
                // Cater to case two or more scores have same scores at the limit
                if (i > limit && tmp == s[i-1]) {
                    result.add(s[i-1]);
                }else {
                    break;
                }
            }
            tmp = s[i-1];
        }
        return result;
    }
    public static Integer[] toObject(int[] intArray) {

        Integer[] result = new Integer[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            result[i] = Integer.valueOf(intArray[i]);
        }
        return result;
    }

    public static class CodeChefRound {
        public int numberOfTeams;
        public int limit;
        public int[] scores;
    }

    public static void main(String[] args) {
        //Reading input here
        java.io.BufferedReader r = new java.io.BufferedReader (new java.io.InputStreamReader (System.in));
        try {
            //Line 1
            int numOfTestcases = Integer.valueOf(r.readLine());
            List<CodeChefRound> testcases = new ArrayList<>();
            while (numOfTestcases > 0){
                CodeChefRound round = new CodeChefRound();
                String line2 = r.readLine();
                int numberOfTeams = Integer.valueOf(line2.split(" ")[0]);
                int limit = Integer.valueOf(line2.split(" ")[1]);

                String[] line3 = r.readLine().split(" ");
                int[] scores = Arrays.stream(line3).mapToInt(Integer::parseInt).toArray();
                round.limit = limit;
                round.numberOfTeams = numberOfTeams;
                round.scores = scores;
                testcases.add(round);
            }

            for (CodeChefRound tc : testcases) {
                List<Integer> qualifiedTeams = findBestTeam(tc.numberOfTeams, tc.limit, toObject(tc.scores));
                System.out.println(qualifiedTeams.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//
//        List<Integer> roundAList = findBestTeam(a1, b1, s1);
//        System.out.println(roundAList.size());
//
//        int a2 = 6;
//        int b2 = 4;
//        Integer []s2 = {6, 5, 4, 3, 2, 1};
//
//        List<Integer> roundBList = findBestTeam(a2, b2,s2);
//        System.out.println(roundBList.size());
    }
}
