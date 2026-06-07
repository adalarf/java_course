package lr8;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
import java.util.Scanner;


public class Task2 {
    public static void main(String[] args) {
        System.out.println("2.2.1 создание XML файла");
        CreateEmployeesXML.main(args);

        System.out.println("\n2.2.2 добавление сотрудника");
        // AddEmployeeXML.main(args);

        System.out.println("\n2.2.3 поиск сотрудников");
        // SearchEmployeesXML.main(args);

        System.out.println("\n2.2.4 удаление сотрудника");
        // DeleteEmployeeXML.main(args);
    }


    static class CreateEmployeesXML {
        public static void main(String[] args) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("employees");
                doc.appendChild(rootElement);

                EmployeeXML emp1 = new EmployeeXML("Иванов Иван Иванович", "Разработчик", "35", "75000");
                addEmployeeToDocument(doc, rootElement, emp1);

                EmployeeXML emp2 = new EmployeeXML("Петров Петр Петрович", "Менеджер", "28", "65000");
                addEmployeeToDocument(doc, rootElement, emp2);

                doc.setXmlStandalone(true);
                doc.normalizeDocument();
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("employees.xml"));
                transformer.transform(source, result);

                System.out.println("XML файл создан");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static void addEmployeeToDocument(Document doc, Element root, EmployeeXML emp) {
            Element employee = doc.createElement("employee");
            root.appendChild(employee);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(emp.getName()));
            employee.appendChild(name);

            Element position = doc.createElement("position");
            position.appendChild(doc.createTextNode(emp.getPosition()));
            employee.appendChild(position);

            Element age = doc.createElement("age");
            age.appendChild(doc.createTextNode(emp.getAge()));
            employee.appendChild(age);

            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode(emp.getSalary()));
            employee.appendChild(salary);
        }
    }

    static class EmployeeXML {
        private String name;
        private String position;
        private String age;
        private String salary;

        public EmployeeXML(String name, String position, String age, String salary) {
            this.name = name;
            this.position = position;
            this.age = age;
            this.salary = salary;
        }

        public String getName() { return name; }
        public String getPosition() { return position; }
        public String getAge() { return age; }
        public String getSalary() { return salary; }
    }

    static class AddEmployeeXML {
        public static void main(String[] args) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Введите ФИО сотрудника:");
                String name = scanner.nextLine();

                System.out.println("Введите должность:");
                String position = scanner.nextLine();

                System.out.println("Введите возраст:");
                String age = scanner.nextLine();

                System.out.println("Введите зарплату:");
                String salary = scanner.nextLine();

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(new File("employees.xml"));

                Element root = doc.getDocumentElement();
                Element newEmployee = doc.createElement("employee");

                Element nameElem = doc.createElement("name");
                nameElem.appendChild(doc.createTextNode(name));
                newEmployee.appendChild(nameElem);

                Element positionElem = doc.createElement("position");
                positionElem.appendChild(doc.createTextNode(position));
                newEmployee.appendChild(positionElem);

                Element ageElem = doc.createElement("age");
                ageElem.appendChild(doc.createTextNode(age));
                newEmployee.appendChild(ageElem);

                Element salaryElem = doc.createElement("salary");
                salaryElem.appendChild(doc.createTextNode(salary));
                newEmployee.appendChild(salaryElem);

                root.appendChild(newEmployee);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("employees.xml"));
                transformer.transform(source, result);

                System.out.println("Сотрудник успешно добавлен!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class SearchEmployeesXML {
        public static void main(String[] args) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Поиск по: 1 - имени, 2 - году рождения (возрасту)");
                int searchType = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите значение для поиска:");
                String searchValue = scanner.nextLine();

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(new File("employees.xml"));

                NodeList employees = doc.getElementsByTagName("employee");
                boolean found = false;

                for (int i = 0; i < employees.getLength(); i++) {
                    Element employee = (Element) employees.item(i);

                    if (searchType == 1) {
                        String name = employee.getElementsByTagName("name").item(0).getTextContent();
                        if (name.toLowerCase().contains(searchValue.toLowerCase())) {
                            printEmployee(employee);
                            found = true;
                        }
                    } else if (searchType == 2) {
                        String age = employee.getElementsByTagName("age").item(0).getTextContent();
                        if (age.equals(searchValue)) {
                            printEmployee(employee);
                            found = true;
                        }
                    }
                }

                if (!found) {
                    System.out.println("Сотрудники не найдены");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static void printEmployee(Element employee) {
            System.out.println("\n--- Сотрудник ---");
            System.out.println("ФИО: " + employee.getElementsByTagName("name").item(0).getTextContent());
            System.out.println("Должность: " + employee.getElementsByTagName("position").item(0).getTextContent());
            System.out.println("Возраст: " + employee.getElementsByTagName("age").item(0).getTextContent());
            System.out.println("Зарплата: " + employee.getElementsByTagName("salary").item(0).getTextContent());
        }
    }

    static class DeleteEmployeeXML {
        public static void main(String[] args) {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Введите ФИО сотрудника для удаления:");
                String nameToDelete = scanner.nextLine();

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(new File("employees.xml"));

                NodeList employees = doc.getElementsByTagName("employee");
                boolean deleted = false;

                for (int i = 0; i < employees.getLength(); i++) {
                    Element employee = (Element) employees.item(i);
                    String name = employee.getElementsByTagName("name").item(0).getTextContent();

                    if (name.equalsIgnoreCase(nameToDelete)) {
                        Node parentNode = employee.getParentNode();
                        parentNode.removeChild(employee);
                        deleted = true;
                        System.out.println("Сотрудник удален!");
                        break;
                    }
                }

                if (!deleted) {
                    System.out.println("Сотрудник не найден!");
                    return;
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("employees.xml"));
                transformer.transform(source, result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
