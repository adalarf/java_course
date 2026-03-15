package timus.task_1794;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        int[] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int k = ((i - a[i] + n) % n) + 1;
            count[k]++;
        }

        int bestK = 1;
        int maxCount = count[1];
        for (int k = 2; k <= n; k++) {
            if (count[k] > maxCount) {
                maxCount = count[k];
                bestK = k;
            }
        }

        System.out.println(bestK);
    }
}
