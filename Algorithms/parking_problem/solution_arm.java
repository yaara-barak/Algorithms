package parking_problem;

public class solution_arm {
    //Finding the Loop in a Circular Linked List
    public static boolean hasLoop(Node head) {
        if (head == null) return false;
        Node fast = head.next;
        Node slow = head;
        boolean ans = true;
        while (ans) {
            if (fast == null || slow == null || fast.next == null) return false;
            if (fast == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return ans;
    }

    //Finding the Start of a Loop in a Circular Linked List
    public static int findLoopStart(Node head) {
        if (head == null) {
            System.out.println("empty list");
            return -1;
        }
        Node fast = head.next;
        Node slow = head;
        boolean ans = false;
        while (!ans) {
            if (fast == null || fast.next == null || slow == null) break;
            if (fast == slow) ans = true;
            fast = fast.next.next;
            slow = slow.next;
        }
        if (ans) {
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            if (fast == slow) return fast.data;
        }
        return -1;
    }

    //Finding the Length of a Loop in a Circular Linked List
    public static int findLoopLength(Node head) {
        if (head == null) {
            System.out.println("empty list");
            return -1;
        }
        int loopLen = -1;
        Node fast = head.next;
        Node slow = head;
        boolean ans = false;
        while (!ans) {
            if (fast == null || fast.next == null || slow == null) break;
            if (fast == slow) ans = true;
            fast = fast.next.next;
            slow = slow.next;
        }
        if (ans) {
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            if (fast == slow) {
                loopLen = 1;
                fast = fast.next;
                while (fast != slow) {
                    loopLen++;
                    fast = fast.next;
                }
            }
        }
        return loopLen;

    }

    public static void main(String[] args) {
        linkedList list= new linkedList();
        for (int i = 1; i <= 6; i++) {
            list.Add(i);
        }
        if (hasLoop(list.head)==true)
        System.out.println("has loop");
        else
            System.out.println("no loop");
        //list.tail.next = list.head;
        list.tail.next = list.head.next.next;
        if (hasLoop(list.head)==true)
            System.out.println("has loop");
        else
            System.out.println("no loop");
        System.out.println("loop start: " + findLoopStart(list.head));
        System.out.println("loop length: " + findLoopLength(list.head));
        System.out.println("arm length: "+ (list.counter-findLoopLength(list.head)));

    }

    static class Node {
        int data;
        Node next;

        Node() {
            data = 0;
            next = null;
        }

        Node(int v) {
            data = v;
            next = null;
        }

    }

    static class linkedList {
        Node head;
        Node tail;
        int counter;

        linkedList() {
            counter = 0;
            head = tail = null;
        }

        void Add(int v) {
            if (head == null) {
                head = new Node(v);
                tail = head;
            } else {
                tail.next = new Node(v);
                tail = tail.next;
            }
            counter++;
        }



    }
}