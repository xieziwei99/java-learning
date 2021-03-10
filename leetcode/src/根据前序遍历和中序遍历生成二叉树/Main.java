package 根据前序遍历和中序遍历生成二叉树;

import util.Util;

/**
 * @author xzw
 * 2021-03-10
 */
public class Main {

    public static void main(String[] args) {
        // [1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
        int[] pre = {1, 2, 3, 4, 5, 6, 7};
        int[] in = {3, 2, 4, 1, 6, 5, 7};
        TreeNode.printTree(new Main().reConstructBinaryTree(pre, in));
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length != 0) {
            TreeNode root = new TreeNode(pre[0]);
            int index = Util.getIndex(in, pre[0]);
            int[] left = Util.getSlice(in, 0, index);
            int[] right = Util.getSlice(in, index + 1, in.length);
            int[] leftPre = Util.getSlice(pre, 1, 1 + left.length);
            int[] rightPre = Util.getSlice(pre, 1 + left.length, pre.length);
            root.left = reConstructBinaryTree(leftPre, left);
            root.right = reConstructBinaryTree(rightPre, right);
            return root;
        }
        return null;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static void printTree(TreeNode tree, int level) {
        if (tree != null) {
            printTree(tree.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println(tree.val);
            printTree(tree.left, level + 1);
        }
    }

    /**
     * RDL(Right, Data, Left)递归打印树
     *
     * @param tree 树
     */
    public static void printTree(TreeNode tree) {
        printTree(tree, 0);
    }

}
