import java.util.Scanner;

public class BTUses {

    public static boolean isNodePresent(BinaryTreeNode<Integer> root, int x) {
        if(root == null){
            return false;
        }
        if (root.data == x) {
            return true;
        }
        boolean left = isNodePresent(root.left, x);
        if(left) return true;
        boolean right = isNodePresent(root.right, x);
        if(right) return true;
        return false;
    }

    public static BinaryTreeNode<Integer> replaceNodewithdepth(BinaryTreeNode<Integer> root, int k) {
        if(root == null){
            return null;
        }
        root.data = k;
        replaceNodewithdepth(root.left, k+1);
        replaceNodewithdepth(root.right, k+1);
        return root;
    }

    public static void printAtDepthK(BinaryTreeNode<Integer> root, int k) {
        if(root == null){
            return;
        }
        if(k == 0){
            System.out.println(root.data);
            return;
        }
        printAtDepthK(root.left, k-1);
        printAtDepthK(root.right, k-1);
    }

    public static int numLeafNodes(BinaryTreeNode<Integer> root) {
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        return numLeafNodes(root.left) + numLeafNodes(root.right);
    }
 
    public static int hightOfTree(BinaryTreeNode<Integer> root){
        if(root == null){
            return 0;
        }
        int left = hightOfTree(root.left);
        int right = hightOfTree(root.right);
        if(left > right){
            return 1 + left;
        }
        else{
            return 1 + right;
        }
    }

    public static int greaterThanX(BinaryTreeNode<Integer> root, int X) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if(root.data > X){
            count++;
        }
        int left = greaterThanX(root.left, X);
        int right = greaterThanX(root.right, X);
       
        return count+left+right;
    }

    public static int numOfNodes(BinaryTreeNode<Integer> root){
        if(root == null) {
            return 0;
        }
        int left = numOfNodes(root.left);
        int right = numOfNodes(root.right);
        return 1 + left + right;
    }

    public static int sumAllNodes(BinaryTreeNode<Integer> root){
        if(root == null) {
            return 0;
        }
        int left = sumAllNodes(root.left);
        int right = sumAllNodes(root.right);
        return root.data + left + right;
    }

    public static BinaryTreeNode<Integer> takeTreeInput() {
        System.out.print("Enter root : ");
        Scanner s = new Scanner(System.in);
        int data = s.nextInt();
        if(data == -1) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(data);
        BinaryTreeNode<Integer> leftNode = takeTreeInput();
        BinaryTreeNode<Integer> rightNode = takeTreeInput();
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    public static BinaryTreeNode<Integer> takeTreeInputDetailed(boolean isRoot, int parentData, boolean isLeft) {
        if(isRoot){
            System.out.print("Enter root : ");
        }
        else if(isLeft){
            System.out.print("Enter left child of " + parentData + " : ");
        }
        else{
            System.out.print("Enter right child of " + parentData + " : ");
        }
        Scanner s = new Scanner(System.in);
        int data = s.nextInt();
        if(data == -1) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(data);
        BinaryTreeNode<Integer> leftNode = takeTreeInputDetailed(false, data, true);
        BinaryTreeNode<Integer> rightNode = takeTreeInputDetailed(false, data, false);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    public static void printTree(BinaryTreeNode<Integer> root) {  
        if(root == null) {
            return;
        }
        System.out.print(root.data+" : ");
        if(root.left != null) {
            System.out.print("L "+root.left.data+", ");
        }
        if(root.right != null) {
            System.out.print("R "+root.right.data);
        }
        System.out.println();
        printTree(root.left);
        printTree(root.right);
    }

    public static void postOrder(BinaryTreeNode<Integer> root) {  
        if(root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" : ");
        if(root.left != null) {
            System.out.print("L "+root.left.data+", ");
        }
        if(root.right != null) {
            System.out.print("R "+root.right.data);
        }
        System.out.println();
    }

    public static void inOrder(BinaryTreeNode<Integer> root) {  
        if(root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" : ");
        if(root.left != null) {
            System.out.print("L "+root.left.data+", ");
        }
        if(root.right != null) {
            System.out.print("R "+root.right.data);
        }
        System.out.println();
        inOrder(root.right);
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = takeTreeInputDetailed(true, 0, false);
       
        System.out.println("Print as PreOrder");
        printTree(root);
       
        System.out.println("Print as PostOrder");
        postOrder(root);
       
        System.out.println("Print as InOrder");
        inOrder(root);
       
        int numOfNodes = numOfNodes(root);
        System.out.println("Numbers of Nodes : "+numOfNodes);
       
        int sumAllNodes = sumAllNodes(root);
        System.out.println("Sum of All Nodes : "+sumAllNodes);
       
        int greaterThanX = greaterThanX(root, 3);
        System.out.println("Greater than 3 : "+greaterThanX+" nodes");
       
        int hightOfTree = hightOfTree(root);
        System.out.println("Hight of Tree : "+ hightOfTree); 

        int numLeafNodes = numLeafNodes(root);
        System.out.println("Number of leaf Nodes : " + numLeafNodes);

        // root = replaceNodewithdepth(root, 0);
        // inOrder(root);

        printAtDepthK(root, 2);
        boolean isNodePresent = isNodePresent(root, 8);
        System.out.println("isNodePresent : " + isNodePresent);
    }
}
