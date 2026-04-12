package timus.task_1893;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        int row = Integer.parseInt(s.substring(0, s.length() - 1));
        char c = s.charAt(s.length() - 1);

        if (row <= 2) {
            System.out.println((c == 'A' || c == 'D') ? "window" :
                    (c == 'B' || c == 'C') ? "aisle" : "neither");
        } else if (row <= 20) {
            System.out.println((c == 'A' || c == 'F') ? "window" :
                    (c >= 'B' && c <= 'E') ? "aisle" : "neither");
        } else {
            System.out.println((c == 'A' || c == 'K') ? "window" :
                    (c == 'C' || c == 'D' || c == 'G' || c == 'H') ? "aisle" : "neither");
        }
    }
}
