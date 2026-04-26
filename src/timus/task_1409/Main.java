package timus.task_1409;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int harryShot = in.nextInt();
        int larryShot = in.nextInt();

        int harryMissed = larryShot - 1;
        int larryMissed = harryShot - 1;

        System.out.println(harryMissed + " " + larryMissed);
    }
}
