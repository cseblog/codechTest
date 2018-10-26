package round1b2017.Q2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static class Test {
        List<int[]> minList = new ArrayList<>();
        List<int[]> maxList = new ArrayList<>();
        int min;
        int max;

        public Test(List<int[]> minList, List<int[]> maxList, int min, int max) {
            this.minList = minList;
            this.maxList = maxList;
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTc = in.nextInt();
        List<Test> tcs = new ArrayList<>();
        while (numberOfTc > 0) {
            int row = in.nextInt();
            int col = in.nextInt();
//                Integer[][] test = new Integer[row][col];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            List<int[]> minList = new ArrayList<>();
            List<int[]> maxList = new ArrayList<>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int number = in.nextInt();
                    min = getMin(min, minList, i, j, number);
                    max = getMax(max, maxList, i, j, number);
//                        test[i][j] = number;
                }
            }
            tcs.add(new Test(minList, maxList, min, max));
            numberOfTc--;
        }
        for (Test test : tcs) {
            if (test.min == test.max) {
                System.out.println(0);
            }
            else {
                int distable = 0;
                for (int[] minPosition : test.minList) {
                    int minDistance = Integer.MAX_VALUE;
                    for (int[] maxPosition : test.maxList) {
                        int d = Math.max(Math.abs(maxPosition[0] - minPosition[0]), Math.abs(maxPosition[1] - minPosition[1]));
                        if (d < minDistance) {
                            minDistance = d;
                        }
                    }
                    if (minDistance > distable) {
                        distable = minDistance;
                    }

                }
                System.out.println(distable);
            }
        }
    }
//1
//4 4
//1 0 1 2
//1 2 1 3
//1 1 2 0
//0 1 2 2

    private static int getMax(int max, List<int[]> minList, int i, int j, int number) {
        if (number > max) {
            max = number;
            minList.clear();
            int[] position = {i, j};
            minList.add(position);
        }
        else if (number == max) {
            int[] position = {i, j};
            minList.add(position);
        }
        return max;
    }

    private static int getMin(int min, List<int[]> minList, int i, int j, int number) {
        if (number < min) {
            min = number;
            minList.clear();
            int[] position = {i, j};
            minList.add(position);
        }
        else if (number == min) {
            int[] position = {i, j};
            minList.add(position);
        }

        return min;
    }

}
