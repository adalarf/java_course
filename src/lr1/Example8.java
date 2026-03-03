package lr1;

import java.util.Scanner;

public class Example8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите название дня недели: ");
        String dayOfWeek = in.nextLine();

        System.out.print("Введите название месяца: ");
        String month = in.nextLine();

        System.out.print("Введите дату: ");
        int date = in.nextInt();

        System.out.println("Сегодня: " + dayOfWeek + ", " + date + " " + month);

        in.close();
    }
}
