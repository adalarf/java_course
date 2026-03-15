package lr2;

import java.util.Scanner;

class Encrypt {

    public static String getEncryptString(String encryptString, int shift) {
        char[] arrayChar = encryptString.toCharArray();
        long[] arrayInt = new long[arrayChar.length];
        char[] arrayCharNew = new char[arrayChar.length];
        for (int i = 0; i < arrayChar.length; i++) {
            arrayInt[i] = arrayChar[i] + shift;
            arrayCharNew[i] = (char) arrayInt[i];
        }
        encryptString = new String(arrayCharNew);

        return encryptString;
    }
}

public class Task3 {
    public static void main(String[] args) {

        Scanner id = new Scanner(System.in);

        System.out.println("Введите текст для шифрования:");
        String text = id.nextLine();

        System.out.println("Введите ключ:");
        int key = id.nextInt();
        id.nextLine();

        String encrypted = Encrypt.getEncryptString(text, key);
        System.out.println("Текст после преобразования: " + encrypted);

        while (true) {
            System.out.println("Выполнить обратное преобразование? (y/n)");
            String answer = id.nextLine();

            if (answer.equals("y")) {
                String decrypted = Encrypt.getEncryptString(encrypted, -key);
                System.out.println("Обратное преобразование: " + decrypted);
                break;
            }
            else if (answer.equals("n")) {
                System.out.println("До свидания!");
                break;
            }
            else {
                System.out.println("Введите корректный ответ");
            }
        }

    }
}
