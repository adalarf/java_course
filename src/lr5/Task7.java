package lr5;

import java.util.List;
import java.util.stream.Collectors;

public class Task7 {
    public static List<String> filterByLength(List<String> list, int minLength) {
        return list.stream()
                .filter(s -> s.length() > minLength)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> words = List.of("Test", "Some text", "text", "123");
        int minLen = 5;

        List<String> result = filterByLength(words, minLen);

        System.out.println("Исходный список: " + words);
        System.out.println("Строки длиннее " + minLen + " символов: " + result);
    }
}
