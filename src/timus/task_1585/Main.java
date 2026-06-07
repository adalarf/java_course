package timus.task_1585;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();

        Map<String, Integer> penguinCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String species = in.nextLine();
            penguinCount.put(species, penguinCount.getOrDefault(species, 0) + 1);
        }

        String mostPopular = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : penguinCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopular = entry.getKey();
            }
        }

        System.out.println(mostPopular);
    }
}
