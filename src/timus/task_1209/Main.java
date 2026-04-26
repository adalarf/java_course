package timus.task_1209;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        if (in.hasNextInt()) {
            int n = in.nextInt();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < n; i++) {
                long k = in.nextLong();
                result.append(findDigit(k));
                if (i < n - 1) {
                    result.append(" ");
                }
            }
            System.out.println(result.toString());
        }
    }

    private static String findDigit(long k) {
        long left = 0;
        long right = 100000;
        long m = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long len = (mid + 1) * (mid + 2) / 2;

            if (len >= k) {
                m = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long prevLen = m * (m + 1) / 2;
        long posInBlock = k - prevLen;
        return (posInBlock == 1) ? "1" : "0";
    }
}
