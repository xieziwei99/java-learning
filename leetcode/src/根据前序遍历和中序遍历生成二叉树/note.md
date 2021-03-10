题目

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。



思路

中序遍历序列至关重要，如果我们确定 1 为根，则 {4,7,2} 为左子树， {5,3,8,6} 为右子树

于是可以递归调用生成二叉树



代码

```java
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
```

