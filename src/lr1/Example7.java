package lr1;

import java.util.Scanner;

public class Example7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Input your name: ");
        String name = in.nextLine();

        System.out.print("Input your age: ");
        int age = in.nextInt();

        System.out.println("Hello, " + name + "! You're " + age + " years old");

        in.close();
    }
}
