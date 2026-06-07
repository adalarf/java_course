package timus.task_1545;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();

        List<String> hieroglyphs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            hieroglyphs.add(in.nextLine().trim());
        }

        String letter = in.nextLine().trim();

        for (String hieroglyph : hieroglyphs) {
            if (hieroglyph.startsWith(letter)) {
                System.out.println(hieroglyph);
            }
        }
    }
}
