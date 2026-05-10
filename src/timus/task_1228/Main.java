package timus.task_1228;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long s = in.nextLong();

        long[] D = new long[n];
        for (int i = 0; i < n; i++) {
            D[i] = in.nextLong();
        }

        long[] k = new long[n];

        k[0] = s / D[0] - 1;

        for (int i = 1; i < n; i++) {
            k[i] = D[i-1] / D[i] - 1;
        }

        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(k[i]);
        }
        System.out.println();
    }
}
