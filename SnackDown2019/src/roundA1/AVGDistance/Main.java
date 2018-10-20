package roundA1.AVGDistance;

import java.io.IOException;
import java.util.*;

public class Main {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distance(Pair pair) {
            return Math.abs(x - pair.x) + Math.abs(y - pair.y);
        }
    }

    static class Test {
        List<Pair> list;
        int row;
        int col;

        public Test(List<Pair> list, int row, int col) {
            this.list = list;
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            Integer testCases = Integer.valueOf(r.readLine());
            ArrayList<Test> tests = new ArrayList<>(testCases);
            for (int i = 0; i < testCases; i++) {
                String[] split = r.readLine().split(" ");
                int row = Integer.parseInt(split[0]);
                int col = Integer.parseInt(split[1]);
                LinkedList<Pair> objects = new LinkedList<>();
                for (int j = 0; j < row; j++) {
                    char[] numbers = r.readLine().toCharArray();
                    for (int k = 0; k < numbers.length; k++) {
                        if (numbers[k] == ('1')) {
                            objects.add(new Pair(j, k));
                        }
                    }
                }
                tests.add(new Test(objects, row, col));
            }
            for (Test test : tests) {
                calculate(test);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calculate(Test test) {
        Map<Integer, Integer> map = getMap(test.list);
        for (int i = 0; i < test.row + test.col - 2; i++) {
            System.out.print(map.getOrDefault(i + 1, 0) / 2 + (i < test.row + test.col - 3 ? " " : ""));
        }
    }

    private static int calculate(int d, List<Pair> list) {
        int count = 0;
        for (Pair pair : list) {
            count += list.stream().filter(p -> p.distance(pair) == d).count();
        }
        return count / 2;
    }

    static Map<Integer, Integer> getMap(List<Pair> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Pair source : list) {
            for (Pair des : list) {
                if (source != des) {
                    int distance = source.distance(des);
                    Integer count = map.getOrDefault(distance, 0);
                    map.put(distance, count + 1);
                }
            }
        }
        return map;
    }

}
