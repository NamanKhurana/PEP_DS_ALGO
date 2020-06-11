import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Stack;
public class l001 {

    public static void main(String[] args) {
        solve();
    }

    public static class Node {
        int data;
        Node left = null; // Node* left=nullptr;   
        Node right = null; // Node* right=nullptr;

        Node(int data) {
            this.data = data;
        }

        Node() {}
    }

    static int idx = 0;
    public static Node constructTree(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }

        Node node = new Node(arr[idx++]); // Node* node=new Node(arr[idx++]);
        node.left = constructTree(arr);
        node.right = constructTree(arr);

        return node;
    }

    public static void display(Node node) {
        if (node == null) return;

        String str = "";
        str += ((node.left != null) ? node.left.data : ".");
        str += " <- " + node.data + " -> ";
        str += ((node.right != null) ? node.right.data : ".");
        System.out.println(str);

        display(node.left);
        display(node.right);

    }

    //Basic.================================================================

    public static int size(Node node) {
        if (node == null) return 0;
        return (size(node.left) + size(node.right) + 1);
    }


    public static int height(Node node) {
        if (node == null) return -1; // return -1, height w.r.t edge, return 0, height w.r.t node.
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int Maximum(Node node) {
        if (node == null) return (int) - 1e8; // java: Integer.MIN_VALUE, c++: INT_MIN; 
        return Math.max(Math.max(Maximum(node.left), Maximum(node.right)), node.data); // max(leftSubtree,rightSubtree,myself);
    }


    public static int Minimum(Node node) {
        if (node == null) return (int) 1e8; // java: Integer.MAX_VALUE, c++: INT_MAX; 
        return Math.min(Math.min(Minimum(node.left), Minimum(node.right)), node.data);
    }

    public static int Minimum_02(Node node) {
        int min_ = node.data;

        if (node.left != null) min_ = Math.min(min_, Minimum_02(node.left));
        if (node.right != null) min_ = Math.min(min_, Minimum_02(node.right));

        return min_;
    }

     //Traversal.============================================================

    public static void preOrder(Node node) {
        if (node == null) return;

        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }


    public static void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");

    }
    
    public static boolean rootToNodePath_(Node root, int data, ArrayList < Node > path) {
        if (root == null) return false;
        if (root.data == data) {
            path.add(root);
            return true;
        }

        boolean res = rootToNodePath_(root.left, data, path) || rootToNodePath_(root.right, data, path);
        if (res) path.add(root);
        return res;
    }

    public static ArrayList < Node > rootToNodePath_02(Node root, int data) {

        if (root == null) {
            return new ArrayList < > ();
        }

        if (root.data == data) {
            ArrayList < Node > base = new ArrayList < > ();
            base.add(root);
            return base;
        }

        ArrayList < Node > left = rootToNodePath_02(root.left, data);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }

        ArrayList < Node > right = rootToNodePath_02(root.right, data);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }

        return new ArrayList < > ();
    }

    public static void rootToNodePath(Node root, int data) {
        ArrayList < Node > path = new ArrayList < > ();
        rootToNodePath_(root, data, path);
        for (Node n: path) {
            System.out.print(n.data + " -> ");
        }
    }

    static Node LCANode = null;
    public static boolean LCA(Node root,Node p,Node q){

        if(root == null){
            return false;
        }

        boolean selfNode = false;

        if(p.data == root.data || q.data == root.data){
            selfNode = true;
        }

        boolean leftNode = LCA(root.left,p,q);
        if(LCA != null){
            return true;
        }
        boolean rightNode = LCA(root.right,p,q);
        if(LCA!=null){
            return true;
        }

        if((selfNode && leftNode) || (selfNode && rightNode) || (leftNode && rightNode){
            LCANode = root;
        }

        return selfNode || leftNode || rightNode ;
    } 
    
    //LevelOrder_series.=========================================================

    public static void levelOrder_00(Node node) {
        LinkedList < Node > pQue = new LinkedList < > (); // addLast and removeFirst.
        pQue.addLast(node);

        while (pQue.size() != 0) {
            Node rnode = pQue.removeFirst();
            System.out.print(rnode.data + " ");
            if (rnode.left != null) pQue.addLast(rnode.left);
            if (rnode.right != null) pQue.addLast(rnode.right);
        }
    }

    public static void levelOrder_01(Node node) {
        LinkedList < Node > pQue = new LinkedList < > (); // addLast and removeFirst.
        LinkedList < Node > cQue = new LinkedList < > (); // addLast and removeFirst.

        pQue.addLast(node);
        int count = 0;
        System.out.print("Level: " + count + " -> ");

        while (pQue.size() != 0) {
            Node rnode = pQue.removeFirst();
            System.out.print(rnode.data + " ");
            if (rnode.left != null) cQue.addLast(rnode.left);
            if (rnode.right != null) cQue.addLast(rnode.right);

            if (pQue.size() == 0) {
                LinkedList < Node > temp = pQue;
                pQue = cQue;
                cQue = temp;
                count++;
                System.out.print("\nLevel: " + count + " -> ");
            }
        }
    }

    public static void levelOrder_02(Node node) {
        LinkedList < Node > Que = new LinkedList < > (); // addLast and removeFirst.

        Que.addLast(node);
        Que.addLast(null);
        int count = 0;
        System.out.print("Level: " + count + " -> ");

        while (Que.size() != 1) {
            Node rnode = Que.removeFirst();
            System.out.print(rnode.data + " ");
            if (rnode.left != null) Que.addLast(rnode.left);
            if (rnode.right != null) Que.addLast(rnode.right);

            if (Que.getFirst() == null) {
                Que.removeFirst();
                Que.addLast(null);
                count++;
                System.out.print("\nLevel: " + count + " -> ");
            }
        }
    }

    public static void levelOrder_03(Node node) {
        LinkedList < Node > pQue = new LinkedList < > (); // addLast and removeFirst.
        pQue.addLast(node);

        int count = 0;
        while (pQue.size() != 0) {
            System.out.print("Level: " + count + " -> ");
            count++;
            int size = pQue.size();

            while (size-- > 0) {
                Node rnode = pQue.removeFirst();
                System.out.print(rnode.data + " ");
                if (rnode.left != null) pQue.addLast(rnode.left);
                if (rnode.right != null) pQue.addLast(rnode.right);
            }
            System.out.println();
        }
    }

    public static boolean find(Node node, int data) {
        if (node == null) return false;

        if (node.data == data) return true;
        return find(node.left, data) || find(node.right, data);


        // if(find(node.left,data)) return true;
        // if(find(node.rigth,data)) return true;

    }
