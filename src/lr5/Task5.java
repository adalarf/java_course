package lr5;

import java.util.List;
import java.util.stream.Collectors;


public class Task5 {
    public static List<String> filterBySubstring(List<String> list, String substring) {
        return list.stream()
                .filter(s -> s.contains(substring))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> words = List.of("Test", "Some text", "text", "123");
        String sub = "text";

        List<String> result = filterBySubstring(words, sub);

        System.out.println("Исходный список: " + words);
        System.out.println("Список с подстрокой \"" + sub + "\": " + result);
    }
}
