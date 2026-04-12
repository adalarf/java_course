package lr3;

public class Task7_head {
    public static void main(String[] args) {
        Node head = null;

        for (int i = 0; i <= 5; i++) {
            head = new Node(i, head);
        }

        Node ref = head;
        while (ref != null) {
            System.out.print(ref.value + " ");
            ref = ref.next;
        }
    }
}
