package lr4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        final int SIZE = 5;

        System.out.println("Введите " + SIZE + " целых чисел:");
        try {
            for (int i = 0; i < SIZE; i++) {
                System.out.print("Элемент " + (i + 1) + ": ");
                int num = in.nextInt();
                if (num > 0) {
                    sum += num;
                    count++;
                }
            }

            int average = sum / count;
            System.out.println("Среднее значение положительных элементов: " + average);

        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: ожидается целое число");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления: положительные элементы отсутствуют");
        } finally {
            System.out.println("Завершение программы");
        }
    }
}
