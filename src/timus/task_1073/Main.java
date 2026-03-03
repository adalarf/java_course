package timus.task_1073;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] squaresNum = new int[n + 1];
        squaresNum[0] = 0;

        for (int i = 1; i <= n; i++) {
            squaresNum[i] = i;
            for (int j = 1; j * j <= i; j++) {
                squaresNum[i] = Math.min(squaresNum[i], squaresNum[i - j * j] + 1);
            }
        }

        System.out.println(squaresNum[n]);
    }
}
