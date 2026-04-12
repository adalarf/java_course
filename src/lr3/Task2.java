package lr3;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите целое число: ");
        if (in.hasNextInt()) {
            int number = in.nextInt();

            System.out.print("Двоичное представление: ");

            if (number == 0) {
                System.out.println(0);
            } else {
                printBinary(number);
                System.out.println();
            }
        } else {
            System.out.println("Ошибка: введено не целое число.");
        }

        in.close();
    }

    public static void printBinary(int n) {
        if (n > 0) {
            printBinary(n / 2);
            System.out.print(n % 2);
        }
    }
}
