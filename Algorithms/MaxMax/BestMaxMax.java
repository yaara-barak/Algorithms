package MaxMax;
import java.util.Arrays;
import java.util.Stack;

class Node{
    int num;
    Stack<Integer> st;
    Node next, prev;
    public Node(int num, Node n, Node p){
        this.num = num;
        st = new Stack<>();
        next = n;
        prev = p;
    }
    public Node(int num){
        this.num = num;
        st = new Stack<>();
        next = null;
        prev = null;
    }
    public String toString(){
        return ""+num;
    }
}


public class BestMaxMax {

    public static int maxMax2LinkedList(int arr[]){//Circle Linked List
        int comparisons=0;
        Node head = new Node(arr[0]);
        Node n = head;
        for (int i=1; i<arr.length; i++){
            n.next = new Node(arr[i], null, n);
            n = n.next;
        }
        head.prev = n;
        n.next = head;

        // Pair Process
        int size = arr.length;
        Node n1 = head;
        Node n2 ;
        while (size>1){
            n2 = n1.next;
            comparisons++;
            if (n1.num<n2.num){
                n2.st.push(n1.num);
                n1.prev.next = n2;// remove n1
                n2.prev = n1.prev;
                n1 = n2.next;
            }
            else{
                n1.st.push(n2.num);
                n1.next = n2.next;// remove n2
                n2.next.prev = n1;
                n1 = n2.next;
            }
            size--;
        }
        // last stack
        int max1 = n1.num;
        int max2 = n1.st.pop();
        System.out.println("comp = "+comparisons);
        System.out.println("n1.st.size = " + n1.st.size());
        while(!n1.st.isEmpty()){
            int number = n1.st.pop();
            comparisons++;
            if (max2<number) max2 = number;
        }
        System.out.println("max1 = "+max1+" ,max2 = "+max2);
        return comparisons;
    }

    public static void main(String[] args) {
        int a[] = {1,10,8,9,5,20,11,18};
        System.out.println(Arrays.toString(a));
        maxMax2LinkedList(a);
    }
}