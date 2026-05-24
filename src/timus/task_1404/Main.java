package timus.task_1404;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String encrypted = in.nextLine();
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }

    public static String decrypt(String encrypted) {
        int n = encrypted.length();
        int[] encryptedNums = new int[n];
        int[] originalNums = new int[n];

        for (int i = 0; i < n; i++) {
            encryptedNums[i] = encrypted.charAt(i) - 'a';
        }

        originalNums[0] = (encryptedNums[0] - 5 + 26) % 26;

        for (int i = 1; i < n; i++) {
            originalNums[i] = (encryptedNums[i] - encryptedNums[i-1] + 26) % 26;
        }

        StringBuilder result = new StringBuilder();
        for (int num : originalNums) {
            result.append((char)('a' + num));
        }

        return result.toString();
    }
}
