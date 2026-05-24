package lr7;

import java.io.*;

public class Task8 {
    public static void main(String[] args) {
        User user = new User("Кирилл", 22);

        String fileName = "user.bin";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(user);
            System.out.println("Объект сохранён в файл");
        } catch (IOException e) {
            System.out.println("Ошибка записи объекта: " + e.getMessage());
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            User restored = (User) ois.readObject();
            System.out.println("Восстановленный объект:");
            System.out.println(restored);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка чтения объекта: " + e.getMessage());
        }
    }

    static class User implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private final String name;
        private final int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + "'}";
        }
    }
}
