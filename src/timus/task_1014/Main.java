package timus.task_1014;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        if (n == 0) {
            System.out.println(10);
            return;
        }

        if (n == 1) {
            System.out.println(1);
            return;
        }

        long result = 0;
        long multiplier = 1;

        for (int i = 9; i >= 2; i--) {
            while (n % i == 0) {
                n /= i;
                result += i * multiplier;
                multiplier *= 10;
            }
        }

        if (n != 1) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}
