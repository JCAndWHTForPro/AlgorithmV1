package structure;

import java.util.List;
import java.util.TooManyListenersException;

/**
 * @ClassName: BST
 * @Author: jicheng
 * @CreateDate: 2019/11/20 10:52 PM
 */
public class BST<T extends Comparable<T>> {

    class BstNode {
        public T node;
        public BstNode leftChild;
        public BstNode rightChild;
    }

    private BstNode root;


    public void add(T data) {
        add(this.root, data);
    }

    private BstNode add(BstNode root, T data) {
        if (root == null) {
            BstNode node = new BstNode();
            node.node = data;
            return node;
        }
        if (data.compareTo(root.node) > 0) {
            root.rightChild = add(root.rightChild, data);
        } else if (data.compareTo(root.node) < 0) {
            root.leftChild = add(root.leftChild, data);
        }
        return root;
    }

    public void preorder(List<T> resultList) {
        if (this.root == null) {
            return;
        }
        preorder(this.root, resultList);
    }

    private void preorder(BstNode root, List<T> resultList) {
        if (root == null) {
            return;
        }
        resultList.add(root.node);
        preorder(root.leftChild, resultList);
        preorder(root.rightChild, resultList);
    }

    public void preorderNotRecursive(List<T> resultList) {
        if (this.root == null) {
            return;
        }
        Stack<BstNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BstNode pop = stack.pop();
            if (pop == null) continue;
            resultList.add(pop.node);
            if (pop.rightChild != null) {
                stack.push(pop.rightChild);
            }
            if (pop.leftChild != null) {
                stack.push(pop.leftChild);
            }

        }
    }

    public void sequence(List<T> resultList) {
        if (root == null) {
            return;
        }
        Queue<BstNode> queue = new Queue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            BstNode dequeue = queue.dequeue();
            if (dequeue == null) continue;
            resultList.add(dequeue.node);
            if (dequeue.leftChild != null) {
                queue.enqueue(dequeue.leftChild);
            }
            if (dequeue.rightChild != null) {
                queue.enqueue(dequeue.rightChild);
            }
        }

    }


    public BstNode minNode() {
        if (this.root == null) {
            return null;
        }
        return minNode(this.root);
    }

    private BstNode minNode(BstNode node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    public BstNode maxNode() {
        if (this.root == null) {
            return null;
        }
        return maxNode(this.root);
    }

    private BstNode maxNode(BstNode node){
        while(node.rightChild !=null){
            node = node.rightChild;
        }
        return node;

    }

    public BstNode delMaxNode() {
        BstNode result = maxNode();
        if (result != null) {
            this.root = delMaxNode(this.root);
        }
        return result;

    }

    private BstNode delMaxNode(BstNode root) {
        if (root.rightChild == null) {
            return root.leftChild;
        }
        root.rightChild = delMaxNode(root.rightChild);
        return root;

    }

    public BstNode delMinNode() {
        BstNode result = minNode();
        if (result != null) {
            this.root = delMinNode(this.root);
        }
        return result;
    }

    private BstNode delMinNode(BstNode root) {
        if (root.leftChild == null) {
            return root.rightChild;
        }
        root.leftChild = delMinNode(root.leftChild);
        return root;
    }

    public void delAnyNode(T data) {
        if (data == null) {
            return;
        }
        this.root = delAnyNode(this.root, data);
    }

    private BstNode delAnyNode(BstNode root, T data) {
        if (root == null) {
            return root;
        }
        if (root.leftChild.node.compareTo(data) > 0) {
            root.leftChild = delAnyNode(root.leftChild, data);
        } else if (root.rightChild.node.compareTo(data) < 0) {
            root.rightChild = delAnyNode(root.rightChild, data);
        } else {
            if (root.leftChild != null) {
                BstNode maxNode = maxNode(root.leftChild);
                maxNode.leftChild = delMaxNode(root.leftChild);
                maxNode.rightChild = root.rightChild;
                return maxNode;
            } else if (root.rightChild != null) {
                BstNode minNode = minNode(root.rightChild);
                minNode.leftChild = root.leftChild;
                minNode.rightChild = delMinNode(root.rightChild);
                return minNode;
            }
            return null;
        }
        return root;
    }

}
