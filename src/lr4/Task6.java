package lr4;

public class Task6 {
    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (ArithmeticException e) {
            System.out.println("1");
        } catch (RuntimeException e) { // ошибка нарушения порядка catch-блоков
            System.out.println("2");   // должно быть от общего к частному
        } catch (Exception e) {        // RuntimeException и Exception поменяны местами
            System.out.println("3");
        }
        System.out.println("4");
    }
}
