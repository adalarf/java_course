package lr1;

import java.util.Scanner;

public class Example15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        int num1 = in.nextInt();

        System.out.print("Введите второе число: ");
        int num2 = in.nextInt();

        int sum = num1 + num2;
        int difference = num1 - num2;

        System.out.println("Сумма чисел " + num1 + " и " + num2 + " равна: " + sum);
        System.out.println("Разность чисел " + num1 + " и " + num2 + " равна: " + difference);

        in.close();
    }
}
