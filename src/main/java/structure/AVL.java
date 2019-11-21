package structure;

/**
 * @ClassName: AVL
 * @Author: jicheng
 * @CreateDate: 2019/11/21 10:45 PM
 */
public class AVL<V extends Comparable<V>> {

    class Node {

        public V data;

        public Node leftChild, rightChild;

        public int height;

        public Node(V data) {
            this.data = data;
            this.height = 1;
        }

        public Node(V data, Node leftChild, Node rightChild) {
            this(data);
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    private Node root;

    public void addNode(V node) {
        if (this.root == null) {
            this.root = new Node(node);
            return;
        }
        addNode(this.root, node);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int heightDiff(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    private Node leftRotate(Node node) {
        if (node == null || node.rightChild == null) {
            return node;
        }
        Node rightChild = node.rightChild;
        Node rightChildLeftNode = rightChild.leftChild;

        rightChild.leftChild = node;
        node.rightChild = rightChildLeftNode;

        node.height = calculateNodeHeight(node);
        rightChild.height = calculateNodeHeight(rightChild);

        return rightChild;
    }

    private Node rightRotate(Node node) {
        if (node == null || node.leftChild == null) {
            return node;
        }
        Node leftChild = node.leftChild;
        Node leftChildRightNode = leftChild.rightChild;


        leftChild.rightChild = node;
        node.leftChild = leftChildRightNode;

        node.height = calculateNodeHeight(node);
        leftChild.height = calculateNodeHeight(leftChild);

        return leftChild;
    }

    private int calculateNodeHeight(Node node){
        if(node == null){
            return 0;
        }
        return 1+Math.max(getHeight(node.leftChild),getHeight(node.rightChild));
    }

    private Node addNode(Node root, V node) {
        if (root == null) {
            return new Node(node);
        }
        if (root.data.compareTo(node) > 0) {
            root.leftChild = addNode(root.leftChild, node);
            root.height = calculateNodeHeight(root);
        } else if (root.data.compareTo(node) < 0) {
            root.rightChild = addNode(root.rightChild, node);
            root.height = calculateNodeHeight(root);
        }
        int heightDiff = heightDiff(root);
        if (heightDiff >= 2 && heightDiff(root.leftChild) >= 0) {
            //LL情况
            return rightRotate(root);
        } else if (heightDiff <= -2 && heightDiff(root.rightChild) <= 0) {
            //RR情况
            return leftRotate(root);
        } else if (heightDiff >= 2 && heightDiff(root.leftChild) < 0) {
            //LR情况
            root.leftChild = leftRotate(root.leftChild);
            return rightRotate(root);
        } else if (heightDiff <= -2 && heightDiff(root.rightChild) > 0) {
            //RL情况
            root.rightChild = rightRotate(root.rightChild);
            return leftRotate(root);
        }

        return root;

    }
}
