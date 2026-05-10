package lr6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Task5 {
    public static int findMaxWithThreads(int[] array) throws InterruptedException, ExecutionException {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("массив не может быть пустым или null");
        }

        int numCores = getNumberOfCores();
        int chunkSize = calculateChunkSize(array.length, numCores);

        ExecutorService executor = createExecutor(numCores);
        List<Future<Integer>> futures = submitTasks(executor, array, chunkSize);

        int globalMax = collectResults(futures);
        executor.shutdown();

        return globalMax;
    }


    private static int getNumberOfCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    private static int calculateChunkSize(int arrayLength, int numCores) {
        return Math.max(1, arrayLength / numCores);
    }

    private static ExecutorService createExecutor(int numThreads) {
        return Executors.newFixedThreadPool(numThreads);
    }

    private static List<Future<Integer>> submitTasks(ExecutorService executor, int[] array, int chunkSize) {
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < array.length; i += chunkSize) {
            final int start = i;
            final int end = Math.min(i + chunkSize, array.length);

            Callable<Integer> task = () -> findLocalMax(array, start, end);
            futures.add(executor.submit(task));
        }

        return futures;
    }

    private static int findLocalMax(int[] array, int start, int end) {
        int max = Integer.MIN_VALUE;
        for (int j = start; j < end; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }
        return max;
    }

    private static int collectResults(List<Future<Integer>> futures) throws InterruptedException, ExecutionException {
        int globalMax = Integer.MIN_VALUE;
        for (Future<Integer> future : futures) {
            int localMax = future.get();
            if (localMax > globalMax) {
                globalMax = localMax;
            }
        }
        return globalMax;
    }

    public static void main(String[] args) {
        int[] array = {1, 7, 5, 9, 10, 13, -4, 6, 5, 10, -1, 15, 0};

        try {
            int max = findMaxWithThreads(array);
            System.out.println("максимальный элемент: " + max);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
