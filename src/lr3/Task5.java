package lr3;

import java.util.HashMap;
import java.util.Map;


public class Task5 {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "zero");
        map.put(1, "first");
        map.put(2, "second");
        map.put(3, "third");
        map.put(4, "fourth");
        map.put(5, "fifth");
        map.put(6, "sixth");
        map.put(7, "seventh");
        map.put(8, "eighth");
        map.put(9, "ninth");

        System.out.println("\nСтроки с ключом > 5");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() > 5) {
                System.out.println("Ключ: " + entry.getKey() + " Значение: \"" + entry.getValue() + "\"");
            }
        }

        System.out.println("\nСтроки с ключом = 0");
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() == 0) {
                if (result.length() > 0) {
                    result.append(", ");
                }
                result.append(entry.getValue());
            }
        }
        System.out.println(result.length() > 0 ? result.toString() : "Нет записей с ключом 0");

        System.out.println("\nПроизведение ключей, где длина строки > 5");
        long product = 1;
        boolean found = false;

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().length() > 5) {
                System.out.println("Ключ: " + entry.getKey() + " Значение: \"" + entry.getValue() +
                        "\" (длина: " + entry.getValue().length() + ")");
                product *= entry.getKey();
                found = true;
            }
        }

        if (found) {
            System.out.println("Итоговое произведение: " + product);
        } else {
            System.out.println("Не найдено строк длиной > 5, произведение = 0");
        }
    }
}
