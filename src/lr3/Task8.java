package lr3;

import java.util.Scanner;

public class Task8 {

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;

        void createHead(int n) {
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < n; i++) {
                int val = sc.nextInt();
                Node newNode = new Node(val);
                newNode.next = head;
                head = newNode;
            }
        }

        void createTail(int n) {
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < n; i++) {
                int val = sc.nextInt();
                Node newNode = new Node(val);

                if (head == null) {
                    head = newNode;
                } else {
                    Node temp = head;
                    while (temp.next != null) {
                        temp = temp.next;
                    }
                    temp.next = newNode;
                }
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node temp = head;
            while (temp != null) {
                sb.append(temp.value).append(" ");
                temp = temp.next;
            }
            return sb.toString();
        }

        void addFirst(int val) {
            Node newNode = new Node(val);
            newNode.next = head;
            head = newNode;
        }

        void addLast(int val) {
            Node newNode = new Node(val);

            if (head == null) {
                head = newNode;
                return;
            }

            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        void insert(int index, int val) {
            if (index == 0) {
                addFirst(val);
                return;
            }

            Node temp = head;
            for (int i = 0; i < index - 1 && temp != null; i++) {
                temp = temp.next;
            }

            if (temp == null) return;

            Node newNode = new Node(val);
            newNode.next = temp.next;
            temp.next = newNode;
        }

        void removeFirst() {
            if (head != null) {
                head = head.next;
            }
        }

        void removeLast() {
            if (head == null) return;

            if (head.next == null) {
                head = null;
                return;
            }

            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
        }

        void remove(int index) {
            if (head == null) return;

            if (index == 0) {
                removeFirst();
                return;
            }

            Node temp = head;
            for (int i = 0; i < index - 1 && temp.next != null; i++) {
                temp = temp.next;
            }

            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }

        void createHeadRec(int n, Scanner sc) {
            if (n == 0) return;

            int val = sc.nextInt();
            addFirst(val);
            createHeadRec(n - 1, sc);
        }

        void createTailRec(int n, Scanner sc) {
            if (n == 0) return;

            int val = sc.nextInt();
            createTailRec(n - 1, sc);
            addLast(val);
        }

        String toStringRec() {
            return toStringRecHelper(head);
        }

        private String toStringRecHelper(Node node) {
            if (node == null) return "";
            return node.value + " " + toStringRecHelper(node.next);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList list = new LinkedList();

        System.out.print("Введите количество элементов: ");
        int n = sc.nextInt();

        System.out.println("Введите элементы (createTail):");
        list.createTail(n);

        System.out.println("Список: " + list.toString());

        list.addFirst(100);
        list.addLast(200);
        System.out.println("После addFirst и addLast: " + list.toString());

        list.insert(2, 999);
        System.out.println("После insert: " + list.toString());

        list.removeFirst();
        list.removeLast();
        System.out.println("После удаления первого и последнего: " + list.toString());

        list.remove(1);
        System.out.println("После удаления по индексу: " + list.toString());

        System.out.println("Рекурсивный вывод: " + list.toStringRec());
    }
}