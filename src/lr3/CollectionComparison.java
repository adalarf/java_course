package lr3;

import java.util.*;

public class CollectionComparison {

    private static final int SIZE = 18_000_000;

    public static void main(String[] args) {
        System.out.println("Количество элементов: " + SIZE);
        System.out.println();

        testArrayList();
        testLinkedList();
        testTreeSet();
    }

    private static void testArrayList() {
        System.out.println("=== ArrayList ===");
        List<Integer> list = new ArrayList<>(SIZE);

        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        System.out.println("Добавление в конец: " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            list.add(0, i);
        }
        System.out.println("Добавление в начало (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        int mid = list.size() / 2;
        for (int i = 0; i < 1000; i++) {
            list.add(mid, i + SIZE);
        }
        System.out.println("Добавление в середину (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            if (!list.isEmpty()) list.remove(0);
        }
        System.out.println("Удаление с начала (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            if (!list.isEmpty()) list.remove(list.size() - 1);
        }
        System.out.println("Удаление с конца (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        mid = list.size() / 2;
        for (int i = 0; i < 1000; i++) {
            if (mid < list.size()) {
                list.remove(mid);
            }
        }
        System.out.println("Удаление из середины (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            list.get(i % list.size());
        }
        System.out.println("Получение по индексу (10000): " + (System.currentTimeMillis() - start) + " мс");
        System.out.println();
    }

    private static void testLinkedList() {
        System.out.println("=== LinkedList ===");
        List<Integer> list = new LinkedList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            list.add(i);
        }
        System.out.println("Добавление в конец: " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            list.add(0, i);
        }
        System.out.println("Добавление в начало (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        int mid = list.size() / 2;
        for (int i = 0; i < 1000; i++) {
            list.add(mid, i + SIZE);
        }
        System.out.println("Добавление в середину (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            if (!list.isEmpty()) list.remove(0);
        }
        System.out.println("Удаление с начала (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            if (!list.isEmpty()) list.remove(list.size() - 1);
        }
        System.out.println("Удаление с конца (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        mid = list.size() / 2;
        for (int i = 0; i < 1000; i++) {
            if (mid < list.size()) {
                list.remove(mid);
            }
        }
        System.out.println("Удаление из середины (1000): " + (System.currentTimeMillis() - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            list.get(i % list.size());
        }
        System.out.println("Получение по индексу (10000): " + (System.currentTimeMillis() - start) + " мс");
        System.out.println();
    }

    private static void testTreeSet() {
        System.out.println("=== TreeSet ===");
        Set<Integer> set = new TreeSet<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            set.add(i);
        }
        System.out.println("Добавление: " + (System.currentTimeMillis() - start) + " мс");
        System.out.println("Добавление в начало/середину/конец: N/A (не поддерживается)");

        start = System.currentTimeMillis();
        Iterator<Integer> iterator = set.iterator();
        for (int i = 0; i < 1000 && iterator.hasNext(); i++) {
            iterator.next();
            iterator.remove();
        }
        System.out.println("Удаление (1000): " + (System.currentTimeMillis() - start) + " мс");
        System.out.println("Удаление с начала/середины/конца: N/A (не поддерживается)");

        System.out.println("Получение по индексу: N/A (не поддерживается)");
        System.out.println();
    }
}