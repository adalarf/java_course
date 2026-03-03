package lr1;

import java.util.Scanner;

public class Example10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int currentYear = 2026;

        System.out.print("Введите ваш год рождения: ");
        int birthYear = in.nextInt();
        int age = currentYear - birthYear;

        System.out.println("Ваш возраст: " + age + " лет");

        in.close();
    }
}
