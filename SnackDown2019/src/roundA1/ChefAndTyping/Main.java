package roundA1.ChefAndTyping;

import java.io.IOException;
import java.util.*;

public class Main {
    static Map<Character, Boolean> hands = new HashMap<>();

    public static void main(String[] args) {
        hands.put('d', false);//left
        hands.put('f', false);//left
        hands.put('j', true);//right
        hands.put('k', true);//right

        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            Integer testCases = Integer.valueOf(r.readLine());
            List<List<String>> tests = new ArrayList<>(testCases);
            for (int i = 0; i < testCases; i++) {
                int n = Integer.valueOf(r.readLine());//N number
                LinkedList<String> aTest = new LinkedList<>();
                for (int j = 0; j < n; j++) {
                    String text = r.readLine();
                    aTest.add(text);
                }
                tests.add(aTest);
            }
            for (List<String> test : tests) {
                System.out.println(calculate(test));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(calculate(Arrays.asList("fdjkd", "dfjdk", "dfd", "fdjkd", "kkjjk")));
    }

    private static int calculate(List<String> test) {
        int time = 0;
        Map<String, Double> typed = new HashMap<>();
        for (String text : test) {
            double calculated = calculate(text);
            if (typed.containsKey(text)) {
                time += calculated / 2;
            }
            else {
                typed.put(text, calculated);
                time += calculated;
            }

        }
        return time;
    }

    private static int calculate(String text) {
        int time = 0;
        Character current = null;

        for (Character character : text.toCharArray()) {
            if (current == null) {
                time += 2;
            }
            else {
                if (hands.get(character) == hands.get(current)) {//same hand
                    time += 4;
                }
                else {
                    time += 2;
                }
            }
            current = character;
        }
        return time;
    }
}
