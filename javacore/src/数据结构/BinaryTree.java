package 数据结构;

import java.util.Scanner;

/**
 * @author xzw
 * 2021-02-25
 */
public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree() {
    }

    public BinaryTree(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public BinaryTree<T> setValue(T value) {
        this.value = value;
        return this;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public BinaryTree<T> setLeft(BinaryTree<T> left) {
        this.left = left;
        return this;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public BinaryTree<T> setRight(BinaryTree<T> right) {
        this.right = right;
        return this;
    }

    /**
     * 按前序遍历输入序列（数字）生成一棵树
     * 示例输入序列：12　23　$　$　36　78　$　$　89　$　$
     */
    public static BinaryTree<Integer> createBinaryTree(Scanner in) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        String input = in.next();
        if ("$".equals(input)) {
            tree = null;
        } else {
            tree.setValue(Integer.parseInt(input));
            tree.setLeft(createBinaryTree(in));
            tree.setRight(createBinaryTree(in));
        }
        return tree;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    /**
     * RDL(Right, Data, Left)递归打印树
     *
     * @param tree 树
     */
    public static <T> void printTree(BinaryTree<T> tree, int level) {
        if (tree != null) {
            printTree(tree.getRight(), level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println(tree.getValue());
            printTree(tree.getLeft(), level + 1);
        }
    }

    /**
     * RDL(Right, Data, Left)递归打印树
     *
     * @param tree 树
     */
    public static <T> void printTree(BinaryTree<T> tree) {
        printTree(tree, 0);
    }

    /**
     * 前序遍历-递归
     */
    public void preorderTraverse(ExecutorWithNoResult executor) {
        if (this.getValue() != null) {
            executor.execute(this.value);
            if (this.getLeft() != null) {
                this.getLeft().preorderTraverse(executor);
            }
            if (this.getRight() != null) {
                this.getRight().preorderTraverse(executor);
            }
        }
    }

    /**
     * 带有返回值的前序遍历-递归
     */
    @SuppressWarnings("unchecked")
    public void preorderTraverse(ExecutorWithResult executor) {
        if (this.getValue() != null) {
            this.setValue((T) executor.execute(this.value));
            if (this.getLeft() != null) {
                this.getLeft().preorderTraverse(executor);
            }
            if (this.getRight() != null) {
                this.getRight().preorderTraverse(executor);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BinaryTree<Integer> tree = BinaryTree.createBinaryTree(in);
        in.close();
        BinaryTree.printTree(tree);

        // 常规的树
        BinaryTree<Integer> tree1 = new BinaryTree<>(12);
        tree1.setLeft(new BinaryTree<>(23));
        tree1.setRight(new BinaryTree<>(36).setLeft(new BinaryTree<Integer>().setValue(78)).setRight(new BinaryTree<>(89)));

        BinaryTree.printTree(tree1);
        tree1.preorderTraverse((ExecutorWithNoResult) System.out::println);
        tree1.preorderTraverse(o -> (int) o + 1);
        BinaryTree.printTree(tree1);

        // 空树
        BinaryTree<Integer> tree2 = new BinaryTree<>();
        BinaryTree.printTree(tree2);
        tree2.preorderTraverse((ExecutorWithNoResult) System.out::println);
    }
}

@FunctionalInterface
interface ExecutorWithNoResult {
    void execute(Object o);
}

@FunctionalInterface
interface ExecutorWithResult {
    Object execute(Object o);
}