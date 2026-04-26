package lr5;

import java.util.List;
import java.util.stream.Collectors;

public class Task9 {
    public static List<String> filterLettersOnly(List<String> list) {
        return list.stream()
                .filter(s -> s != null && s.matches("[a-zA-Zа-яА-ЯёЁ]+"))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> words = List.of("Test", "Some text", "text", "123");

        List<String> result = filterLettersOnly(words);

        System.out.println("Исходный список: " + words);
        System.out.println("Только буквы: " + result);
    }
}
