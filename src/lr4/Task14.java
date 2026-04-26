package lr4;

public class Task14 {
    public static void m(int x) throws ArithmeticException {
        int h = 10 / x; // нигде не используется/не возвращается
    }

    public static void main(String[] args) {
        try {
            int l = args.length;
            System.out.println("размер массива= " + l);
            m(l);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Деление на ноль");
        }
    }

}
