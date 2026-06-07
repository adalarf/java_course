package lr8;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("2.3.1 запись новостей в файл");
        NewsToFile.main(args);

        System.out.println("\n2.3.2 парсинг с обработкой ошибок");
        NewsParserWithErrorHandling.main(args);
    }

    static class NewsToFile {
        public static void main(String[] args) {
            String url = "http://fat.urfu.ru/index.html";
            String outputFile = "news_output.txt";

            try {
                Document doc = Jsoup.connect(url).get();

                Elements newsParent = doc.select("body > table > tbody > tr > td > div > table > " +
                        "tbody > tr:nth-child(5) > td:nth-child(3) > table > tbody > " +
                        "tr > td:nth-child(1)");

                List<String> newsList = new ArrayList<>();

                for (int i = 1; i < 20; i++) {
                    if (i % 2 == 0) {
                        List<org.jsoup.nodes.Node> nodes = newsParent.get(0).childNodes();
                        if (i < nodes.size()) {
                            Element newsElement = (Element) nodes.get(i);

                            String title = newsElement
                                    .getElementsByClass("blocktitle")
                                    .get(0).childNodes().get(0).toString();

                            String date = newsElement
                                    .getElementsByClass("blockdate")
                                    .get(0).childNodes().get(0).toString();

                            String newsEntry = "Тема: " + title + "\nДата: " + date + "\n";
                            newsList.add(newsEntry);
                        }
                    }
                }

                try (FileWriter writer = new FileWriter(outputFile)) {
                    writer.write("=== НОВОСТИ С САЙТА fat.urfu.ru ===\n\n");

                    for (int i = 0; i < newsList.size(); i++) {
                        writer.write((i + 1) + ". " + newsList.get(i) + "\n");
                    }

                    writer.write("\nВсего новостей: " + newsList.size());
                }

                System.out.println("Новости успешно записаны в файл: " + outputFile);
                System.out.println("Всего записано новостей: " + newsList.size());

            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Ошибка при парсинге: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    static class NewsParserWithErrorHandling {
        private static final int MAX_RETRIES = 3;
        private static final int RETRY_DELAY_MS = 2000;

        public static void main(String[] args) {
            String url = "http://fat.urfu.ru/index.html";
            boolean success = false;
            int attempts = 0;

            while (!success && attempts < MAX_RETRIES) {
                attempts++;
                System.out.println("Попытка подключения #" + attempts + " из " + MAX_RETRIES);

                try {
                    System.out.println("Подключение к сайту: " + url);

                    Document doc = Jsoup.connect(url)
                            .timeout(10000)
                            .userAgent("Mozilla/5.0")
                            .get();

                    if (doc == null) {
                        throw new IOException("Не удалось получить HTML-документ");
                    }

                    System.out.println("Страница успешно загружена!");
                    System.out.println("Заголовок страницы: " + doc.title());

                    Elements newsParent = doc.select("body > table > tbody > tr > td > div > table > " +
                            "tbody > tr:nth-child(5) > td:nth-child(3) > table > tbody > " +
                            "tr > td:nth-child(1)");

                    if (newsParent.isEmpty()) {
                        throw new IOException("Не найдены элементы новостей на странице");
                    }

                    System.out.println("\n=== НОВОСТИ ===\n");
                    int newsCount = 0;

                    for (int i = 1; i < 20; i++) {
                        if (i % 2 == 0) {
                            List<org.jsoup.nodes.Node> nodes = newsParent.get(0).childNodes();
                            if (i < nodes.size()) {
                                Element newsElement = (Element) nodes.get(i);

                                String title = newsElement
                                        .getElementsByClass("blocktitle")
                                        .get(0).childNodes().get(0).toString();

                                String date = newsElement
                                        .getElementsByClass("blockdate")
                                        .get(0).childNodes().get(0).toString();

                                System.out.println("Тема: " + title);
                                System.out.println("Дата: " + date);
                                System.out.println();
                                newsCount++;
                            }
                        }
                    }

                    System.out.println("Всего новостей: " + newsCount);
                    success = true;

                } catch (org.jsoup.HttpStatusException e) {
                    System.err.println("Ошибка HTTP: " + e.getStatusCode() + " - " + e.getMessage());
                    System.err.println("Рекомендация: Проверьте доступность сайта");
                    handleRetry(attempts);

                } catch (java.net.ConnectException e) {
                    System.err.println("Ошибка подключения: " + e.getMessage());
                    System.err.println("Рекомендация: Проверьте подключение к интернету");
                    handleRetry(attempts);

                } catch (java.net.SocketTimeoutException e) {
                    System.err.println("Превышено время ожидания: " + e.getMessage());
                    System.err.println("Рекомендация: Сайт отвечает слишком медленно");
                    handleRetry(attempts);

                } catch (IOException e) {
                    System.err.println("Ошибка ввода-вывода: " + e.getMessage());
                    System.err.println("Рекомендация: " + getRecommendation(e.getMessage()));
                    handleRetry(attempts);

                } catch (Exception e) {
                    System.err.println("Неизвестная ошибка: " + e.getMessage());
                    e.printStackTrace();
                    handleRetry(attempts);
                }
            }

            if (!success) {
                System.err.println("\n=== НЕ УДАЛОСЬ ПОДКЛЮЧИТЬСЯ ===");
                System.err.println("Превышено максимальное количество попыток: " + MAX_RETRIES);
                System.err.println("Рекомендации:");
                System.err.println("1. Проверьте подключение к интернету");
                System.err.println("2. Убедитесь, что сайт доступен");
                System.err.println("3. Попробуйте позже");
            }
        }

        private static void handleRetry(int currentAttempt) {
            if (currentAttempt < MAX_RETRIES) {
                System.out.println("Повторная попытка через " + (RETRY_DELAY_MS / 1000) + " сек...");
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    System.err.println("Поток прерван");
                }
            }
        }

        private static String getRecommendation(String errorMessage) {
            if (errorMessage == null) {
                return "Попробуйте снова";
            }

            String msg = errorMessage.toLowerCase();

            if (msg.contains("permission") || msg.contains("access")) {
                return "Проверьте права доступа к файлу или ресурсу";
            } else if (msg.contains("not found") || msg.contains("404")) {
                return "Проверьте правильность URL";
            } else if (msg.contains("timeout")) {
                return "Увеличьте время ожидания или проверьте соединение";
            } else {
                return "Попробуйте снова или обратитесь к администратору";
            }
        }
    }
}
