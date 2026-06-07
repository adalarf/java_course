package lr8;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Task3 {
    public static void main(String[] args) {
        System.out.println("2.3.1 создание JSON файла");
        CreateEmployeesJSON.main(args);

        System.out.println("\n2.3.2 поиск сотрудника");
        // SearchEmployeeJSON.main(args);

        System.out.println("\n2.3.3 добавление сотрудника");
        // AddEmployeeJSON.main(args);

        System.out.println("\n2.3.4 удаление сотрудника");
        // DeleteEmployeeJSON.main(args);
    }

    static class CreateEmployeesJSON {
        public static void main(String[] args) {
            JSONObject company = new JSONObject();
            JSONArray employees = new JSONArray();

            JSONObject emp1 = new JSONObject();
            emp1.put("name", "Иванов Иван Иванович");
            emp1.put("position", "Разработчик");
            emp1.put("age", 35);
            emp1.put("salary", 75000);
            employees.add(emp1);

            JSONObject emp2 = new JSONObject();
            emp2.put("name", "Петров Петр Петрович");
            emp2.put("position", "Менеджер");
            emp2.put("age", 28);
            emp2.put("salary", 65000);
            employees.add(emp2);

            company.put("employees", employees);

            try (FileWriter file = new FileWriter("employees.json")) {
                file.write(company.toJSONString());
                System.out.println("JSON файл с сотрудниками успешно создан!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class SearchEmployeeJSON {
        public static void main(String[] args) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Введите имя для поиска:");
                String searchName = in.nextLine();
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("employees.json"));
                JSONArray employees = (JSONArray) jsonObject.get("employees");
                List<JSONObject> foundEmployees = new ArrayList<>();

                for (Object obj : employees) {
                    JSONObject emp = (JSONObject) obj;
                    String name = (String) emp.get("name");
                    if (name.toLowerCase().contains(searchName.toLowerCase())) {
                        foundEmployees.add(emp);
                    }
                }

                if (foundEmployees.isEmpty()) {
                    System.out.println("Сотрудники не найдены");
                } else {
                    foundEmployees.forEach(emp -> {
                        System.out.println("\n--- Сотрудник ---");
                        System.out.println("ФИО: " + emp.get("name"));
                        System.out.println("Должность: " + emp.get("position"));
                        System.out.println("Возраст: " + emp.get("age"));
                        System.out.println("Зарплата: " + emp.get("salary"));
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class AddEmployeeJSON {
        public static void main(String[] args) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Введите ФИО сотрудника:");
                String name = scanner.nextLine();

                System.out.println("Введите должность:");
                String position = scanner.nextLine();

                System.out.println("Введите возраст:");
                int age = scanner.nextInt();

                System.out.println("Введите зарплату:");
                double salary = scanner.nextDouble();

                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("employees.json"));
                JSONArray employees = (JSONArray) jsonObject.get("employees");

                JSONObject newEmployee = new JSONObject();
                newEmployee.put("name", name);
                newEmployee.put("position", position);
                newEmployee.put("age", age);
                newEmployee.put("salary", salary);

                employees.add(newEmployee);

                try (FileWriter file = new FileWriter("employees.json")) {
                    file.write(jsonObject.toJSONString());
                    System.out.println("Сотрудник успешно добавлен!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class DeleteEmployeeJSON {
        public static void main(String[] args) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Введите ФИО сотрудника для удаления:");
                String nameToDelete = in.nextLine();

                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("employees.json"));
                JSONArray employees = (JSONArray) jsonObject.get("employees");

                Iterator iterator = employees.iterator();
                boolean deleted = false;

                while (iterator.hasNext()) {
                    JSONObject employee = (JSONObject) iterator.next();
                    String name = (String) employee.get("name");

                    if (name.equalsIgnoreCase(nameToDelete)) {
                        iterator.remove();
                        deleted = true;
                        System.out.println("Сотрудник удален!");
                        break;
                    }
                }

                if (!deleted) {
                    System.out.println("Сотрудник не найден!");
                    return;
                }

                try (FileWriter file = new FileWriter("employees.json")) {
                    file.write(jsonObject.toJSONString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
