package timus.task_1494;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] removed = new int[n];
        for (int i = 0; i < n; i++) {
            removed[i] = in.nextInt();
        }

        Stack<Integer> pocket = new Stack<>();
        int nextBall = 1;
        boolean possible = true;

        for (int i = 0; i < n; i++) {
            int needed = removed[i];

            if (!pocket.isEmpty() && pocket.peek() == needed) {
                pocket.pop();
            }
            else if (nextBall <= n) {
                if (nextBall == needed) {
                    nextBall++;
                }
                else {
                    boolean found = false;
                    while (nextBall <= n) {
                        if (nextBall == needed) {
                            nextBall++;
                            found = true;
                            break;
                        }
                        pocket.push(nextBall);
                        nextBall++;
                    }
                    if (!found) {
                        possible = false;
                    }
                }
            }
            else {
                possible = false;
            }

            if (!possible) {
                break;
            }
        }

        if (possible) {
            System.out.println("Not a proof");
        } else {
            System.out.println("Cheater");
        }
    }
}
