package timus.task_1079;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int n = in.nextInt();
            if (n == 0) {
                break;
            }
            int[] a = new int[n + 1];

            a[0] = 0;
            if (n >= 1) {
                a[1] = 1;
            }

            for (int i = 1; 2 * i <= n; i++) {
                if (2 * i <= n) {
                    a[2 * i] = a[i];
                }
                if (2 * i + 1 <= n) {
                    a[2 * i + 1] = a[i] + a[i + 1];
                }
            }

            int maxVal = a[0];
            for (int i = 0; i <= n; i++) {
                if (a[i] > maxVal) {
                    maxVal = a[i];
                }
            }

            System.out.println(maxVal);
        }
    }
}
