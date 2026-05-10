package lr6;

public class Task4 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            int threadNumber = i + 1;
            threads[i] = new Thread(() -> {
                System.out.println("поток №" + threadNumber);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("потоки завершены");
    }
}
