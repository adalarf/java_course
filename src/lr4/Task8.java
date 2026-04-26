package lr4;

public class Task8 {
    public static int m() { // метод m() выбрасывает RuntimeException, но оно не ловится
        try {
            System.out.println("0");
            throw new RuntimeException();
        } finally {
            System.out.println("1");
        }
    }

    public static void main(String[] args) {
        try { // перехватываем исключение
            System.out.println(m());
        } catch (RuntimeException e) {
            System.out.println("Перехвачено: " + e);
        }
    }

}
