package R1b2018.Q5;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class Test {
        int N, K;
        int sets[][];

        public Test(int n, int k, int[][] sets) {
            N = n;
            K = k;
            this.sets = sets;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTc = scanner.nextInt();
        List<Test> tcs = new ArrayList<>();
        for (int i = 0; i < numberOfTc; i++) {
            int N = scanner.nextInt();
            int K = scanner.nextInt();
            int[][] set = new int[N][2];
            for (int j = 0; j < N; j++) {
                int[] a = new int[2];
                a[0] = scanner.nextInt();
                a[1] = scanner.nextInt();
                set[j] = a;
            }
            tcs.add(new Test(N, K, set));
        }
        for (Test test : tcs) {
            System.out.println(calculate3(test));
        }


//        int arr[][] = {{1, 6}, {2, 4}, {3, 6}, {4, 7}, {3, 5}};
//        int r = 3;
//        System.out.println(calculate3(new Test(3, r, arr)));

    }

    private static void reduce(int[][] array) {
        Iterator<int[]> iterator = Arrays.stream(array).iterator();
        while (iterator.hasNext()) {
            int[] segment = iterator.next();
        }
    }

    private static int calculate3(Test test) {
        int length = test.sets.length;
        int distance = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            int[] current = test.sets[i];
            Map<Integer, List<int[]>> map = new HashMap<>();
            for (int[] arr : test.sets) {
                if (current == arr) {
                    continue;
                }
                else {
                    Integer d = distance(arr, current);
                    List<int[]> list = map.getOrDefault(d, new LinkedList<>());
                    list.add(arr);
                    map.put(d, list);
                }

            }
            List<Integer> dList = map.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            dList = dList.subList(0, Math.min(test.K - 1, dList.size()));
            LinkedList<int[]> retain = new LinkedList<>();
            for (Integer index : dList) {
                retain.addAll(map.get(index));
//                if (retain.size() >= test.K) {
//                    break;
//                }
            }
            retain.add(current);
            int d = calculate(retain.toArray(new int[retain.size()][2]), test.K);
            if (distance < d) {
                distance = d;
            }
//            int[] merg = Arrays.copyOf(current, 2);
//            for (int j = 0; j < retain.size(); j++) {
//                merg = interSection(merg, retain.get(j));
//            }
//            int d = merg[1] - merg[0];
//            if (distance < d) {
//                distance = d;
//            }
        }
        return distance;
    }

    private static Integer distance(int[] arr, int[] current) {
        int[] ints = interSection(arr, current);
        return ints[1] - ints[0];
    }


//    private static int calculate2(Test test) {
//        int length = test.sets.length;
//        int[][] arr = Arrays.copyOf(test.sets, length);
//        int[][] joined = new int[length][test.K];//save joined element
//        for (int i = 1; i <= test.K; i++) {//join at k-time
//            for (int j = 0; j < length; j++) {//find best join for every element;
//                int[] segment = arr[j];
//                int[] current = new int[]{-1, -1};
//                //find bestjoin;
//                for (int k = 0; k < length; k++) {
//                    int finalK = k;
//                    boolean joinAlready = Arrays.stream(joined[j]).boxed().anyMatch(join -> join == finalK);
//                    if (joinAlready) {
//                        continue;
//                    }
//                    int[] inter = interSection(segment, test.sets[k]);
//                    if (current[0] == -1 || inter[1] - inter[0] > current[1] - current[0]) {
//                        current = inter;
//                        joined[j][i-1] = k;
//                    }
//                }
//                arr[j] = current;
//            }
//        }
//        int[] current = new int[]{-1, -1};
//        for (int i = 0; i < length; i++) {
//            int[] segment = arr[i];
//            if (current[0] == -1 || segment[1] - segment[0] > current[1] - current[0]) {
//                current = segment;
//            }
//        }
//        return current[1] - current[0];
//    }


    private static int calculate(int[][] arr, int r) {

        List<int[][]> ret = getCombination(arr, r);

        int distance = 0;
        int[][] best = new int[r][2];

        for (int[][] a : ret) {
            int left = Integer.MIN_VALUE;
            int right = Integer.MAX_VALUE;
            for (int i = 0; i < a.length; i++) {
                int left1 = a[i][0];
                if (left < left1) {
                    left = left1;
                }
                int right1 = a[i][1];
                if (right > right1) {
                    right = right1;
                }
            }
            int d = right - left;
            if (distance < d) {
                distance = d;
//                for (int i = 0; i < a.length; i++) {
//                    System.out.print("{" + a[i][0] + ',' + a[i][1] + "}");
//                }
//                System.out.println("");
            }
        }
        return distance;

    }

    private static int[] interSection(int[] current, int[] ints) {
        int left = Math.max(current[0], ints[0]);
        int right = Math.min(current[1], ints[1]);
        return new int[]{left, right};
    }

    static void combinationUtil(int arr[][], int r,
                                int index, int data[][], int i, List<int[][]> result) {
        // Current combination is ready to be printed,
        // print it
        int n = arr.length;
        if (index == r) {
            int[][] a = new int[data.length][2];
            result.add(Arrays.copyOf(data, data.length));
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n) {
            return;
        }

        // current is included, put next at next
        // location
        data[index] = arr[i];
        combinationUtil(arr, r, index + 1,
                data, i + 1, result);

        // current is excluded, replace it with
        // next (Note that i+1 is passed, but
        // index is not changed)
        combinationUtil(arr, r, index, data, i + 1, result);
    }

    // The main function that prints all combinations
    // of size r in arr[] of size n. This function
    // mainly uses combinationUtil()
    static List<int[][]> getCombination(int[][] arr, int r) {
        // A temporary array to store all combination
        // one by one
        int data[][] = new int[r][2];
        List<int[][]> result = new LinkedList<>();

        // Print all combination using temprary
        // array 'data[]'
        combinationUtil(arr, r, 0, data, 0, result);
        return result;
    }
}
