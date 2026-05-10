package lr6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Task6 {
    public static long sumWithThreads(int[] array) throws InterruptedException, ExecutionException {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("массив не может быть пустым или null");
        }

        int numCores = Runtime.getRuntime().availableProcessors();
        int chunkSize = Math.max(1, array.length / numCores);

        ExecutorService executor = Executors.newFixedThreadPool(numCores);
        List<Future<Long>> futures = submitSumTasks(executor, array, chunkSize);

        long totalSum = collectSums(futures);

        executor.shutdown();
        return totalSum;
    }

    private static List<Future<Long>> submitSumTasks(ExecutorService executor, int[] array, int chunkSize) {
        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < array.length; i += chunkSize) {
            final int start = i;
            final int end = Math.min(i + chunkSize, array.length);

            Callable<Long> task = () -> computeLocalSum(array, start, end);
            futures.add(executor.submit(task));
        }

        return futures;
    }

    private static long computeLocalSum(int[] array, int start, int end) {
        long localSum = 0;
        for (int j = start; j < end; j++) {
            localSum += array[j];
        }
        return localSum;
    }

    private static long collectSums(List<Future<Long>> futures) throws InterruptedException, ExecutionException {
        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }
        return totalSum;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        try {
            long sum = sumWithThreads(array);
            System.out.println("сумма элементов: " + sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
