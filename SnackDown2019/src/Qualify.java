import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Qualify {

    //BigO(nlogn) + BigO(n) ~ BigO(nlogn)
    public static List<Integer> findBestTeam(int limit, Integer[] s) {
        Arrays.sort(s, Collections.reverseOrder()); //nlog(n)
        List<Integer> result = new ArrayList<>();
        int tmp = s[0];
        for (int i = 1; i <= s.length; i++) { //n
            if (i <= limit) {
                result.add(s[i - 1]);
            } else {
                // Cater to case two or more scores have same scores at the limit
                if (i > limit && tmp == s[i - 1]) {
                    result.add(s[i - 1]);
                } else {
                    break;
                }
            }
            tmp = s[i - 1];
        }
        return result;
    }

    static int findBestTeam2(int limit, Integer[] teams) {
        Map<Integer, Long> map = Arrays.stream(teams).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> keys = map.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (keys.size() <= limit) {
            return teams.length;
        } else {
            long score = keys.get(limit - 1);
            return map.entrySet().stream().filter(e -> e.getKey() >= score)
                    .map(Map.Entry::getValue).mapToInt(Long::intValue).sum();
        }
    }

    public static class CodeChefRound {
        public int limit;
        public Integer[] scores;
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            //Line 1
            int numOfTestcases = Integer.valueOf(r.readLine());
            List<CodeChefRound> testcases = new ArrayList<>();
            while (numOfTestcases > 0) {
                CodeChefRound round = new CodeChefRound();
                String line2 = r.readLine();
//                int numberOfTeams = Integer.valueOf(line2.split(" ")[0]);
                int limit = Integer.valueOf(line2.split(" ")[1]);

                String[] line3 = r.readLine().split(" ");
                Integer[] scores = Arrays.stream(line3).map(Integer::valueOf).toArray(value -> new Integer[line3.length]);
                round.limit = limit;
                round.scores = scores;
                testcases.add(round);
                numOfTestcases--;
            }

            for (CodeChefRound tc : testcases) {

//                int qualifiedTeams = findBestTeam2(tc.limit, tc.scores);
                List<Integer> qualifiedTeams = findBestTeam(tc.limit, tc.scores);
                System.out.println(qualifiedTeams.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
