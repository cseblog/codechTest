package round1b2017.Q1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static void check(String data){
        int m = 0;
        int s = 0;
        int detla = 0;
        char tmp = 'x';
        for(int i = 0; i < data.length(); i++){
            if(data.charAt(i) == 's')
                s++;
            //
            if(data.charAt(i) == 'm')
                m++;

            if(tmp != data.charAt(i) && tmp != 'x'){
                detla++;
                tmp = 'x'; //reset
            } else {
                tmp = data.charAt(i);
            }


        }


        if (m > s - detla){
            System.out.println("mongooses");
        }
        if(m < (s-detla)){
            System.out.println("snakes");
        }

        if(m == (s-detla)){
            System.out.println("tie");
        }
    }
    public static void main(String[] args) {
        int numberOfTc = 0;
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try {
            numberOfTc = Integer.valueOf(r.readLine());
            List<String> tcs = new ArrayList<>();
            while( numberOfTc > 0) {
                tcs.add(r.readLine());
                numberOfTc--;
            }

            for (String data: tcs){
                check(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
