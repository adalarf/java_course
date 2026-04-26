package lr4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] matrix = {
                {10, 20, 30},
                {40, 50, 60},
                {70, 80, 90}
        };

        System.out.println("Матрица 3x3. Введите номер столбца (0, 1 или 2):");
        try {
            System.out.print("Номер столбца: ");
            int col = in.nextInt();

            System.out.print("Содержимое столбца " + col + ": ");
            for (int row = 0; row < matrix.length; row++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();

        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: номер столбца должен быть целым числом");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка индекса: столбца с таким номером не существует");
        } finally {
            System.out.println("Завершение программы");
        }
    }
}
