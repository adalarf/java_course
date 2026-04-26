package lr4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task17 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Размер массива: ");
            int n = in.nextInt();
            byte[] arr = new byte[n];

            System.out.println("Введите элементы (от -128 до 127):");
            for (int i = 0; i < n; i++) arr[i] = in.nextByte();

            int sum = 0;
            for (byte b : arr) {
                int next = sum + b;
                if (next > Byte.MAX_VALUE || next < Byte.MIN_VALUE)
                    throw new ArithmeticException("Переполнение типа byte при вычислении");
                sum = next;
            }
            System.out.println("Сумма: " + sum);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: не число или выход за диапазон byte.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления: " + e.getMessage());
        } finally {
            System.out.println("Завершение программы");
        }
    }
}
