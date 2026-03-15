package timus.task_1683;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> result = new ArrayList<>();

        while (n > 1) {
            int fold = n / 2;
            result.add(fold);
            n -= fold;
        }

        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + (i < result.size() - 1 ? " " : "\n"));
        }
    }
}
