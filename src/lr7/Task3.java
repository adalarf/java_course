package lr7;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class Task3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.print("Введите имя файла: ");
        String fileName = in.nextLine();

        long count = 0;

        try (BufferedReader br = Files.newBufferedReader(Path.of(fileName), StandardCharsets.UTF_8)) {
            while (br.readLine() != null) {
                count++;
            }
            System.out.println("Количество строк: " + count);
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
    }
}
