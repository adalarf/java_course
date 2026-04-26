package lr4;

public class Task12 {
    public static void m(String str, double chislo) {
        if (str == null) {
            throw new IllegalArgumentException("Строка введена неверно");
        }
        if (chislo > 0.001) {
            throw new IllegalArgumentException("Неверное число");
        }
    }

    public static void main(String[] args) {
        try { // не перехватывается IllegalArgumentException
            m(null, 0.000001);
        } catch (IllegalArgumentException e) {
            System.out.println("ошибка: " + e.getMessage());
        }
        System.out.println("программа продолжается");
    }

}
