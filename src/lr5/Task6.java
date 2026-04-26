package lr5;

import java.util.List;
import java.util.stream.Collectors;

public class Task6 {
    public static List<Integer> filterDivisible(List<Integer> list, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Делитель не может быть равен нулю");
        }
        return list.stream()
                .filter(n -> n % divisor == 0)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 5, 6, 3, 9, 10);
        int divisor = 3;

        List<Integer> result = filterDivisible(numbers, divisor);

        System.out.println("Исходный список: " + numbers);
        System.out.println("Делятся на " + divisor + " без остатка: " + result);
    }
}
