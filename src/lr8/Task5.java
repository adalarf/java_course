package lr8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Scanner;


public class Task5 {
    public static void main(String[] args) {
        System.out.println("создание Excel файла");
        CreateEmployeesExcelWithErrorHandling.main(args);

        System.out.println("\nчтение Excel");
        ReadEmployeesExcelWithErrorHandling.main(args);

        System.out.println("\nдобавление сотрудника");
        // AddEmployeeExcelWithValidation.main(args);
    }

    static class CreateEmployeesExcelWithErrorHandling {
        public static void main(String[] args) {
            Workbook workbook = null;
            FileOutputStream outputStream = null;

            try {
                workbook = new XSSFWorkbook();
                System.out.println("Книга Excel успешно создана");

                Sheet sheet = workbook.createSheet("Сотрудники");
                System.out.println("Лист 'Сотрудники' создан");

                Row headerRow = sheet.createRow(0);
                String[] headers = {"ФИО", "Должность", "Возраст", "Зарплата"};

                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    CellStyle style = workbook.createCellStyle();
                    Font font = workbook.createFont();
                    font.setBold(true);
                    style.setFont(font);
                    cell.setCellStyle(style);
                }

                Object[][] data = {
                        {"Иванов Иван Иванович", "Разработчик", 35, 75000},
                        {"Петров Петр Петрович", "Менеджер", 28, 65000}
                };

                int rowNum = 1;
                for (Object[] rowData : data) {
                    Row row = sheet.createRow(rowNum++);
                    for (int i = 0; i < rowData.length; i++) {
                        Cell cell = row.createCell(i);
                        if (rowData[i] instanceof String) {
                            cell.setCellValue((String) rowData[i]);
                        } else if (rowData[i] instanceof Integer) {
                            cell.setCellValue((Integer) rowData[i]);
                        } else if (rowData[i] instanceof Double) {
                            cell.setCellValue((Double) rowData[i]);
                        }
                    }
                }

                for (int i = 0; i < headers.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                String filePath = "employees.xlsx";
                outputStream = new FileOutputStream(filePath);
                workbook.write(outputStream);

                System.out.println("Данные успешно записаны в файл: " + filePath);
                System.out.println("Файл закрыт корректно");

            } catch (FileNotFoundException e) {
                System.err.println("Ошибка: Файл не найден. Проверьте путь к файлу.");
                System.err.println("Рекомендация: Убедитесь, что у программы есть права на запись в указанную директорию.");
            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода: " + e.getMessage());
                System.err.println("Рекомендация: Проверьте, не открыт ли файл в другой программе.");
            } catch (Exception e) {
                System.err.println("Неизвестная ошибка: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                        System.out.println("Output stream закрыт");
                    }
                    if (workbook != null) {
                        workbook.close();
                        System.out.println("Workbook закрыт");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии ресурсов: " + e.getMessage());
                }
            }
        }
    }

    static class ReadEmployeesExcelWithErrorHandling {
        public static void main(String[] args) {
            Workbook workbook = null;
            FileInputStream inputStream = null;
            String filePath = "employees.xlsx";

            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    System.err.println("Ошибка: Файл '" + filePath + "' не существует!");
                    System.err.println("Рекомендация: Сначала создайте файл с помощью CreateEmployeesExcelWithErrorHandling");
                    return;
                }

                if (!file.canRead()) {
                    System.err.println("Ошибка: Нет прав на чтение файла!");
                    System.err.println("Рекомендация: Проверьте права доступа к файлу.");
                    return;
                }

                inputStream = new FileInputStream(filePath);
                System.out.println("Файл успешно открыт: " + filePath);

                workbook = new XSSFWorkbook(inputStream);
                System.out.println("Книга Excel загружена");

                Sheet sheet = workbook.getSheet("Сотрудники");
                if (sheet == null) {
                    System.err.println("Ошибка: Лист 'Сотрудники' не найден!");
                    System.err.println("Доступные листы:");
                    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                        System.err.println(" - " + workbook.getSheetName(i));
                    }
                    return;
                }
                System.out.println("Лист 'Сотрудники' найден");

                System.out.println("\n=== Данные сотрудников ===");
                int rowNum = 0;
                for (Row row : sheet) {
                    if (rowNum == 0) {
                        System.out.println("--- Заголовки ---");
                    }

                    StringBuilder rowString = new StringBuilder();
                    for (Cell cell : row) {
                        String cellValue = getCellValueAsString(cell);
                        rowString.append(cellValue).append("\t");
                    }
                    System.out.println(rowString.toString());
                    rowNum++;
                }

                System.out.println("\nВсего записей: " + (rowNum - 1));

            } catch (FileNotFoundException e) {
                System.err.println("Ошибка: Файл не найден - " + e.getMessage());
                System.err.println("Рекомендация: Убедитесь, что файл существует по указанному пути.");
            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода: " + e.getMessage());
                System.err.println("Рекомендация: Возможно файл поврежден или открыт в другой программе.");
            } catch (Exception e) {
                System.err.println("Неизвестная ошибка: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                        System.out.println("\nInput stream закрыт");
                    }
                    if (workbook != null) {
                        workbook.close();
                        System.out.println("Workbook закрыт");
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии ресурсов: " + e.getMessage());
                }
            }
        }

        private static String getCellValueAsString(Cell cell) {
            if (cell == null) {
                return "";
            }

            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().toString();
                    } else {
                        return String.valueOf(cell.getNumericCellValue());
                    }
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    return cell.getCellFormula();
                default:
                    return "";
            }
        }
    }

    static class AddEmployeeExcelWithValidation {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            Workbook workbook = null;
            FileInputStream inputStream = null;
            FileOutputStream outputStream = null;
            String filePath = "employees.xlsx";

            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    System.err.println("Файл не существует. Создайте файл сначала.");
                    return;
                }

                inputStream = new FileInputStream(filePath);
                workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheet("Сотрудники");

                if (sheet == null) {
                    System.err.println("Лист 'Сотрудники' не найден!");
                    return;
                }

                System.out.println("Введите ФИО сотрудника:");
                String name = in.nextLine();
                if (name.trim().isEmpty()) {
                    System.err.println("Ошибка: ФИО не может быть пустым!");
                    return;
                }

                System.out.println("Введите должность:");
                String position = in.nextLine();
                if (position.trim().isEmpty()) {
                    System.err.println("Ошибка: Должность не может быть пустой!");
                    return;
                }

                System.out.println("Введите возраст (число):");
                int age;
                try {
                    age = Integer.parseInt(in.nextLine());
                    if (age < 18 || age > 100) {
                        System.err.println("Ошибка: Возраст должен быть от 18 до 100!");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: Возраст должен быть числом!");
                    return;
                }

                System.out.println("Введите зарплату (число):");
                double salary;
                try {
                    salary = Double.parseDouble(in.nextLine());
                    if (salary < 0) {
                        System.err.println("Ошибка: Зарплата не может быть отрицательной!");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: Зарплата должна быть числом!");
                    return;
                }

                int lastRowNum = sheet.getLastRowNum() + 1;
                Row newRow = sheet.createRow(lastRowNum);
                newRow.createCell(0).setCellValue(name);
                newRow.createCell(1).setCellValue(position);
                newRow.createCell(2).setCellValue(age);
                newRow.createCell(3).setCellValue(salary);

                outputStream = new FileOutputStream(filePath);
                workbook.write(outputStream);

                System.out.println("Сотрудник успешно добавлен!");
                System.out.println("Всего сотрудников: " + lastRowNum);

            } catch (FileNotFoundException e) {
                System.err.println("Ошибка: Файл не найден - " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода: " + e.getMessage());
                System.err.println("Рекомендация: Закройте файл Excel, если он открыт.");
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) outputStream.close();
                    if (workbook != null) workbook.close();
                    if (inputStream != null) inputStream.close();
                    System.out.println("Ресурсы освобождены");
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии ресурсов");
                }
            }
        }
    }
}
