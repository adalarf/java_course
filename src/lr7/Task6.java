package lr7;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.print("Введите имя файла: ");
        String fileName = in.nextLine();

        System.out.print("Введите слово для поиска: ");
        String word = in.nextLine();

        try (BufferedReader br = Files.newBufferedReader(Path.of(fileName), StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 1;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.contains(word)) {
                    System.out.println(lineNumber + ": " + line);
                    found = true;
                }
                lineNumber++;
            }

            if (!found) {
                System.out.println("Строки с этим словом не найдены");
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
    }
}
