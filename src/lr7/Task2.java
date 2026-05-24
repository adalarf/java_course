package lr7;

import java.io.*;
import java.util.Scanner;

public class Task2 {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1 пример");
        fileExample();
        System.out.println("===========================");

        System.out.println("2 пример");
        byteStreamExample();
        System.out.println("===========================");

        System.out.println("3 пример");
        charStreamExample();
        System.out.println("===========================");

        System.out.println("4 пример");
        bufferedStreamExample();
        System.out.println("===========================");

        System.out.println("5 пример");
        adapterExample();
        System.out.println("===========================");

        System.out.println("6 пример");
        printWriterExample();
        System.out.println("===========================");
    }

    public static void fileExample() {
        System.out.print("Введите имя папки: ");
        String folderName = in.nextLine();
        File folder = new File(folderName);

        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Папка создана: " + folder.getAbsolutePath());
            } else {
                System.out.println("Не удалось создать папку: " + folder.getAbsolutePath());
            }
        } else {
            System.out.println("Папка уже существует: " + folder.getAbsolutePath());
        }

        System.out.print("Введите имя файла: ");
        String fileName = in.nextLine();
        File file = new File(folder, fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("Файл создан: " + file.getAbsolutePath());
            } else {
                System.out.println("Не удалось создать файл: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }

        System.out.print("Удалить файл и папку? (y/n): ");
        String answer = in.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            if (file.delete()) {
                System.out.println("Файл удален: " + file.getAbsolutePath());
            }
            if (folder.delete()) {
                System.out.println("Папка удалена: " + folder.getAbsolutePath());
            }
        }
    }

    public static void byteStreamExample() {
        System.out.print("Введите имя файла: ");
        String fileName = in.nextLine();

        System.out.print("Введите данные для записи: ");
        String data = in.nextLine();

        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            outputStream.write(data.getBytes());
            System.out.println("Данные записаны в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }

        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String readData = new String(buffer, 0, bytesRead);
            System.out.println("Прочитанные данные: " + readData);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        if (new File(fileName).delete()) {
            System.out.println("Файл удален: " + fileName);
        }
    }

    public static void charStreamExample() {
        System.out.print("Введите имя файла: ");
        String fileName = in.nextLine();

        System.out.print("Введите данные для записи: ");
        String data = in.nextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(data);
            System.out.println("Данные записаны в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }

        try (FileReader reader = new FileReader(fileName)) {
            char[] buffer = new char[1024];
            int charRead = reader.read(buffer);
            String readData = new String(buffer, 0, charRead);
            System.out.println("Прочитанные данные: " + readData);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        if (new File(fileName).delete()) {
            System.out.println("Файл удален: " + fileName);
        }
    }

    public static void bufferedStreamExample() {
        System.out.print("Введите имя файла: ");
        String fileName = in.nextLine();

        System.out.print("Введите данные для записи: ");
        String data = in.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
            System.out.println("Данные записаны в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            char[] buffer = new char[1024];
            int charRead = reader.read(buffer);
            String readData = new String(buffer, 0, charRead);
            System.out.println("Прочитанные данные: " + readData);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        if (new File(fileName).delete()) {
            System.out.println("Файл удален: " + fileName);
        }
    }

    public static void adapterExample() {
        System.out.print("Введите имя входного файла: ");
        String inputFileName = in.nextLine();
        System.out.print("Введите имя выходного файла: ");
        String outputFileName = in.nextLine();

        System.out.println("Введите данные для записи (введите 'END' на новой строке для завершения):");
        StringBuilder inputData = new StringBuilder();
        while (true) {
            String line = in.nextLine();
            if (line.equals("END")) break;
            inputData.append(line).append("\n");
        }

        try (FileWriter fw = new FileWriter(inputFileName)) {
            fw.write(inputData.toString());
        } catch (IOException e) {
            System.out.println("Не удалось создать входной файл: " + e.getMessage());
            return;
        }

        try (InputStream inputStream = new FileInputStream(inputFileName);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

             OutputStream outputStream = new FileOutputStream(outputFileName);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line.toUpperCase());
                bufferedWriter.newLine();
            }
            System.out.println("Данные записаны в файл: " + outputFileName);

            System.out.println("Результат в файле output (в верхнем регистре):");
            try (BufferedReader checkReader = new BufferedReader(new FileReader(outputFileName))) {
                String resLine;
                while ((resLine = checkReader.readLine()) != null) {
                    System.out.println(resLine);
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
        }

        new File(inputFileName).delete();
        new File(outputFileName).delete();
    }

    public static void printWriterExample() {
        System.out.print("Введите имя входного файла: ");
        String inputFileName = in.nextLine();
        System.out.print("Введите имя выходного файла: ");
        String outputFileName = in.nextLine();

        System.out.println("Введите данные для записи (введите 'END' на новой строке для завершения):");
        StringBuilder inputData = new StringBuilder();
        while (true) {
            String line = in.nextLine();
            if (line.equals("END")) break;
            inputData.append(line).append("\n");
        }

        try (PrintWriter setupWriter = new PrintWriter(inputFileName, "UTF-8")) {
            setupWriter.print(inputData.toString());
        } catch (IOException e) {
            System.out.println("Не удалось создать входной файл: " + e.getMessage());
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName));
             PrintWriter printWriter = new PrintWriter(outputFileName, "UTF-8")) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line.toUpperCase());
            }
            System.out.println("Данные записаны в файл: " + outputFileName);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
        }

        new File(inputFileName).delete();
        new File(outputFileName).delete();
    }
}
