package lr3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Task6 {
    public static void main(String[] args) {
        int N = 100000;

        long startAL = System.nanoTime();
        int survivorAL = ArrayListSolution.solve(N);
        long timeAL = System.nanoTime() - startAL;

        long startLL = System.nanoTime();
        int survivorLL = LinkedListSolution.solve(N);
        long timeLL = System.nanoTime() - startLL;

        System.out.println("ArrayList:");
        System.out.printf("Оставшийся: %d Время выполнения: %.2f мс%n", survivorAL, timeAL / 1000000.0);
        System.out.println("LinkedList:");
        System.out.printf("Оставшийся: %d Время выполнения: %.2f мс%n", survivorLL, timeLL / 1000000.0);
    }
}

class ArrayListSolution {
    public static int solve(int n) {
        List<Integer> circle = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) circle.add(i);

        int index = 0;
        while (circle.size() > 1) {
            index = (index + 1) % circle.size();
            circle.remove(index);
        }
        return circle.get(0);
    }
}

class LinkedListSolution {
    public static int solve(int n) {
        List<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= n; i++) circle.add(i);

        int index = 0;
        while (circle.size() > 1) {
            index = (index + 1) % circle.size();
            circle.remove(index);
        }
        return circle.get(0);
    }
}