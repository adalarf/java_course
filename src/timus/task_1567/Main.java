package timus.task_1567;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String slogan = in.nextLine();
        int[] costs = new int[256];

        String[] buttonGroups = {
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqr",
                "stu",
                "vwx",
                "yz",
                ".,!",
                " "
        };

        for (int i = 0; i < buttonGroups.length; i++) {
            String group = buttonGroups[i];
            for (int j = 0; j < group.length(); j++) {
                costs[group.charAt(j)] = j + 1;
            }
        }

        int totalCost = 0;
        for (int i = 0; i < slogan.length(); i++) {
            totalCost += costs[slogan.charAt(i)];
        }

        System.out.println(totalCost);
    }
}
