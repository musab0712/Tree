import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BTUses {

    public static boolean isBST3(BinaryTreeNode<Integer> root, int min, int max) {
        if(root == null) {
            return true;
        }
        if(root.data < min || root.data > max) {
            return false;
        }
        boolean isleftBST = isBST3(root.left, min, root.data - 1);
        boolean isRightBST = isBST3(root.right, root.data , max);
        return isRightBST && isleftBST;
    }

    public static isBSTreturn isBST2(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return new isBSTreturn(Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        isBSTreturn leftAns = isBST2(root.left);
        isBSTreturn rightAns = isBST2(root.right);
        int min = Math.min(root.data, Math.min(leftAns.min, rightAns.min));
        int max = Math.max(root.data, Math.max(leftAns.max, rightAns.max));
        boolean isBST = true;
        if(leftAns.max >= root.data) isBST = false;
        if(rightAns.min < root.data) isBST = false;
        if(!leftAns.isBST) isBST = false;
        if(!rightAns.isBST) isBST = false;
        return new isBSTreturn(min, max, isBST);
    }

    public static int maximum(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return -1;
        }
        int leftMax = maximum(root.left);
        int rightMax = maximum(root.right);
        return Math.max(root.data, Math.max(leftMax, rightMax));
    }

    public static int minimum(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return Integer.MAX_VALUE;
        }
        int leftMin = minimum(root.left);
        int rightMin = minimum(root.right);
        return Math.min(root.data, Math.max(leftMin, rightMin));
    }

    public static boolean isBST(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return true;
        }
        int leftMax = maximum(root.left);
        if(leftMax >= root.data) {
            return false;
        }
        int rightMin = minimum(root.right);
        if(rightMin < root.data) {
            return false;
        }
        boolean isLeftBST = isBST(root.left);
        boolean isRightBST = isBST(root.right);
        return isLeftBST && isRightBST;
    }

    public static BinaryTreeNode<Integer> construcBSTfromSortArray(int[] arr, int si, int ei) {
        if(si > ei) {
            return null;
        }
        int midI = (si + ei + 1)/2;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(arr[midI]);
        root.left = construcBSTfromSortArray(arr, si, midI - 1);
        root.right = construcBSTfromSortArray(arr, midI + 1, ei);
        return root;
    }

    public static void printDataK1toK2(BinaryTreeNode<Integer> root, int k1, int k2) {
        if(root == null) {
            return;
        }
        if (k1 <= root.data && k2 >= root.data) {
            System.out.print(root.data + " ");
        }
        if(k1 >= root.data) {
            printDataK1toK2(root.right, k1, k2);
        }
        else if(k2 < root.data) {
            printDataK1toK2(root.left, k1, k2);
        }
        else {
            printDataK1toK2(root.left, k1, k2);
            printDataK1toK2(root.right, k1, k2);
        }
    }

    public static boolean isDataPresentinBST(BinaryTreeNode<Integer> root, int data) {
        if(root == null) {
            return false;
        }
        if(root.data == data) {
            return true;
        }
        if(data >= root.data) {
            return isDataPresentinBST(root.right, data);
        }
        return isDataPresentinBST(root.left, data);
    }

    public static void printLevelWise(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return;
        }
        Queue<BinaryTreeNode<Integer>> temp = new LinkedList<>();
        temp.add(root);
        while(!temp.isEmpty()) {
            BinaryTreeNode<Integer> front = temp.poll();
            System.out.print(front.data + " : ");
            if(front.left != null) {
                System.out.print("L " + front.left.data);
                temp.add(front.left);
            } 
            if(front.right != null) {
                System.out.print(", R " + front.right.data);
                temp.add(front.right);
            }
            System.out.println();
        }
    } 

    public static int daimeterOfTree(BinaryTreeNode<Integer> root){
        if(root == null) {
            return 0;
        }
        int left = daimeterOfTree(root.left);
        int right = daimeterOfTree(root.right);
        int cur = 1 + hightOfTree(root.left) + hightOfTree(root.right);
        return Math.max(Math.max(left, right), cur);
    }

    public static BinaryTreeNode<Integer> levelWiseInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter root data : ");
        int data = sc.nextInt();
        if(data == -1) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(data);
        Queue<BinaryTreeNode<Integer>> pChild = new LinkedList<BinaryTreeNode<Integer>>();
        pChild.add(root);
        while(!pChild.isEmpty()) {
            BinaryTreeNode<Integer> front = pChild.poll();
            System.out.print("Enter left child of " + front.data + " : ");
            int leftdata = sc.nextInt();
            if(leftdata != -1) {
                BinaryTreeNode<Integer> lChild = new BinaryTreeNode<Integer>(leftdata);
                front.left = lChild;
                pChild.add(lChild); 
            }
            System.out.print("Enter right child of " + front.data + " : ");
            int rightdata = sc.nextInt();
            if(rightdata != -1) {
                BinaryTreeNode<Integer> rChild = new BinaryTreeNode<Integer>(rightdata);
                front.right = rChild;
                pChild.add(rChild); 
            }
        }
        return root;
    }

    public static BalancedTree isBalancedTreeBetter(BinaryTreeNode<Integer> root) {
        if(root == null) {
            int height = 0;
            boolean isBal = true;
            BalancedTree ans = new BalancedTree();
            ans.height = height;
            ans.isBalenced = isBal;
            return ans;
        }
        BalancedTree left = isBalancedTreeBetter(root.left);
        BalancedTree right = isBalancedTreeBetter(root.right);
        int height = 1 + Math.max(left.height, right.height);
        boolean isBal = true;
        if(Math.abs(left.height - right.height) > 1) {
            isBal = false;
        }
        if(!left.isBalenced || ! right.isBalenced) {
            isBal = false;
        }
        BalancedTree ans  = new BalancedTree();
        ans.height = height;
        ans.isBalenced = isBal;
        return ans;
    }

    public static boolean isBalencedTree(BinaryTreeNode<Integer> root) {
        if(root == null) {
            return true;
        }
        int leftHeight = hightOfTree(root.left);
        int rightHight = hightOfTree(root.right);
        if(Math.abs((leftHeight - rightHight))>1) {
            return false;
        }
        boolean left = isBalencedTree(root.left);
        boolean right = isBalencedTree(root.right);
        return left && right;
    }

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
            System.out.print("L "+root.left.data);
        }
        if(root.right != null) {
            System.out.print(", R "+root.right.data);
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
            System.out.print("L "+root.left.data);
        }
        if(root.right != null) {
            System.out.print(", R "+root.right.data);
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
            System.out.print("L "+root.left.data);
        }
        if(root.right != null) {
            System.out.print(", R "+root.right.data);
        }
        System.out.println();
        inOrder(root.right);
    }

    public static void main(String[] args) {
        //BinaryTreeNode<Integer> root = takeTreeInputDetailed(true, 0, false);
        
        // System.out.println("Level wise Input : ");
        // BinaryTreeNode<Integer> root = levelWiseInput();

        int[] arr = {1,2,3,4,5};
        BinaryTreeNode<Integer> root = construcBSTfromSortArray(arr, 0, arr.length-1);
       
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

        BalancedTree isBalencedTree = isBalancedTreeBetter(root);
        System.out.println("isBalencedTree : " + isBalencedTree.isBalenced); 

        int daimeterOfTree = daimeterOfTree(root);
        System.out.println("DaimeterOfTree : " + daimeterOfTree);

        System.out.println("Print Level Wise");
        printLevelWise(root);

        System.out.print("Data between K1 to K2 in BST : ");
        printDataK1toK2(root, 1, 4);
        System.out.println();

        System.out.println("Is 5 present in BST : " + isDataPresentinBST(root, 5));

        boolean isBST = isBST(root);
        System.out.println("isBST : " + isBST);

        isBSTreturn isBST2 = isBST2(root);
        System.out.println("isBST2 : " + isBST2.isBST);

        boolean isBST3 = isBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("isBST3 : " + isBST3);
    }
}
