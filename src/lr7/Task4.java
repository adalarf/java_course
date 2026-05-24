package lr7;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Task4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите имя исходного файла: ");
        Path source = Path.of(in.nextLine());

        System.out.print("Введите имя файла-приёмника: ");
        Path target = Path.of(in.nextLine());

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован");
        } catch (IOException e) {
            System.out.println("Ошибка копирования: " + e.getMessage());
        }
    }
}
