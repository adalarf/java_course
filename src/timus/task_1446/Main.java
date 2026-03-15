package timus.task_1446;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        List<String> slytherin = new ArrayList<>();
        List<String> hufflepuff = new ArrayList<>();
        List<String> gryffindor = new ArrayList<>();
        List<String> ravenclaw = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = in.nextLine();
            String faculty = in.nextLine();

            switch (faculty) {
                case "Slytherin":
                    slytherin.add(name);
                    break;
                case "Hufflepuff":
                    hufflepuff.add(name);
                    break;
                case "Gryffindor":
                    gryffindor.add(name);
                    break;
                case "Ravenclaw":
                    ravenclaw.add(name);
                    break;
            }
        }

        printFaculty("Slytherin", slytherin);
        System.out.println();
        printFaculty("Hufflepuff", hufflepuff);
        System.out.println();
        printFaculty("Gryffindor", gryffindor);
        System.out.println();
        printFaculty("Ravenclaw", ravenclaw);
    }


    private static void printFaculty(String facultyName, List<String> students) {
        System.out.println(facultyName + ":");
        for (String student : students) {
            System.out.println(student);
        }
    }
}
