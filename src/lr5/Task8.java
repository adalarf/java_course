package lr5;

import java.util.List;
import java.util.stream.Collectors;

public class Task8 {
    public static List<Integer> filterGreaterThan(List<Integer> list, int threshold) {
        return list.stream()
                .filter(n -> n > threshold)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 10, 20, 50);
        int threshold = 10;

        List<Integer> result = filterGreaterThan(numbers, threshold);

        System.out.println("Исходный список: " + numbers);
        System.out.println("Числа больше " + threshold + ": " + result);
    }
}
