package algorithm.geektime;

import structure.Stack;

/**
 * 使用栈辅助，判断一个链表是否是回文串，链表的结点数据都是字符
 * （递归的方式实在想不出来）
 * @ClassName: Alm01
 * @Author: jicheng
 * @CreateDate: 2019/11/27 11:08 PM
 */
public class Alm01 {

    static class Node {
        public char node;
        public Node next;

        public Node(char node, Node next) {
            this.node = node;
            this.next = next;
        }
    }

    public boolean solution(Node fisrt) {
        if (fisrt == null) {
            return false;
        }
        if (fisrt.next == null) {
            return true;
        }


        Node head = fisrt;
        Stack<Node> stack = new Stack<>();
        while(head!=null){
            stack.push(head);
            head = head.next;
        }

        while(fisrt!=null){
            Node pop = stack.pop();
            if(pop.node!=fisrt.node){
                return false;
            }
            fisrt = fisrt.next;
        }
        return true;

    }


    public static void main(String[] args) {

        Node node1 = new Node('a', null);
        Node node2 = new Node('d', node1);
        Node node3 = new Node('c', node2);
        Node node4 = new Node('b', node3);
        Node node5 = new Node('a', node4);

        boolean solution = new Alm01().solution(node5);
        System.out.println(solution);
    }
}
