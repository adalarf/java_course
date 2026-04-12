package lr3;

public class Task1 {

    public static void example1(int x) {
        System.out.println("x=" + x);
        if ((2 * x + 1) < 20) {
            example1(2 * x + 1);
        }
    }

    public static void example2(int x) {
        if ((2 * x + 1) < 20) {
            example2(2 * x + 1);
        }
        System.out.println("x=" + x);
    }

    private static int step = 0;

    public static void example3(int x) {
        space();
        System.out.println("" + x + "-> ");
        step++;
        if ((2 * x + 1) < 20) {
            example3(2 * x + 1);
        }
        step --;
        space();
        System.out.println("" + x + "<-");
    }

    public static void space() {
        for (int i = 0; i < step; i++) {
            System.out.print(" ");
        }
    }


    public static int example4(int n) {
        int result;
        if (n == 1) return 1;
        else {
            result = example4(n - 1) * n;
            return result;
        }
    }


    public static int example5(int n) {
//        if (n == 0) {
//            return 0;
//        } else if (n == 1) {
//            return 1;
//        } else {
//            return example5(n - 2) + example5(n - 1);
//        }

        space();
        System.out.println(n + "-> ");
        step++;

        int result;
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            result = example5(n - 2) + example5(n - 1);
        }

        step--;
        space();
        System.out.println(n + "<-");

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Первый пример:");
        example1(1);
        System.out.println("Второй пример:");
        example2(2);
        System.out.println("Третий пример:");
        example3(1);
        System.out.println("Четвертый пример:");
        System.out.println(example4(5));
        System.out.println("Пятый пример:");
        System.out.println(example5(5));

    }
}
