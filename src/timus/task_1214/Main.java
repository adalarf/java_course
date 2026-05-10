package timus.task_1214;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int X = in.nextInt();
        int Y = in.nextInt();

        if (X <= 0 || Y <= 0) {
            System.out.println(X + " " + Y);
            return;
        }

        int sum = X + Y;
        if (sum % 2 == 0) {
            System.out.println(X + " " + Y);
        } else {
            System.out.println(Y + " " + X);
        }
    }
}
