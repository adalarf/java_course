package lr7;

import java.io.*;

public class Task1 {
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
        File folder = new File("example1_folder");

        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Папка создана: " + folder.getAbsolutePath());
            } else {
                System.out.println("Не удалось создать папку: " + folder.getAbsolutePath());
            }
        } else {
            System.out.println("Папка уже существует: " + folder.getAbsolutePath());
        }

        File file = new File(folder, "example_file.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("Файл создан: " + file.getAbsolutePath());
            } else {
                System.out.println("Не удалось создать файл: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }

        if (file.delete()) {
            System.out.println("Файл удален: " + file.getAbsolutePath());
        } else {
            System.out.println("Не удалось удалить файл: " + file.getAbsolutePath());
        }

        if (folder.delete()) {
            System.out.println("Папка удалена: " + folder.getAbsolutePath());
        } else {
            System.out.println("Не удалось удалить папку: " + folder.getAbsolutePath());
        }
    }

    public static void byteStreamExample() {
        String fileName = "example2_byte_file.txt";
        String data = "Это данные для записи в файл";

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
        } else {
            System.out.println("Не удалось удалить файл: " + fileName);
        }
    }

    public static void charStreamExample() {
        String fileName = "example3_char_file.txt";
        String data = "Это данные для записи в файл";

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
        } else {
            System.out.println("Не удалось удалить файл: " + fileName);
        }
    }

    public static void bufferedStreamExample() {
        String fileName = "example4_buffered_file.txt";
        String data = "Это данные для записи в файл";

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
        } else {
            System.out.println("Не удалось удалить файл: " + fileName);
        }
    }

    public static void adapterExample() {
        String inputFileName = "example5_input.txt";
        String outputFileName = "example5_output.txt";

        // для выполнения кода файл должен существовать зараннее - создадим его
        try (FileWriter fw = new FileWriter(inputFileName)) {
            fw.write("hello world\nthis is a test line");
        } catch (IOException e) {
            System.out.println("Не удалось создать тестовый входной файл");
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

            System.out.println("Результат в файле output:");
            try (BufferedReader checkReader = new BufferedReader(new FileReader(outputFileName))) {
                String resLine;
                while ((resLine = checkReader.readLine()) != null) {
                    System.out.println(resLine);
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
        }

        // удалим созданный файл
        new File(inputFileName).delete();
        new File(outputFileName).delete();
    }


    public static void printWriterExample() {
        String inputFileName = "example6_input.txt";
        String outputFileName = "example6_output.txt";

        // для выполнения кода файл должен существовать зараннее - создадим его
        try (PrintWriter setupWriter = new PrintWriter(inputFileName, "UTF-8")) {
            setupWriter.println("test line 1 for printwriter");
            setupWriter.println("test line 2 for printwriter");
        } catch (IOException e) {
            System.out.println("Не удалось создать входной файл для теста");
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

        // удалим созданный файл
        new File(inputFileName).delete();
        new File(outputFileName).delete();
    }
}
