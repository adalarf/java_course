package lr8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; // могут быть конфликты для этого нужно закоммитить библиотеки
import org.jsoup.nodes.Element;  // импортирующие модули с тем же названием (Document, Element) и соответствующий им код
import org.jsoup.select.Elements; // т.к перенёс весь код в один Java Class для удобства
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Task1 {
    public static void main(String[] args) throws IOException {
        System.out.println("--- Xml ---");
        CreateXMLFile.main(args);
        XmlParser.main(args);

        System.out.println("\n--- Json ---");
        JsonCreator.main(args);
        JsonParser.main(args);


        System.out.println("\n--- Html ---");
        NewsParser.main(args);
        LinkParser.main(args);

        System.out.println("\n--- Excel ---");
        WriteExcelFileExample.main(args);
        ReadExcelFileExample.main(args);

    }

    static class CreateXMLFile {
        public static void main(String[] args) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("library");
                doc.appendChild(rootElement);

                Element book1 = doc.createElement("book");
                rootElement.appendChild(book1);

                Element title1 = doc.createElement("title");
                title1.appendChild(doc.createTextNode("Война и мир"));
                book1.appendChild(title1);

                Element author1 = doc.createElement("author");
                author1.appendChild(doc.createTextNode("Лев Толстой"));
                book1.appendChild(author1);

                Element year1 = doc.createElement("year");
                year1.appendChild(doc.createTextNode("1869"));
                book1.appendChild(year1);

                Element book2 = doc.createElement("book");
                rootElement.appendChild(book2);

                Element title2 = doc.createElement("title");
                title2.appendChild(doc.createTextNode("Мастер и Маргарита"));
                book2.appendChild(title2);

                Element author2 = doc.createElement("author");
                author2.appendChild(doc.createTextNode("Михаил Булгаков"));
                book2.appendChild(author2);

                Element year2 = doc.createElement("year");
                year2.appendChild(doc.createTextNode("1967"));
                book2.appendChild(year2);

                doc.setXmlStandalone(true);
                doc.normalizeDocument();
                javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
                javax.xml.transform.Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(javax.xml.transform.OutputKeys.STANDALONE, "yes");
                transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
                javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);

                javax.xml.transform.stream.StreamResult result =
                        new javax.xml.transform.stream.StreamResult(new File("example.xml"));

                transformer.transform(source, result);

                System.out.println("XML-файл успешно создан!");

            } catch (Exception pce) {
                pce.printStackTrace();
            }
        }
    }
    static class XmlParser {
        public static void main(String[] args) {
            try {
                File inputFile = new File("example.xml");

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();

                System.out.println("Корневой элемент: " + doc.getDocumentElement().getNodeName());
                NodeList nodeList = doc.getElementsByTagName("book");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    System.out.println("\nТекущий элемент: " + node.getNodeName());
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        System.out.println("Название книги: "
                                + element.getElementsByTagName("title").item(0)
                                .getTextContent());
                        System.out.println("Автор: "
                                + element.getElementsByTagName("author").item(0)
                                .getTextContent());
                        System.out.println("Год издания: "
                                + element.getElementsByTagName("year").item(0)
                                .getTextContent());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class JsonCreator {
        public static void main(String[] args) {
            JSONObject library = new JSONObject();
            JSONArray books = new JSONArray();

            JSONObject book1 = new JSONObject();
            book1.put("title", "Война и мир");
            book1.put("author", "Лев Толстой");
            book1.put("year", 1869);

            JSONObject book2 = new JSONObject();
            book2.put("title", "Мастер и Маргарита");
            book2.put("author", "Михаил Булгаков");
            book2.put("year", 1967);

            books.add(book1);
            books.add(book2);

            library.put("books", books);

            try (FileWriter file = new FileWriter("example-json.json")) {
                file.write(library.toJSONString());
                System.out.println("Json файл успешно создан!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class JsonParser {
        public static void main(String[] args) {
            try {
                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader("example-json.json"));
                JSONObject jsonObject = (JSONObject) obj;
                System.out.println("Корневой элемент: "
                        + jsonObject.keySet().iterator().next());
                JSONArray jsonArray = (JSONArray) jsonObject.get("books");

                for (Object o : jsonArray) {
                    JSONObject book = (JSONObject) o;
                    System.out.println("\nТекущий элемент: book");
                    System.out.println("Название книги: " + book.get("title"));
                    System.out.println("Автор: " + book.get("author"));
                    System.out.println("Год издания: " + book.get("year"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class LinkParser {
        public static void main(String[] args) {
            String url = "https://itlearn.ru/first-steps";
            try {
                Document doc = Jsoup.connect(url).get();
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    System.out.println(link.attr("abs:href"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class NewsParser {
        public static void main(String[] args) {
            try {
                Document doc = Jsoup.connect("http://fat.urfu.ru/index.html").get();

                Elements newsParent = doc.select("body > table > tbody > tr > td > div > table > " +
                        "tbody > tr:nth-child(5) > td:nth-child(3) > table > tbody > " +
                        "tr > td:nth-child(1)");

                for (int i = 1; i < 20; i++) {
                    if (i % 2 == 0) {
                        List<Node> nodes = newsParent.get(0).childNodes();
                        System.out.println("Тема : " +
                                ((Element) nodes.get(i))
                                        .getElementsByClassName("blocktitle")
                                        .get(0).childNodes().get(0));

                        System.out.println("Дата : " +
                                ((Element) nodes.get(i))
                                        .getElementsByClassName("blockdate")
                                        .get(0).childNodes().get(0) +
                                "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static class WriteExcelFileExample {
        public static void main(String[] args) throws IOException {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("Товары");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Товар");
            headerRow.createCell(1).setCellValue("Характеристики");
            headerRow.createCell(2).setCellValue("Стоимость");

            Row dataRow1 = sheet.createRow(1);
            dataRow1.createCell(0).setCellValue("Книга");
            dataRow1.createCell(1).setCellValue("Жанр: Фантастика, Автор: Иванов И.И.");
            dataRow1.createCell(2).setCellValue(500.0);

            Row dataRow2 = sheet.createRow(2);
            dataRow2.createCell(0).setCellValue("Компьютер");
            dataRow2.createCell(1).setCellValue("Процессор: Intel Core i5, Оперативная память: 8 Гб");
            dataRow2.createCell(2).setCellValue(25000.0);

            String filePath = "example3.xlsx";
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            System.out.println("Данные записаны в файл: " + filePath);
        }
    }

    static class ReadExcelFileExample {
        public static void main(String[] args) throws IOException {
            String filePath = "example3.xlsx";
            FileInputStream inputStream = new FileInputStream(filePath);

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = workbook.getSheet("Товары");

            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
            }

            workbook.close();
            inputStream.close();
        }
    }

}
