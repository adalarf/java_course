package lr3;
import java.util.Scanner;


public class Task3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = in.nextInt();
        int[] array = new int[size];

        System.out.println("Введите элементы массива:");
        readArrayRecursively(array, 0, in);

        System.out.print("Содержимое массива: ");
        printArrayRecursively(array, 0);

        in.close();
    }


    private static void readArrayRecursively(int[] arr, int index, Scanner sc) {
        if (index >= arr.length) {
            return;
        }
        System.out.print("arr[" + index + "] = ");
        arr[index] = sc.nextInt();
        readArrayRecursively(arr, index + 1, sc);
    }

    private static void printArrayRecursively(int[] arr, int index) {
        if (index >= arr.length) {
            System.out.println();
            return;
        }
        System.out.print(arr[index] + " ");
        printArrayRecursively(arr, index + 1);
    }
}
