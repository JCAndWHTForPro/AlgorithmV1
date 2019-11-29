package algorithm.geektime;

/**
 * 链表翻转（使用递归）
 *
 * @ClassName: Alm02
 * @Author: jicheng
 * @CreateDate: 2019/11/29 10:26 PM
 */
public class Alm02 {




    public Node sulutions(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node sulutions = sulutions(head, head.next);
        head.next = null;
        return sulutions;
    }

    private Node sulutions(Node currentNode, Node nextNode) {
        if (nextNode == null) {
            return currentNode;
        }
        Node temp = nextNode.next;
        nextNode.next = currentNode;
        return sulutions(nextNode, temp);
    }


    public static void main(String[] args) {
        Node<String> n8 = new Node<>("129",null);
        Node<String> n7 = new Node<>("128",n8);
        Node<String> n6 = new Node<>("127",n7);
        Node<String> n5 = new Node<>("126",n6);
        Node<String> n4 = new Node<>("125",n5);
        Node<String> n3 = new Node<>("124",n4);
        Node<String> n2 = new Node<>("123",n3);
        Node<String> n1 = new Node<>("12",n2);

        System.out.println(n1);
        Node sulutions = new Alm02().sulutions(n1);
        System.out.println(sulutions);
    }
}
