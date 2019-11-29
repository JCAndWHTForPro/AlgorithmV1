package algorithm.geektime;

/**
 * 删除链表倒数第 n 个结点
 *
 * @ClassName: Alm04
 * @Author: jicheng
 * @CreateDate: 2019/11/29 11:04 PM
 */
public class Alm04 {

    /**
     * 使用递归的方式来做
     *
     * @param head
     * @return
     */
    public Node solutions01(Node head, int n) {
        if (head == null) {
            return head;
        }


        Node last = head;
        Node pre = head;
        while (n>0){
            pre = pre.next;
            if(pre == null){
                break;
            }
            n--;
        }

        if(pre == null && n==1){
            Node next = head.next;
            head.next = null;
            return next;
        }

        if(pre == null){
            return head;
        }

        while(pre.next!=null){
            pre = pre.next;
            last = last.next;
        }
        Node tem = last.next;
        last.next = tem.next;
        tem.next = null;



        return head;
    }

    public static void main(String[] args) {
        Node<String> n8 = new Node<>("129", null);
        Node<String> n7 = new Node<>("128", n8);
        Node<String> n6 = new Node<>("127", n7);
        Node<String> n5 = new Node<>("126", n6);
        Node<String> n4 = new Node<>("125", n5);
        Node<String> n3 = new Node<>("124", n4);
        Node<String> n2 = new Node<>("123", n3);
        Node<String> n1 = new Node<>("12", n2);


        Node node = new Alm04().solutions01(n1, 8);
        System.out.println(node);
    }

}
