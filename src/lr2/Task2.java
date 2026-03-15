package lr2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner id = new Scanner(System.in);

        System.out.print("Введите количество строк: ");
        int n = id.nextInt();

        System.out.print("Введите количество столбцов: ");
        int m = id.nextInt();

        int[][] arr = new int[n][m];
        int value = 1;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = value++;
                }
            }
            else {
                for (int j = m - 1; j >= 0; j--) {
                    arr[i][j] = value++;
                }
            }
        }

        System.out.println("Массив:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%4d", arr[i][j]);
            }
            System.out.println();
        }
    }
}
