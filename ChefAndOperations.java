import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChefAndOperations {
    //Transform to BFS
    public static void main(String[] args) {
//        System.out.println(isPossible(Arrays.asList(0, 0, 0, 0, 0), Arrays.asList(1, 2, 4, 2, 3)));
//        System.out.println(isPossible(Arrays.asList(0, 0, 0, 0, 0), Arrays.asList(1, 2, 4, 2, 4)));
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            Integer testCases = Integer.valueOf(r.readLine());
            List<List<Integer>> tests = new ArrayList<>(testCases);
            for (int i = 0; i < testCases; i++) {
                r.readLine();//N number
                List<Integer> a = Arrays.stream(r.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
                List<Integer> b = Arrays.stream(r.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
                tests.add(a);
                tests.add(b);
            }
            for (int i = 0; i < testCases; i++) {
                if (isPossible(tests.get(i), tests.get(i + 1))) {
                    System.out.println("TAK");
                }
                else {
                    System.out.println("NIE");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean isPossible(List<Integer> a, List<Integer> b) {
        List<Integer> diff = diff(a, b);
        if (diff == null) {
            return false;
        }
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(diff);
        while (!queue.isEmpty()) {
            List<Integer> node = queue.poll();
//            System.out.println(node);
            if (allZero(node)) {
                return true;
            }
            else {
                for (int i = 1; i <= node.size() - 2; i++) {//N-1
                    List<List<Integer>> child = child(node, i);
                    if (!child.isEmpty()) {
                        queue.addAll(child);
//                        for (int j = 0; j < child.size(); j++) {
//                            System.out.print(child.get(j) + " ");
//                        }
                    }
                }
            }
//            System.out.println();
        }
        return false;
    }

    static boolean allZero(List<Integer> a) {
        return a.stream().allMatch(i -> i == 0);
    }

    static List<Integer> subtract(List<Integer> source, int fromIndex, int time) {

        ArrayList<Integer> result = new ArrayList<>(source);
        for (int i = 1; i <= 3; i++) {
            int index = fromIndex - 1;
            int value = source.get(index) - i * time;
            if (value < 0) {
                return null;
            }
            result.set(index, value);
            fromIndex++;
        }

        return result;
    }

    static List<List<Integer>> child(List<Integer> source, int fromIndex) {
        source = new ArrayList<>(source);
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= source.get(fromIndex - 1); i++) {
            List<Integer> child = subtract(source, fromIndex, i);
            if (child == null) {
                return result;
            }
            else {
                result.add(child);
            }
        }
        return result;
    }

    static List<Integer> diff(List<Integer> a, List<Integer> b) {
        int size = a.size();
        List<Integer> c = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int element = b.get(i) - a.get(i);
            if (element < 0) {
                return null;
            }
            c.add(element);
        }
        return c;
    }
}
