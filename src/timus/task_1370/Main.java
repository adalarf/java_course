package timus.task_1370;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int M = in.nextInt();

        int[] digits = new int[N];
        for (int i = 0; i < N; i++) {
            digits[i] = in.nextInt();
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int position = (M + i) % N;
            result.append(digits[position]);
        }

        System.out.println(result.toString());
    }
}
