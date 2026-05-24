package lr7;

import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.print("Введите имя файла: ");
        String fileName = in.nextLine();

        System.out.print("Введите текст: ");
        String text = in.nextLine();

        try {
            Files.writeString(Path.of(fileName), text, StandardCharsets.UTF_8);
            System.out.println("Текст записан");
            System.out.println("Количество символов: " + text.length());
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }
    }
}
