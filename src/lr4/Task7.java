package lr4;

public class Task7 {
    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (NullPointerException e) {
            System.out.println("1");
            try { // ошибка: нельзя перехватить несколько исключений из catch в одном блоке с try
                throw new ArithmeticException(); // создадим вложенный try-catch
            } catch (ArithmeticException ex) {
                System.out.println("2");
            }
        }
        System.out.println("3");
    }
}
