package lr1;

import java.util.Scanner;

public class Example14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите число: ");
        int num = in.nextInt();

        int numMinus1 = num - 1;
        int numPlus1 = num + 1;
        int sumFirstThree = numMinus1 + num + numPlus1;
        int fourthNumber = sumFirstThree * sumFirstThree;

        System.out.println(numMinus1 + " " + num + " " + numPlus1 + " " + fourthNumber);

        in.close();
    }
}
