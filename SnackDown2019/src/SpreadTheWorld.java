import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class SpreadTheWorld {


    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            Integer testCases = Integer.valueOf(r.readLine());
            List<List<Integer>> tests = new LinkedList<>();
            while (testCases > 0) {
                int n = Integer.valueOf(r.readLine());//N number
                List<Integer> numbers = Arrays.stream(r.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
                tests.add(numbers);
                testCases--;
            }
            for (List<Integer> test : tests) {
                System.out.println(getDay(test));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * bigO()??
     * @param listPeople
     * @return
     */
    public static int getDay(List<Integer> listPeople) {
        Queue<Integer> queue = new LinkedList<>();
        int i = 0;
        queue.add(listPeople.remove(0));//day 0
        while (!listPeople.isEmpty()) {
            i++;
            Queue<Integer> list = new LinkedList<>();
            while (!queue.isEmpty()) {
                Integer ability = queue.poll();
                if (ability == 0) {
                    continue;
                }

                if (ability >= listPeople.size()) {
                    return i;
                }

                List<Integer> c = listPeople.subList(0, ability);
                list.addAll(c);
                listPeople = listPeople.subList(ability, listPeople.size());
                list.add(ability);
            }
            queue = list;
        }
        return i;
    }
}
