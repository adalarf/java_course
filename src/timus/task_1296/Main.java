package timus.task_1296;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int maxSum = 0;
        int currentSum = 0;

        for (int i = 0; i < n; i++) {
            int p = in.nextInt();

            currentSum += p;

            if (currentSum < 0) {
                currentSum = 0;
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        System.out.println(maxSum);
    }
}
