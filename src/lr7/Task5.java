package lr7;

import java.util.Scanner;
import java.io.File;

public class Task5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите имя файла: ");
        String fileName = in.nextLine();

        File file = new File(fileName);

        if (file.exists() && file.isFile()) {
            System.out.println("Размер файла: " + file.length() + " байт");
        } else {
            System.out.println("Файл не найден");
        }
    }
}
