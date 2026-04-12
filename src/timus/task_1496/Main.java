package timus.task_1496;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();

        Map<String, Integer> submissionCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String team = in.nextLine();
            submissionCount.put(team, submissionCount.getOrDefault(team, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : submissionCount.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey());
            }
        }
    }
}
